package com.techelevator.crm;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CustomerTests {

    @Test
    public void getBalanceDue_returns_correct_balance(){
        Customer sut = new Customer("Megan", "Janes");

        Map<String, Double> expectedMap = new HashMap<>();
        expectedMap.put("Grooming", 70.00);
        expectedMap.put("Walking", 10.00);
        expectedMap.put("Sitting", 14.99);

        double result = sut.getBalanceDue(expectedMap);


        Assert.assertEquals(94.99, result, 0.001);
    }
}
