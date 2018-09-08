/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.dao.FlooringPersistenceException;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.service.FlooringDataValidationException;
import com.mycompany.flooringmastery.service.FlooringServiceLayer;
import com.mycompany.flooringmastery.service.ProductService;
import com.mycompany.flooringmastery.service.TaxService;
import com.mycompany.flooringmastery.ui.FlooringView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author sonia
 */
public class FlooringController {
    
    FlooringView view;

    FlooringServiceLayer service;
    TaxService tService;
    ProductService pService;
    // constructor that initializes these members view and dao
    public FlooringController(FlooringServiceLayer service,TaxService tService,ProductService pService ,FlooringView view) {
        this.service =  service;
        this.tService=tService;
        this.pService=pService;
        this.view = view;
    }
    
     public void run() {
         
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
            
           boolean answer=view.askForMode();
            
            service.switchMode("Training");
            if (answer==true) {
                service.switchMode("Training");
                view.displayModeTraining();
            } else {
                service.switchMode("Production");
                view.displayModeProduction();
            }
            
            while (keepGoing) {

                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrdersByDate();
                        break;
                    case 2:
                        addNewOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        if (answer) {
                            saveCurrentWork();
                            view.doesNotSave();
                        } else {
                           saveCurrentWork();
                        }
             
                        break;
                    case 6:
                        if (answer) {
                            keepGoing = false;
                            break;
                        } else {
                            
                            keepGoing = false;
                            
                        break;
                        }
                    default:
                    
                        return;
                }

            }
            exitMessage();

        } catch (FlooringPersistenceException |FlooringDataValidationException e) {
            view.displayErrorMessage(e.getMessage());

        }
    }
     
     // print the menu
       
     
     private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayAllOrders()throws FlooringPersistenceException,FlooringDataValidationException {
        LocalDate dateChoice = view.dateChoice();
       
        try { 
            List<Order> ordersList = service.getOrdersByDate(dateChoice);
             view.getAllOrders(ordersList);
        } catch (FlooringPersistenceException |FlooringDataValidationException ex) {
            view.displayErrorMessage(ex.getMessage());  }
        
    }
    
    private void displayOrdersByDate() {
        view.displayDisplayOrdersBanner();
        LocalDate date = view.dateChoice();
        try {
            List<Order> orders = service.getOrdersByDate(date);
            view.getAllOrders(orders);
          } catch (FlooringPersistenceException|FlooringDataValidationException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
      }
    
    

   /* private void addNewOrder() throws FlooringPersistenceException,FlooringDataValidationException{
        view.addOrderBanner();
        try{
        Order currentOrder = view.getNewOrderInfo();
        Order newOrder =calculateOrder(currentOrder);
        view.getNewOrder(newOrder);
        //List<Order> orders= new ArrayList<>();
        // List<Order> orders = service.getOrdersByDate(newOrder.getOrderDate());
         List<Order> orders = new ArrayList<>();
         orders=service.getOrdersByDate(newOrder.getOrderDate());
         orders.add(newOrder);
        if(view.askUserIfSaveOrder()==true){ 
        for(Order order:orders){
        service.addOrder(order);
        }}
        } catch (FlooringPersistenceException|FlooringDataValidationException e){
            view.displayErrorMessage(e.getMessage());
            
        }
        }*/
    
    private void addNewOrder() throws FlooringPersistenceException,FlooringDataValidationException{
        view.addOrderBanner();
        try{
        Order currentOrder = view.getNewOrderInfo();
        Order newOrder =calculateOrder(currentOrder);
        view.getNewOrder(newOrder);
        //List<Order> orders = service.getOrdersByDate(newOrder.getOrderDate());
        List<Order> orders = new ArrayList<>();
         orders.add(newOrder);
        
        // orders=service.getOrdersByDate(newOrder.getOrderDate());
        if(view.askUserIfSaveOrder()==true){
            orders.stream()
        .forEach(o -> {
                try {
                    service.addOrder(o);
                } catch (FlooringDataValidationException | FlooringPersistenceException ex) {
                    view.displayErrorMessage(ex.getMessage());}
            });
            
        }
        } catch (FlooringPersistenceException|FlooringDataValidationException e){
            view.displayErrorMessage(e.getMessage());
            
        }}
    
    
    private void editOrder() throws FlooringPersistenceException,FlooringDataValidationException {
        view.editBanner();
        LocalDate date = view.searchOrderDate();
        try {
            List<Order> orders = service.getOrdersByDate(date);
            view.getAllOrders(orders);
            String number = view.orderChoice();
            int orderNumber = Integer.parseInt(number);
          for(Order order:orders){
            
            if(orderNumber == order.getOrderNumber()){
            service.getOrder(order);
            Order editOrder = view.editOrderInfo(order);
            editOrder=calculateOrder(editOrder);
            service.editOrder(editOrder);
            }}
             
            view.editSuccessBanner();
           
        } catch (FlooringPersistenceException | FlooringDataValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }}
   
    

   private void removeOrder() throws FlooringPersistenceException, FlooringDataValidationException{
        view.removeBanner();
        LocalDate date = view.searchOrderDate();
        try {
            List<Order> orders = service.getOrdersByDate(date);
            
            view.getAllOrders(orders);
            int orderNumber = view.getOrderChoice();
            if (view.askUserIfWantToRemoveTheOrder() == true) {
                
                 Iterator<Order> it = orders.iterator();
                 while (it.hasNext()) {
                 Order order = it.next();
                if(orderNumber == order.getOrderNumber()) {
                       it.remove();
                        }
                    }
            }
             view.removeSuccessBanner();
                  
              } catch (FlooringDataValidationException |FlooringPersistenceException ex) {
                  view.displayErrorMessage(ex.getMessage());  }
                
            
        } 
   
   
  
   
   private void saveCurrentWork() throws FlooringPersistenceException,FlooringDataValidationException {
     
            try{
             
             service.saveWork();
             view.saveSuccessBanner();
           } catch (FlooringDataValidationException | FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
       
   }

   private void unknownCommand() throws FlooringPersistenceException {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

  
    private Order calculateOrder(Order myOrder) throws FlooringPersistenceException,
            FlooringDataValidationException {
        
        service.validateOrder(myOrder);
        pService.calculateMateriel(myOrder);
        tService.calculateTax(myOrder);
        service.calculateTotal(myOrder);

        return myOrder;

    }
    
    
    
}
