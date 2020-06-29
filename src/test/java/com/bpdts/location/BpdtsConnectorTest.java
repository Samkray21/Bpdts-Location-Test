package com.bpdts.location;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BpdtsConnectorTest {

    @Test
    public void testGetAllUsers() {

        BpdtsConnector bpdtsConnector = new BpdtsConnector();

        String expectedResult = "{\"id\": 5, \"first_name\": \"Rosita\", \"last_name\": \"Ferrulli\", \"email\": \"rferrulli4@unesco.org\", \"ip_address\": \"182.189.27.66\", \"latitude\": 33.5719791, \"longitude\": -84.3396421, \"city\": \"Shuishi\"}\n";
        String actualResult = bpdtsConnector.getResponse("user/5");

        assertEquals("test passed", expectedResult, actualResult);
    }
}
