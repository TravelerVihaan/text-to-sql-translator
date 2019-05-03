package com.github.travelervihaan.sqltranslator.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractQuery implements Query {
	
	protected List<String> statement;
	private String preparedQuery;
	private StringBuilder stringBuilder;
	
	AbstractQuery(String[] statement) {
		this.statement = new ArrayList<>(Arrays.asList(statement));
		stringBuilder = new StringBuilder();
	}

	@Override
	public abstract void prepareQuery();

	@Override
	public String getPreparedQuery() {
		return preparedQuery;
	}
	
	protected void convertToPreparedQuery() {
		preparedQuery = stringBuilder.toString();
	}

	protected void appendToStringBuilder(String textToAppend) {
		stringBuilder.append(textToAppend);
	}
	
	protected void setStatement(List<String> statement) {
		this.statement = statement;
	}
	
	protected List<String> getStatement() {
		return statement;
	}

}
