package com.github.travelervihaan.sqltranslator.query;

import java.util.List;

public interface Query {
	
	void prepareQuery();
	
	String getPreparedQuery();

	void initQuery(List<String> statement, String firstWord);
	
}
