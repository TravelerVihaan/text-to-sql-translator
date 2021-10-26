package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import com.mongodb.MongoSocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Query createSpecifiedQuery(String firstWord, List<String> splitStatementFragments) {

		if(compareFirstWord(SELECT, firstWord)) {
			selectQuery.initQuery(splitStatementFragments, "SELECT ");
			return selectQuery;
		}
		if(compareFirstWord(DELETE, firstWord)) {
			deleteQuery.initQuery(splitStatementFragments, "DELETE ");
			return deleteQuery;
		}
		if(compareFirstWord(UPDATE, firstWord)) {
			updateQuery.initQuery(splitStatementFragments, "UPDATE ");
			return updateQuery;
		}
		if(compareFirstWord(CREATE, firstWord)) {
			createQuery.initQuery(splitStatementFragments, "CREATE ");
			return createQuery;
		}

		return createQuery;
	}
	
	private boolean compareFirstWord(String queryType, String firstWord) {
		try {
			return dictionaryService.compareWord(dictionaryService.getByName(queryType), firstWord);
		}catch(MongoSocketException e) {
			LOGGER.error("Problem with database connection!");
			return false;
		}
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(QueryFactory.class);
}
