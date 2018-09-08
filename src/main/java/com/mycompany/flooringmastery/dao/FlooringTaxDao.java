/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface FlooringTaxDao {

    //List<Tax> getAllTax();

    public  Tax getTax(String state)throws FlooringPersistenceException;
    
    public void loadTheTaxes() throws FlooringPersistenceException;
    
}
