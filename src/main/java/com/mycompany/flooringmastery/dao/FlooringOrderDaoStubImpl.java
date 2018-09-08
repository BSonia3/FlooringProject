/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sonia
 */
public class FlooringOrderDaoStubImpl implements FlooringOrderDao {
     
    Order myOrder;
    List<Order> orderList = new ArrayList<>();
    LocalDate date = LocalDate.parse("01022080",DateTimeFormatter.ofPattern("MMddyyyy"));
      
    public void FlooringAuditDaoStubImpl() {
         myOrder = new Order();
         myOrder.setOrderNumber(170);
         myOrder.setCustomerName("Jack");
         myOrder.setState("PA");
         myOrder.setTaxRate(new BigDecimal("6.75"));
         myOrder.setProductType("Wood");
         myOrder.setArea(new BigDecimal("250"));
         myOrder.setCostPerSquareFoot(new BigDecimal("100"));
         myOrder.setMaterialCost(new BigDecimal("50.00"));
         myOrder.setLaborCostPerSquareFoot(new BigDecimal("500"));
         myOrder.setLaborCost(new BigDecimal("15.00"));
         myOrder.setTax(new BigDecimal("66"));
         myOrder.setTotal(new BigDecimal("600"));
         myOrder.setOrderDate(date);
         
         orderList.add(myOrder);
         
         
    }

    
    @Override
    public Order addOrder(Order order)throws FlooringPersistenceException {
    if (order.getOrderNumber()==(myOrder.getOrderNumber())){
        return myOrder;
    }else{
        return null;
    }
    }

    @Override
    public List<Order> getAllOrders(LocalDate myDate) throws FlooringPersistenceException {
     return orderList;
    }

    @Override
    public Order getOrder(Order order) throws FlooringPersistenceException{
        if (order ==myOrder){
        return myOrder;
    }else{
        return null;
    } }

    @Override
    public Order removeOrder(Order order) throws FlooringPersistenceException {
      if (order==myOrder){
        return myOrder;
    }else{
        return null;
    } 
    
    }

    
    @Override
    public Order editOrder(Order order) throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getListOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCurrentWork() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
    
}
