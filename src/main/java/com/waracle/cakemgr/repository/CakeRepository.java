package com.waracle.cakemgr.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.Cake;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CakeRepository {

    private List<Cake> cakesInMemoryDatabase = new ArrayList<>();

    public List<Cake> getCakes()  {
        if(cakesInMemoryDatabase.isEmpty()) {
            loadData();
        }
        return Collections.unmodifiableList(cakesInMemoryDatabase);
    }

    //initial data load => we can move this to connectors so it can either fe
    private void loadData(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            final URL url = new URL("https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json");
            cakesInMemoryDatabase = mapper.readValue(url, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cake saveCake(Cake cake) {
        cakesInMemoryDatabase.add(cake);
        return cake;
    }
}




