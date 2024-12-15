/**
 * @file InterfacciaUtenteController.java
 * @brief Controller per gestire l'interfaccia utente dell'applicazione.
 * 
 * Questa classe controlla la gestione della GUI e fornisce funzionalità per aggiungere,
 * modificare, eliminare e cercare contatti. Inoltre permette l'aggiunta o la rimozione dei contatti dai preferiti
 * 
 * @author Gruppo09
 * 
 * @see Rubrica
 * @see VisualizzazioneContatti
 * @see GestioneRubricaController
 * 
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InterfacciaUtenteController extends VisualizzazioneContatti implements Initializable, GestioneRubricaController {


    public GestioneRubrica addressBook;

    /**
     * @brief Componenti FXML della interfaccia
     */
    ///@{
    @FXML
    private Button viewAddButton;   ///< Bottone per accedere all'interfaccia di aggiunta contatti.
    @FXML
    private Button deleteButton;   ///< Bottone per eliminare un contatto selezionato.
    @FXML
    private Button searchButton;  ///<  Bottone per cercare un contatto.
    @FXML
    private Button editButton;  ///< Bottone per modificare un contatto selezionato.
    @FXML
    private ToggleButton favoriteButton; ///< Bottone per aggiungere/rimuovere un contatto dai preferiti.
    @FXML
    private ImageView editImageView;  ///< Icona per la funzione di modifica.
    @FXML
    private ImageView deleteImageView; ///< Icona per la funzione di eliminazione.
    @FXML
    private TextField searchBar;    ///< Barra di ricerca 

    @FXML
    private MenuItem exportButton; ///< Opzione per esportare la rubrica in CSV.
    @FXML
    private ListView<Contatto> contactListView;  ///< Vista della lista principale dei contatti.
    @FXML
    private ListView<Contatto> listViewFavorites; ///< Vista della lista dei contatti preferiti.
    @FXML
    private Button switchToFavoriteButton;  ///< Bottone per andare alla rubrica dei preferiti
    @FXML
    private ImageView favoriteImageView;    ///< Icona per la funzione di aggiunta contatto ai preferiti
    @FXML
    private ImageView profilePicImageView;  ///< Icona per l'immagina di profilo
    ///@}
    
    /**
     * @brief Campi dell'anagrafica del Contatto
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
    private Label defaultText; ///< Scritta di default quando nessun contatto è selezionato
    ///@}


    
       /**
     * @brief Restituisce la vista della lista dei contatti.
     *
     *
     * @throws IOException Se la `ListView` è null o non valida.
     * @return Una `ListView` contenente i contatti
     */
    public ListView<Contatto> getContactListView() throws IOException {
        if (contactListView == null) {
            throw new IOException("contactListView non valida");
        }
        return contactListView;
    }

    
      /**
     * @brief Imposta una nuova `ListView` per visualizzare i contatti.
     *
     * @param[in] contactListView Una `ListView` di tipo `Contatto` da associare 
     * all'interfaccia utente.
     *
     * @throws IOException Se la `ListView` fornita è null o non valida.
     */
    public void setListView(ListView<Contatto> contactListView) throws IOException {
        if (contactListView == null) {
            throw new IOException("contactListView : Argomento non valido");
        }
        this.contactListView = contactListView;
    }

   /**
     * @brief Lista osservabile dei contatti.
     */
    private ObservableList<Contatto> contactList;

    
     /**
     * @brief Restituisce la lista dei contatti.
     *
     * @throws IOException Se la lista dei contatti non è valida (null).
     * @return Una ObservableList contenente i contatti dell'applicazione.
     */
    public ObservableList<Contatto> getContactList() throws IOException {
        if (contactList == null) {
            throw new IOException("Contact List non valida");
        }
        return contactList;
    }

    
    /**
     * @brief Imposta una nuova lista di contatti.
     *
     * @param[in] contactList Una ObservableList che rappresenta la nuova lista dei contatti da impostare.
     * @throws IOException Se la lista fornita è null.
     */
    public void setContactList(ObservableList<Contatto> contactList) throws IOException {
        if (contactList == null) {
            throw new IOException("contactList: Argomento non valido");
        }
        this.contactList = contactList;
    }

      /**
     * @brief Lista osservabile dei contatti preferiti.
     */
    private ObservableList<Contatto> favoriteList;

    
     /**
     * @brief Inizializza il controller con i dati della rubrica e configura la vista dei contatti.
     * 
     * Questo metodo configura le ListView per la visualizzazione dei contatti e dei preferiti, 
     * e imposta i listener per la selezione di contatti e la ricerca.
     * 
     * @param location URL della risorsa FXML.
     * @param resources Risorse localizzate per l'interfaccia utente.
     * @throws Exception Se si verifica un errore durante l'inizializzazione.
     * 
     */
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Verifica degli argomenti
            if (location == null || resources == null) {
                System.err.print("Initialize : Argomenti non validi");
            }

            this.addressBook = new Rubrica();
            contactList = addressBook.getContactList();
            favoriteList = addressBook.getFavoriteList();

            // Carica i contatti e i preferiti nelle rispettive ListView
            contactListView.setItems(contactList);
            //      favoriteListView.setItems(favoriteList);
            // Sincronizzo la vista del contatto corrente
            configureContactListView();

            // Aggiungi un listener per la selezione di un contatto
            contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldContact, selectedContact) -> {
                if (selectedContact != null) {
                    // Aggiorna i dettagli del contatto
                    super.updateContactDetails(selectedContact);
                    deleteButton.setVisible(true);
                    deleteImageView.setVisible(true);

                    // Aggiorna l'icona dei preferiti
                    updateFavoriteIcon(selectedContact);
                } else {
                    // Se nessun contatto è selezionato, nascondi l'icona
                    favoriteImageView.setVisible(false);
                    deleteButton.setVisible(false);
                    deleteImageView.setVisible(false);
                }
            });

            // Aggiungi un listener per la ricerca
            searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
                contactListView.setItems(addressBook.searchContact(newValue));
            });
        } catch (Exception ex) {
            Logger.getLogger(InterfacciaUtenteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
     /**
     * @brief Aggiorna l'icona dei preferiti per il contatto selezionato.
     *
     * Se il contatto è nei preferiti, viene visualizzata una stella piena, 
     * altrimenti una stella vuota.
     * 
     * 
     * @param selectedContact Il contatto selezionato la cui icona dei preferiti deve essere aggiornata.
     * 
     * @pre Il contatto `selectedContact` deve essere valido.
     * @post L'icona dei preferiti è aggiornata in base allo stato `isFav` del contatto.
     */
    private void updateFavoriteIcon(Contatto selectedContact) {
        if (selectedContact.isFav()) {
            favoriteImageView.setImage(new Image(getClass().getResourceAsStream("stellaPiena.png")));
        } else {
            favoriteImageView.setImage(new Image(getClass().getResourceAsStream("stellaVuota.png")));
        }
    }

    
    
     /**
     * @brief Configura la ListView per visualizzare i contatti, mostrando solo il cognome e il nome.
     * 
     * Inoltre, l'icona dei preferiti viene aggiornata in base allo stato del contatto.
     * 
     */
    private void configureContactListView() {
        contactListView.setCellFactory(listView -> new ListCell<Contatto>() {
            @Override
            protected void updateItem(Contatto contact, boolean empty) {
                super.updateItem(contact, empty);
                if (empty || contact == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(contact.getSurname() + " " + contact.getName());

                    // Configura l'icona in base allo stato `isFav`
                    if (contact.isFav()) {
                        favoriteImageView.setImage(new Image(getClass().getResourceAsStream("stellaPiena.png")));
                    } else {
                        favoriteImageView.setImage(new Image(getClass().getResourceAsStream("stellaVuota.png")));
                    }

                }
            }
        });
    }

    /**
 * @brief Gestisce l'azione di apertura della finestra per aggiungere o modificare un contatto.
 * 
 * Carica la vista di aggiunta o modifica contatto tramite il file FXML 
 * "InterfacciaAggiungiModifica.fxml" e inizializza il controller corrispondente 
 * per la modalità di aggiunta.
 * 
 * 
 * @param event L'evento che ha attivato l'azione, il click sul bottone
 * 
 * @throws IOException Se si verifica un errore durante il caricamento del file FXML.
 * 
 * @pre Il file FXML "InterfacciaAggiungiModifica.fxml" deve essere presente nel percorso 
 * di risorse.
 * @post Una nuova finestra viene aperta per aggiungere o modificare un contatto.
 */
    @FXML
    public void addAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
        Parent root = loader.load();

        InterfacciaAggiungiModificaController addController = loader.getController();
        //   addController.setUserInterfaceController(this);
        addController.initializeForAdd( addressBook, contactList);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    
    /**
 * @brief Gestisce l'azione per aggiungere un contatto ai preferiti.
 * 
 * Se un contatto è selezionato dalla ListView e non è già nei preferiti, 
 * questo metodo lo aggiunge ai preferiti.
 * 
 * @param event L'evento che ha attivato l'azione, il click sul bottone
 * 
 * @post Il contatto selezionato viene aggiunto alla lista dei preferiti.
 */
    void addToFavoriteAction(ActionEvent event) {
        Contatto selectedContact = contactListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null && !selectedContact.isFav()) {
            selectedContact.setFav(true);

            addressBook.addToFavorites(selectedContact);
        }
    }

    /**
     * @brief Gestisce l'azione di eliminazione di un contatto dalla rubrica.
     * 
     * Se un contatto è selezionato nella ListView, questo metodo lo rimuove dalla rubrica,
     * ripristina le etichette dei dettagli e aggiorna la lista dei contatti visualizzati.
     *
     * @post Il contatto selezionato è stato rimosso dalla lista dei contatti e
     * la lista aggiornata è stata salvata nel file.
     *
     * @param[in] event Evento del mouse che ha attivato l'azione, il click sul bottone
     * 
     */
@FXML
    @Override
    public void deleteAction(ActionEvent event) {
        int selected = contactListView.getSelectionModel().getSelectedIndex();

        if (selected >= 0) {
            Contatto contactToRemove = contactListView.getSelectionModel().getSelectedItem();
            //System.out.println("sto passando al gestore della rubrica il contatto " + );
            System.out.println("Ho selezionato da eliminare " + contactToRemove);

            //invio messaggio di conferma prima di eliminare
            boolean confirmed = showConfirmationDialog("Conferma Eliminazione", "Sei sicuro di voler eliminare " + contactToRemove.getName() + " " +  contactToRemove.getSurname() + "?");

            if (confirmed) {
                addressBook.deleteContact(contactToRemove);
            }

            // Ripristina le etichette e nascondi i dettagli del contatto eliminato
            super.resetContactDetails();

            refreshContactList();
            // Verifica se si sta visualizzando una lista filtrata
            String searchQuery = searchBar.getText().toLowerCase().trim();

            if (searchQuery.isEmpty()) {
                // Mostra la lista principale se non c'è filtro
                refreshContactList();
            } else {
                // Aggiorna la lista filtrata
                ObservableList<Contatto> filteredList = addressBook.searchContact(searchQuery);
                contactListView.setItems(filteredList);
            }
        }
        contactListView.getSelectionModel().clearSelection();
    }

    /**
     * @brief Gestisce il passaggio alla schermata di modifica di un contatto
     * selezionato.
     *
     *
     * @throws IOException Se si verifica un errore durante il caricamento della
     * scena FXML.
     *
     * @post La finestra di modifica viene visualizzata con il contatto
     * selezionato caricato nel relativo controller.
     * 
     * @see InterfacciaAggiungiModificaController
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
            // Permette di interagire con i metodi e le variabili definiti in quel controller.
            editController.initializeForEdit(addressBook, selectedContact, contactList);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }

    }


    /**
     * @brief Gestisce l'azione di ricerca dei contatti nella rubrica.
     *
     * Se la barra di ricerca è vuota, viene restituita la lista completa dei contatti, altrimenti chiama la searchContact
     * Se il testo di ricerca non corrisponde a nessun contatto, viene mostrato un messaggio di errore.
     *
     * @pre  La `contactListView` deve essere inizializzata e contenere la lista dei contatti.
     * 
     * @post Se il testo di ricerca è vuoto, viene mostrata la lista completa; 
     * altrimenti, viene mostrata la lista filtrata in base al termine di ricerca. 
     * Se il testo di ricerca non corrisponde a nessun contatto, viene mostrato un messaggio di errore.
     *
     * @param[in] event Evento associato al bottone di ricerca, il click
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
        if (filteredList.isEmpty()) {
            showErrorDialog("Errore", "Nessun contatto trovato");
        }

        contactListView.setItems(filteredList);
    }


    /**
     * @brief Mostra una finestra di dialogo con un messaggio di errore.
     *
     * @param title Il titolo della finestra di dialogo.
     * @param message Il messaggio da visualizzare nella finestra di dialogo.
     *
     */
    @Override
    public void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

/**
     * @brief Mostra una finestra di dialogo con un messaggio di conferma.
     *
     * @param title Il titolo della finestra di dialogo.
     * @param message Il messaggio da visualizzare nella finestra di dialogo.
     * @return true se l'utente conferma l'azione, false se annulla.
     */
    public boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);

        // Mostra la finestra di dialogo e restituisce la risposta dell'utente
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
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

    
    /**
     * @brief Esporta la lista dei contatti in un file CSV.
     * 
     *
     * @param[in] event L'evento che attiva l'azione di esportazione
     * @throws IOException Se si verifica un errore durante l'esportazione dei
     * dati nel file CSV.
     * @see SalvaCaricaRubrica
     */
    
    @FXML
    private void exportAction(ActionEvent event) throws IOException {
        SalvaCaricaRubrica.exportToCSV(contactList);
    }

    /*  protected void refreshContactList() {
        ObservableList<Contatto> contactList = addressBook.getContactList(); // Assumendo che restituisca una ObservableList
        contactListView.setItems(contactList);
        //contactListView.getSelectionModel().clearSelection(); // Deseleziona qualsiasi elemento
    }*/
    
    
 /**
 * @brief Rinfresca la visualizzazione della lista dei contatti.
 * 
 *
 */
    public void refreshContactList() {
        ObservableList<Contatto> contactList = addressBook.getContactList(); 
        contactListView.setItems(contactList);
    }

    
    /**
 * @brief Gestisce l'azione di aggiungere o rimuovere un contatto dai preferiti.
 *
 * @post Lo stato del contatto selezionato viene aggiornato, e il contatto viene aggiunto o rimosso dai preferiti.
 *       I dati aggiornati vengono salvati nei rispettivi file di rubrica e preferiti.
 * 
 * @param[in] event L'evento che attiva l'azione di toggling : il click
 */
    @FXML
    private void toggleFavorite(ActionEvent event) {
        System.out.println("Sono in toggle favorite");

        // Ottieni il contatto selezionato
        Contatto selectedContatto = contactListView.getSelectionModel().getSelectedItem();

        if (selectedContatto == null) {
            System.out.println("Nessun contatto selezionato.");
            return;
        }

        // Cambia lo stato del preferito
        if (selectedContatto.isFav()) {
            selectedContatto.setFav(false); // Aggiorna il valore booleano
            SalvaCaricaRubrica.saveAddressBook(contactList);
            SalvaCaricaPreferiti.saveFavoritesAddressBook(favoriteList);
            addressBook.removeFromFavorites(selectedContatto); // Rimuovi dai preferiti
            System.out.println("Rimosso dai preferiti: " + selectedContatto);
        } else {
            selectedContatto.setFav(true); // Aggiorna il valore booleano
            SalvaCaricaRubrica.saveAddressBook(contactList);
            SalvaCaricaPreferiti.saveFavoritesAddressBook(favoriteList);
            addressBook.addToFavorites(selectedContatto); // Aggiungi ai preferiti
            System.out.println("Aggiunto ai preferiti: " + selectedContatto);
        }

        // Aggiorna l'icona in base al nuovo stato
        updateFavoriteIcon(selectedContatto);
    }

}
