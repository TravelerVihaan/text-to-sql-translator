package com.github.travelervihaan.sqltranslator.query;

public class UpdateQuery extends AbstractQuery {
	
	UpdateQuery(String[] statement) {
		super(statement, "UPDATE ");
	}
	
	@Override
	public void prepareQuery() {
		// TODO Auto-generated method stub
	}

	@Override
	void prepareConditionForQuery() {

	}

}
