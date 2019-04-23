package com.github.travelervihaan.sqltranslator.service;

import org.springframework.stereotype.Service;

@Service
public class TranslatorService {
	
	private String naturalLanguageStatement;
	private String[] statementWords;
	
	public void setNaturalLanguageStatement(String statement) {
		this.naturalLanguageStatement = statement;
		splitStatement();
	}
	
	private void splitStatement() {
		statementWords = naturalLanguageStatement.split(" ");
	}
	
	public String[] getStatementWords() {
		return statementWords;
	}
	

}
