/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;


import com.mycompany.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author sonia
 */
public class FlooringOrdersDaoTrainingFileImpl implements FlooringOrderDao{
     
     Map<LocalDate, List<Order>> ordersMap = new HashMap<>();
    
    @Override
    public Order addOrder(Order order) throws FlooringPersistenceException {
        readLastOrderNumber();
        lastOrderNumber++;
        order.setOrderNumber(lastOrderNumber);
        writeLastOrderNumber(lastOrderNumber); 
        
        List<Order> orders =ordersMap.get(order.getOrderDate());
        if(orders==null){
        orders =new ArrayList<>() ;
        
        }
        orders.add(order);
        
        ordersMap.put(order.getOrderDate(),orders);
        //writeOrders(orders, order.getOrderDate());
         return order;
        
    }

    @Override
    public List<Order> getAllOrders(LocalDate myDate) throws FlooringPersistenceException {
             
       loadOrders(myDate);
       List<Order> orders=ordersMap.get(myDate);
            
        return orders;
         
    }

    @Override
    public Order getOrder(Order order) throws FlooringPersistenceException {
       List<Order>orders= ordersMap.get(order.getOrderDate());
        for(Order myOrder:orders){
            int orderNumber= order.getOrderNumber();
                 if (orderNumber==myOrder.getOrderNumber()){
                  order=myOrder;
                   }
        
        }
        return order;
    }

    @Override
    public Order removeOrder(Order order) throws FlooringPersistenceException {
      List<Order>orders=ordersMap.get(order.getOrderDate());
        
        orders.remove(order);
        
       ordersMap.put(order.getOrderDate(),orders);
                 
        //writeOrders(orders, order.getOrderDate());
        return order; 
    }

    @Override
    public Order editOrder(Order order) throws FlooringPersistenceException {
        List<Order>orders=ordersMap.get(order.getOrderDate());
       ordersMap.put(order.getOrderDate(), orders);
       //writeOrders(orders, order.getOrderDate());
       return order;
    }

    @Override
    public List<Order> getListOrder() throws FlooringPersistenceException {
      return new ArrayList(ordersMap.values()); 
    }

    @Override
    public void saveCurrentWork() throws FlooringPersistenceException {
        // do nothing in training mode
    }
    
    
     private int lastOrderNumber;
    
    private void readLastOrderNumber() throws FlooringPersistenceException {
        Scanner scanner;

        try {
            //Create Scanner to read file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_PATH + "orderNumber.txt")));
        } catch (FileNotFoundException e) {
            //Throwing a general exception to the calling code
            throw new FlooringPersistenceException(
                    "-_- Could not load order number into memory.", e);
        }

        int savedOrderNumber = Integer.parseInt(scanner.nextLine());

        this.lastOrderNumber = savedOrderNumber;

        scanner.close();

    }

    private void writeLastOrderNumber(int lastOrderNumber) throws FlooringPersistenceException {
        //do nothing in training mode
    }
    
    
    public static final String ORDER_PATH = "ORDERS/";
    private static final String DELIMITER = ",";   
  
   private List<Order> loadOrders(LocalDate myDate) throws FlooringPersistenceException {
        Scanner scanner;
        String fileDate = myDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));

        File file = new File(String.format(ORDER_PATH + "Orders_%s.txt", fileDate));
        
         List<Order>orders= new ArrayList<>();
         
        if (file.isFile()) {
            try {
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(file)));
            } catch (FileNotFoundException e) {
                throw new FlooringPersistenceException(
                        "-_- Could not load order data into memory.", e);
            }
            String currentLine;
            String[] currentTokens;
              
            
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                //currentTokens = currentLine.split(DELIMITER, 12);
                currentTokens = currentLine.split(DELIMITER);
                if (currentTokens.length == 12) {
                Order currentOrder = new Order();

                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                currentOrder.setCustomerName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                currentOrder.setProductType(currentTokens[4]);
                currentOrder.setArea(new BigDecimal(currentTokens[5]));
                currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                currentOrder.setTax(new BigDecimal(currentTokens[10]));
                currentOrder.setTotal(new BigDecimal(currentTokens[11]));

                currentOrder.setOrderDate(LocalDate.parse(fileDate,
                            DateTimeFormatter.ofPattern("MMddyyyy")));
                orders.add(currentOrder);
                ordersMap.put(currentOrder.getOrderDate(), orders);
                
              } else {
                    
                }  
            }
            scanner.close();
             
        
        
        }
       return orders;
    }
    
    
   private void writeOrders(List<Order> orders,LocalDate myDate)throws FlooringPersistenceException {
        
       // do nothing in training mode
        
           /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String dateFormat = myDate.format(formatter);
            dateFormat = dateFormat.replace("-", "");//MMddyyyy

            File fileName = new File("ORDERS/Orders_" + dateFormat + ".txt");

            PrintWriter out;

            try {
                out = new PrintWriter(new FileWriter(fileName));
            } catch (IOException e) {
                throw new FlooringPersistenceException(
                        "Could not save order data.", e);
            }
             // Write out the Order objects to the file.
            // out.println(HEADER);/// added
             
           orders = this.getAllOrders(myDate);
           orders = ordersMap.get(myDate);
           
        
            
            ////
            
            for (Order currentOrder : orders) {
                    
                    out.println(currentOrder.getOrderNumber() + DELIMITER
                            + currentOrder.getCustomerName() + DELIMITER
                            + currentOrder.getState() + DELIMITER
                            + currentOrder.getTaxRate() + DELIMITER
                            + currentOrder.getProductType() + DELIMITER
                            + currentOrder.getArea() + DELIMITER
                            + currentOrder.getCostPerSquareFoot() + DELIMITER
                            + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                            + currentOrder.getMaterialCost() + DELIMITER
                            + currentOrder.getLaborCost() + DELIMITER
                            + currentOrder.getTax() + DELIMITER
                            + currentOrder.getTotal());

                
                out.flush();

            }
            out.close();
        */
        
    }
   
    
    
}
