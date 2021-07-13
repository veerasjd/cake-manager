package com.waracle.cakemgr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CakeController.class)
public class CakeControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;

    @MockBean CakeService cakeService;

    //happy path test cases
    @Test
    public void testHappyPathWithNoCakes() throws Exception {

        Mockito.when(cakeService.getCakes()).thenReturn(EMPTY_LIST);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/cakes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testHappyPathWithSomeCakes() throws Exception {
        List<Cake> cakes = Arrays.asList(
                new Cake("Cake1", "Cake1Desc", "Cake1Image"),
                new Cake("Cake2", "Cake2Desc", "Cake2Image"),
                 new Cake("Cake3", "Cake3Desc", "Cake3Image")
        );

        //stub the service
        Mockito.when(cakeService.getCakes()).thenReturn(cakes);

        //when request made
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/cakes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].title", Matchers.is("Cake2")));

        //verify the mock
        Mockito.verify(cakeService).getCakes();
    }


    //sad path test cases
    @Test
    public void testWhenInValidUri() throws Exception {
        //post invalid request
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/ca")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        //make sure no request made to service
        Mockito.verifyNoInteractions(cakeService);
    }

    @Test
    public void testPostingCake() throws Exception {
        Cake newCake =  new Cake("newCakeTitle", "newCakeDesc", "newCakeImage");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/cakes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(newCake)))
                .andExpect(status().isOk());

        //check the service is called with new cake
        Mockito.verify(cakeService).addCake(newCake);
    }

}
