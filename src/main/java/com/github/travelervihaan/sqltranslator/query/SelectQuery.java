package com.github.travelervihaan.sqltranslator.query;

public class SelectQuery extends AbstractQuery {
	
	SelectQuery(String[] statement) {
		super(statement, "SELECT ");
	}
	
	@Override
	public void prepareQuery() {
		if(checkAllDictionary())
			return;
		//TODO
	}

}
