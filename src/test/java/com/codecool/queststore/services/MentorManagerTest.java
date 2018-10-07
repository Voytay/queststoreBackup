package com.codecool.queststore.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class MentorManagerTest {

    @Test
    @DisplayName("Test 'get' method with invalid input")
    void testGetWithInvalidLogin() throws ServiceLayerException {
        int expectedSize = 0;
        int actualSize = new MentorManager().get("invalid login").size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("Test 'edit' method with invalid input")
    void testEditWithInvalidLogin() throws ServiceLayerException {
        MentorManager managerSpy = spy(new MentorManager());
        when(managerSpy.get(anyString())).thenReturn(new ArrayList<>());
        assertThrows(ServiceLayerException.class, () ->
                managerSpy.edit("invalid login", "fname", "lname", "email",
                        "password", new HashMap<String, String>()));
    }

    @Test
    @DisplayName("Test 'remove' method with invalid input")
    void testRemoveWithInvalidLogin() throws ServiceLayerException {
        MentorManager managerSpy = spy(new MentorManager());
        when(managerSpy.get(anyString())).thenReturn(new ArrayList<>());
        assertThrows(ServiceLayerException.class, () -> managerSpy.remove("invalid login"));
    }
}