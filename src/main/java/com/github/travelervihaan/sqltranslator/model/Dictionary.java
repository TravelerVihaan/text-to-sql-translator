package com.github.travelervihaan.sqltranslator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "dictionary")
public class Dictionary {

    @Id
    private String id;
    private String name;
    private List<String> dictionaryWords;

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
