/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellojms.beans;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author ben
 */
@Singleton
@Startup
public class KickStartJMS {
    
    @Inject
    private JMSContext context;
    
    @Resource(lookup="java:/jms/queue/DLQ")
    private Queue queue;
    
    @PostConstruct
    public void initialize()
    {
        
        final Destination destination = queue;
        
        System.out.println("Application Started");
        for(int i = 1; i <= 1000; i++)
        {
            
            context.createProducer().send(destination, "Code order ID " + String.valueOf(i));
        }
    }
    
}
