package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import com.mongodb.MongoSocketException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractQuery implements Query {
	
	private List<String> statementList;
	private String preparedQuery;
	private StringBuilder stringBuilder;
	private final DictionaryService dictionaryService;

	@Autowired
	AbstractQuery(DictionaryService dictionaryService){
		this.dictionaryService = dictionaryService;
	}

	@Override
	public void initQuery(List<String> statement, String firstWord) {
		this.statementList = statement;
		stringBuilder = new StringBuilder(firstWord);
		try {
			statementList.remove(0);
		}catch(NullPointerException e){
			System.err.println("[ERROR] NullPointerException in AbstractQuery constructor");
		}
	}

	@Override
	public abstract void prepareQuery();

	@Override
	public String getPreparedQuery() {
		appendToStringBuilder(";");
		convertToPreparedQuery();
		return preparedQuery;
	}
	
	private void convertToPreparedQuery() {
		preparedQuery = stringBuilder.toString();
	}

	void appendToStringBuilder(String textToAppend) {
		stringBuilder.append(textToAppend);
	}

	void setStringBuilder(StringBuilder stringBuilder){
		this.stringBuilder = stringBuilder;
	}

	public void setStatement(List<String> statementList) {
		this.statementList = statementList;
	}
	
	List<String> getStatement() {
		return statementList;
	}

	boolean checkAllDictionary(){
		if(isWordInDictionary("all")) {
			appendToStringBuilder("FROM ");
			popFirstElementFromList();
			//z
			popFirstElementFromList();
			//tabeli
			popFirstElementFromList();
			appendToStringBuilder(getStatement().get(0));
			//usuwanie nazwy tabeli
			popFirstElementFromList();
			return true;
		}else
			return false;
	}

	void prepareConditionForQuery(){
		if(isWordInDictionary("where")){
			//usuwanie "gdzie"
			popFirstElementFromList();
			appendToStringBuilder(" WHERE ");
			do {
				checkIfStatementIsNegation();
				appendNumericOrStringToStatement();
				if(!getStatement().isEmpty()) {
					if ("i".equalsIgnoreCase(getStatement().get(0))) {
						appendToStringBuilder("AND ");
						popFirstElementFromList();
					}else if ("lub".equalsIgnoreCase(getStatement().get(0))) {
						appendToStringBuilder("OR ");
						popFirstElementFromList();
					}else{
						break;
					}
				}
			}while(getStatement().size()>0);
		}
		appendToStringBuilder(" ");
	}

	private void checkIfStatementIsNegation(){
		if("nie".equalsIgnoreCase(getStatement().get(1))){
			appendToStringBuilder("NOT " + getStatement().get(0));
			//usuwanie nazwy pola
			popFirstElementFromList();
			appendToStringBuilder(" = ");
			//usuwanie "nie wynosi"
			popFirstElementFromList();
			popFirstElementFromList();
		}else{
			//nazwa pola
			appendToStringBuilder(getStatement().get(0));
			popFirstElementFromList();
			appendToStringBuilder(" = ");
			//usuwanie "wynosi"
			popFirstElementFromList();
		}
	}

	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}

	void appendNumericOrStringToStatement(){
		if (isNumeric(getStatement().get(0)))
			appendToStringBuilder(getStatement().get(0)+" ");
		else
			appendToStringBuilder("'" + getStatement().get(0) + "' ");
		popFirstElementFromList();
	}

	boolean isWordInDictionary(String dictionaryName){
		try {
			return dictionaryService.compareWord(dictionaryService.getByName(dictionaryName), getStatement().get(0));
		}catch(MongoSocketException e){
			System.err.println("[ERROR] Problem with database connection!\n");
			return false;
		}catch(IndexOutOfBoundsException e){
			System.err.println("[ERROR] Niepoprawna forma wyrazenia!\n");
			return false;
		}
	}

	void popFirstElementFromList(){
		getStatement().remove(0);
	}

}
