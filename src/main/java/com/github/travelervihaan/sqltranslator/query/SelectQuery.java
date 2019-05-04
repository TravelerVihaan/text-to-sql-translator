package com.github.travelervihaan.sqltranslator.query;

public class SelectQuery extends AbstractQuery {
	
	SelectQuery(String[] statement) {
		super(statement, "SELECT ");
	}
	
	@Override
	public void prepareQuery() {
		if(checkAllDictionary()) {
			prepareConditionForQuery();
			return;
		}
		prepareElementsToSelect();
		appendToStringBuilder("FROM ");
		popFirstElementFromList();
		popFirstElementFromList();
		appendToStringBuilder(getStatement().get(0)+" ");
		prepareConditionForQuery();
	}

	private void prepareElementsToSelect(){
		while(getStatement().get(0).substring(getStatement().get(0).length()-1).equals(",")){
			appendToStringBuilder(getStatement().get(0).substring(0,getStatement().get(0).length()-2));
			popFirstElementFromList();
			if(getStatement().get(0).substring(getStatement().get(0).length()-1).equals(","))
				appendToStringBuilder(", ");
			else
				appendToStringBuilder(" ");
		}
	}


}
