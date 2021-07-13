package com.waracle.cakemgr.repository;

import com.waracle.cakemgr.model.Cake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CakeRepositoryTest {

    @InjectMocks
    private CakeRepository cakeRepository;

    @Test
    public void testAddCake() {
        List<Cake> cakes = cakeRepository.getCakes();
        Integer initialSize = cakes.size();
        Cake newCake = new Cake("newCakeTitle", "newCakeDesc", "newCakeImage");
        cakeRepository.saveCake(newCake);
        cakes = cakeRepository.getCakes();
        assertEquals(initialSize + 1, cakes.size());
    }

}
