package com.techelevator.crm;

import org.junit.Assert;
import org.junit.Test;

public class PetTests {

    @Test
    public void listVaccinations_returns_formatted_string() {
        Pet sut = new Pet("Fred", "Orange tabby");
        sut.setVaccinations("distemper");
        sut.setVaccinations("rabies");
        String result = sut.listVaccinations();
        Assert.assertEquals("distemper, rabies", result);
    }

}
