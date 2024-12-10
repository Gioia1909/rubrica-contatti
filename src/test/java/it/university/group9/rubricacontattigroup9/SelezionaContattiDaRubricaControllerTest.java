/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.SelezionaContattiDaRubricaController;
import java.net.URL;
import java.util.ResourceBundle;
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
public class SelezionaContattiDaRubricaControllerTest {
    
    public SelezionaContattiDaRubricaControllerTest() {
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
     * Test of initialize method, of class SelezionaContattiDaRubricaController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        SelezionaContattiDaRubricaController instance = new SelezionaContattiDaRubricaController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContacts method, of class SelezionaContattiDaRubricaController.
     */
    @Test
    public void testSetContacts() {
        System.out.println("setContacts");
        ObservableList<Contatto> rubrica = null;
        ObservableList<Contatto> rubricaPreferiti = null;
        SelezionaContattiDaRubricaController instance = new SelezionaContattiDaRubricaController();
        instance.setContacts(rubrica, rubricaPreferiti);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
