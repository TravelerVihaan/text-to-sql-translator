package com.github.travelervihaan.sqltranslator.query;

public class DeleteQuery extends AbstractQuery{
	
	public DeleteQuery(String[] statement) {
		super(statement, "DELETE ");
	}

	@Override
	public void prepareQuery() {
		checkAllDictionary();
		if(getDictionaryService().compareWord(getDictionaryService().getByName("table"),getStatement().get(0))) {
			prepareDropTableQuery();
			return;
		}


	}

	void prepareDropTableQuery(){
		getStatement().remove(0);
		setStringBuilder(new StringBuilder("DROP TABLE "));
		appendToStringBuilder(getStatement().get(0));
	}



}
