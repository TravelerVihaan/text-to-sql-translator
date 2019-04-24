package com.github.travelervihaan.sqltranslator.service;

import com.github.travelervihaan.sqltranslator.query.Query;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService {
	
	private String naturalLanguageStatement;
	private String[] splittedStatement;

	private Query query;

	public TranslatorService(){ }
	
	public void setNaturalLanguageStatement(String statement) {
		this.naturalLanguageStatement = statement;
		splitStatement();
	}

	private void splitStatement(){
		this.splittedStatement = naturalLanguageStatement.split(" ");
	}
	
	public String getFirstWord(){
		return splittedStatement[0];
	}

	public String[] getSplittedStatement(){
		return splittedStatement;
	}
}
