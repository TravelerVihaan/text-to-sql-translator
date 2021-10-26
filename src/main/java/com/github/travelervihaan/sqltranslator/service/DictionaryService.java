package com.github.travelervihaan.sqltranslator.service;

import com.github.travelervihaan.sqltranslator.model.Dictionary;
import com.github.travelervihaan.sqltranslator.repository.DictionaryRepository;
import com.mongodb.MongoSocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
			LOGGER.error("[ERROR] Problem with database connection!");
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
				LOGGER.error("[ERROR] Problem with database connection!");
			}
		} else {
			LOGGER.warn("[ERROR] Form was filled incorrect!");
		}
	}

	private boolean isFormCorrectlyFilled(String input) {
		return input != null && input.length() >= 1;
	}

	public void addWordToDictionary(String dictionaryName, String word){
		try {
			if (!word.isEmpty()) {
				if(!isWordAlreadyExist(dictionaryName, word)){
					Dictionary dictionary = dictionaryRepository.findByName(dictionaryName);
					dictionary.getDictionaryWords().add(word);
					dictionaryRepository.save(dictionary);
				}
			}
		}catch(IllegalArgumentException e) {
			LOGGER.warn("[ERROR] Niepoprawne słowo!!");
		}catch(MongoSocketException e) {
			LOGGER.error("[ERROR] Problem with database connection!");
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
			LOGGER.warn("Podane slowo nie istnieje w tym slowniku!!");
		}
	}

	private boolean isWordAlreadyExist(String dictionaryName, String newWord){
		List<String> wordsList = dictionaryRepository.findByName(dictionaryName).getDictionaryWords();
		return wordsList.stream().anyMatch(word -> word.equalsIgnoreCase(newWord));
	}
	
	public boolean compareWord(Dictionary dictionary, String word) {
		return dictionary.getDictionaryWords().stream().anyMatch(dictionaryWord -> dictionaryWord.equalsIgnoreCase(word));
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryService.class);
}
