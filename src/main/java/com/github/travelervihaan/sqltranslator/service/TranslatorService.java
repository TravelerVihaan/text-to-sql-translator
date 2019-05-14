package com.github.travelervihaan.sqltranslator.service;

import com.github.travelervihaan.sqltranslator.query.Query;
import com.github.travelervihaan.sqltranslator.query.QueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TranslatorService {
	
	private String naturalLanguageStatement;
	private List<String> splittedStatement;
	private QueryFactory queryFactory;
	private Query query;

	@Autowired
	TranslatorService(QueryFactory queryFactory){
		this.queryFactory = queryFactory;
	}
	
	public void setNaturalLanguageStatement(String statement) {
		this.naturalLanguageStatement = statement;
		splitStatement();
		query = queryFactory.createSpecifiedQuery(getFirstWord(), getSplittedStatement());
		query.prepareQuery();
		this.naturalLanguageStatement = query.getPreparedQuery();
	}

	private void splitStatement(){
		this.splittedStatement = new ArrayList<>(Arrays.asList(naturalLanguageStatement.split(" ")));
	}

	public String getNaturalLanguageStatement(){return naturalLanguageStatement;}
	
	private String getFirstWord(){
		return splittedStatement.get(0);
	}

	private List<String> getSplittedStatement(){
		return splittedStatement;
	}
	
}
