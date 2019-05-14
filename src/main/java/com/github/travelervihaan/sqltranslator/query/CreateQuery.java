package com.github.travelervihaan.sqltranslator.query;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="createQuery")
public class CreateQuery extends AbstractQuery {

    @Autowired
    CreateQuery(DictionaryService dictionaryService){
        super(dictionaryService);
    }

    @Override
    public void prepareQuery() {
        getPreparedQuery();
    }

}
