package com.github.travelervihaan.sqltranslator.repository;

import com.github.travelervihaan.sqltranslator.model.Dictionary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends MongoRepository<Dictionary, String> {

    public Dictionary findByName(String dictionaryName);
}
