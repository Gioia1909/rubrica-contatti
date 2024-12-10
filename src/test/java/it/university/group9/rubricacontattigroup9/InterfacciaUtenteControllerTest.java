/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.InterfacciaUtenteController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
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
public class InterfacciaUtenteControllerTest {
    
    public InterfacciaUtenteControllerTest() {
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
     * Test of initialize method, of class InterfacciaUtenteController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        InterfacciaUtenteController instance = new InterfacciaUtenteController();
        instance.initialize(location, resources);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaContatti method, of class InterfacciaUtenteController.
     */
    @Test
    public void testGetListaContatti() {
        System.out.println("getListaContatti");
        InterfacciaUtenteController instance = new InterfacciaUtenteController();
        List<Contatto> expResult = null;
        List<Contatto> result = instance.getListaContatti();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContactList method, of class InterfacciaUtenteController.
     */
    @Test
    public void testSetContactList() {
        System.out.println("setContactList");
        ObservableList<Contatto> contactList = null;
        InterfacciaUtenteController instance = new InterfacciaUtenteController();
        instance.setContactList(contactList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ordinaContatti method, of class InterfacciaUtenteController.
     */
    @Test
    public void testOrdinaContatti() {
        System.out.println("ordinaContatti");
        InterfacciaUtenteController instance = new InterfacciaUtenteController();
        instance.ordinaContatti();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteContact method, of class InterfacciaUtenteController.
     */
    @Test
    public void testDeleteContact() {
        System.out.println("deleteContact");
        MouseEvent event = null;
        InterfacciaUtenteController instance = new InterfacciaUtenteController();
        instance.deleteContact(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchContact method, of class InterfacciaUtenteController.
     */
    @Test
    public void testSearchContact() {
        System.out.println("searchContact");
        ActionEvent event = null;
        InterfacciaUtenteController instance = new InterfacciaUtenteController();
        instance.searchContact(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
