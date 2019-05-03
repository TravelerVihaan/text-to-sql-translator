package com.github.travelervihaan.sqltranslator.query;

public class SelectQuery extends AbstractQuery {
	
	public SelectQuery(String[] statement) {
		super(statement, "SELECT ");
	}
	
	@Override
	public void prepareQuery() {
		//TODO
	}

}
