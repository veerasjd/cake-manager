package com.waracle.cakemgr.service;


import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CakeServiceTest {
    @Mock
    CakeRepository cakeRepository;

    @InjectMocks
    CakeService cakeService;

    @Test
    public void testGetCakes() {
        Mockito.when(cakeRepository.getCakes()).thenReturn(Collections.emptyList());
        List<Cake> cakes = cakeService.getCakes();
        assertEquals(0,cakes.size());
        Mockito.verify(cakeRepository).getCakes();
    }

    @Test
    public void testAddCake() {
        Cake newCake =  new Cake("newCakeTitle", "newCakeDesc", "newCakeImage");
        Mockito.when(cakeRepository.saveCake(newCake)).thenReturn(newCake);
        Cake cake = cakeService.addCake(newCake);
        assertEquals(newCake, cake);
        Mockito.verify(cakeRepository).saveCake(newCake);
    }
}
