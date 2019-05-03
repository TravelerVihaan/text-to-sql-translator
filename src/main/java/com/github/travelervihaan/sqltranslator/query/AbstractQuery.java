package com.github.travelervihaan.sqltranslator.query;

public abstract class AbstractQuery implements Query {
	
	protected String[] statement;
	private String preparedQuery;
	private StringBuilder stringBuilder;
	
	AbstractQuery(String[] statement) {
		this.statement = statement;
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
	
	protected void setStatement(String[] statement) {
		this.statement = statement;
	}
	
	protected String[] getStatement() {
		return statement;
	}

}
