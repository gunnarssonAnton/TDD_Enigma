package com.dt042g.enigma.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.dt042g.enigma.views.EnigmaMainView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test class for MainEnigmaController
 * @author Anton Gunnarsson
 */
class MainEnigmaControllerTest {
    EnigmaMainView enigmaMainView;

    @BeforeEach
    void setUp() {
        enigmaMainView = new EnigmaMainView();
    }

    @AfterEach
    void tearDown() {}

    /**
     * tests if EnigmaMainView Is Not Null
     */
    @Test
    void testsIfEnigmaMainViewIsNotNull(){
        assertNotNull(enigmaMainView);
    }

}