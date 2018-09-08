/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface FlooringProductDao {

    Product getProduct(String productName)throws FlooringPersistenceException;

    public void loadAllProducts() throws FlooringPersistenceException;

    
}
