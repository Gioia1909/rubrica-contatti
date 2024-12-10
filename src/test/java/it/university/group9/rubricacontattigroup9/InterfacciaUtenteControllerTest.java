/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import it.university.group9.rubricacontattigroup9.InterfacciaUtenteController;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
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
    
    private InterfacciaUtenteController instance;
    
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
        System.out.println("Setup del test: eseguito prima di ogni test");
        instance = new InterfacciaUtenteController();
    }

    @AfterEach
    public void tearDown() {
         System.out.println("Cleanup del test: eseguito dopo ogni test");
         instance = null; // Libera risorse o resetta stati
    }

    /**
     * Test of initialize method, of class InterfacciaUtenteController.
     */
    @Test
    public void testInitialize() {
        // caso 1 : la rubrica inizialmente è vuota
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        
        instance.initialize(location, resources);
        
        // Verifica che le proprietà iniziali siano corrette
        assertNotNull(instance.getListaContatti());
        assertTrue(instance.getListaContatti().isEmpty());
    }
    
    @Test
    public void testInitializeWithContact() {
        // caso 2: la lista contiene dei contatti caricati sda SalvaCaricaRubrica
        System.out.println("initialize");

        // Verifica che le proprietà iniziali siano corrette
        ObservableList<Contatto> contattiIniziali = FXCollections.observableArrayList(
                new Contatto("Emanuela", "Rossi", Arrays.asList("9876543219"), Arrays.asList("e.rossi@gmail.com"), "Nota di test"),
                new Contatto("Rossella", "Farese", Arrays.asList("1234567891"), Arrays.asList("r.farese@gmail.com"), "Nota di test")
        );

        SalvaCaricaRubrica.salvaRubrica(contattiIniziali);

        URL location = null;
        ResourceBundle resources = null;
        instance.initialize(location, resources);

        assertNotNull(instance.getListaContatti());
        assertFalse(instance.getListaContatti().isEmpty());

        ObservableList<Contatto> contattiCaricati = instance.getListaContatti();
        assertEquals(2, contattiCaricati.size()); // Dovrebbero esserci 2 contatti

        Contatto contatto1 = contattiCaricati.get(0);
        assertEquals("Emanuela", contatto1.getNome());
        assertEquals("Rossi", contatto1.getCognome());
        assertEquals("9876543219", contatto1.getNumeri().get(0));
        assertEquals("e.rossi@gmail.com", contatto1.getEmails().get(0));
        assertEquals("Nota di test", contatto1.getNote());

        Contatto contatto2 = contattiCaricati.get(1);
        assertEquals("Rossella", contatto2.getNome());
        assertEquals("Farese", contatto2.getCognome());
        assertEquals("1234567891", contatto2.getNumeri().get(0));
        assertEquals("r.farese@gmail.com", contatto2.getEmails().get(0));
        assertEquals("Nota di test", contatto2.getNote());

    }
    
    

    /**
     * Test of getListaContatti method, of class InterfacciaUtenteController.
     */
    @Test
    public void testGetListaContatti() {
        System.out.println("getListaContatti");
  
        // Configura una lista di contatti
        ObservableList<Contatto> listaContatti = FXCollections.observableArrayList(
                new Contatto("Luigi", "Bianchi", Arrays.asList("987654321"), Arrays.asList("luigi.bianchi@example.com"), "Nota importante")
        );

        // Imposta la lista nel controller
        instance.setContactList(listaContatti);

        // Recupera la lista tramite il metodo getListaContatti
        ObservableList<Contatto> result = instance.getListaContatti();

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

        // Configura una lista di contatti
        ObservableList<Contatto> contactList = FXCollections.observableArrayList(
                new Contatto("Anna", "Verdi", Arrays.asList("123456789"), Arrays.asList("anna.verdi@example.com"), "Nota di test"),
                new Contatto("Marco", "Neri", Arrays.asList("987654321"), Arrays.asList("marco.neri@example.com"), "Un'altra nota")
        );

        // Imposta la lista tramite il metodo setContactList
        instance.setContactList(contactList);

        // Recupera la lista tramite il metodo getListaContatti
        ObservableList<Contatto> result = instance.getListaContatti();

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

        // creo una lista di contatti non ordinata:
        ObservableList<Contatto> listaContatti = FXCollections.observableArrayList(
                new Contatto("Emanuela", "Rossi", Arrays.asList("9876543219"), Arrays.asList("e.rossi@gmail.com"), "Nota di test"),
                new Contatto("Rossella", "Farese", Arrays.asList("1234567891"), Arrays.asList("r.farese@gmail.com"), "Nota di test"),
                new Contatto("Luigi", "Rossi", Arrays.asList("9876543210"), Arrays.asList("luigi.bianchi@gmail.com"), "Nota di test")
        );

        instance.setContactList(listaContatti);
        instance.ordinaContatti();

     
        ObservableList<Contatto> result = instance.getListaContatti();

        assertEquals(3, result.size());
        
        
        assertEquals("Farese", result.get(0).getCognome());
        assertEquals("Rossi", result.get(1).getCognome());
        assertEquals("Rossi", result.get(2).getCognome());

        assertEquals("Rossella", result.get(0).getNome());
        assertEquals("Emanuela", result.get(1).getNome());
        assertEquals("Luigi", result.get(2).getNome());

    }

    /**
     * Test of deleteContact method, of class InterfacciaUtenteController.
     */
    @Test
    public void testDeleteContact() {
        System.out.println("deleteContact");

        ObservableList<Contatto> listaContatti = FXCollections.observableArrayList(
                new Contatto("Emanuela", "Rossi", Arrays.asList("9876543219"), Arrays.asList("e.rossi@gmail.com"), "Nota di test"),
                new Contatto("Rossella", "Farese", Arrays.asList("1234567891"), Arrays.asList("r.farese@gmail.com"), "Nota di test"),
                new Contatto("Luigi", "Rossi", Arrays.asList("9876543210"), Arrays.asList("luigi.bianchi@gmail.com"), "Nota di test")
        );
        
        instance.setContactList(listaContatti);
         assertEquals(3, instance.getListaContatti().size());
        instance.deleteContact(new ActionEvent());
        
        //verifico la lista dopo l'eliminazione 
          ObservableList<Contatto> result = instance.getListaContatti();
          assertEquals(2, result.size());
          
           // Controlla le proprietà del primo contatto
        Contatto primoContatto = result.get(0);
        assertEquals("Emanuela", primoContatto.getNome());
        assertEquals("Rossi", primoContatto.getCognome());
        assertEquals("9876543219", primoContatto.getNumeri().get(0));
        assertEquals("e.rossi@gmail.com", primoContatto.getEmails().get(0));
        assertEquals("Nota di test", primoContatto.getNote());

        // Controlla le proprietà del secondo contatto
        Contatto secondoContatto = result.get(1);
        assertEquals("Luigi", secondoContatto.getNome());
        assertEquals("Rossi", secondoContatto.getCognome());
        assertEquals("9876543210", secondoContatto.getNumeri().get(0));
        assertEquals("luigi.bianchi@gmail.com", secondoContatto.getEmails().get(0));
        assertEquals("Nota di test", secondoContatto.getNote());  
     
   
    }

    /**
     * Test of searchContact method, of class InterfacciaUtenteController.
     */
    @Test
    public void testSearchContact() {
        System.out.println("searchContact");
        ActionEvent event = null;
        
           ObservableList<Contatto> listaContatti = FXCollections.observableArrayList(
            new Contatto("Emanuela", "Rossi", Arrays.asList("9876543219"), Arrays.asList("e.rossi@gmail.com"), "Nota di test"),
            new Contatto("Rossella", "Farese", Arrays.asList("1234567891"), Arrays.asList("r.farese@gmail.com"), "Nota di test"),
            new Contatto("Luigi", "Rossi", Arrays.asList("9876543210"), Arrays.asList("luigi.bianchi@gmail.com"), "Nota di test")
    );
           
            instance.setContactList(listaContatti);
            instance.getListView().setItems(listaContatti);
            Contatto contattoSelezionato=listaContatti.get(0);
            
            TextField searchBar = new TextField();
            instance.setSearchBar(searchBar);
            searchBar.setText("Emanuela");
            instance.searchContact(new ActionEvent());
            
            //Verifica che la lista filtrata contenga solo il contatto corrispondente 
   
           ObservableList<Contatto> listaFiltrata = instance.getListView().getItems();
           assertEquals(1,listaFiltrata.size(),"La lista filtrata dovrebbe contenere un solo elemento");
           assertTrue(listaFiltrata.contains(contattoSelezionato));
    

    }

}
