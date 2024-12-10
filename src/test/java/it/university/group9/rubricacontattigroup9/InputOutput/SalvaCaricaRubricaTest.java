/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.InputOutput;

import it.university.group9.rubricacontattigroup9.Contatto;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author imacpro
 */
public class SalvaCaricaRubricaTest {
    
    public SalvaCaricaRubricaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of salvaRubrica method, of class SalvaCaricaRubrica.
     */
    @Test
    public void testSalvaRubrica() {
        System.out.println("salvaRubrica");
        ObservableList<Contatto> rubrica = null;
        SalvaCaricaRubrica.salvaRubrica(rubrica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of caricaRubrica method, of class SalvaCaricaRubrica.
     */
    @Test
    public void testCaricaRubrica() {
        System.out.println("caricaRubrica");
        ObservableList<Contatto> expResult = null;
        ObservableList<Contatto> result = SalvaCaricaRubrica.caricaRubrica();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
