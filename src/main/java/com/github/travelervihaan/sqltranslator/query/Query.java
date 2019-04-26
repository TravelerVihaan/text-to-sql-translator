package com.github.travelervihaan.sqltranslator.query;

public interface Query {
	
	public void prepareQuery();
	
	public String getPreparedQuery();
	
	public void appendToString();
	
}
