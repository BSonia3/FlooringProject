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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sonia
 */
public class FlooringOrderDaoTest {
    
    private FlooringOrderDao dao; // = new FlooringOrderDaoFileImpl();
    
    
    public FlooringOrderDaoTest() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    dao = ctx.getBean("flooringOrderDao", FlooringOrderDao.class);

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        //LocalDate date = LocalDate.parse("01022077",DateTimeFormatter.ofPattern("MMddyyyy"));
        List<Order>ordersList = dao.getListOrder();
        for (Order order : ordersList) {
            dao.removeOrder(order);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testAddGetOrder() throws Exception{
        LocalDate date = LocalDate.parse("01022055",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order = new Order();
        order.setOrderNumber(1);
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

        dao.addOrder(order);

        Order fromDao = dao.getOrder(order);

        assertEquals(order, fromDao);
    }

    /**
     * Test of getAllOrders method, of class FlooringOrderDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        LocalDate date = LocalDate.parse("02022045",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order = new Order();
        order.setOrderNumber(1);
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
        dao.addOrder(order);
        
        Order order2 = new Order();
        order2.setOrderNumber(2);
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
        dao.addOrder(order2);
       
        
       assertEquals(2,dao.getAllOrders(date).size() ) ;
    }

    /**
     * Test of getOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testGetOrder() {
    }

    /**
     * Test of removeOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        
        LocalDate date = LocalDate.parse("01022036",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order = new Order();
        order.setOrderNumber(177);
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
        order.setOrderDate(date);
        dao.addOrder(order);
        
        Order order2 = new Order();
        order2.setOrderNumber(255);
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
        order2.setOrderDate(date);
        dao.addOrder(order2);
         
        assertEquals(2, dao.getAllOrders(date).size());
        
        dao.removeOrder(order);
        assertEquals(1, dao.getAllOrders(date).size());
        assertNotNull(dao.getOrder(order));

        dao.removeOrder(order2);
        assertEquals(0, dao.getAllOrders(date).size());
        assertNotNull(dao.getOrder(order));
        
        
        
    }

    

  public void saveWork() throws Exception{
        List<Order> orders= new ArrayList<>();
        LocalDate date = LocalDate.parse("02022045",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order = new Order();
        order.setOrderNumber(99);
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
        orders.add(order);
        dao.saveCurrentWork();
        
        Order order2 = new Order();
        order2.setOrderNumber(98);
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
        orders.add(order2);
        dao.saveCurrentWork();
       
        
       assertEquals(2,dao.getAllOrders(date).size() ) ;
   
    
  }
   
    
}
