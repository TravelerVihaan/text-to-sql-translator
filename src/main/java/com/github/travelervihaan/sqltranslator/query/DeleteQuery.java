package com.github.travelervihaan.sqltranslator.query;

public class DeleteQuery implements Query {
	
	private String[] statement;
	private String preparedQuery;
	private StringBuilder stringBuilder;
	
	public DeleteQuery(String[] statement) {
		this.statement = statement;
	}

	@Override
	public void prepareQuery() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getPreparedQuery() {
		return preparedQuery;
	}
	
	@Override
	public void appendToString() {
	}
}
