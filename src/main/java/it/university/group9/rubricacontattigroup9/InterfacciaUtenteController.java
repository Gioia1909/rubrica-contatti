/**
 * @file InterfacciaUtenteController.java
 * @brief Controller per gestire l'interfaccia utente dell'applicazione.
 * @date 05/12/2024
 * @author Gruppo09
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InterfacciaUtenteController extends VisualizzazioneContatti implements Initializable, AddressBookManager {
    
    public Rubrica addressBook;

    /**
     * @name Componenti FXML
     */
    ///@{
   

    @FXML
    private Button viewAddButton;

    /**
     * < Bottone per accedere all'interfaccia di aggiunta contatti.
     */
    @FXML
    private Button deleteButton;
    /**
     * < Bottone per eliminare un contatto selezionato.
     */
    @FXML
    private Button searchButton;
    /**
     * < Bottone per cercare un contatto.
     */

    @FXML
    private Button editButton;

    @FXML
    private Button favoriteButton;
    
    @FXML
    private Button addToFavorite;

    @FXML
    private ImageView editImageView;

    @FXML
    private ImageView deleteImageView;
    
 

  
    @FXML
    private TextField searchBar;
    /**
     * <Barra di testo per input di ricerca.
     */

    /**
     * @name Campi dell'anagrafica del Contatto
     */
    ///@{
    @FXML
    private Label nameField;
    @FXML
    private Label surnameField;
    @FXML
    private Label email1Field;
    @FXML
    private Label email2Field;
    @FXML
    private Label email3Field;
    @FXML
    private Label number1Field;
    @FXML
    private Label number2Field;
    @FXML
    private Label number3Field;
    @FXML
    private Label noteField;
    @FXML
    private Label defaultText;
    /**
     * <Label Testo di Default
     */
    ///@}
   
    @FXML
    private MenuItem exportButton;
    
    @FXML
    private ListView<Contatto> contactListView;

    public ListView<Contatto> getContactListView() {
        return contactListView;
    }

    public void setListView(ListView<Contatto> contactListView) {
        this.contactListView = contactListView;
    }

   
    private ObservableList<Contatto> contactList;
    
     public ObservableList<Contatto> getContactList() {
        return contactList;
    }

    public void setContactList(ObservableList<Contatto> contactList) {
        this.contactList = contactList;
    }
  
    private ObservableList<Contatto> favoriteList;
    
    
   @Override
   public void initialize(URL location, ResourceBundle resources) {
        this.addressBook = new Rubrica ();
        contactList=addressBook.getContactList();
        favoriteList = addressBook.getFavoriteList();
        // Carica i contatti e i preferiti nelle rispettive ListView
        contactListView.setItems(contactList);
  //      favoriteListView.setItems(favoriteList);
        configureContactListView();
        
        // Aggiungi un listener per la ricerca
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            contactListView.setItems(addressBook.searchContact(newValue));
        });
        
    }
     
    /**
     * @brief Configura la ListView per visualizzare e gestire i contatti.
     *
     *
     * @pre La ListView `contactListView` deve essere
     * inizializzata
     * @post La ListView La ListView viene configurata per visualizzare solo
     * Cognome e Nome del contatto
     */
    private void configureContactListView() {
        contactListView.setCellFactory(listView -> new ListCell<Contatto>() {
            @Override
            protected void updateItem(Contatto contact, boolean empty) {
                // Chiama la versione base del metodo per assicurare il corretto comportamento della cella
                super.updateItem(contact, empty);
                if (empty || contact == null) {
                    // Se vuota o nulla, non mostra alcun testo
                    setText(null);
                } else {
                    // Mostra solo il cognome e il nome
                    setText(contact.getSurname() + " " + contact.getName());
                }
            }

        });

        contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldContact, selectedContact) -> {
            if (selectedContact != null) {
                super.updateContactDetails(selectedContact);
            }
        });
    }



    @FXML
    public void addAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
        Parent root = loader.load();

        InterfacciaAggiungiModificaController addController = loader.getController();
     //   addController.setUserInterfaceController(this);
        addController.initializeForAdd(addressBook,contactList);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    
    @FXML
    void addToFavoriteAction(ActionEvent event) {
            Contatto selectedContact = contactListView.getSelectionModel().getSelectedItem();
            if(selectedContact!=null) {
               selectedContact.setFav(true);
      
    
    }
    }
    
    

    /**
     * @brief Elimina il contatto selezionato dalla lista.
     *
     * Questo metodo rimuove il contatto selezionato dalla lista dei contatti
     * visualizzata nell'interfaccia utente. Dopo aver rimosso il contatto, il
     * metodo salva la lista aggiornata nel file per persistente i dati.
     *
     * @pre Il contatto selezionato deve esistere nella lista.
     * @post Il contatto selezionato è stato rimosso dalla lista dei contatti e
     * la lista aggiornata è stata salvata nel file.
     *
     * @param[in] event Evento del mouse che ha attivato l'azione.
     */
    @FXML
    @Override
    public void deleteAction(ActionEvent event) {
        int selected = contactListView.getSelectionModel().getSelectedIndex();

        if (selected >= 0) {
            Contatto contactToRemove = contactListView.getSelectionModel().getSelectedItem();
            addressBook.deleteContact(contactToRemove);

            // Ripristina le etichette e nascondi i dettagli del contatto eliminato
           super.resetContactDetails();

  //         contactListView.setItems(contactList);
  //         contactListView.getSelectionModel().clearSelection();
        }
    }
    
  
   
    /**
     * @brief Gestisce il passaggio alla schermata di modifica di un contatto
     * selezionato.
     *
     *
     * @throws IOException Se si verifica un errore durante il caricamento della
     * scena FXML.
     *
     * @pre Il contatto selezionato deve essere presente nella ListView.
     * @post La finestra di modifica viene visualizzata con il contatto
     * selezionato caricato nel relativo controller.
     */
    @FXML
    @Override
    public void editAction(ActionEvent event) throws IOException {
        // Ottieni il contatto selezionato
        Contatto selectedContact = contactListView.getSelectionModel().getSelectedItem();
        //getSelectionModel(): Ottiene il modello di selezione associato alla lista, che gestisce la selezione degli elementi: dà accesso a tutte le informazioni sulla selezione.
        //getSelectedItem(): Ritorna l'elemento attualmente selezionato dall'utente nella ListView.     

        if (selectedContact != null) {  //Verifica se un contatto è stato selezionato. Se null, significa che l'utente non ha selezionato nulla, quindi non deve procedere.
            // Carica la scena di modifica contatto
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
            Parent root = loader.load();
            // Ottieni il controller della scena di modifica
            InterfacciaAggiungiModificaController editController = loader.getController();
            //editController: Oggetto del controller della scena di modifica contatto. Permette di interagire con i metodi e le variabili definiti in quel controller.
            //getController(): Ottiene il controller associato al file FXML appena caricato
            // Passa il contatto selezionato al controller della scena di modifica
            editController.initializeForEdit(addressBook, selectedContact, contactList);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
        }
    }
    
     /**
     * @brief Cerca un contatto nella lista in base al testo inserito nella
     * barra di ricerca.
     *
     * Questo metodo filtra la lista dei contatti in base al testo inserito
     * nella barra di ricerca textBar. La ricerca viene effettuata considerando
     * il nome, il cognome e il numero di telefono del contatto.
     *
     * @pre La lista di contatti contactList è già popolata.
     * @post La myListView mostra la lista filtrata di contatti che soddisfano i
     * criteri di ricerca.
     *
     * @param[in] event Evento associato al bottone di ricerca.
     */
    @FXML
    @Override
    public void searchAction(ActionEvent event) {
        // se la barra di ricerca è vuota, restituisci tutta la lista
        String searchQuery = searchBar.getText().toLowerCase().trim();
        if (searchQuery.isEmpty()) {
            contactListView.setItems(contactList);
            return;
        }

        ObservableList<Contatto> filteredList = addressBook.searchContact(searchQuery);
        
        if(filteredList.isEmpty()){
            showErrorDialog("Errore","Nessun contatto trovato");
        }
        
        contactListView.setItems(filteredList);
    } 
    
    
     /**
     * @brief Mostra una finestra di dialogo di errore con un messaggio
     * personalizzato,una finestra di dialogo di tipo errore con un titolo e un
     * messaggio.
     *
     * @param titolo Il titolo della finestra di dialogo.
     * @param messaggio Il contenuto del messaggio da visualizzare nella
     * finestra di dialogo.
     *
     * @post Viene mostrata una finestra di dialogo con il titolo e il messaggio
     * forniti.
     */
    @Override
    public void showErrorDialog(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }


    /**
     * @brief Passa alla schermata dei contatti preferiti.
     *
     * @throws IOException Se non riesce a caricare la nuova schermata.
     * @see MenuPreferitiController
     */
    @FXML
    protected void switchToFavorite() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPreferiti.fxml"));
        Parent root = loader.load();

        MenuPreferitiController menuPreferitiController = loader.getController();
        menuPreferitiController.setFavoriteList(favoriteList);

        Scene scene = favoriteButton.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void exportAction(ActionEvent event) throws IOException {
        SalvaCaricaRubrica.exportToCSV(contactList);
    }
    
}
    


