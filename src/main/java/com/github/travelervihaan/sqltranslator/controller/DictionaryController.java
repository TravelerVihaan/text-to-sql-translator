package com.github.travelervihaan.sqltranslator.controller;

import com.github.travelervihaan.sqltranslator.model.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.travelervihaan.sqltranslator.service.DictionaryService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DictionaryController {
	
	private DictionaryService dictionaryService;
	
	@Autowired
	public DictionaryController(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	
	@GetMapping("/dictionary")
	public String dictionaries(Model model) {
		List<Dictionary> dictionaries = dictionaryService.getAllDictionaries();
		List<String> dictionaryNames = new ArrayList<>();
		for(Dictionary dictionary: dictionaries)
			dictionaryNames.add(dictionary.getName());
		model.addAttribute("dictionaries", dictionaryNames);
		return "dictionary/dictionary";
	}

	@PostMapping("/addnewdictionary")
	public String addNewDictionary(@RequestParam String dictionaryName, @RequestParam String words){
		dictionaryService.addNewDictionary(dictionaryName,words);
		return "redirect:/dictionary";
	}

	@GetMapping("/dictionarypanel")
	public String specifiedDictionaryGet(Model model, @RequestParam(value = "dictionaryName") String dictName){
		model.addAttribute("dictionary",dictionaryService.getByName(dictName));
		return "dictionary/specifieddictionary";
	}
	
	@PostMapping("/addword")
	public String addWordToDrictionary(@RequestParam String dictionaryName, @RequestParam String word, Model model) {
		dictionaryService.addWordToDictionary(dictionaryName,word);
		model.addAttribute("dictionary",dictionaryService.getByName(dictionaryName));
		return "dictionary/specifieddictionary";
	}
	
	@PostMapping("/deleteword")
	public String deleteWordFromDictionary(@RequestParam String dictionaryName, @RequestParam String word, Model model) {
		dictionaryService.deleteWordFromDictionary(dictionaryName,word);
		model.addAttribute("dictionary",dictionaryService.getByName(dictionaryName));
		return "dictionary/specifieddictionary";
	}
		
}
