package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @Autowired
    private CakeService cakeService;

    @GetMapping
    public String listCakes(Model model) {
   // public String listCakes(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("cakes", cakeService.getCakes());
        return "cakes";
    }
}
