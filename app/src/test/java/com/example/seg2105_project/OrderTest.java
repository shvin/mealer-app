package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderTest {
    Order order = new Order("001", "999", "001", "888", "Pizza", true, false, false);

    @Test
    public void checkIfPending() {
        Boolean expected = true;
        Boolean actual = order.isPending();
        assertEquals(actual, expected);
    }

    @Test
    public void checkIfPending2() {
        order.setPending(false);
        Boolean expected = false;
        Boolean actual = order.isPending();
        assertEquals(actual, expected);
    }

    @Test
    public void checkIfApproved() {
        Boolean expected = false;
        Boolean actual = order.isApproved();
        assertEquals(actual, expected);
    }

    @Test
    public void checkIfApproved2() {
        order.setApproved(true);
        Boolean expected = true;
        Boolean actual = order.isApproved();
        assertEquals(actual, expected);
    }

    @Test
    public void checkIfRejected() {
        Boolean expected = false;
        Boolean actual = order.isRejected();
        assertEquals(actual, expected);
    }

    @Test
    public void checkIfRejected2() {
        order.setRejected(true);
        Boolean expected = true;
        Boolean actual = order.isRejected();
        assertEquals(actual, expected);
    }

    @Test
    public void checkMealName() {
        String expected = "Pizza";
        String actual = order.getMealName();
        assertEquals(actual, expected);
    }
}
