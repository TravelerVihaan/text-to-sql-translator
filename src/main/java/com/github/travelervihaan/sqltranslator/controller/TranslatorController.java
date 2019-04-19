package com.github.travelervihaan.sqltranslator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TranslatorController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/translate")
    public String translate(){
        return "redirect:/index";
    }
}
