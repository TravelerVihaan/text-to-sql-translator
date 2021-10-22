package com.github.travelervihaan.sqltranslator.service;

import com.github.travelervihaan.sqltranslator.query.Query;
import com.github.travelervihaan.sqltranslator.query.QueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TranslatorService {
	
	private String naturalLanguageStatement;
	private final QueryFactory queryFactory;

	@Autowired
	public TranslatorService(QueryFactory queryFactory){
		this.queryFactory = queryFactory;
	}
	
	public void setNaturalLanguageStatement(Optional<String> statement) {
		this.naturalLanguageStatement = statement
				.map(this::splitStatement)
				.map(splitStatementFragments -> queryFactory.createSpecifiedQuery(splitStatementFragments.get(0), splitStatementFragments))
				.map(this::prepareAndGetQuery)
				.orElse("");
	}

	private String prepareAndGetQuery(Query query){
		query.prepareQuery();
		return query.getPreparedQuery();
	}

	private List<String> splitStatement(String statement){
		return Arrays.asList(naturalLanguageStatement.split(" "));
	}

	public String getNaturalLanguageStatement(){return naturalLanguageStatement;}
	
}
