/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
public class FlooringServiceLayerTest {
    private FlooringServiceLayer service;
    LocalDate date1 = LocalDate.parse("01022000",DateTimeFormatter.ofPattern("MMddyyyy"));
    LocalDate date2 = LocalDate.parse("05152010",DateTimeFormatter.ofPattern("MMddyyyy"));
   
    public FlooringServiceLayerTest() {
        
   /*   FlooringOrderDao dao = new FlooringOrderDaoFileImpl();
      //FlooringOrderDao daoStub =new FlooringOrderDaoStubImpl();
      FlooringAuditDao auditDao = new FlooringAuditDaoStubImpl();
      FlooringProductDao daoProduct = new FlooringProductDaoImpl();
      FlooringTaxDao daoTax = new FlooringTaxDaoImpl();
      FlooringDaoTraining modeDao =new FlooringDaoTrainingFileImpl();
      service = new FlooringServiceLayerImpl(dao,auditDao,daoProduct,daoTax,modeDao);
    }*/
    
  ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

  service = ctx.getBean("serviceLayer", FlooringServiceLayer.class);

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
     * Test of getAllOrders method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        LocalDate date = LocalDate.parse("01022044",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order = new Order();
        order.setOrderNumber(300);
        order.setCustomerName("John");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.75"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("250"));
        order.setCostPerSquareFoot(new BigDecimal("100"));
        order.setMaterialCost(new BigDecimal("50.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("500"));
        order.setLaborCost(new BigDecimal("15.00"));
        order.setTax(new BigDecimal("66"));
        order.setTotal(new BigDecimal("500"));
        order.setOrderDate((date));
        service.addOrder(order);
        
        Order order2 = new Order();
        order2.setOrderNumber(400);
        order2.setCustomerName("BOB");
        order2.setState("PA");
        order2.setTaxRate(new BigDecimal("6.75"));
        order2.setProductType("Carpet");
        order2.setArea(new BigDecimal("250"));
        order2.setCostPerSquareFoot(new BigDecimal("100"));
        order2.setMaterialCost(new BigDecimal("50.00"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("500"));
        order2.setLaborCost(new BigDecimal("15.00"));
        order2.setTax(new BigDecimal("66"));
        order2.setTotal(new BigDecimal("500"));
        order2.setOrderDate((date));
        service.addOrder(order2);
       
        
       assertEquals(2,service.getOrdersByDate(date).size() ) ;
            
   
    }

    
    /**
     * Test of addOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception{
        Order order = new Order();
        order.setOrderNumber(101);
        order.setCustomerName("PiPi");
        order.setState("PA");
        order.setProductType("Wood");
        order.setArea(new BigDecimal("200"));
        String fileDate = "12-12-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate orderDate = LocalDate.parse(fileDate, formatter);
        order.setOrderDate(orderDate);
        service.addOrder(order);
        assertNotNull(order) ;
        
    }

    /**
     * Test of getOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetOrder() throws Exception{
        Order order = new Order();
        order.setOrderNumber(156);
        order.setCustomerName("Jack");
        order.setState("PA");
        order.setProductType("Wood");
        order.setArea(new BigDecimal("200"));
        String fileDate = "05-22-2012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate orderDate = LocalDate.parse(fileDate, formatter);
        order.setOrderDate(orderDate);
        service.addOrder(order);
        
        assertNotNull(order);
       
       

    }

    /**
     * Test of removeOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        LocalDate date = LocalDate.parse("01022050",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order = new Order();
        order.setOrderNumber(39);
        order.setCustomerName("John");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.75"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("250"));
        order.setCostPerSquareFoot(new BigDecimal("100"));
        order.setMaterialCost(new BigDecimal("50.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("500"));
        order.setLaborCost(new BigDecimal("15.00"));
        order.setTax(new BigDecimal("66"));
        order.setTotal(new BigDecimal("500"));
        order.setOrderDate((date));
        service.addOrder(order);
        service.removeOrder(order);
        assertNotNull(order);
        
      
        
         }

    
    /**
     * Test of writeOrders method, of class FlooringServiceLayer.
     */
    @Test
    public void testWriteOrders() throws Exception {
    }

   

     @Test
    public void testNoDateException() throws Exception {
        Order order = new Order();
        order.setCustomerName("John");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.75"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("250"));
        order.setCostPerSquareFoot(new BigDecimal("100"));
        order.setMaterialCost(new BigDecimal("50.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("500"));
        order.setLaborCost(new BigDecimal("15.00"));
        order.setTax(new BigDecimal("66"));
        order.setTotal(new BigDecimal("500"));
        String fileDate = "12-12-1987";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate orderDate = LocalDate.parse(fileDate, formatter);
        order.setOrderDate(orderDate);
        
        
        String fileDate2 = "11-09-1999";
     
        LocalDate orderDate2 = LocalDate.parse(fileDate2, formatter);
    
        service.addOrder(order);
      
  
        boolean exceptionThrown = false;
        try {
            service.getOrdersByDate(orderDate2);
        } catch (FlooringDataValidationException e) {
            exceptionThrown = true;
        }

        Assert.assertTrue("No Date Matching Order!", exceptionThrown);
    }

   

    /**
     * Test of checkMode method, of class FlooringServiceLayer.
     */
    @Test
    public void testCheckMode() throws Exception {
    }
}

        