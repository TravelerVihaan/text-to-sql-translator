package com.github.travelervihaan.sqltranslator.service;

import com.github.travelervihaan.sqltranslator.query.Query;
import com.github.travelervihaan.sqltranslator.query.QueryFactory;

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
	
	public void setNaturalLanguageStatement(String statement) {
		this.naturalLanguageStatement = statement;
		splitStatement();
		queryFactory = new QueryFactory();
		query = queryFactory.createSpecifiedQuery(getFirstWord(), getSplittedStatement());
		if(query==null){
			System.err.println("ERROR");
		}
		query.prepareQuery();
		this.naturalLanguageStatement = query.getPreparedQuery();
	}

	private void splitStatement(){
		this.splittedStatement = new ArrayList<>(Arrays.asList(naturalLanguageStatement.split(" ")));
	}

	public String getNaturalLanguageStatement(){return naturalLanguageStatement;}
	
	//public String getPreparedQuery() {
	//	return query.getPreparedQuery();
	//}
	
	public String getFirstWord(){
		return splittedStatement.get(0);
	}

	private List<String> getSplittedStatement(){
		return splittedStatement;
	}
	
}
