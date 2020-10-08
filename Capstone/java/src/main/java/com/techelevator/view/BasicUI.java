package com.techelevator.view;

import java.math.BigDecimal;

public interface BasicUI {

    void output(String content);

    void pauseOutput();

    BigDecimal promptForBigDecimal();

    String promptForSelection(String[] options);

    String promptForString();
}