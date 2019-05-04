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
		prepareSortingForQuery();
	}

	private void prepareSortingForQuery(){
		//if(getStatement.get(0).equalsIgnoreCase("posortowane")
		if(isWordInDictionary("sort")){
			appendToStringBuilder("ORDER BY ");
			popFirstElementFromList();
			if(isAscendingOrDescending(getStatement().get(0)))
				checkAscendingOrDescending();

		}
	}

	private boolean isAscendingOrDescending(String word){
		return (word.equalsIgnoreCase("rosnąco")||word.equalsIgnoreCase("rosnaco")||word.equalsIgnoreCase("malejąco")||word.equalsIgnoreCase("malejaco"));
	}

	private void checkAscendingOrDescending(){
		
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
