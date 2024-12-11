package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.InterfacciaAggiungiModificaController;
import it.university.group9.rubricacontattigroup9.InterfacciaUtenteController;
import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.Start;
/**
 * Classe di test per InterfacciaAggiungiModificaController.
 */


public class InterfacciaAggiungiModificaControllerTest {
    private InterfacciaAggiungiModificaController controller;
    private ObservableList<Contatto> contactList;

  @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

     
    @BeforeEach
    public void setUp() {
        //inizializza il controller prima di ogni test e le liste condivise
        controller = new InterfacciaAggiungiModificaController();
        contactList = FXCollections.observableArrayList();
        setupFields();
    }
        
    @AfterEach
    public void tearDown() {
        controller = null;
        contactList.clear();
    }
    
       private void setupFields() {
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
    
    
     @Test
    public void testAddContact_EmptyFields_ShouldThrowException() {
        controller.setNameField(new TextField(""));
        controller.setSurnameField(new TextField(""));

        controller.initializeForAdd(contactList);

        CampoNonValidoException exception = assertThrows(CampoNonValidoException.class, () -> controller.addAction(new ActionEvent()));
        assertEquals("Nome e cognome sono obbligatori.", exception.getMessage());
    }

    
      @Test
    public void testAddContact_InvalidPhoneNumber_ShouldThrowException() {
        controller.setNameField(new TextField("Mario"));
        controller.setSurnameField(new TextField("Rossi"));
        controller.setNumber1Field(new TextField("1234")); // Numero invalido

        controller.initializeForAdd(contactList);

        CampoNonValidoException exception = assertThrows(CampoNonValidoException.class, () -> controller.addAction(new ActionEvent()));
        assertEquals("Numero di telefono non valido.", exception.getMessage());
    }

    
    @Test
    public void testAddContact_DuplicateContact_ShouldPreventAddition() {
        Contatto contatto = new Contatto("Giovanni", "Verdi", Arrays.asList("111222333"), Arrays.asList("giovanni.verdi@example.com"), "Nota duplicato");
        contactList.add(contatto);

        controller.setNameField(new TextField("Giovanni"));
        controller.setSurnameField(new TextField("Verdi"));
        controller.setNumber1Field(new TextField("111222333"));
        controller.setEmail1Field(new TextField("giovanni.verdi@example.com"));

        controller.initializeForAdd(contactList);

        Exception exception = assertThrows(Exception.class, () -> controller.addAction(new ActionEvent()));
        assertTrue(exception.getMessage().contains("Un contatto con lo stesso nome e cognome esiste già."));
    }
    
        @Test
    public void testEditContact_ValidInput() throws Exception {
        Contatto existingContact = new Contatto("Luigi", "Bianchi", Arrays.asList("987654321"), Arrays.asList("luigi.bianchi@example.com"), "Nota Test");
        contactList.add(existingContact);

        controller.initializeForEdit(existingContact, contactList);

        // Modifica dei campi
        controller.getNameField().setText("Luigi");
        controller.getSurnameField().setText("Verdi");
        controller.getNumber1Field().setText("123456789");

        controller.editAction(new ActionEvent());

        // Verifica aggiornamento
        Contatto updatedContact = contactList.get(0);
        assertEquals("Luigi", updatedContact.getName());
        assertEquals("Verdi", updatedContact.getSurname());
        assertTrue(updatedContact.getNumbers().contains("123456789"));
    }

    @Test
    public void testInitializeForEdit_NullContact_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> controller.initializeForEdit(null, contactList));
    }
 
}
    
 
