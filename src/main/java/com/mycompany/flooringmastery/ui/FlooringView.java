/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import com.mycompany.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sonia
 */
public class FlooringView {

    Scanner input = new Scanner(System.in);
    private UserIO io;

    //construction that initializes the io member field
    public FlooringView(UserIO io) {
        this.io = io;
    }

    // to display the choices  
    public int printMenuAndGetSelection() {
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("*");
        io.print("  * <<Flooring Program>>");
        io.print("  * 1. Display Orders");
        io.print("  * 2. Add an Order ");
        io.print("  * 3. Edit an Order ");
        io.print("  * 4. Remove an Order ");
        io.print("  * 5. Save Current Work ");
        io.print("  * 6. Quit ");
        io.print("  *");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("*");
        System.out.println("Please select from the above choices : ");
        return io.readInt("Please select from the above choices.", 1, 6);

    }

    // unknown command and exit
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    // to catch exception 
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayDisplayOrdersBanner() {
        io.print("\n=== Display ORDERS ===");
    }

    public void getAllOrders(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print(" OrderNumber: " + currentOrder.getOrderNumber() + ", "
                    + " Customer: " + currentOrder.getCustomerName() + ", "
                    + " State: " + currentOrder.getState() + ", "
                    + " Tax Rate: " + currentOrder.getTaxRate() + ", "
                    + " Product: " + currentOrder.getProductType() + ", "
                    + " Area: " + currentOrder.getArea() + ", " + "\n"
                    + " Cost per square foot: " + currentOrder.getCostPerSquareFoot() + ", "
                    + " Labor cost per square foot: " + currentOrder.getLaborCostPerSquareFoot() + ", "
                    + " Materials cost: " + currentOrder.getMaterialCost() + ", "
                    + " Labor cost: " + currentOrder.getLaborCost() + ", " + "\n"
                    + " Tax total: " + currentOrder.getTax() + ", "
                    + " Total price: " + currentOrder.getTotal());
        }
    }

    public LocalDate dateChoice() {
        io.print("Please enter date you want to search (MMddyyyy): ");
        return io.readDate("Please enter date you want to search (MMddyy): ");
    }

    public void addOrderBanner() {
        io.print("\n=== ADD NEW ORDER ===");
    }

    public Order getNewOrderInfo() {
        io.print("Enter customer name : ");
        String customerName = io.readString("Enter customer name : \n");
        io.print("Enter state : ");
        String state = io.readString("Enter state : \n");
        io.print("Enter product type : ");
        String productType = io.readString("Enter product type : \n");
        io.print("Enter area : ");
        BigDecimal area = io.readBigDecimal("Enter area : ");
        io.print("Please enter date (MMddyyyy) :");
        LocalDate date = io.readDate("Please enter date (MMddyyyy) : \n");

        Order currentOrder = new Order();
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);
        currentOrder.setOrderDate(date);

        io.print("Customer name : " + currentOrder.getCustomerName()+", "
                + "State : " + currentOrder.getState()+", "
                + "Product : " + currentOrder.getProductType()+", "
                + "Area : " + currentOrder.getArea()+", "
                + "Date : " + currentOrder.getOrderDate());

        return currentOrder;
    }

    public void getNewOrder(Order currentOrder) {
        io.print( " Customer: " + currentOrder.getCustomerName() + ", "
                + " State: " + currentOrder.getState() + ", "
                + " Tax Rate: " + currentOrder.getTaxRate() + ", "
                + " Product: " + currentOrder.getProductType() + ", "
                + " Area: " + currentOrder.getArea() + ", " + "\n"
                + " Cost per square foot: " + currentOrder.getCostPerSquareFoot() + ", "
                + " Labor cost per square foot: " + currentOrder.getLaborCostPerSquareFoot() + ", "
                + " Materials cost: " + currentOrder.getMaterialCost() + ", "
                + " Labor cost: " + currentOrder.getLaborCost() + ", " + "\n"
                + " Tax total: " + currentOrder.getTax() + ", "
                + " Total price: " + currentOrder.getTotal());
    }

    public boolean askUserIfSaveOrder() {
                        io.print("\n Would you like to save? Y/N");
        String answer = io.readString("Would you like to save? Y/N");
        String yes = "y";
        if (answer.toLowerCase().equals(yes)) {
            return true;
        } else {
            return false;
        }
    }

    public LocalDate searchOrderDate() {
             io.print("Please enter date you want to search (MMDDYYYY): ");
      return io.readDate("Please enter date you want to search (MMddyyyy): ");
    
    }

    public Integer getOrderChoice() {
             io.print("Please provide order Number: ");
      return io.readInt("Please provide order Number: "); 
    }

    public boolean askUserIfWantToRemoveTheOrder() {
                      io.print("Are you sure you want to remove this order? Y/N");
        String answer = io.readString("Are you sure you want to remove this order? Y/N");
        String yes = "y";
        if (answer.toLowerCase().equals(yes)) {
            return true;
        } else {
           return false;
        } 
    }
    public void removeSuccessBanner() {
        io.print("=== The order has been successfully removed ====");
    }
    
    public void removeBanner() {
        io.print("\n=== REMOVE AN ORDER ===");;
    }
    
    public Order editOrderInfo(Order order) {
        //Order editedOrder = new Order();
        
        io.print("Customer: " + order.getCustomerName());
        io.print("Enter a new name : ");
        String newCustomerName = io.readString( order.getCustomerName());
        if (newCustomerName == null || newCustomerName.trim().equals("")) { 
            order.setCustomerName(order.getCustomerName());
        }else{
        order.setCustomerName(newCustomerName);}
        
        io.print("State: " + order.getState());
        io.print("Enter a new State : ");
        String newState = io.readString( order.getState());
       
        if ( newState == null ||  newState.trim().equals("")) { 
            order.setState(order.getState());
        }else{
        order.setState(newState);}
        
        io.print("Product: " + order.getProductType());
        io.print("Enter a new Product : ");
        String newProductType = io.readString( order.getProductType());
        
        if ( newProductType == null ||  newProductType.trim().equals("")) { 
            order.setProductType(order.getProductType());
        }else{
        order.setProductType(newProductType);}

        io.print("Area: " + order.getArea() + " sq ft");
        io.print("Enter a new area : ");
        String newArea = io.readString( "(" + order.getArea() + ")");
        if ( newArea == null ||  newArea.trim().equals("")) { 
            order.setArea(order.getArea());
        }else{
        order.setArea(new BigDecimal(newArea));}
        
        io.print("Date: "+order.getOrderDate());
        io.print("Enter New Date : ");
        LocalDate newDate = io.readDate( "(" + order.getOrderDate() + ")");
        if ( newDate==null) { 
            order.setOrderDate(order.getOrderDate());
        }else{
        order.setOrderDate(newDate);}
        
        io.print(("Would you like to save this changes? Y/N"));
        String answer = io.readString("Would you like to save this edited order? Y/N");
        String yes = "y";

        if (answer.toLowerCase().equals(yes)) {
            return order;
        } else {
            return order;
        }
    }
    
    public void editSuccessBanner() {
        io.print("=== The order has been successfully edited ====");
    }
    
    public void saveSuccessBanner() {
        io.print("=== The work has been successfully Saved ====");
    }
    
    public void editBanner() {
        io.print("\n=== EDIT AN ORDER ===");;
    }

    public String orderChoice() {
         io.print("Please enter the order Number: ");
         return io.readString("Please enter the order Number: ");
    }
    
     public boolean askForMode() {
        
       io.print("Please choose your mode? TRAINING/PROD");
       String answer = io.readString("enter your mode? training/prod");
       String training = "training";
        if (answer.toLowerCase().equals(training)) {
            return true ;
        } else {
           return false;
     }
     
    }
     
    public void displayModeTraining() {
        io.print("\n****TRAINING MODE****\n");
    }

    public void displayModeProduction() {
        io.print("\n****PRODUCTION MODE****\n");
    }

    public void doesNotSave() {
        io.print("Not allowed to write to files in training mode !!!");
    }

   
    
}
