package com.codecool.queststore.services;

import com.codecool.queststore.entities.Wallet;
import com.codecool.queststore.repositories.PersistenceLayerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class ShopManagerTest {

    @Test
    @DisplayName("Test 'buyArtifact' method with invalid Student login")
    void testBuyArtifactWithInvalidLogin() throws PersistenceLayerException {
        ShopManager managerSpy = spy(new ShopManager());
        doReturn(null).when(managerSpy).loadArtifactById(anyInt());
        assertThrows(ServiceLayerException.class, () -> managerSpy.buyArtifact("invalid login", 1));
    }

    @Test
    @DisplayName("Test 'buyArtifact' method with invalid artifact Id")
    void testBuyArtifactWithInvalidArtifactId() throws PersistenceLayerException {
        ShopManager managerSpy = spy(new ShopManager());
        Wallet wallet = new Wallet("owner");
        doReturn(wallet).when(managerSpy).loadWalletByLogin(anyObject() ,anyString());
        assertThrows(ServiceLayerException.class, () -> managerSpy.buyArtifact("", -1));
    }
}