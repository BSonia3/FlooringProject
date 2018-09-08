/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery;

import com.mycompany.flooringmastery.controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sonia
 */
public class App {
    public static void main(String[] args) {
        
    
    
       /* UserIO myIo = new UserIOConsoleImpl();
        FlooringView myView = new FlooringView(myIo); 
        FlooringOrderDao myDao = new FlooringOrderDaoFileImpl();
        FlooringAuditDao myAuditDao = new FlooringAuditDaoFileImpl();
        FlooringProductDao mydaoProduct = new FlooringProductDaoImpl();
        FlooringTaxDao mydaoTax = new FlooringTaxDaoImpl();
        FlooringDaoTraining mymodeDao = new FlooringDaoTrainingFileImpl();
        FlooringServiceLayer myService = new FlooringServiceLayerImpl(myDao, myAuditDao,mydaoProduct,mydaoTax,mymodeDao);
        
        FlooringController controller = new FlooringController(myService, myView);
                            controller.run();*/
                            
                            
ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller = ctx.getBean("controller", FlooringController.class);
        controller.run();
    }}
