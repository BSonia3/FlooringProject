/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringPersistenceException;
import com.mycompany.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface FlooringServiceLayer {
   
    public List<Order> getOrdersByDate(LocalDate date) throws FlooringDataValidationException,FlooringPersistenceException;

    void addOrder(Order order)throws FlooringDataValidationException,FlooringPersistenceException;


    Order getOrder(Order order)throws FlooringDataValidationException,FlooringPersistenceException;


    Order removeOrder(Order order) throws FlooringDataValidationException,FlooringPersistenceException;
    
    Order editOrder(Order order) throws FlooringPersistenceException,FlooringDataValidationException;
    
    void saveWork()throws FlooringDataValidationException,FlooringPersistenceException;

    public void validateOrder(Order myOrder) throws FlooringDataValidationException;
    
    public void calculateTotal(Order myOrder) ;
    public void switchMode (String dao)throws FlooringPersistenceException;
}