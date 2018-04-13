package com.local.dev;

import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageProducer {
	private Connection connection;
	private Session session;
	private javax.jms.MessageProducer messageProducer;
	
	public void createProducer(String clientId, String queueName) throws JMSException {
		
		//Create ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
	    
		//Create Connection
		connection = connectionFactory.createConnection();
	    
		//Set clientId
		connection.setClientID(clientId);
	    
		//Create Session
	    connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    
	    //Create Queue
	    Queue queue = session.createQueue(queueName);
	    
	    //Create MessageProducer
	    messageProducer = session.createProducer(queue);
	}
	
	public void closeConnections() throws JMSException {
		messageProducer.close();
		session.close();
		connection.close();
	}
	
	public void sendMessage(String text) throws JMSException {
		
		//Create TextMessage
		TextMessage textMessage = session.createTextMessage(text);
		System.out.println("Sending message...");
		//Send nessage
		messageProducer.send(textMessage);
	}
	
}
