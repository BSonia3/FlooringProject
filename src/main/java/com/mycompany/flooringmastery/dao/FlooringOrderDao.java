/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface FlooringOrderDao {
    
    Order addOrder(Order order)throws FlooringPersistenceException;

    List<Order> getAllOrders(LocalDate myDate) throws FlooringPersistenceException;

    Order getOrder(Order order)throws FlooringPersistenceException;

    Order removeOrder(Order order) throws FlooringPersistenceException;
    
    Order editOrder(Order order) throws FlooringPersistenceException;
    
     List<Order> getListOrder() throws FlooringPersistenceException;
    void saveCurrentWork()throws FlooringPersistenceException;
    

}
