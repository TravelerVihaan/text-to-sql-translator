package com.github.travelervihaan.sqltranslator.query;

public class SelectQuery implements Query {
	
	private String[] statement;
	private String preparedQuery;
	
	public SelectQuery(String[] statement) {
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
}
