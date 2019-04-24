package com.github.travelervihaan.sqltranslator.service;

import com.github.travelervihaan.sqltranslator.query.Query;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService {
	
	private String naturalLanguageStatement;
	private String firstWord;

	private Query query;

	public TranslatorService(){ }
	
	public void setNaturalLanguageStatement(String statement) {
		this.naturalLanguageStatement = statement;
		this.firstWord = digFirstWord();
	}
	
	private String digFirstWord() {
		String[] splittedStatement = naturalLanguageStatement.split(" ");
		return splittedStatement[0];
	}

	public String getFirstWord(){
		return firstWord;
	}
}
