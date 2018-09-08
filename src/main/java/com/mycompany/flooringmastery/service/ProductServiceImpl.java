/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringPersistenceException;
import com.mycompany.flooringmastery.dao.FlooringProductDao;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;

/**
 *
 * @author sonia
 */
public class ProductServiceImpl implements ProductService {
    private FlooringProductDao daoProduct;
    public ProductServiceImpl( FlooringProductDao daoProduct) {
       
       this.daoProduct = daoProduct;
       
    }
    
    
    @Override
    public void calculateMateriel(Order myOrder) throws FlooringDataValidationException,FlooringPersistenceException{
        Product chosenProduct  = daoProduct.getProduct(myOrder.getProductType());
        if (chosenProduct == null) {
            throw new FlooringDataValidationException("ERROR: We do not sell that "
                    + "product.");
        }else{
        myOrder.setProductType(chosenProduct.getProductType());
        myOrder.setCostPerSquareFoot(chosenProduct.getProductCostPerSquareFoot());
        myOrder.setLaborCostPerSquareFoot(chosenProduct.getLaborCostPerSquareFoot());
        }
        
    }
    
}
