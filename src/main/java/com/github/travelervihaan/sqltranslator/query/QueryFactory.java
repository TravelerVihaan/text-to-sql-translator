package com.github.travelervihaan.sqltranslator.query;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import com.mongodb.MongoSocketException;
import org.springframework.stereotype.Component;

import java.util.Dictionary;
import java.util.List;

@Component
public class QueryFactory {

	private final String SELECT = "select";
	private final String DELETE = "delete";
	private final String UPDATE = "update";
	private final String CREATE = "create";
	private DictionaryService dictionaryService;

	@Autowired
	public QueryFactory(DictionaryService dictionaryService){
		this.dictionaryService = dictionaryService;
	}
	
	public Query createSpecifiedQuery(String firstWord, List<String> splittedStatement) {
		if(dictionaryService==null){
			System.err.println("Dupa!\n");
		}

		if(compareFirstWord(SELECT, firstWord))
			return new SelectQuery(splittedStatement);
		
		if(compareFirstWord(DELETE, firstWord))
			return new DeleteQuery(splittedStatement);
		
		if(compareFirstWord(UPDATE, firstWord))
			return new UpdateQuery(splittedStatement);

		if(compareFirstWord(CREATE, firstWord))
			return new CreateQuery(splittedStatement);

		return new CreateQuery(splittedStatement);
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
