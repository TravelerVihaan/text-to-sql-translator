package com.github.travelervihaan.sqltranslator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
	
	private DictionaryService dictionaryService;
	
	@Autowired
	public DictionaryController(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	
	@GetMapping(value={"/",""})
	public String dictionaries(Model model) {
		model.addAttribute("dictionaries", dictionaryService.getAllDictionaries());
		return "dictionary/dictionary";
	}

	@GetMapping("/{dictionaryName}")
	public String specifiedDictionaryGet(Model model, @PathVariable(value = "dictionaryName") String dictName){
		model.addAttribute("dictionary",dictionaryService.getByName(dictName));
		return "dictionary/specifieddictionary";
	}

}
