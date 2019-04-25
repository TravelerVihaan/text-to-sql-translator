package com.github.travelervihaan.sqltranslator.service;

import com.github.travelervihaan.sqltranslator.query.DeleteQuery;
import com.github.travelervihaan.sqltranslator.query.Query;
import com.github.travelervihaan.sqltranslator.query.SelectQuery;
import com.github.travelervihaan.sqltranslator.query.UpdateQuery;
import com.mongodb.MongoSocketException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService {
	
	private String naturalLanguageStatement;
	private String[] splittedStatement;
	private DictionaryService dictionaryService;
	
	private final String SELECT = "select";
	private final String DELETE = "delete";
	private final String UPDATE = "update";

	private Query query;

	@Autowired
	public TranslatorService(DictionaryService dictionaryService){
		this.dictionaryService = dictionaryService;
	}
	
	public void setNaturalLanguageStatement(String statement) {
		this.naturalLanguageStatement = statement;
		splitStatement();
		createSpecifiedQuery();
		query.prepareQuery();
	}

	private void splitStatement(){
		this.splittedStatement = naturalLanguageStatement.split(" ");
	}
	
	private void createSpecifiedQuery() {
		if(compareFirstWord(SELECT))
			query = new SelectQuery(this.getSplittedStatement());
		
		if(compareFirstWord(DELETE))
			query = new DeleteQuery(this.getSplittedStatement());
		
		if(compareFirstWord(UPDATE))
			query = new UpdateQuery(this.getSplittedStatement());
	}
	
	private boolean compareFirstWord(String queryType) {
		try {
			if(dictionaryService.compareWord(dictionaryService.getByName(queryType), this.getFirstWord()))
				return true;
			else
				return false;
		}catch(MongoSocketException e) {
			System.err.println("[ERROR] Problem with database connection!\n");
			return false;
		}
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
