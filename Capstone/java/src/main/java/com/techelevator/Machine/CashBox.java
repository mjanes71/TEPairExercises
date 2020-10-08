package com.techelevator.Machine;

import com.techelevator.Exceptions.NotAWholeDollarAmountException;

import java.math.BigDecimal;

public class CashBox {

    //method to display balance

    private BigDecimal customerBalance = new BigDecimal("0.00");


    public BigDecimal getCustomerBalance(){
        return customerBalance;
    }

    public void setCustomerBalance(BigDecimal customerBalance){
        this.customerBalance = customerBalance;
    }

    public void deposit(BigDecimal moneyIn) throws NotAWholeDollarAmountException{

        BigDecimal is10 = new BigDecimal("10.00");
        BigDecimal is0 = new BigDecimal("0.00");
        if(((moneyIn.multiply(is10)).remainder(is10)).compareTo(is0) == 0){
            customerBalance = customerBalance.add(moneyIn);
        }else {
            throw new NotAWholeDollarAmountException("Not a whole dollar amount.");

        }
    }


}


