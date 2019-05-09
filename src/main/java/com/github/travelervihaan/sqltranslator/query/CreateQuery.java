package com.github.travelervihaan.sqltranslator.query;

import java.util.List;

public class CreateQuery extends AbstractQuery {

    CreateQuery(List<String> statement) { super(statement, "CREATE "); }

    @Override
    public void prepareQuery() {
        getPreparedQuery();
    }

}
