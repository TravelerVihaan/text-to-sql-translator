package com.github.travelervihaan.sqltranslator.query;

public class DeleteQuery extends AbstractQuery{
	
	DeleteQuery(String[] statement) {
		super(statement, "DELETE ");
	}

	@Override
	public void prepareQuery() {
		if(isWordInDictionary("table")) {
			prepareDropTableQuery();
			return;
		}
		if(checkAllDictionary()) {
			prepareConditionForQuery();
		}
	}

	private void prepareDropTableQuery(){
		popFirstElementFromList();
		setStringBuilder(new StringBuilder("DROP TABLE "));
		appendToStringBuilder(getStatement().get(0));
	}

}
