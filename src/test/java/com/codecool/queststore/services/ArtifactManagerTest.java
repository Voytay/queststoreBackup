package com.codecool.queststore.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class ArtifactManagerTest {

    @Test
    @DisplayName("Test 'get' method with invalid input")
    void testGetWithInvalidIndex() throws ServiceLayerException {
        int expectedSize = 0;
        int actualSize = new ArtifactManager().get(-1).size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("Test 'edit' method with invalid input")
    void testEditWithInvalidIndex() throws ServiceLayerException {
        ArtifactManager managerSpy = spy(new ArtifactManager());
        when(managerSpy.get(-1)).thenReturn(new ArrayList<>());
        assertThrows(ServiceLayerException.class, () ->
                managerSpy.edit(-1, "name", "description", 1));
    }

    @Test
    @DisplayName("Test 'delete' method with invalid input")
    void testDeleteWithInvalidIndex() throws ServiceLayerException {
        ArtifactManager managerSpy = spy(new ArtifactManager());
        when(managerSpy.get(-1)).thenReturn(new ArrayList<>());
        assertThrows(ServiceLayerException.class, () -> managerSpy.delete(-1));
    }
}