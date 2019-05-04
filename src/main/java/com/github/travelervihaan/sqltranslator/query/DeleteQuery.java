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

	@Override
	void prepareConditionForQuery() {
		if(isWordInDictionary("where")){
			popFirstElementFromList();
			appendToStringBuilder("WHERE ");
			appendToStringBuilder(getStatement().get(0));
			appendToStringBuilder(" = ");
			popFirstElementFromList();
			if(isNumeric(getStatement().get(0)))
				appendToStringBuilder("'"+getStatement().get(0)+"'");
			else
				appendToStringBuilder(getStatement().get(0));
			popFirstElementFromList();
		}
	}

	private void prepareDropTableQuery(){
		popFirstElementFromList();
		setStringBuilder(new StringBuilder("DROP TABLE "));
		appendToStringBuilder(getStatement().get(0));
	}





}
