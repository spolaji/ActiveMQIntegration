package com.local.dev;

import javax.jms.JMSException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ProducerConsumerTest  extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @throws JMSException 
     */
    public ProducerConsumerTest( String testName ) throws JMSException {
        super( testName );
        MessageProducer producer = new MessageProducer();
        producer.createProducer("1234", "TEST");
        producer.sendMessage("Hello World");
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( ProducerConsumerTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue( true );
    }
}
