/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringPersistenceException;
import com.mycompany.flooringmastery.dto.Order;

/**
 *
 * @author sonia
 */
public interface TaxService {
    
     public void calculateTax(Order myOrder) throws FlooringPersistenceException,
            FlooringDataValidationException ;
    
}
