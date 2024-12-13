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

     @FXML
    private ListView<Contatto> favoriteListView;
    

 
  
   public void initialize() {
        this.addressBook = new Rubrica ();
        // Carica i contatti e i preferiti nelle rispettive ListView
        contactListView.setItems(addressBook.getContactList());
        favoriteListView.setItems(addressBook.getFavoriteList());
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
    @Override
    public void addAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
        Parent root = loader.load();

        InterfacciaAggiungiModificaController addController = loader.getController();
        addController.setUserInterfaceController(this);
        addController.initializeForAdd(addressBook.getContactList());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
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

           contactListView.setItems(addressBook.getContactList());
           contactListView.getSelectionModel().clearSelection();
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
            contactListView.setItems(addressBook.getContactList());
            return;
        }

        ObservableList<Contatto> filteredList = FXCollections.observableArrayList();
        for (Contatto contact : addressBook.getContactList()) {
            if (contact.getName().toLowerCase().contains(searchQuery)
                    || contact.getSurname().toLowerCase().contains(searchQuery)
                    || contact.getNumbers().stream().anyMatch(num -> num.contains(searchQuery))) {
                filteredList.add(contact);
            }
        }
        contactListView.setItems(filteredList);
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
        Contatto selectedContact = myListView.getSelectionModel().getSelectedItem();
        //getSelectionModel(): Ottiene il modello di selezione associato alla lista, che gestisce la selezione degli elementi: dà accesso a tutte le informazioni sulla selezione.
        //getSelectedItem(): Ritorna l'elemento attualmente selezionato dall'utente nella ListView.     

        if (selectedContact != null) {  //Verifica se un contatto è stato selezionato. Se null, significa che l'utente non ha selezionato nulla, quindi non deve procedere.
            // Carica la scena di modifica contatto
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
            //FXMLLoader: Classe per caricare i file FXML.
            //getClass().getResource(...): Trova il file InterfacciaModificaContatto.fxml nel percorso delle risorse.
            Parent root = loader.load();
            //loader: Istanza del loader che si occuperà di caricare l'interfaccia specificata.
            //load(): Metodo che carica il file FXML e crea una gerarchia di nodi per l'interfaccia.
            //Parent root: Il nodo radice della nuova scena. Tutti i componenti grafici dell'interfaccia vengono aggiunti come figli di questo nodo.

            // Ottieni il controller della scena di modifica
            InterfacciaAggiungiModificaController editController = loader.getController();
            //modificaController: Oggetto del controller della scena di modifica contatto. Permette di interagire con i metodi e le variabili definiti in quel controller.
            //getController(): Ottiene il controller associato al file FXML appena caricato

            // Passa il contatto selezionato al controller della scena di modifica
            editController.initializeForEdit(selectedContact, contactList);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
        } else {
            System.out.println("Nessun contatto selezionato");
        }

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
        menuPreferitiController.setContactList(contactList);

        Scene scene = favoriteButton.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void exportAction(ActionEvent event) throws IOException {
        SalvaCaricaRubrica.exportToCSV(contactList);
    }
    
}
    


