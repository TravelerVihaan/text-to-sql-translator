package com.github.travelervihaan.sqltranslator.query;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import com.mongodb.MongoSocketException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryFactory {

	private static final String SELECT = "select";
	private static final String DELETE = "delete";
	private static final String UPDATE = "update";
	private static final String CREATE = "create";
	private final DictionaryService dictionaryService;

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
	
	public Query createSpecifiedQuery(String firstWord, List<String> splitStatementFragmets) {

		if(compareFirstWord(SELECT, firstWord)) {
			selectQuery.initQuery(splitStatementFragmets, "SELECT ");
			return selectQuery;
		}
		if(compareFirstWord(DELETE, firstWord)) {
			deleteQuery.initQuery(splitStatementFragmets, "DELETE ");
			return deleteQuery;
		}
		if(compareFirstWord(UPDATE, firstWord)) {
			updateQuery.initQuery(splitStatementFragmets, "UPDATE ");
			return updateQuery;
		}
		if(compareFirstWord(CREATE, firstWord)) {
			createQuery.initQuery(splitStatementFragmets, "CREATE ");
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
