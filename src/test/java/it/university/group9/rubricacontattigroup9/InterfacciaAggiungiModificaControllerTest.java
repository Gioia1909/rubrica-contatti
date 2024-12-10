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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
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
    private InterfacciaAggiungiModificaController controller;
    private ObservableList<Contatto> contactList;
    private ObservableList<Contatto> preferitiList;
    
    
    
    @BeforeEach
    public void setUp() {
        //inizializza il controller prima di ogni test e le liste condivise
        controller = new InterfacciaAggiungiModificaController();
        contactList = FXCollections.observableArrayList();
        preferitiList = FXCollections.observableArrayList();
        
        controller.setNameField(new TextField());
        controller.setSurnameField(new TextField());
        controller.setNumber1Field(new TextField());
        controller.setNumber2Field(new TextField());
        controller.setNumber3Field(new TextField());
        controller.setEmail1Field(new TextField());
        controller.setEmail2Field(new TextField());
        controller.setEmail3Field(new TextField());
        controller.setNoteField(new TextField());
    }
    
    @AfterEach
    public void tearDown() {
    }
    /**
     * Test Inizializzazione con Lista Vuota
     */
    @Test
    public void testInitializeForAddEmptyList(){
        //metodo testato
        controller.initializeForAdd(contactList);
        
        //verifica che tutti i campi siano vuoti
        assertEquals("", controller.getNameField().getText());
        assertEquals("", controller.getSurnameField().getText());
        assertEquals("", controller.getNumber1Field().getText());
        assertEquals("", controller.getNumber2Field().getText());
        assertEquals("", controller.getNumber3Field().getText());
        assertEquals("", controller.getEmail1Field().getText());
        assertEquals("", controller.getEmail2Field().getText());
        assertEquals("", controller.getEmail3Field().getText());
        assertEquals("", controller.getNoteField().getText());
        
        //lista vuota
        assertEquals(0,contactList.size());
        }
    
    
    /**
     * Test aggiunta con valori di Input validi
     */
    @Test
    public void testAdd_ValidInput() throws Exception {
        
        controller.setNameField(new TextField("Rossella"));
        controller.setSurnameField(new TextField("Rosario"));
        controller.setNumber1Field(new TextField("082538033"));
        controller.setEmail1Field(new TextField("rossella.rosario@gmail.com"));
        controller.setNoteField(new TextField("Nota Test"));
       
        //simulazione aggiunta
        controller.initializeForAdd(contactList);
        controller.addContact(new ActionEvent());
        
        //verifica che il contatto sia stato aggiunto correttamente
        assertEquals(1,contactList.size());
        Contatto contattoAggiunto = contactList.get(0);
        assertEquals("Rossella", contattoAggiunto.getNome());
        assertEquals("Rosario", contattoAggiunto.getCognome());
        assertEquals("082538033", contattoAggiunto.getNumeri().get(0));
        assertEquals("rossella.rosario@gmail.com", contattoAggiunto.getEmails().get(0));
        assertEquals("Nota Test", contattoAggiunto.getNote());
        }   
        /**
     * Test aggiunta con campi vuoti
     */
        
        @Test
        public void testAdd_EmptyInput() throws Exception {
        
        controller.setNameField(new TextField(""));
        controller.setSurnameField(new TextField(""));
       
        //simulazione aggiunta
        controller.initializeForAdd(contactList);
        
        //verifica che il contatto sia stato aggiunto correttamente
        
        // Verifica che venga lanciata un'eccezione
        Exception exception = assertThrows(Exception.class, () -> controller.addContact(new ActionEvent()));
        assertTrue(exception.getMessage().contains("Nome non valido"));
        } 
    
        /**
        * Test aggiunta con Numero di telefono sbagliato 
        */
        
        @Test
        public void testAdd_WrongNumber() throws Exception {
        
        controller.setNameField(new TextField("Mario"));
        controller.setSurnameField(new TextField("Rossi"));
        controller.setNumber1Field(new TextField("1234"));
       
        //simulazione aggiunta
        controller.initializeForAdd(contactList);
        
        //verifica che il contatto sia stato aggiunto correttamente
        
        // Verifica che venga lanciata un'eccezione
        Exception exception = assertThrows(Exception.class, () -> controller.addContact(new ActionEvent()));
       assertTrue(exception.getMessage().contains("Numero di telefono non valido"));
        } 
        
        @Test
        public void testInitializeForAdd_EmptyNumber1() throws Exception {
        
        controller.setNameField(new TextField("Mario"));
        controller.setSurnameField(new TextField("Rossi"));
        controller.setNumber1Field(new TextField(""));
       
        //simulazione aggiunta
        controller.initializeForAdd(contactList);
        controller.addContact(new ActionEvent());
        
        //verifica che il contatto sia stato aggiunto correttamente
        
        // Verifica che venga lanciata un'eccezione
        Exception exception = assertThrows(Exception.class, () -> controller.addContact(new ActionEvent()));
        assertEquals("Numero di telefono non valido", exception.getMessage());
        } 
        
        @Test
    public void testAddContact_WithDuplicate() throws Exception {
        
        // Prepara un contatto esistente
        Contatto contatto = new Contatto("Giovanni", "Verdi", Arrays.asList("111222333"), Arrays.asList("giovanni.verdi@example.com"), "Duplicato");
        contactList.add(contatto);

        // Prepara il controller con gli stessi dati
        controller.setNameField(new TextField("Giovanni"));
        controller.setSurnameField(new TextField("Verdi"));
        controller.setNumber1Field(new TextField("111222333"));
        controller.setEmail1Field(new TextField("giovanni.verdi@example.com"));

        // Simula l'evento di aggiunta
        controller.initializeForAdd(contactList);
        controller.addContact(new ActionEvent());

        // Verifica che non ci siano duplicati nella lista
        assertEquals(1, contactList.size());
    }
        
        /**
     * Questo test dovrebbe popolare i campi perché input valido 
     */
    @Test
    public void testInitializeForEdit_ValidInput() {
        // Preparazione contatto esistente
        Contatto contatto = new Contatto("Luigi", "Bianchi", Arrays.asList("987654321"), Arrays.asList("luigi.bianchi@example.com"), "Nota importante");
        contactList.add(contatto);
        
        //metodo di test
        controller.initializeForEdit(contatto, contactList);
        
        //verifico che abbia popolato i contatti correttamente
        assertEquals("Luigi", controller.getNameField().getText());
        assertEquals("Bianchi", controller.getSurnameField().getText());
        assertEquals("987654321", controller.getNumber1Field().getText());
        assertEquals("luigi.bianchi@example.com", controller.getEmail1Field().getText());
        assertEquals("Nota importante", controller.getNoteField().getText());
    }
    @Test
    public void testInitializeForEdit_WithNullContact_ShouldThrowException() {
        // Metodo in test con contatto nullo
        assertThrows(IllegalArgumentException.class, () -> controller.initializeForEdit(null, contactList));
    }

    /**
     * Test of switchToInterfaccia method, of class InterfacciaAggiungiModificaController.
     */
    @Test
    public void testSwitchToInterfaccia() throws Exception {
        // Crea un'istanza reale del controller principale
    InterfacciaUtenteController mainController = new InterfacciaUtenteController();
    
    // Prepara una lista di contatti
    ObservableList<Contatto> contactList = FXCollections.observableArrayList(
        new Contatto("Mario", "Rossi", Arrays.asList("123456789"), Arrays.asList("mario.rossi@example.com"), "Note")
    );
    mainController.setContactList(contactList);

    // Associa il controller principale al controller da testare
    controller.setInterfacciaUtenteController(mainController);

    // Verifica lo stato iniziale della lista (non ordinata)
    contactList.add(new Contatto("Luigi", "Bianchi", Arrays.asList("987654321"), Arrays.asList(), ""));
    assertEquals("Rossi", contactList.get(0).getCognome()); // Non ancora ordinata

    // Simula l'evento di switch
    controller.switchToInterfaccia(new ActionEvent());

    // Verifica che la lista sia stata ordinata dal mainController
    assertEquals("Bianchi", contactList.get(0).getCognome()); // Ora ordinata
     assertEquals("Rossi", contactList.get(1).getCognome());
        
    }

    
    
}
