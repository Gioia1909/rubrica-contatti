/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.util.List;
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
public class ContattoValidatorTest {
    
    public ContattoValidatorTest() {
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
     * Test of isNumeroDuplicato method, of class ContattoValidator.
     */
    @Test
    public void testIsNumeroDuplicato() {
        System.out.println("isNumeroDuplicato");
        List<Contatto> contatti = null;
        String numero = "";
        boolean expResult = false;
        boolean result = ContattoValidator.isNumeroDuplicato(contatti, numero);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isContattoDuplicato method, of class ContattoValidator.
     */
    @Test
    public void testIsContattoDuplicato() {
        System.out.println("isContattoDuplicato");
        List<Contatto> contatti = null;
        String nome = "";
        String cognome = "";
        boolean expResult = false;
        boolean result = ContattoValidator.isContattoDuplicato(contatti, nome, cognome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmailDuplicata method, of class ContattoValidator.
     */
    @Test
    public void testIsEmailDuplicata() {
        System.out.println("isEmailDuplicata");
        List<Contatto> contatti = null;
        String email = "";
        boolean expResult = false;
        boolean result = ContattoValidator.isEmailDuplicata(contatti, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
