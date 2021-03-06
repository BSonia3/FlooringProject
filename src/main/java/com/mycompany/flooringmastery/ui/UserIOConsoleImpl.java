/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author sonia
 */
public class UserIOConsoleImpl implements UserIO {

   
    Scanner scanner = new Scanner(System.in);

    @Override
    public int readInt(String prompt) {
        int value = 0;
        prompt = scanner.nextLine();
        value = Integer.parseInt(prompt);
        return value;
    }

    @Override
    public int readInt(String prompt, int min, int max) {

        min = 1;
        max = 100;
        int value;

        // System.out.println("Prompt the user to enter a number from the menu : ");
        value = readInt(prompt);
        if (value >= min && value <= max) {
            //System.out.println("Thank you this is a correct answer ");

            return value;
        } else {

            //  System.out.println(" enter a value from the menu : ");  
            readInt(prompt, min, max);

        }

        return value;

    }

    @Override
    public String readString(String prompt) {
        prompt = scanner.nextLine();
        return prompt;
    }

 
    @Override
    public void print(String prompt) {
        System.out.println(prompt);
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal value = null;
        prompt = scanner.nextLine();
        value = new BigDecimal(prompt);
        return value;
    }
    
    @Override
    public float readFloat(String prompt) {
        float value;
        prompt = scanner.nextLine();
        value = Float.parseFloat(prompt);
        return value;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        min = 1;
        max = 100;
        float value;
        //System.out.println("Prompt the user to enter a number from the menu : ");
        prompt = scanner.nextLine();
        value = readFloat(prompt);
        if (value <= max && value >= min) {
            return value;
        } else {

            // System.out.println("try again; this value is not between min and max ");  
            readFloat(prompt, min, max);
            return value;
        }

    }

    @Override
    public double readDouble(String prompt) {
        float value;
        prompt = scanner.nextLine();
        value = Float.parseFloat(prompt);
        return value;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {

        min = 1;
        max = 100;
        double value;
        //System.out.println("Prompt the user to enter a number from the menu  : ");
        prompt = scanner.nextLine();
        value = readDouble(prompt);
        if (value <= max && value >= min) {
            return value;
        } else {

            // System.out.println("try again; this value from the menu  ");  
            readDouble(prompt, min, max);
            return value;
        }

    }

    @Override
    public long readLong(String prompt) {
        long value;
        prompt = scanner.nextLine();
        value = Long.parseLong(prompt);
        return value;
    }

    @Override
    public long readLong(String prompt, long min, long max) {

        min = 1;
        max = 100;
        long value;
        // System.out.println("Prompt the user to enter a number from the menu  : ");
        prompt = scanner.nextLine();
        value = readLong(prompt);
        if (value <= max && value >= min) {
            return value;
        } else {

            //System.out.println("try again; this value is not from the menu  ");  
            readDouble(prompt, min, max);
            return value;
        }

    }
   
    
     @Override
    public LocalDate readDate(String prompt) {
        boolean valid = false;
        LocalDate result = null;
        do {
            String value = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
                value = readString(prompt);
                result = LocalDate.parse(value, formatter);
                valid = true;
            } catch (DateTimeParseException ex) {
                System.out.printf("The value '%s' is not a valid date. (MMddyyyy)\n", value);
            }
        } while (!valid);

        return result;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate min, LocalDate max) {
        boolean valid = false;
        LocalDate result = null;
        do {
            result = readDate(prompt);
            if (result.isAfter(min) && result.isBefore(max)) {
                valid = true;
            } else {
                System.out.printf("Please choose a date within bounds. (%s to %s)\n", min, max);
            }
        } while (!valid);

        return result;
    }
}
