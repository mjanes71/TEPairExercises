package com.techelevator;

import com.techelevator.crm.Pet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Pet harley = new Pet("Harley", "Goldendoodle");
        //List<String> vaccines = new ArrayList<>(Arrays.asList("parvo", "distemper"));
//        vaccines.set(0, "parvo");
//        vaccines.set(1, "distemper");
        harley.setVaccinations("vaccines");
        System.out.println(harley.listVaccinations());
        System.out.println(harley.getVaccinations());

    }

}
