package com.techelevator.reports;





import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


public class SalesReports {

    private BigDecimal machineTotalSales = new BigDecimal("0.00");

    public String getTimeStamp(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        String pattern = "MM/dd/yyyy hh:mm:ss a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(timestamp);
        return formattedDate ;
    }

    public void addToTransactionLog(String transactionDescription){
        try(FileOutputStream stream = new FileOutputStream("log.txt", true);
            PrintWriter writer = new PrintWriter(stream)){
            writer.println(getTimeStamp() + transactionDescription);
        }catch(IOException e){
            System.out.println("Could not find file. Try again.");
        }


    }





}

