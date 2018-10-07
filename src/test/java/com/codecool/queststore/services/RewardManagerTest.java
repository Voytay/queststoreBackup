package com.codecool.queststore.services;

import com.codecool.queststore.entities.Quest;
import com.codecool.queststore.repositories.PersistenceLayerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class RewardManagerTest {

    @Test
    @DisplayName("Test 'grant' method with invalid Student login")
    void testGrantWithInvalidLogin() throws PersistenceLayerException {
        RewardManager managerSpy = spy(new RewardManager());
        doReturn(new Quest(1, "name", "description", 1))
                .when(managerSpy).loadQuestById(anyInt());
        assertThrows(ServiceLayerException.class, () -> managerSpy.grant("invalid login", 1));
    }

    @Test
    @DisplayName("Test 'grant' method with invalid Quest Id")
    void testGrantWithInvalidQuestId() throws PersistenceLayerException {
        RewardManager managerSpy = spy(new RewardManager());
        doReturn(null).when(managerSpy).loadStudentByLogin(anyString());
        assertThrows(ServiceLayerException.class, () -> managerSpy.grant("", -1));
    }
}