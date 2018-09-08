/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;


import com.mycompany.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sonia
 */
public class FlooringTaxDaoImpl implements  FlooringTaxDao{
   
private static final String STATES_FILE = "Taxes.txt";
    private static final String DELIMITER = ",";


    private List<Tax> loadStates() throws FlooringPersistenceException {
        Scanner scanner;
        List<Tax> states = new ArrayList<>();

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(STATES_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "-_- Could not load states data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        scanner.nextLine();//Skips scanning document headers
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            if (currentTokens.length == 2) {
                Tax currentState = new Tax();
                currentState.setState(currentTokens[0]);
                currentState.setTaxRate(new BigDecimal(currentTokens[1]));
                // Put currentState into the map using stateAbbr as the key
                states.add(currentState);
            } else {
                //Ignores line if delimited wrong or empty.
            }
        }
        scanner.close();

        if (!states.isEmpty()) {
            return states;
        } else {
            return null;
        }
    }
    
        

    @Override
    public Tax getTax(String state) throws FlooringPersistenceException{
        List<Tax> states = loadStates();;
        if (states == null) {
            return null;
        } else {
            Tax chosenState = states.stream()
                    .filter(s -> s.getState().equalsIgnoreCase(state))
                    .findFirst().orElse(null);
            return chosenState;
        }
    }

   
    @Override
    public void loadTheTaxes() throws FlooringPersistenceException {
        loadStates();
    }
    
}
 