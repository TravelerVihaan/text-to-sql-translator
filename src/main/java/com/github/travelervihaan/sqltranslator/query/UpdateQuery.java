package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value="updateQuery")
public class UpdateQuery extends AbstractQuery {

	@Autowired
	UpdateQuery(DictionaryService dictionaryService){
		super(dictionaryService);
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
