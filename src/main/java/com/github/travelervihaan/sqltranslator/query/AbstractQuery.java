package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import com.mongodb.MongoSocketException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractQuery implements Query {
	
	private List<String> statementList;
	private String preparedQuery;
	private StringBuilder stringBuilder;
	private DictionaryService dictionaryService;
	
	AbstractQuery(String[] statement, String startingWord) {
		this.statementList = new ArrayList<>(Arrays.asList(statement));
		stringBuilder = new StringBuilder(startingWord);
		statementList.remove(0);
	}

	@Override
	public abstract void prepareQuery();

	@Override
	public String getPreparedQuery() {
		return preparedQuery;
	}
	
	protected void convertToPreparedQuery() {
		preparedQuery = stringBuilder.toString();
	}

	void appendToStringBuilder(String textToAppend) {
		stringBuilder.append(textToAppend);
	}

	void setStringBuilder(StringBuilder stringBuilder){
		this.stringBuilder = stringBuilder;
	}

	@Autowired
	protected void setDictionaryService(DictionaryService dictService){
		this.dictionaryService = dictService;
	}

	DictionaryService getDictionaryService(){ return dictionaryService; }
	
	protected void setStatement(List<String> statementList) {
		this.statementList = statementList;
	}
	
	List<String> getStatement() {
		return statementList;
	}

	void checkAllDictionary(){
		if(getDictionaryService().compareWord(getDictionaryService().getByName("all"),getStatement().get(0)))
			appendToStringBuilder("* FROM");
	}

	protected boolean isWordInDictionary(String dictionaryName){
		try {
			if (getDictionaryService().compareWord(getDictionaryService().getByName(dictionaryName), getStatement().get(0)))
				return true;
			else
				return false;
		}catch(MongoSocketException e){
			System.err.println("[ERROR] Problem with database connection!\n");
			return false;
		}
	}

}
