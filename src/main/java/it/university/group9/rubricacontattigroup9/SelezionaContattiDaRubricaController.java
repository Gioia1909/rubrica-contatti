
/**
 * @file SelezionaContattiDaRubricaController.java
 * @brief Controller per la selezione di contatti da una rubrica.
 * 
 * Questa classe gestisce l'interfaccia utente e la logica per selezionare contatti dalla rubrica e aggiungerli ai preferiti.
 * Fornisce funzionalità di ricerca dinamica e gestione delle liste di contatti e preferiti.
 * 
 * @author Group09
 * @date 05/12/2024
 */

package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SelezionaContattiDaRubricaController implements Initializable {
    /**
     * @brief Campo di testo per la barra di ricerca.
     * 
     * Consente all'utente di digitare il testo per filtrare i contatti visibili nella lista.
     */
    @FXML
    private TextField searchBar;
  /**
     * @brief Lista dei contatti visualizzata nella GUI.
     * 
     * Mostra i contatti filtrati o l'intera rubrica, a seconda dell'input dell'utente nella barra di ricerca.
     */
    @FXML
    private ListView<Contatto> contactListView;
    /**
     * @brief Pulsante per aggiungere un contatto ai preferiti.
     * 
     * Permette di selezionare un contatto dalla lista e aggiungerlo ai preferiti.
     */
    @FXML
    private Button addButton;
     /**
     * @brief Pulsante per chiudere la finestra attuale.
     * 
     * Torna al menu principale dei preferiti.
     */
    @FXML
    private Button closeButton;
   /**
     * @brief Pulsante per eseguire la ricerca.
     * 
     * Questo pulsante non è utilizzato poiché la ricerca è implementata dinamicamente.
     */
    @FXML
    private Button searchButton;
    
    
    
    /**
     * @brief Lista dei contatti della rubrica
     */
    
    //mantengo riferimento alla lista dei contatti della rubrica
    private ObservableList <Contatto> rubrica;
    
    /**
     * @brief Contatto selezionato
     */
    //contatto selezionato 
    private Contatto selectedContact;
    
    /**
     * @brief Lista dei contatti preferiti
     */
    //riferimento alla lista dei preferiti
    private ObservableList <Contatto> rubricaPreferiti;
    

    /**
     * @brief Metodo di inizializzazione del controller
     * @param[in] url URL di inizializzazione
     * @param[in] rb ResourceBundle di inizializzazione
     */
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html#selectionModelProperty--
        //così specifichiamo che la selezione del contatto nella lista è singolo 
        
    }
    /**
     * @brief Configura le liste dei contatti e dei preferiti.
     * 
     * @param[in] rubrica Lista completa dei contatti.
     * @param[in] rubricaPreferiti Lista dei contatti preferiti.
     */
    public void setContacts(ObservableList<Contatto> rubrica, ObservableList<Contatto> rubricaPreferiti) {
        if (rubrica == null || rubricaPreferiti == null) {
        System.err.println("Le liste rubrica o preferiti sono null!");
        return;
        }
        this.rubrica = rubrica; //prendo la rubrica normale
        this.rubricaPreferiti = rubricaPreferiti;

        //configurazione filtro per la ricerca
        //creo una lista filtrata, che inizialmente mostra tutti i contatti, grazie al predicato p->true
        FilteredList<Contatto> contattiFiltrati = new FilteredList<>(rubrica, p -> true); //la filtered list mostra solo quelli con predicato true
        //aggiungo un listener alla textProperty della barra di ricerca.  
        //Questo si attiva quando il contenuto della barra di ricerca cambia

        //observable si riferisce alla proprietà osservabile, textProperty
        //oldValue --> valore precedente di textfield
        //newValue --> valore attuale di textfield
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {

            contattiFiltrati.setPredicate(contatto -> { //set predicate accetta un input e restituisce un boolean. 
                //decidiamo se un Contatto deve essere mostrato nella lista filtrata
                //questa operazione viene fatta per tutti i contatti
                //contatto-> è una lambda expression, contatto in input

                if (newValue == null || newValue.isEmpty()) {
                    return true; //continua a visualizzare la lista della rubrica per intero 
                }
                String lowerCaseFilter = newValue.toLowerCase(); //questo la rende insensitive, mette tutto in minuscolo 

                //verifica se la stringa passata da textfield è contenuta dal nome del contatto o dal suo cognome
                return contatto.getNome().toLowerCase().contains(lowerCaseFilter) //
                || contatto.getCognome().toLowerCase().contains(lowerCaseFilter)
                
                //verifica anche per email o numeri di telefono
                || contatto.getNumeri().stream().anyMatch(num -> num.toLowerCase().contains(lowerCaseFilter))
                || contatto.getEmails().stream().anyMatch(email -> email.toLowerCase().contains(lowerCaseFilter));
            });
        });

        //colleghiamo questa lista di contatti filtrata a quella che visualizziamo in questa finestra
        contactListView.setItems(contattiFiltrati);
        
        configureContactListView();
        /*Effetto dinamico:
        Ogni volta che il filtro cambia (ad esempio, quando si digita nella barra di ricerca), 
        la FilteredList si aggiorna automaticamente.
        Poiché la ListView è collegata alla FilteredList, anche la ListView si aggiorna automaticamente.
         */
    }

    
/**
 * @brief Configura la visualizzazione della lista dei contatti nella `ListView`. Ogni cella mostra il cognome e il nome del contatto. 
 *          Se la cella è vuota o il contatto è nullo, il testo della cella viene impostato su `null`.
 * 
 * @post Ogni elemento della `ListView` mostrerà il cognome seguito dal nome del contatto.
 */
    private void configureContactListView() {
        contactListView.setCellFactory(listView -> new ListCell<Contatto>() {
            @Override
            protected void updateItem(Contatto contatto, boolean empty) {
                super.updateItem(contatto, empty);
                if (empty || contatto == null) {
                    setText(null);
                } else {
                    // Mostra solo il cognome e il nome
                    setText(contatto.getCognome() + " " + contatto.getNome());
                }
            }
        });
    }
    

    /**
     * @brief Aggiunge il contatto selezionato ai preferiti.
     * 
     * @param event Evento di tipo ActionEvent.
     * @throws IOException Se si verifica un errore durante il cambio di scena
     */
    @FXML
    private void handleAddContact(ActionEvent event) throws IOException {
        selectedContact = contactListView.getSelectionModel().getSelectedItem(); // prendo l'elemento selezionato 
        
        if (selectedContact != null) {
        if (!rubricaPreferiti.contains(selectedContact)) {
            rubricaPreferiti.add(selectedContact);
            
            System.out.println("Aggiunto ai preferiti: " + selectedContact.getNome());
            FXCollections.sort(rubricaPreferiti); // Ordina la lista preferiti
            
            // Salva il file
            SalvaCaricaPreferiti.salvaRubricaPreferiti(rubricaPreferiti);
        } else {
            showErrorDialog("Contatto Presente", selectedContact + " è già nei Preferiti.");
        }
    } else {
        System.out.println("Nessun contatto selezionato.");
    }
    handleClosePopup(event);
        
    }

    /**
     * @brief Torna al menu dei preferiti.
     * 
     * @param event Evento di tipo ActionEvent.
     * @throws IOException Se si verifica un errore durante il cambio di scena.
     */
    @FXML
    private void handleClosePopup(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
/**
 * @brief Mostra una finestra di dialogo di errore.
 * 
 * @param titolo Il titolo della finestra di dialogo.
 * @param messaggio Il messaggio di errore da visualizzare.
 * 
 * @post La finestra di dialogo di errore verrà mostrata con il titolo e il messaggio specificati.
 */
    private void showErrorDialog(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

    /*@FXML Questo non serve se la ricerca è dinamica
    private void handleSearch(ActionEvent event) {
    }*/ 
    
}