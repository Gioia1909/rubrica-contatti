/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.SelezionaContattiDaRubricaController;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
public class SelezionaContattiDaRubricaControllerTest {
private SelezionaContattiDaRubricaController controller; 
private TextField searchBar; 
private ListView<Contatto> contactListView;
private Button addButton; 
private Button closeButton; 
private ObservableList<Contatto> rubrica;   
private ObservableList<Contatto> rubricaPreferiti;   

    
    @BeforeEach
    public void setUp() {
              System.out.println("Setup per ogni test.");

        // Inizializzazione del controller e dei suoi componenti
        controller = new SelezionaContattiDaRubricaController();
        searchBar = new TextField();
        contactListView = new ListView<>();
        addButton = new Button();
        closeButton = new Button();

        // Inizializzazione delle liste
        rubrica = FXCollections.observableArrayList(
            new Contatto("Mario", "Rossi", Arrays.asList("123456789"), Arrays.asList("mario@example.com"), " "),
            new Contatto("Luigi", "Bianchi", Arrays.asList("987654321"), Arrays.asList("luigi@example.com"), " ")
        );
        rubricaPreferiti = FXCollections.observableArrayList();

        // Simula l'iniezione dei campi FXML
        controller.setSearchBar(searchBar);
        controller.setContactListView(contactListView);
        controller.setAddButton(addButton);
        controller.setCloseButton(closeButton);

        // Configuro le liste nel controller
        controller.setContacts(rubrica, rubricaPreferiti);
    }
    
    /*
    @AfterEach
    public void tearDown() {
    controller = null;
    searchBar = null;
    contactListView = null;
    addButton = null;
    closeButton = null;
    rubrica = null;
    rubricaPreferiti = null;
    }*/

    /**
     * Test of initialize method, of class SelezionaContattiDaRubricaController.
     */
    
    /**
     * Test of setContacts method, of class SelezionaContattiDaRubricaController.
     */
    @Test
    public void testSetContactsWithNullParameter() {
        assertThrows(NullPointerException.class,() -> controller.setContacts(null, rubricaPreferiti), "Dovrebbe lanciare un eccezione di tipo NullPointerException" );
        assertThrows(NullPointerException.class,() -> controller.setContacts(rubrica, null), "Dovrebbe lanciare un eccezione di tipo NullPointerException" );
        assertThrows(NullPointerException.class,() -> controller.setContacts(null, null), "Dovrebbe lanciare un eccezione di tipo NullPointerException" );

    }
    
    public void testSetContactsWithoutNullParameter(){
    rubricaPreferiti.add(rubrica.get(0));
    assertDoesNotThrow(() -> controller.setContacts(rubrica, rubricaPreferiti), "Non dovrebbe lanciare un eccezione di tipo NullPointerException" );
    }
    
    
}
