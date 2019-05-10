package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value="selectQuery")
public class SelectQuery extends AbstractQuery {

	@Autowired
	SelectQuery(DictionaryService dictionaryService){
		super(dictionaryService);
	}

	@Override
	public void prepareQuery() {
		if(checkAllDictionary()) {
			prepareConditionForQuery();
			try {
				prepareSortingForQuery();
			}catch(IndexOutOfBoundsException e){
				System.err.println("[ERROR] Wykroczono poza liste!!");
			}
			return;
		}
		prepareElementsToSelect();
		appendToStringBuilder("FROM ");
		popFirstElementFromList();
		popFirstElementFromList();
		appendToStringBuilder(getStatement().get(0)+" ");
		prepareConditionForQuery();
		try{
			prepareSortingForQuery();
		}catch(IndexOutOfBoundsException e){
			System.err.println("[ERROR] Wykroczono poza liste!!");
		}
	}

	private void prepareSortingForQuery() throws IndexOutOfBoundsException{
		//if(getStatement.get(0).equalsIgnoreCase("posortowane")
		if(isWordInDictionary("sort")){
			appendToStringBuilder("ORDER BY ");
			//
			System.err.println(getPreparedQuery());
			popFirstElementFromList();
			if(isAscendingOrDescending(getStatement().get(0))) {
				appendToStringBuilder(getStatement().get(1));
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
			appendToStringBuilder(getStatement().get(0).substring(0,getStatement().get(0).length()-1));
			popFirstElementFromList();
			if(getStatement().get(0).substring(getStatement().get(0).length()-1).equals(","))
				appendToStringBuilder(", ");
			else
				appendToStringBuilder(" ");
		}
	}


}
