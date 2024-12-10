/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.InterfacciaAggiungiModificaController;
import it.university.group9.rubricacontattigroup9.InterfacciaUtenteController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
public class InterfacciaAggiungiModificaControllerTest {
    
    public InterfacciaAggiungiModificaControllerTest() {
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
     * Test of initializeForAdd method, of class InterfacciaAggiungiModificaController.
     */
    @Test
    public void testInitializeForAdd() {
        System.out.println("initializeForAdd");
        ObservableList<Contatto> rubrica = null;
        InterfacciaAggiungiModificaController instance = new InterfacciaAggiungiModificaController();
        instance.initializeForAdd(rubrica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInterfacciaUtenteController method, of class InterfacciaAggiungiModificaController.
     */
    @Test
    public void testSetInterfacciaUtenteController() {
        System.out.println("setInterfacciaUtenteController");
        InterfacciaUtenteController controller = null;
        InterfacciaAggiungiModificaController instance = new InterfacciaAggiungiModificaController();
        instance.setInterfacciaUtenteController(controller);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of switchToInterfaccia method, of class InterfacciaAggiungiModificaController.
     */
    @Test
    public void testSwitchToInterfaccia() throws Exception {
        System.out.println("switchToInterfaccia");
        ActionEvent event = null;
        InterfacciaAggiungiModificaController instance = new InterfacciaAggiungiModificaController();
        instance.switchToInterfaccia(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addContact method, of class InterfacciaAggiungiModificaController.
     */
    @Test
    public void testAddContact() throws Exception {
        System.out.println("addContact");
        ActionEvent event = null;
        InterfacciaAggiungiModificaController instance = new InterfacciaAggiungiModificaController();
        instance.addContact(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class InterfacciaAggiungiModificaController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        InterfacciaAggiungiModificaController instance = new InterfacciaAggiungiModificaController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeForEdit method, of class InterfacciaAggiungiModificaController.
     */
    @Test
    public void testInitializeForEdit() {
        System.out.println("initializeForEdit");
        Contatto contatto = null;
        ObservableList<Contatto> rubrica = null;
        InterfacciaAggiungiModificaController instance = new InterfacciaAggiungiModificaController();
        instance.initializeForEdit(contatto, rubrica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
