package com.github.travelervihaan.sqltranslator.service;

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

	public void addWordToDictionary(String dictionaryName, String word){
		try {
			if (!word.equals("") && word.length() > 0) {
				if(isWordAlreadyExist(dictionaryName, word)){
					Dictionary dictionary = dictionaryRepository.findByName(dictionaryName);
					dictionary.getDictionaryWords().add(word);
					dictionaryRepository.save(dictionary);
				}
			}
		}catch(IllegalArgumentException e) {
			System.err.println("Niepoprawne słowo!!");
		}
	}

	private boolean isWordAlreadyExist(String dictionaryName, String word){
		List<String> wordsList = dictionaryRepository.findByName(dictionaryName).getDictionaryWords();
		for(String wordToCheck : wordsList) {
			if(wordToCheck.equalsIgnoreCase(word))
				return false;
		}
		return true;
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
