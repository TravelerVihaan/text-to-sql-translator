package com.github.travelervihaan.sqltranslator.query;

import java.util.List;

public class SelectQuery extends AbstractQuery {
	
	SelectQuery(List<String> statement) {
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
		try {
			prepareSortingForQuery();
		}catch(IndexOutOfBoundsException e){
			System.err.println("Wykroczono poza listę!");
		}
	}

	private void prepareSortingForQuery() throws IndexOutOfBoundsException{
		//if(getStatement.get(0).equalsIgnoreCase("posortowane")
		if(isWordInDictionary("sort")){
			appendToStringBuilder("ORDER BY ");
			popFirstElementFromList();
			if(isAscendingOrDescending(getStatement().get(0))) {
				appendToStringBuilder(getStatement().get(2));
				appendToStringBuilder(checkAscendingOrDescending(getStatement().get(0)));
				return;
			}
			appendToStringBuilder(getStatement().get(1));
		}
	}

	private boolean isAscendingOrDescending(String word){
		return (word.equalsIgnoreCase("rosnąco")||word.equalsIgnoreCase("rosnaco")||word.equalsIgnoreCase("malejąco")||word.equalsIgnoreCase("malejaco"));
	}

	private String checkAscendingOrDescending(String word){
		if(word.equalsIgnoreCase("rosnąco")||word.equalsIgnoreCase("rosnaco"))
			return " ASC";
		else if(word.equalsIgnoreCase("malejąco")||word.equalsIgnoreCase("malejaco"))
			return " DESC";
		else
			return "";
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
