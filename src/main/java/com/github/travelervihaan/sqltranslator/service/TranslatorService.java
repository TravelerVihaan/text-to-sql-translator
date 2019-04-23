package com.github.travelervihaan.sqltranslator.service;

import org.springframework.stereotype.Service;

@Service
public class TranslatorService {
	
	private String naturalLanguageStatement;
	private String[] statementWords;
	
	public void setNaturalLanguageStatement(String statement) {
		this.naturalLanguageStatement = statement;
	}
	
	private void splitStatement() {
		statementWords = naturalLanguageStatement.split(" ");
	}
	

}
