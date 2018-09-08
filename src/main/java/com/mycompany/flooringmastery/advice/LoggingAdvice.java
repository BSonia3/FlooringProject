/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.advice;

import com.mycompany.flooringmastery.dao.FlooringAuditDao;
import com.mycompany.flooringmastery.dao.FlooringPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author sonia
 */
public class LoggingAdvice {
   FlooringAuditDao auditDao;
   public LoggingAdvice(FlooringAuditDao auditDao){
       this.auditDao=auditDao;
   }
   
   public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
   
}
