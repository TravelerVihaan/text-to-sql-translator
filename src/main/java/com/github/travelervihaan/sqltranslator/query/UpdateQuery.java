package com.github.travelervihaan.sqltranslator.query;

public class UpdateQuery extends AbstractQuery {
	
	public UpdateQuery(String[] statement) {
		super(statement, "UPDATE ");
	}
	
	@Override
	public void prepareQuery() {
		// TODO Auto-generated method stub
	}

}
