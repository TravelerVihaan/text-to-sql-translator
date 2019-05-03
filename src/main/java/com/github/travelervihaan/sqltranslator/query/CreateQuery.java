package com.github.travelervihaan.sqltranslator.query;

public class CreateQuery extends AbstractQuery {

    CreateQuery(String[] statement) { super(statement, "CREATE "); }

    @Override
    public void prepareQuery() {
        //TODO
    }
}
