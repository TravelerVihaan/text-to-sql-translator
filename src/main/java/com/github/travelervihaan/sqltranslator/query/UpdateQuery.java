package com.github.travelervihaan.sqltranslator.query;

import java.util.List;

public class UpdateQuery extends AbstractQuery {
	
	UpdateQuery(List<String> statement) {
		super(statement, "UPDATE ");
	}
	
	@Override
	public void prepareQuery() {
		if(isWordInDictionary("table"))
			popFirstElementFromList();
		appendToStringBuilder(getStatement().get(0)+" SET ");
		popFirstElementFromList();
		prepareElementsToSet();
		prepareConditionForQuery();
	}

	private void prepareElementsToSet(){
		do{
			appendToStringBuilder(getStatement().get(0) + " = ");
			//usuniecie nazwy
			popFirstElementFromList();
			//usuniecie "wynosi"
			popFirstElementFromList();
			if(getStatement().get(0).substring(getStatement().get(0).length()-1).equals(","))
				appendNumericOrStringToStatement();
			else{
				appendNumericOrStringToStatement();
				break;
			}
		}while(getStatement().size()>0);
	}

}
