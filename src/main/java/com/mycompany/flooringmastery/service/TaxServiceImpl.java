/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringPersistenceException;
import com.mycompany.flooringmastery.dao.FlooringTaxDao;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Tax;

/**
 *
 * @author sonia
 */
public class TaxServiceImpl implements TaxService {
    
    private FlooringTaxDao daoTax;
    
    public TaxServiceImpl(FlooringTaxDao daoTax) {
      this.daoTax=daoTax;
    }
    @Override
   public void calculateTax(Order myOrder) throws FlooringPersistenceException,
            FlooringDataValidationException {
        //Set state information in order.
        
        Tax chosenState = daoTax.getTax(myOrder.getState());
        if (chosenState == null){
            throw new FlooringDataValidationException("ERROR: No such State in our Program");
        }else{
        myOrder.setState(chosenState.getState());
        myOrder.setTaxRate(chosenState.getTaxRate());
    }}
    
    
}
