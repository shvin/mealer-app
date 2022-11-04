package com.example.seg2105_project;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClientCreationTest {
    @Test
    public void testClientCreation1() {
        Client client = new Client();
        client.setExpiry("1222");
        assertEquals("1222", client.getExpiry());
    }

    @Test
    public void testClientCreation2() {
        Client client = new Client();
        client.setCardNumber("1234567890123456");
        assertEquals("1234567890123456", client.getCardNumber());
    }

    @Test
    public void testClientCreation3() {
        Client client = new Client();
        client.setCvv("123");
        assertEquals("123", client.getCvv());
    }
}
