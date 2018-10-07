package com.codecool.queststore.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GetWalletTest {

    @Test
    @DisplayName("Test 'get' method with invalid input")
    void testGetWithInvalidIndex() {
        assertThrows(ServiceLayerException.class, () -> new GetWallet().getWallet(-1));
    }
}