package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value="deleteQuery")
public class DeleteQuery extends AbstractQuery{

	@Autowired
	DeleteQuery(DictionaryService dictionaryService) {
		super(dictionaryService);
	}

	@Override
	public void prepareQuery() {
		if(isWordInDictionary("table")) {
			prepareDropTableQuery();
			return;
		}
		if(checkAllDictionary()) {
			if(!getStatement().isEmpty())
				prepareConditionForQuery();
		}
	}

	private void prepareDropTableQuery(){
		popFirstElementFromList();
		setStringBuilder(new StringBuilder("DROP TABLE "));
		appendToStringBuilder(getStatement().get(0));
	}

}
