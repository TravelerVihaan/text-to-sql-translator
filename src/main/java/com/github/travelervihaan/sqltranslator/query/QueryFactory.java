package com.github.travelervihaan.sqltranslator.query;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import com.mongodb.MongoSocketException;

public class QueryFactory {

	private final String SELECT = "select";
	private final String DELETE = "delete";
	private final String UPDATE = "update";
	private final String CREATE = "create";
	private DictionaryService dictionaryService;
	
	public QueryFactory() {
		setDictionaryService(dictionaryService);
	}
	
	@Autowired
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	
	public Query createSpecifiedQuery(String firstWord, String[] splittedStatement) {
		if(compareFirstWord(SELECT, firstWord))
			return new SelectQuery(splittedStatement);
		
		if(compareFirstWord(DELETE, firstWord))
			return new DeleteQuery(splittedStatement);
		
		if(compareFirstWord(UPDATE, firstWord))
			return new UpdateQuery(splittedStatement);

		if(compareFirstWord(CREATE, firstWord))
			return new CreateQuery(splittedStatement);
		return null;
	}
	
	private boolean compareFirstWord(String queryType, String firstWord) {
		try {
			return dictionaryService.compareWord(dictionaryService.getByName(queryType), firstWord);
		}catch(MongoSocketException e) {
			System.err.println("[ERROR] Problem with database connection!\n");
			return false;
		}
	}
}
