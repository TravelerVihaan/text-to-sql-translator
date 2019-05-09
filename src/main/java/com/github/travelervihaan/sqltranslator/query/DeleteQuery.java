package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class DeleteQuery extends AbstractQuery{

	DeleteQuery(List<String> statement) {
		super(statement, "DELETE ");
	}

	@Autowired
	public void setDictionaryService(DictionaryService dictionaryService){
		super.setDictionaryService(dictionaryService);
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

	private void prepareDropTableQuery(){
		popFirstElementFromList();
		setStringBuilder(new StringBuilder("DROP TABLE "));
		appendToStringBuilder(getStatement().get(0));
	}

}
