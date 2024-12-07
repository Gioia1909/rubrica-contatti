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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
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
    * @brief Label per il campo di testo delle note.
    * 
    */
    @FXML
    private Label noteField;

    /**
    * @brief Label per la barra di scorrimento.
    * 
    */
    @FXML
    private ScrollBar scrollBar;

    /**
    * @brief Label per il testo di default.
    * 
    */
    @FXML
    private Label defaultText;

        
        
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

 /**
 * @brief Questo metodo permette di impostare la lista dei contatti e aggiornare la ListView con nuovi dati
 *
 * 
 * @param[in] contactList Lista osservabile dei contatti.
 */


public void setContactList(ObservableList<Contatto> contactList) {
    this.contactList = contactList;
    // Aggiorna la ListView con i nuovi dati
    myListView.setItems(contactList);
}


    /**
     * @brief Ordina la lista dei contatti in base al cognome e nome.
     *
     * Questo metodo ordina la lista `contactList` di contatti prima per
     * cognome, e in caso di parità, per nome. L'ordinamento è fatto in ordine
     * crescente, utilizzando il metodo `compareTo` definito nella classe
     * `Contatto`.
     */
    public void ordinaContatti() {
        // Utilizza il metodo sort() per ordinare direttamente la lista
        FXCollections.sort(contactList);  // Contatto deve implementare Comparable<Contatto>
    }


    /**
     * @brief Passa alla schermata dei contatti preferiti.
     * 
     * @throws IOException Se non riesce a caricare la nuova schermata.
     */
    @FXML
    private void switchToFavorite() throws IOException { 
        // Carica il file FXML della scena dei preferiti
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPreferiti.fxml"));
        Parent root = loader.load();

        // Ottieni il controller associato alla scena caricata
        MenuPreferitiController menuPreferitiController = loader.getController();

        // Passa la lista di contatti al controller dei preferiti
        menuPreferitiController.setContactList(contactList);

        // Cambia la scena
        Scene scene = favoriteButton.getScene(); // Ottieni la scena corrente
        scene.setRoot(root); // Sostituisci il root della scena corrente con il nuovo root
    }
    
    
    
    /**
     * @brief Passa alla schermata di aggiunta di un nuovo contatto.
     * 
     * Questo metodo carica la scena della finestra di aggiunta di un nuovo contatto e la visualizza in una nuova finestra.
     * Inoltre, imposta il controller della nuova finestra per consentire l'intererazione con l'interfaccia principale
     * 
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
    }
    
    
    
    /**
     * @brief Elimina il contatto selezionato dalla lista.
     * 
     * Questo metodo rimuove il contatto selezionato dalla lista dei contatti visualizzata nell'interfaccia utente.
     * Dopo aver rimosso il contatto, il metodo salva la lista aggiornata nel file per persistente i dati.

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
     * @brief Inizializza i componenti e imposta la lista di contatti
     * 
     * Questo metodo viene eseguito automaticamente quando l'interfaccia utente viene caricata. 
     * Carica la lista dei contatti utilizzando il metodo `SalvaCaricaRubrica.caricaRubrica()`.
     * Configura la `ListView` per visualizzare correttamente i contatti, mostrando solo il cognome e il nome.
     * Aggiunge un listener alla `ListView` per reagire alla selezione di un contatto, aggiornando i campi di testo 
     * con i dettagli del contatto selezionato
     * 
     * @param[in] location URL della risorsa utilizzata per risolvere i percorsi relativi.
     * @param[in] resources Risorse utilizzate per localizzare i componenti.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactList = SalvaCaricaRubrica.caricaRubrica();

        /* non penso sia necessario
        if (searchButton != null && searchButton.getScene() != null) {
            searchButton.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        }*/
        // CellFactory per la ListView
        myListView.setCellFactory(listView -> new ListCell<Contatto>() {
            @Override
            protected void updateItem(Contatto contatto, boolean empty) {       //metodo per personalizzare come viene aggiornato il contenuto di una cella in una ListView.
                super.updateItem(contatto, empty);
                if (empty || contatto == null) {
                    setText(null);
                } else {
                    // Mostra solo il cognome e il nome
                    setText(contatto.getCognome() + " " + contatto.getNome());
                }
            }
        });

        myListView.setItems(contactList);

        // listener per la selezione della ListView. Con getSelectionModel ottengo il modello di selezione della ListView, 
        //  selecredItemProperty è la proprietà che rappresenta l'elemento selezionato. A questa aggiungo un listener.
        // observable è la proprietà osservata (selectedItem), contattoPrecedente è il valore dell'elemento selezionato prima che la seleziona cambiasse
        // contattoSelezionato  il nuvoo valore dell'elemento selezionato dopo che è cambiato. 
        
        myListView.getSelectionModel().selectedItemProperty().addListener((observable, contattoPrecedente, contattoSelezionato) -> {
            if (contattoSelezionato != null) {
                // Aggiorna le label con i dati del contatto selezionato
                defaultText.setVisible(false);
                deleteButton.setVisible(true);
                nameField.setVisible(true);
                surnameField.setVisible(true);
                email1Field.setVisible(true);
                email2Field.setVisible(true);
                email3Field.setVisible(true);
                number1Field.setVisible(true);
                 number2Field.setVisible(true);
                 number3Field.setVisible(true);
                 noteField.setVisible(true);
                
                
                
                
                nameField.setText(contattoSelezionato.getNome());
                surnameField.setText(contattoSelezionato.getCognome());
                
                // Gestione dei numeri di telefono
                List<String> numeri = contattoSelezionato.getNumeri();
                number1Field.setText(numeri.size() > 0 ? numeri.get(0) : "");
                number2Field.setText(numeri.size() > 1 ? numeri.get(1) : "");   //se esiste un secondo numero scrivilo, altrimenti setta uno testo vuoto
                number3Field.setText(numeri.size() > 2 ? numeri.get(2) : "");

                // Gestione delle email
                List<String> emails = contattoSelezionato.getEmails();
                email1Field.setText(emails.size() > 0 ? emails.get(0) : "");
                email2Field.setText(emails.size() > 1 ? emails.get(1) : "");
                email3Field.setText(emails.size() > 2 ? emails.get(2) : "");
                
                noteField.setText(contattoSelezionato.getNote());

              
            }
        });


    }

}
