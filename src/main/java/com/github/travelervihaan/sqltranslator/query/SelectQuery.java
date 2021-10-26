package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
				LOGGER.error("Wykroczono poza liste!!");
			}
			return;
		}
		prepareElementsToSelect();
		appendToStringBuilder("FROM ");
		popFirstElementFromList();
		popFirstElementFromList();
		appendToStringBuilder(getStatement().get(0));
		//usun nazwa tabeli
		popFirstElementFromList();
		prepareConditionForQuery();
		try{
			prepareSortingForQuery();
		}catch(IndexOutOfBoundsException e){
			LOGGER.error("Wykroczono poza liste!!");
		}
	}

	private void prepareSortingForQuery() throws IndexOutOfBoundsException {
		//if(getStatement.get(0).equalsIgnoreCase("posortowane")
		if(isWordInDictionary("sort")){
			appendToStringBuilder("ORDER BY ");
			//usuwanie sort
			popFirstElementFromList();
			if(isAscendingOrDescending(getStatement().get(0))) {
				appendToStringBuilder(getStatement().get(2));
				appendToStringBuilder(checkAscendingOrDescending(getStatement().get(0)));
				return;
			}
			//nazwa kolumny pomijajac 'wedlug'
			appendToStringBuilder(getStatement().get(1));
		}
	}

	private boolean isAscendingOrDescending(String word){
		return (INCREASING_LITERAL_NOPL.equalsIgnoreCase(word) || INCREASING_LITERAL.equalsIgnoreCase(word)||
				DECREASING_LITERAL.equalsIgnoreCase(word) || DECREASING_LITERAL_NOPL.equalsIgnoreCase(word));
	}

	private String checkAscendingOrDescending(String word){
		if(INCREASING_LITERAL.equalsIgnoreCase(word) || INCREASING_LITERAL_NOPL.equalsIgnoreCase(word))
			return " ASC";
		else if (DECREASING_LITERAL.equalsIgnoreCase(word) || DECREASING_LITERAL_NOPL.equalsIgnoreCase(word))
			return " DESC";
		else
			return "";
	}

	private void prepareElementsToSelect(){
		do{
			appendToStringBuilder(getStatement().get(0).substring(0,getStatement().get(0).length()-1));
			if(COMMA_CHAR.equals(getStatement().get(0).substring(getStatement().get(0).length()-1)))
				appendToStringBuilder(", ");
			else
				appendToStringBuilder(" ");
			popFirstElementFromList();
		}while(COMMA_CHAR.equals(getStatement().get(0).substring(getStatement().get(0).length()-1)));
		appendToStringBuilder(getStatement().get(0));
		appendToStringBuilder(" ");
		popFirstElementFromList();
	}

	private static final String INCREASING_LITERAL = "rosnąco";
	private static final String INCREASING_LITERAL_NOPL = "rosnaco";
	private static final String DECREASING_LITERAL = "malejąco";
	private static final String DECREASING_LITERAL_NOPL = "malejaco";
	private static final String COMMA_CHAR = ",";

	private static final Logger LOGGER = LoggerFactory.getLogger(SelectQuery.class);
}
