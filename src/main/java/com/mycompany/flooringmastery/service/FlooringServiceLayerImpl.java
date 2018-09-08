/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringAuditDao;
import com.mycompany.flooringmastery.dao.FlooringOrderDao;
import com.mycompany.flooringmastery.dao.FlooringOrderDaoFileImpl;
import com.mycompany.flooringmastery.dao.FlooringPersistenceException;
import com.mycompany.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 *
 * @author sonia
 */
public class FlooringServiceLayerImpl implements FlooringServiceLayer{
    
    private FlooringOrderDao dao;
    
    private FlooringAuditDao auditDao;
   
    
    public FlooringServiceLayerImpl(FlooringOrderDao dao, FlooringAuditDao auditDao) {
       this.dao=dao;
       this.auditDao=auditDao;
       
    }
    
     @Override
    public void switchMode(String mode) throws FlooringPersistenceException {
         ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
     
        if (mode.equalsIgnoreCase("training")){
            dao = ctx.getBean("ordersDaoTraining", FlooringOrderDao.class);
           
        }
        else {if (mode.equalsIgnoreCase("production")){
       
        dao= ctx.getBean("flooringOrderDao", FlooringOrderDao.class);
              
        }}
    }

 /* @Override
    public List<Order> getOrdersByDate(LocalDate date) throws FlooringDataValidationException,FlooringPersistenceException
{
       List<Order> orders = new ArrayList<>();
       
        try {
             List<Order> orderList = new ArrayList<>();
              // orderList = dao.getAllOrders(date);
              orderList=dao.getListOrder();
       

    for (Order order : orderList) {
            if (order.getOrderDate().equals(date)) {
                orders.add(order);
               orders= dao.getAllOrders(date);
            }
            else {
                throw new FlooringDataValidationException(" No Orders related to this Date !\n");
       
            }
        }
     } catch (FlooringPersistenceException ex) {
                          ex.getMessage();    }
    
       /*  orderList.stream() 
              .filter(o->o.getOrderDate().equals(date))
              .forEach(o -> {
              orders.add(o);
      });
      
        
        return orders; 
      }*/
     
    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws FlooringPersistenceException,FlooringDataValidationException {
        List<Order>orderList= new ArrayList<>();
        try {
             
            orderList=dao.getAllOrders(date);
            
        } catch (FlooringPersistenceException ex) {
            
                ex.getMessage();    } 
        
        if (orderList==null) {
            throw new FlooringDataValidationException(" No Orders related to this Date !\n");
        }
        return orderList;
    }
    
   

    @Override
    public void addOrder(Order order) throws FlooringDataValidationException,FlooringPersistenceException
{        
        dao.addOrder(order);
    }

    @Override
    public Order getOrder(Order order) throws FlooringDataValidationException,FlooringPersistenceException
{     if (dao.getOrder(order)==null) {
            throw new FlooringDataValidationException("ERROR: NO ORDER RELATED TO THIS NUMBER "
                    + "product.");
        }else{
       return  dao.getOrder(order);}
    }

    @Override
    public Order removeOrder(Order order)throws FlooringDataValidationException,FlooringPersistenceException
 {   
     Order removedOrder=dao.removeOrder(order);
     return removedOrder;
    }
    
    @Override
    public Order editOrder(Order order) throws FlooringPersistenceException,FlooringDataValidationException{
     Order editedOrder=dao.editOrder(order);
     return editedOrder;
        
    }
   

    @Override
    public void saveWork()throws FlooringDataValidationException,FlooringPersistenceException{
        
           dao.saveCurrentWork();
            
    }
  
    
    @Override
   public void calculateTotal(Order myOrder) {
        
        myOrder.setMaterialCost(myOrder.getCostPerSquareFoot().multiply(myOrder.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        myOrder.setLaborCost(myOrder.getLaborCostPerSquareFoot().multiply(myOrder.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        myOrder.setTax(myOrder.getTaxRate().divide(new BigDecimal("100.00"))
                .multiply((myOrder.getMaterialCost().add(myOrder.getLaborCost())))
                .setScale(2, RoundingMode.HALF_UP));
        myOrder.setTotal(myOrder.getMaterialCost().add(myOrder.getLaborCost()).add(myOrder.getTax()));
    }
    // calculate Tax moved to taxService
    
    @Override
    public void validateOrder(Order myOrder) throws FlooringDataValidationException {
        String message = "";
        if (myOrder.getCustomerName().trim().isEmpty() || myOrder.getCustomerName() == null) {
            message += "Customer name is required.\n";
        }
        if (myOrder.getState().trim().isEmpty() || myOrder.getState() == null) {
            message += "State is required.\n";
        }
        if (myOrder.getProductType().trim().isEmpty() || myOrder.getProductType() == null) {
            message += "Product type is required.\n";
        }
        if (myOrder.getArea().compareTo(BigDecimal.ZERO) == 0 || myOrder.getArea() == null) {
            message += "Area square footage is required.";
        }
        if (!message.isEmpty()) {
            throw new FlooringDataValidationException(message);
        }
    }

   
    
       
    
}
