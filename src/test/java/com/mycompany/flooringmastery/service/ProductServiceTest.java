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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sonia
 */
public class ProductServiceTest {
    private ProductService pService;
    public ProductServiceTest() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    pService = ctx.getBean("pService", ProductService.class);

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
     * Test of calculateMateriel method, of class ProductService.
     */
    @Test
    public void testCalculateMateriel() throws Exception {
        
        Order order = new Order();
        order.setProductType("Tile");
        BigDecimal pPrice = new BigDecimal(3.50);
        order.setCostPerSquareFoot(pPrice);
        BigDecimal lPrice = new BigDecimal(4.15);
        order.setLaborCostPerSquareFoot(lPrice);
        
        assertEquals(pPrice,order.getCostPerSquareFoot());
        assertEquals(lPrice, order.getLaborCostPerSquareFoot());
        
    }

    
    
}
