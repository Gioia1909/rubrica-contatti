package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * @file InterfacciaUtenteController.java
 * @brief Controller per gestire l'interfaccia utente dell'applicazione.
 * @date 05/12/2024
 * @author Gruppo09
 */
public class InterfacciaUtenteController implements Initializable {

    /**
     * @brief Bottone per accedere all'interfaccia di aggiunta contatti.
     */
    @FXML
    private Button viewAddButton;

    /**
     * @brief Bottone per eliminare un contatto selezionato.
     */
    @FXML
    private Button deleteButton;

    /**
     * @brief Bottone per cercare un contatto.
     */
    @FXML
    private Button searchButton;
    
    /**
     * @brief Bottone per visualizzare i contatti preferiti.
     */
     @FXML
    private Button favoriteButton;

    /**
     * @brief Lista per visualizzare i contatti.
     */
    @FXML
    private ListView<Contatto> myListView;

    /**
     * @brief Bottone principale per operazioni varie.
     */
    @FXML
    private Button primaryButton;
    
    /**
     * @brief Barra di testo per input di ricerca.
     */
    @FXML
    private TextField textBar;

    /**
     * @brief Label per visualizzare il nome del contatto selezionato.
     */
    @FXML
    private Label nameField;

    /**
     * @brief Label per visualizzare il cognome del contatto selezionato.
     */
    @FXML
    private Label surnameField;

    /**
     * @brief Label per visualizzare il primo indirizzo email del contatto selezionato.
     */
    @FXML
    private Label email1Field;

    /**
     * @brief Label per visualizzare il secondo indirizzo email del contatto selezionato.
     */
    @FXML
    private Label email2Field;

    /**
     * @brief Label per visualizzare il terzo indirizzo email del contatto selezionato.
     */
    @FXML
    private Label email3Field;

    /**
     * @brief Label per visualizzare il primo numero di telefono del contatto selezionato.
     */
    @FXML
    private Label number1Field;

    /**
     * @brief Label per visualizzare il secondo numero di telefono del contatto selezionato.
     */
    @FXML
    private Label number2Field;
    /**
     * @brief Label per visualizzare il terzo numero di telefono del contatto selezionato.
     */
    @FXML
    private Label number3Field;

    /**
     * @brief Lista osservabile dei contatti.
     */
  private ObservableList<Contatto> contactList;
  
  
  /**
 * @brief Restituisce la lista di tutti i contatti.
 *
 * Questo metodo recupera e restituisce la lista dei contatti memorizzati nel sistema. 
 * La lista è rappresentata come una collezione di oggetti del tipo `Contatto`. 
 * Se non sono presenti contatti, viene restituita una lista vuota.
 *
 * @return Una lista di oggetti `Contatto`, che rappresenta tutti i contatti disponibili.
 */
public List<Contatto> getListaContatti() {
    return contactList;
}

   
    //metodo provvisorio per vedere la listView
    
    /**
     * @brief Elimina il contatto selezionato dalla lista.
     * 
     * @param[in] event Evento del mouse che ha attivato l'azione.
     */
    @FXML
    public void deleteContact(MouseEvent event) {
        int selezionato = myListView.getSelectionModel().getSelectedIndex();
        if (selezionato >= 0) {
        myListView.getItems().remove(selezionato);

        // Salva i contatti aggiornati nel file
        SalvaCaricaRubrica.salvaRubrica(contactList);
        }
    }

   
    /**
     * @brief Passa alla schermata dei contatti preferiti.
     * 
     * @throws IOException Se non riesce a caricare la nuova schermata.
     */
    @FXML
    private void switchToFavorite() throws IOException {
        App.setRoot("MenuPreferiti");
    }
    
    /**
     * @brief Passa alla schermata di aggiunta di un nuovo contatto.
     * 
     * @throws IOException Se non riesce a caricare la nuova schermata.
     */
      @FXML
    private void switchToAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungi.fxml"));
        Parent root = loader.load(); // Carica la scena
          // Ottieni il controller della scena di aggiunta contatto
    InterfacciaAggiungiController aggiungiController = loader.getController();
     // Passa l'istanza di InterfacciaUtenteController al controller della schermata di aggiunta
    aggiungiController.setInterfacciaUtenteController(this);
     
    // Crea una nuova scena e visualizzala
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
    
    //App.setRoot("InterfacciaAggiungi");
    }
    

    /**
     * @brief Inizializza i componenti e configura l'interfaccia utente.
     * 
     * @param[in] location URL della risorsa utilizzata per risolvere i percorsi relativi.
     * @param[in] resources Risorse utilizzate per localizzare i componenti.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactList=SalvaCaricaRubrica.caricaRubrica();
        if (searchButton != null && searchButton.getScene() != null) {
            searchButton.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        }
        
        myListView.setItems(contactList);

    }

}
