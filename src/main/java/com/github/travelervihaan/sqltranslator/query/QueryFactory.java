package com.github.travelervihaan.sqltranslator.query;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import com.mongodb.MongoSocketException;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier(value="deleteQuery")
	Query deleteQuery;
	@Autowired
	@Qualifier(value="selectQuery")
	Query selectQuery;
	@Autowired
	@Qualifier(value="updateQuery")
	Query updateQuery;
	@Autowired
	@Qualifier(value="createQuery")
	Query createQuery;

	@Autowired
	public QueryFactory(DictionaryService dictionaryService){
		this.dictionaryService = dictionaryService;
	}
	
	public Query createSpecifiedQuery(String firstWord, List<String> splittedStatement) {

		if(compareFirstWord(SELECT, firstWord)) {
			selectQuery.initQuery(splittedStatement, "SELECT ");
			return selectQuery;
		}
		if(compareFirstWord(DELETE, firstWord)) {
			deleteQuery.initQuery(splittedStatement, "DELETE ");
			return deleteQuery;
		}
		if(compareFirstWord(UPDATE, firstWord)) {
			updateQuery.initQuery(splittedStatement, "UPDATE ");
			return updateQuery;
		}
		if(compareFirstWord(CREATE, firstWord)) {
			createQuery.initQuery(splittedStatement, "CREATE ");
			return createQuery;
		}

		return createQuery;
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
