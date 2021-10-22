package com.github.travelervihaan.sqltranslator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.travelervihaan.sqltranslator.model.Dictionary;
import com.github.travelervihaan.sqltranslator.repository.DictionaryRepository;
import com.mongodb.MongoSocketException;

@Service
public class DictionaryService {
	
	private final DictionaryRepository dictionaryRepository;
	
	@Autowired
	public DictionaryService(DictionaryRepository dictionaryRepository) {
		this.dictionaryRepository = dictionaryRepository;
	}

	public Dictionary getByName(String dictionaryName) {
		return dictionaryRepository.findByName(dictionaryName);
	}
	
	public List<Dictionary> getAllDictionaries() {
		try {
			return dictionaryRepository.findAll();
		}catch(MongoSocketException e) {
			System.err.println("[ERROR] Problem with database connection!\n");
			return Collections.emptyList();
		}
	}

	public void addNewDictionary(String dictionaryName, String words) {
		if (isFormCorrectlyFilled(dictionaryName) && isFormCorrectlyFilled(words)) {
			List<String> wordsList = Arrays.asList(words.split(" "));
			Dictionary dictionary = new Dictionary(dictionaryName, wordsList);
			try {
				dictionaryRepository.save(dictionary);
			}catch(MongoSocketException e) {
				System.err.println("[ERROR] Problem with database connection!\n");
			}
		} else {
			System.err.println("[ERROR] Form was filled incorrect!\n");
		}
	}

	private boolean isFormCorrectlyFilled(String input) {
		return input != null && input.length() >= 1;
	}

	public void addWordToDictionary(String dictionaryName, String word){
		try {
			if (word.length() > 0) {
				if(!isWordAlreadyExist(dictionaryName, word)){
					Dictionary dictionary = dictionaryRepository.findByName(dictionaryName);
					dictionary.getDictionaryWords().add(word);
					dictionaryRepository.save(dictionary);
				}
			}
		}catch(IllegalArgumentException e) {
			System.err.println("[ERROR] Niepoprawne słowo!!");
		}catch(MongoSocketException e) {
			System.err.println("[ERROR] Problem with database connection!\n");
		}
	}
	
	public void deleteWordFromDictionary(String dictionaryName, String word) {
		try {
			if(isWordAlreadyExist(dictionaryName,word)){
				Dictionary dictionary = dictionaryRepository.findByName(dictionaryName);
				dictionary.getDictionaryWords().remove(word);
				///
				System.out.println(dictionary.getDictionaryWords());
				dictionaryRepository.save(dictionary);
			}
		}catch(MongoSocketException e) {
			System.err.println("[ERROR] Podane slowo nie istnieje w tym slowniku!!");
		}
	}

	private boolean isWordAlreadyExist(String dictionaryName, String word){
		List<String> wordsList = dictionaryRepository.findByName(dictionaryName).getDictionaryWords();
		for(String wordToCheck : wordsList) {
			if(wordToCheck.equalsIgnoreCase(word))
				return true;
		}
		return false;
	}
	
	public boolean compareWord(Dictionary dictionary, String word) {
		try {
			for (String dictionaryWord : dictionary.getDictionaryWords()) {
				if (dictionaryWord.equalsIgnoreCase(word))
					return true;
			}
			return false;
		}catch(MongoSocketException e){
			System.err.println("[ERROR] Problem with database connection!\n");
			return false;
		}
	}
}
