/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dto.Order;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sonia
 */
public class TaxServiceTest {
    
    private TaxService tService;
    
    public TaxServiceTest() {
   
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    tService = ctx.getBean("tService", TaxService.class);

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateTax method, of class TaxService.
     */
    @Test
    public void testCalculateTax() throws Exception {
        Order order = new Order();
        order.setState("OH");
        BigDecimal tax = new BigDecimal(6.25);
        order.setTaxRate(tax);
        tService.calculateTax(order);
        assertEquals(tax,order.getTaxRate());
        
        
    }

   
    
}
