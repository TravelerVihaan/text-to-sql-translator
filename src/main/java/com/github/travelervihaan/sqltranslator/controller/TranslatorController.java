package com.github.travelervihaan.sqltranslator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.travelervihaan.sqltranslator.service.TranslatorService;

import java.util.Optional;

@Controller
public class TranslatorController {
	
	private final TranslatorService translatorService;
	
	@Autowired
	public TranslatorController(TranslatorService translatorService) {
		this.translatorService = translatorService;
	}

    @RequestMapping("/")
    public String home(@RequestParam(required = false, defaultValue = "") String statement, Model model){
        model.addAttribute("statement", statement);
	    return "index";
    }

    @PostMapping("/translate")
    public String translate(@RequestParam String query, Model model){
	    translatorService.setNaturalLanguageStatement(Optional.ofNullable(query));
    	model.addAttribute("statement",translatorService.getNaturalLanguageStatement());
    	System.out.println(translatorService.getNaturalLanguageStatement());
        return "index";
    }
}
