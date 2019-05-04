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
			getStatement().remove(0);
			appendToStringBuilder("WHERE ");
			appendToStringBuilder(getStatement().get(0));
			appendToStringBuilder(" = ");
			getStatement().remove(0);
			if(isNumeric(getStatement().get(0)))
				appendToStringBuilder("'"+getStatement().get(0)+"'");
			else
				appendToStringBuilder(getStatement().get(0));
			getStatement().remove(0);
		}
	}

	private void prepareDropTableQuery(){
		getStatement().remove(0);
		setStringBuilder(new StringBuilder("DROP TABLE "));
		appendToStringBuilder(getStatement().get(0));
	}





}
