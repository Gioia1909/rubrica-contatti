/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.MenuPreferitiController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
public class MenuPreferitiControllerTest {
    
    private MenuPreferitiController instance;
    private ObservableList <Contatto> contactList;
    private ObservableList <Contatto> preferitiList;
    
    
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Inizio del testing per il MenuPreferiti.");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("Test Menu Preferiti Completato");
    }
    
    @BeforeEach
    public void setUp() {
        instance = new MenuPreferitiController();
        contactList = FXCollections.observableArrayList();
        preferitiList = FXCollections.observableArrayList();
    
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of setContactList method, of class MenuPreferitiController.
     */
    @Test
    public void testSetContactList() {
        System.out.println("setContactList");
        ObservableList<Contatto> contactList = null;
        MenuPreferitiController instance = new MenuPreferitiController();
        instance.setContactList(contactList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class MenuPreferitiController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        MenuPreferitiController instance = new MenuPreferitiController();
        instance.initialize(location, resources);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}