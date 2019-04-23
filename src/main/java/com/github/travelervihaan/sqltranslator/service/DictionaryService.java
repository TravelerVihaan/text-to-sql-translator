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
	
	public String compareWord(String word) {
		List<Dictionary> dictionaries = getAllDictionaries();
		for(Dictionary dict : dictionaries) {
			List<String> words = dict.getDictionaryWords();
			for(String dictionaryWord : words) {
				if(dictionaryWord.equals(word))
					return dict.getName();
			}
		}
		return "";
	}
}
