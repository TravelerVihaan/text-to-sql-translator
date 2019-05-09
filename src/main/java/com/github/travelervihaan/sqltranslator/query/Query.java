package com.github.travelervihaan.sqltranslator.query;

import java.util.List;

public interface Query {
	
	public void prepareQuery();
	
	public String getPreparedQuery();

	public void setStatement(List<String> statementList);
	
}
