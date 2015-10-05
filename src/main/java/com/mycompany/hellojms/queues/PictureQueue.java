/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellojms.queues;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author ben
 */
@MessageDriven(name="PictureQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue="queue/DLQ"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue="javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class PictureQueue implements MessageListener {
    
    private final static Logger LOGGER = Logger.getLogger(PictureQueue.class.toString());
            
    
    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            msg = (TextMessage) message;
            LOGGER.info("----------- Processing --------------");
            //LOGGER.info("Received message from queue: " + msg.getText());
            System.out.println("Received message from queue:"  + msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(PictureQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
