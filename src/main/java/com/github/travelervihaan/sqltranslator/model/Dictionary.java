package com.github.travelervihaan.sqltranslator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "dictionary")
public class Dictionary {

    @Id
    private long id;
    private String name;
    private List<String> dictionaryWords;

    public Dictionary(String name, List<String> dictionaryWords){
        this.name = name;
        this.dictionaryWords = dictionaryWords;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<String> getDictionaryWords() {
        return dictionaryWords;
    }

    public void setName(String dictionaryType) {
        this.name = dictionaryType;
    }

    public void setDictionaryWords(List<String> dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "dictionaryType='" + name + '\'' +
                ", dictionaryWords=" + dictionaryWords +
                '}';
    }
}
