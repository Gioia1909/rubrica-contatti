/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class GestioneDuplicatiTest {
     private ObservableList<Contatto> contactList;

    
    public GestioneDuplicatiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testIsAddValid_NoDuplicate() {
        // Crea contatti validi e una lista di contatti
        List<String> numeri1 = new ArrayList<>(Arrays.asList("1234567890"));
        List<String> email1 = new ArrayList<>(Arrays.asList("email1@gmail.com"));
        Contatto contatto1 = new Contatto("Mario", "Rossi", numeri1, email1, " ");
        
        List<String> numeri2 = new ArrayList<>(Arrays.asList("0987654321"));
        List<String> email2 = new ArrayList<>(Arrays.asList("email2@gmail.com"));
        Contatto contatto2 = new Contatto("Luigi", "Bianchi", numeri2, email2, " ");

        ObservableList<Contatto> contactList = FXCollections.observableArrayList(contatto1);

        // Test: Non deve essere rilevato un duplicato
        assertTrue(GestioneDuplicati.isAddValid(contatto2, contactList));
    }
    
     @Test
    public void testAddValid_WithDuplicateNumbers() {
        
        // Crea contatti validi
        List<String> numeri1 = Arrays.asList("1234567890");
        List<String> email1 = Arrays.asList("email1@example.com");
        Contatto contatto1 = new Contatto("Mario", "Rossi", numeri1, email1, " ");

        // Crea un nuovo contatto con lo stesso numero
        List<String> numeri2 = Arrays.asList("1234567890");
        List<String> email2 = Arrays.asList("email2@example.com");
        Contatto contatto2 = new Contatto("Luigi", "Bianchi", numeri2, email2, " ");
        contactList = FXCollections.observableArrayList();

        // Aggiungi il primo contatto alla lista
        contactList.add(contatto1);

        // Test: Numero duplicato, dovrebbe restituire false
        assertFalse(GestioneDuplicati.isAddValid(contatto2, contactList));
    }
    

    @Test
    public void testModifyValid_WithDuplicateNumbers() {
        // Crea due contatti iniziali
        List<String> numeri1 = Arrays.asList("1234567890");
        List<String> email1 = Arrays.asList("email1@example.com");
        Contatto contatto1 = new Contatto("Mario", "Rossi", numeri1, email1, " ");

        List<String> numeri2 = Arrays.asList("0987654321");
        List<String> email2 = Arrays.asList("email2@example.com");
        Contatto contatto2 = new Contatto("Luigi", "Bianchi", numeri2, email2, " ");

        // Aggiungi i contatti alla lista
        ObservableList<Contatto> contactList = FXCollections.observableArrayList();
        contactList.add(contatto1);
        contactList.add(contatto2);
        
        // Crea un nuovo contatto con lo stesso numero del primo contatto
        List<String> numeri3 = Arrays.asList("1234567890"); // Lo stesso numero di contatto1
        List<String> email3 = Arrays.asList("email3@example.com");
        Contatto contatto3 = new Contatto("Giovanni", "Verdi", numeri3, email3, " ");
              
        // Test: Numero duplicato, dovrebbe restituire false
        assertTrue(GestioneDuplicati.isModifyValid(contatto1, contatto3, contactList));
        
          List<String> numeri4 = Arrays.asList("1234567890"); // Lo stesso numero di contatto1

        List<String> email4 = Arrays.asList("email1@example.com");
        Contatto contatto4 = new Contatto("Mario", "Rossi", numeri2, email2, " ");
        
                assertFalse(GestioneDuplicati.isModifyValid(contatto1, contatto4, contactList));

    }


    @Test
    public void testAddValid_WithDuplicateEmails() {
        // Crea contatti validi
        List<String> numeri1 = Arrays.asList("1234567890");
        List<String> email1 = Arrays.asList("email1@example.com");
        Contatto contatto1 = new Contatto("Mario", "Rossi", numeri1, email1, " ");

        // Crea un nuovo contatto con la stessa email
        List<String> numeri2 = Arrays.asList("0987654321");
        List<String> email2 = Arrays.asList("email1@example.com");
        Contatto contatto2 = new Contatto("Luigi", "Bianchi", numeri2, email2, " ");

        // Aggiungi il primo contatto alla lista
        contactList.add(contatto1);

        // Test: Email duplicata, dovrebbe restituire false
        assertFalse(GestioneDuplicati.isAddValid(contatto2, contactList));
    }

    
    
     @Test
    public void testIsAddValid_DuplicateNameAndPhone() {
        // Crea contatti validi e una lista di contatti
        List<String> numeri1 = new ArrayList<>(Arrays.asList("1234567890"));
        List<String> email1 = new ArrayList<>(Arrays.asList("email1@example.com"));
        Contatto contatto1 = new Contatto("Mario", "Rossi", numeri1, email1, " ");

        List<String> numeri2 = new ArrayList<>(Arrays.asList("1234567890"));
        List<String> email2 = new ArrayList<>(Arrays.asList("email2@example.com"));
        Contatto contatto2 = new Contatto("Mario", "Rossi", numeri2, email2, " ");

        ObservableList<Contatto> contactList = FXCollections.observableArrayList(contatto1);

        // Test: Deve rilevare duplicato per nome e numero di telefono
        assertFalse(GestioneDuplicati.isAddValid(contatto2, contactList));
    }

    @Test
    public void testIsModifyValid_NoDuplicate() {
        // Crea contatti validi e una lista di contatti
        List<String> numeri1 = new ArrayList<>(Arrays.asList("1234567890"));
        List<String> email1 = new ArrayList<>(Arrays.asList("email1@example.com"));
        Contatto contatto1 = new Contatto("Mario", "Rossi", numeri1, email1, " ");

        List<String> numeri2 = new ArrayList<>(Arrays.asList("0987654321"));
        List<String> email2 = new ArrayList<>(Arrays.asList("email2@example.com"));
        Contatto contatto2 = new Contatto("Luigi", "Bianchi", numeri2, email2, " ");

        ObservableList<Contatto> contactList = FXCollections.observableArrayList(contatto1);

        // Test: Quando modifichiamo il contatto senza cambiare il nome, cognome, numero o email, non deve esserci duplicato
        assertTrue(GestioneDuplicati.isModifyValid(contatto1, contatto2, contactList));
    }


    @Test
    public void testIsModifyValid_DuplicateEmail() {
        // Crea contatti validi e una lista di contatti
        List<String> numeri1 = new ArrayList<>(Arrays.asList("1234567890"));
        List<String> email1 = new ArrayList<>(Arrays.asList("email1@example.com"));
        Contatto contatto1 = new Contatto("Mario", "Rossi", numeri1, email1, " ");

        List<String> numeri2 = new ArrayList<>(Arrays.asList("0987654321"));
        List<String> email2 = new ArrayList<>(Arrays.asList("email1@example.com")); // La stessa email
        Contatto contatto2 = new Contatto("Luigi", "Bianchi", numeri2, email2, " ");

        ObservableList<Contatto> contactList = FXCollections.observableArrayList(contatto1);

        // Test: Quando modifichiamo il contatto e l'email è uguale, deve essere rilevato come duplicato
        assertFalse(GestioneDuplicati.isModifyValid(contatto1, contatto2, contactList));
    }
    
   
    
    


 
    
}
    
    
    
    
    
    
    
    

