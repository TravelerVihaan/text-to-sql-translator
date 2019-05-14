package com.github.travelervihaan.sqltranslator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.travelervihaan.sqltranslator.model.Dictionary;
import com.github.travelervihaan.sqltranslator.repository.DictionaryRepository;
import com.mongodb.MongoSocketException;

@Service
public class DictionaryService {
	
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	public DictionaryService(DictionaryRepository dictionaryRepository) {
		this.dictionaryRepository = dictionaryRepository;
	}

	public Dictionary getByName(String dictionaryName) {
		return dictionaryRepository.findByName(dictionaryName);
	}
	
	public List<Dictionary> getAllDictionaries() {
		List<Dictionary> dictionaries = null;
		try {
			dictionaries = dictionaryRepository.findAll();
			return dictionaries;
		}catch(MongoSocketException e) {
			System.err.println("[ERROR] Problem with database connection!\n");
			return dictionaries;
		}
	}

	public void addNewDictionary(String dictionaryName, String words) {
		try {
			if (isFormCorrectlyFilled(dictionaryName) && isFormCorrectlyFilled(words)) {
				List<String> wordsList = new ArrayList<>(Arrays.asList(words.split(" ")));
				Dictionary dictionary = new Dictionary(dictionaryName, wordsList);
				dictionaryRepository.save(dictionary);
			}
		}catch(MongoSocketException e){
			System.err.println("[ERROR] Problem with database connection!\n");
		}catch(IllegalArgumentException e){
			System.err.println("[ERROR] Form was filled incorrect!\n");
		}
	}

	private boolean isFormCorrectlyFilled(String dictName) throws IllegalArgumentException{
		if(dictName.equals("")||dictName.length()<1) {
			throw new IllegalArgumentException();
		}else
			return true;
	}

	public void addWordToDictionary(String dictionaryName, String word){
		try {
			if (!word.equals("") && word.length() > 0) {
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
