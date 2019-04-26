package com.github.travelervihaan.sqltranslator.service;

import com.github.travelervihaan.sqltranslator.query.Query;
import com.github.travelervihaan.sqltranslator.query.QueryFactory;

import org.springframework.stereotype.Service;

@Service
public class TranslatorService {
	
	private String naturalLanguageStatement;
	private String[] splittedStatement;
	private QueryFactory queryFactory;
	private Query query;
	
	public void setNaturalLanguageStatement(String statement) {
		this.naturalLanguageStatement = statement;
		splitStatement();
		query = queryFactory.createSpecifiedQuery(this.getFirstWord(), this.getSplittedStatement());
		query.prepareQuery();
	}

	private void splitStatement(){
		this.splittedStatement = naturalLanguageStatement.split(" ");
	}
	
	public String getPreparedQuery() {
		return query.getPreparedQuery();
	}
	
	public String getFirstWord(){
		return splittedStatement[0];
	}

	private String[] getSplittedStatement(){
		return splittedStatement;
	}
	
}
