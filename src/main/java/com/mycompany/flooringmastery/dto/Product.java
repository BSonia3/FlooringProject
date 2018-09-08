/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author sonia
 */
public class Product {
 private String productType;
 private BigDecimal productlCostPerSquareFoot;
 private BigDecimal LaborCostPerSquareFoot ;

    
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductCostPerSquareFoot() {
        return productlCostPerSquareFoot;
    }

    public void setProductCostPerSquareFoot(BigDecimal productCostPerSquareFoot) {
        this.productlCostPerSquareFoot = productCostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal LaborCostPerSquareFoot) {
        this.LaborCostPerSquareFoot = LaborCostPerSquareFoot;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.productType);
        hash = 89 * hash + Objects.hashCode(this.productlCostPerSquareFoot);
        hash = 89 * hash + Objects.hashCode(this.LaborCostPerSquareFoot);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.productlCostPerSquareFoot, other.productlCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.LaborCostPerSquareFoot, other.LaborCostPerSquareFoot)) {
            return false;
        }
        return true;
    }
 
 
 
 
 
}
