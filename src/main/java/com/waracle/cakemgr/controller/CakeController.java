package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CakeController {

    @Autowired
    private CakeService cakeService;

    @GetMapping("/cakes")
    public List<Cake> getCakes(){
  //  public List<Cake> getCakes(@AuthenticationPrincipal OAuth2User principal){
        return cakeService.getCakes();
    }

    @PostMapping("/cakes")
    public Cake addCake(@RequestBody Cake cake) {
  //  public Cake addCake(@AuthenticationPrincipal OAuth2User principal, @RequestBody Cake cake) {
        return cakeService.addCake(cake);
    }
}
