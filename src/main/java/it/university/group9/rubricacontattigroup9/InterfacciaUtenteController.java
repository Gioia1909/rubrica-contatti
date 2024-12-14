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

public class InterfacciaUtenteController extends VisualizzazioneContatti implements Initializable, AddressBookManager {

    // MODEL della rubrica. Gestisce le funzioni di Add, Delete, Edit, Search
    public Rubrica addressBook;

    /**
     * @name Componenti FXML della interfaccia
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
    private ToggleButton favoriteButton;

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

    // Vista della Rubrica
    @FXML
    private ListView<Contatto> contactListView;
    @FXML
    private ListView<Contatto> listViewFavorites;
    @FXML
    private Button switchToFavoriteButton;
    @FXML
    private ImageView favoriteImageView;

    public ListView<Contatto> getContactListView() throws IOException {
        if (contactListView == null) {
            throw new IOException("contactListView non valida");
        }
        return contactListView;
    }

    public void setListView(ListView<Contatto> contactListView) throws IOException {
        if (contactListView == null) {
            throw new IOException("contactListView : Argomento non valido");
        }
        this.contactListView = contactListView;
    }

    // Reattore alla lista dei contatti (Add, Edit, Delete)
    private ObservableList<Contatto> contactList;

    public ObservableList<Contatto> getContactList() throws IOException {
        if (contactList == null) {
            throw new IOException("Contact List non valida");
        }
        return contactList;
    }

    public void setContactList(ObservableList<Contatto> contactList) throws IOException {
        if (contactList == null) {
            throw new IOException("contactList: Argomento non valido");
        }
        this.contactList = contactList;
    }

    // Lista dei preferiti
    private ObservableList<Contatto> favoriteList;

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
                    favoriteImageView.setImage(null);
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

    private void updateFavoriteIcon(Contatto selectedContact) {
        if (selectedContact.isFav()) {
            favoriteImageView.setImage(new Image(getClass().getResourceAsStream("stellaPiena.png")));
        } else {
            favoriteImageView.setImage(new Image(getClass().getResourceAsStream("stellaVuota.png")));
        }
    }

    /**
     * @brief Configura la ListView per visualizzare e gestire i contatti.
     *
     *
     * @pre La ListView `contactListView` deve essere inizializzata
     * @post La ListView La ListView viene configurata per visualizzare solo
     * Cognome e Nome del contatto
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

    @FXML
    public void addAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
        Parent root = loader.load();

        InterfacciaAggiungiModificaController addController = loader.getController();
        //   addController.setUserInterfaceController(this);
        addController.initializeForAdd(addressBook, contactList);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    void addToFavoriteAction(ActionEvent event) {
        Contatto selectedContact = contactListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null && !selectedContact.isFav()) {
            selectedContact.setFav(true);

            addressBook.addToFavorites(selectedContact);
            /*Mostra un messaggio di conferma con un popup
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Contatto aggiunto ai preferiti");
            alert.setHeaderText("Il contatto è stato aggiunto ai preferiti con successo!");
            //       alert.setContentText("Il file CSV è stato salvato correttamente in: \n" + fileCSV);
            alert.showAndWait(); */

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
            //System.out.println("sto passando al gestore della rubrica il contatto " + );
            System.out.println("Ho selezionato da eliminare " + contactToRemove);

            addressBook.deleteContact(contactToRemove);
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
        //     refreshContactList();

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
        if (filteredList.isEmpty()) {
            showErrorDialog("Errore", "Nessun contatto trovato");
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
    private void exportAction(ActionEvent event) {
        try {
            SalvaCaricaRubrica.exportToCSV(contactList);
        } catch (IOException ex) {
            showErrorDialog("Errore durante l'esportazione", "Si è verificato un errore durante il salvataggio del file CSV. ");
        }
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Esportazione completata");
            alert.setHeaderText("Rubrica esportata con successo!");
            alert.setContentText("Il file CSV è stato salvato correttamente.");
            alert.showAndWait();
        } 
       
    
    
    

    /*  protected void refreshContactList() {
        ObservableList<Contatto> contactList = addressBook.getContactList(); // Assumendo che restituisca una ObservableList
        contactListView.setItems(contactList);
        //contactListView.getSelectionModel().clearSelection(); // Deseleziona qualsiasi elemento
    }*/
    public void refreshContactList() {
        ObservableList<Contatto> contactList = addressBook.getContactList(); // Assumendo che restituisca una ObservableList
        contactListView.setItems(contactList);
        //contactListView.getSelectionModel().clearSelection();
    }

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
