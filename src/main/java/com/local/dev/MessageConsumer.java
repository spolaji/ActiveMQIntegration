package com.local.dev;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;

public class MessageConsumer {

	private Connection connection;
	private Session session;
	private javax.jms.MessageConsumer messageConsumer;
	
	public void createConsumer(String clientId, String queueName) throws JMSException {

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
	    messageConsumer = session.createConsumer(queue);
		
	}

	public void closeConnections() throws JMSException {
		messageConsumer.close();
		session.close();
		connection.close();
	}
	
	public void receiveMessage(long timeOut) throws JMSException {
		
		System.out.println("Receiving message...");
		
		//Send nessage
		Message message = messageConsumer.receive(timeOut);
		System.out.println("Message received");
		//message.acknowledge();
	}

}
