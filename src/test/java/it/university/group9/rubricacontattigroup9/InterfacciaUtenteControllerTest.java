/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.InterfacciaUtenteController;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
        // Verifica che le proprietà iniziali siano corrette
        assertNotNull(instance.getListaContatti());
        assertTrue(instance.getListaContatti().isEmpty());
    }

    /**
     * Test of getListaContatti method, of class InterfacciaUtenteController.
     */
    @Test
    public void testGetListaContatti() {
        System.out.println("getListaContatti");
        InterfacciaUtenteController instance = new InterfacciaUtenteController();

        // Configura una lista di contatti
        ObservableList<Contatto> listaContatti = FXCollections.observableArrayList(
                new Contatto("Luigi", "Bianchi", Arrays.asList("987654321"), Arrays.asList("luigi.bianchi@example.com"), "Nota importante")
        );

        // Imposta la lista nel controller
        instance.setContactList(listaContatti);

        // Recupera la lista tramite il metodo getListaContatti
        List<Contatto> result = instance.getListaContatti();

        // Verifica che la lista non sia null
        assertNotNull(result);

        // Verifica che la lista contenga il contatto atteso
        assertEquals(1, result.size());
        Contatto contatto = result.get(0);
        assertEquals("Luigi", contatto.getNome());
        assertEquals("Bianchi", contatto.getCognome());
        assertEquals("987654321", contatto.getNumeri().get(0));
        assertEquals("luigi.bianchi@example.com", contatto.getEmails().get(0));
        assertEquals("Nota importante", contatto.getNote());

    }

    /**
     * Test of setContactList method, of class InterfacciaUtenteController.
     */
    @Test
    public void testSetContactList() {
        System.out.println("setContactList");

        // Crea un'istanza del controller
        InterfacciaUtenteController instance = new InterfacciaUtenteController();

        // Configura una lista di contatti
        ObservableList<Contatto> contactList = FXCollections.observableArrayList(
            new Contatto("Anna", "Verdi", Arrays.asList("123456789"), Arrays.asList("anna.verdi@example.com"), "Nota di test"),
            new Contatto("Marco", "Neri", Arrays.asList("987654321"), Arrays.asList("marco.neri@example.com"), "Un'altra nota")
        );

        // Imposta la lista tramite il metodo setContactList
            instance.setContactList(contactList);

        // Recupera la lista tramite il metodo getListaContatti
          List<Contatto> result = instance.getListaContatti();
        
          // Verifica che la lista non sia null
            assertNotNull(result);

            // Verifica che la lista contenga gli elementi attesi
             assertEquals(2, result.size());    //2 perchè ci sono due contatti 

        // Controlla le proprietà del primo contatto
            Contatto primoContatto = result.get(0);
            assertEquals("Anna", primoContatto.getNome());
            assertEquals("Verdi", primoContatto.getCognome());
            assertEquals("123456789", primoContatto.getNumeri().get(0));
            assertEquals("anna.verdi@example.com", primoContatto.getEmails().get(0));
            assertEquals("Nota di test", primoContatto.getNote());

            // Controlla le proprietà del secondo contatto
            Contatto secondoContatto = result.get(1);
            assertEquals("Marco", secondoContatto.getNome());
            assertEquals("Neri", secondoContatto.getCognome());
            assertEquals("987654321", secondoContatto.getNumeri().get(0));
            assertEquals("marco.neri@example.com", secondoContatto.getEmails().get(0));
            assertEquals("Un'altra nota", secondoContatto.getNote());
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
