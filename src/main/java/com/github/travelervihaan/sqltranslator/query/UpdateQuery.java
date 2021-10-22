package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
			String statement = getStatement().get(0);
			appendToStringBuilder(statement + " = ");
			//usuniecie nazwy
			popFirstElementFromList();
			//usuniecie "wynosi"
			popFirstElementFromList();
			if(",".equals(statement.substring(statement.length()-1)))
				appendNumericOrStringToStatement();
			else{
				appendNumericOrStringToStatement();
				break;
			}
		}while(getStatement().size()>0);
	}

}
