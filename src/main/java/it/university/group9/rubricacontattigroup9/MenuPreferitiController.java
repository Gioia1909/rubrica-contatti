/**
 * @file MenuPreferitiController.java
 *
 * @brief Controller per la gestione del menu dei contatti preferiti.
 *
 * @see VisualizzazioneContatti
 * @see GestioneRubrica
 * @see AddressBookManager
 *
 * @author Gruppo09
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuPreferitiController extends VisualizzazioneContatti implements Initializable, GestioneRubricaController {

    private GestioneRubrica addressBook;

    /**
    * @brief Lista dei contatti preferiti visualizzati nella GUI
    */
    @FXML
    private ListView<Contatto> favoriteListView;

    /**
    * @brief Bottone per l'aggiunta o rimozione di un contatto dai preferiti 
    */
    @FXML
    private ToggleButton favoriteButton;

    /**
    * @brief ImageView per visualizzare l'icona dei preferiti
    */
    @FXML
    private ImageView favoriteImageView;

    /**
    * @brief ImageView per visualizzare la foto del profilo del contatto.
    */
    @FXML
    private ImageView profilePicImageView;

    /**
    * @brief Bottoni per la modifica, ricerca e ritorno alla rubrica
    */
   @FXML
    private Button editButton, searchButton, secondaryButton;

    /**
    * @brief ImageView per l'icona di modifica
    */
    @FXML
    private ImageView editImageView;

    @FXML
    private TextField searchBar;

    @FXML
    private Label nameField, surnameField, number1Field, number2Field, number3Field, email1Field, email2Field, email3Field, noteField, defaultText;


    /**
    * @brief Restituisce la lista dei contatti preferiti
    */
    public ListView<Contatto> getFavoriteListView() {
        return favoriteListView;
    }

    /**
    *@brief Imposta la lista dei contatti preferiti
    */
    public void setListViewFavorites(ListView<Contatto> listViewFavorites) {
        this.favoriteListView = listViewFavorites;
    }

    public void setDefaultText(Label defaultText) {
        this.defaultText = defaultText;
    }

    private ObservableList<Contatto> favoriteList;

    public ObservableList<Contatto> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(ObservableList<Contatto> favoriteList) {
        this.favoriteList = favoriteList;
    }

    /**
     * @brief Inizializza il controller e configura la lista dei preferiti
     *
     * @param[in] location URL di localizzazione del file FXML.
     * @param[in] resources Risorse per la localizzazione.
     *
     * @pre La classe deve essere caricata con una vista FXML valida. Gli
     * elementi `listViewFavorites` devono essere correttamente inizializzati.
     * @post La lista `favoriteList` contiene contatti univoci caricati da
     * `SalvaCaricaPreferiti`. La vista `listViewFavorites` è popolata con i
     * contatti preferiti ordinati. I dettagli del contatto selezionato sono
     * aggiornati automaticamente al cambiamento della selezione.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addressBook = new Rubrica();
        favoriteList = FXCollections.observableArrayList(addressBook.getFavoriteList());
        System.out.println("Stato preferiti attuale: " + favoriteList);
        favoriteListView.setItems(favoriteList);
        System.out.println("Stato Vista Lista preferiti attuale: " + favoriteListView);
        configurePreferitiListView();

        // Aggiungi un listener per la selezione di un contatto
        favoriteListView.getSelectionModel().selectedItemProperty().addListener((observable, oldContact, selectedContact) -> {
            if (selectedContact != null) {
                // Aggiorna i dettagli del contatto
                super.updateContactDetails(selectedContact);

                // Aggiorna l'icona dei preferiti
                updateFavoriteIcon(selectedContact);
            } else {
                // Se nessun contatto è selezionato, nascondi l'icona
                favoriteImageView.setImage(null);
            }
        });

        // Aggiungi un listener per la ricerca
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            favoriteListView.setItems(addressBook.searchFavoriteContact(newValue));
        });

    }

    /**
     * @brief Aggiorna l'icona dei preferiti in base allo stato del contatto.
     *
     * @param[in] selectedContact Il contatto selezionato.
     */
    private void updateFavoriteIcon(Contatto selectedContact) {
        if (selectedContact.isFav()) {
            favoriteImageView.setImage(new Image(getClass().getResourceAsStream("stellaPiena.png")));
        } else {
            favoriteImageView.setImage(new Image(getClass().getResourceAsStream("stellaVuota.png")));
        }
    }

    /**
     * @brief Configura la ListView per mostrare solo nome e cognome dei
     * contatti
     *
     * @pre L'elemento `listViewFavorites` deve essere inizializzato e non
     * nullo.
     * @post La cell factory di `listViewFavorites` è configurata per
     * visualizzare solo cognome e nome dei contatti. Gli elementi vuoti o nulli
     * nella lista vengono rappresentati come celle vuote.
     */
    private void configurePreferitiListView() {
        favoriteListView.setCellFactory(listView -> new ListCell<Contatto>() {
            @Override
            protected void updateItem(Contatto contact, boolean empty) {
                super.updateItem(contact, empty);
                if (empty || contact == null) {
                    setText(null);
                } else {
                    // Mostra solo il cognome e il nome
                    setText(contact.getSurname() + " " + contact.getName());
                }
            }
        });

        favoriteListView.getSelectionModel().selectedItemProperty().addListener((observable, oldContact, selectedContact) -> {
            if (selectedContact != null) {
                // Aggiorna le label con i dati del contatto selezionato
                super.updateContactDetails(selectedContact);
            }
        });

    }

    /**
     * @brief Gestisce l'azione di rimozione di un contatto dalla lista preferiti.
     *
     * @param event[in] L'evento che ha attivato l'azione di eliminazione.
     *
     * @pre Deve essere selezionato un contatto dalla `ListView`.
     * @post Il contatto selezionato viene rimosso dalla `ListView` e i contatti
     * aggiornati vengono salvati nel file.
     */
    @Override
    public void deleteAction(ActionEvent event) {
        Contatto selectedContact = favoriteListView.getSelectionModel().getSelectedItem();
        System.out.println("Ho premuto cancella su " + selectedContact);

        if (selectedContact != null) {
            addressBook.removeFromFavorites(selectedContact);
            refreshFavoriteList();
            favoriteListView.getSelectionModel().clearSelection();
            super.resetContactDetails();
        }
        System.out.println("La lista contiene ora: " + favoriteList);
    }

    /**
     * @brief Gestisce l'azione di modifica di un contatto selezionato dalla
     * lista dei preferiti.
     *
     * @param event L'evento che ha attivato l'azione di modifica.
     *
     * @pre Il contatto selezionato nella `ListView` deve essere non nullo.
     * @post Viene aperta una nuova finestra di modifica con i dettagli del
     * contatto selezionato, pronti per essere modificati.
     *
     * @throws java.io.IOException
     */
    @FXML
    @Override
    public void editAction(ActionEvent event) throws IOException {
        Contatto selectedContact = favoriteListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
            Parent root = loader.load();
            InterfacciaAggiungiModificaController modificaController = loader.getController();
            modificaController.initializeForEdit((Rubrica) addressBook, selectedContact, favoriteList);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Dopo la modifica, aggiorna la lista
            refreshFavoriteList();
        }
    }

    /**
     * @brief Gestisce l'azione di ricerca dei contatti nella lista preferiti
     *
     * @param[in] event L'evento che ha attivato l'azione di ricerca
     *
     * @pre La barra di ricerca contiene il testo da cercare
     * @post La ListView dei preferiti viene aggiornata con i contatti che
     * corrispondono alla ricerca
     */
    @FXML
    @Override
    public void searchAction(ActionEvent event) {
        System.out.println("Ho premuto la Ricerca");
        String searchQuery = searchBar.getText().toLowerCase().trim();

        if (searchQuery.isEmpty()) {
            refreshFavoriteList(); // Assicurati che la lista originale sia sincronizzata
            System.out.println("Con la SearchQuery vuota la Lista preferiti: " + favoriteList.size());
            return;
        }
        ObservableList<Contatto> filteredList = FXCollections.observableArrayList(
                addressBook.searchFavoriteContact(searchQuery)
        );
        System.out.println("Lista filtrata: " + filteredList.size());

        if (filteredList.isEmpty()) {
            favoriteListView.setItems(FXCollections.observableArrayList());
            showErrorDialog("Errore", "Nessun contatto trovato.");
        } else {
            favoriteListView.setItems(filteredList);
        }
    }

    /**
     * @brief Mostra una finestra di dialogo di errore con un messaggio
     * personalizzato.
     *
     * @param[in] title Il titolo della finestra di dialogo.
     * @param[in] message Il contenuto del messaggio da visualizzare nella
     * finestra di dialogo.
     *
     * @post Viene mostrata una finestra di dialogo con il titolo e il messaggio
     * forniti.
     */
    @Override
    public void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * @brief Cambia la scena all'interfaccia utente.
     *
     * @throws IOException Se il caricamento della scena fallisce.
     */
    @FXML
    private void switchToInterface() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaUtente.fxml"));
        Parent root = loader.load();

        // Ottieni il controller della rubrica
        InterfacciaUtenteController controller = loader.getController();

        // Passa la lista di contatti al nuovo controller
        controller.setContactList(addressBook.getContactList());

        // Cambia la scena
        Scene scene = favoriteListView.getScene();
        scene.setRoot(root);

        // Risincronizza la lista dei contatti
        controller.refreshContactList();
    }

    /**
     * @brief Rinfresca la lista dei contatti preferiti.
     *
     * @post La lista `favoriteListView` è aggiornata con i contatti
     * preferiti correnti.
     */
    private void refreshFavoriteList() {
        ObservableList<Contatto> favoriteList = addressBook.getFavoriteList(); // Assumendo che restituisca una ObservableList
        favoriteListView.setItems(favoriteList);
    }

    /**
     * @brief Gestisce l'azione di rimozione dei contatti preferiti.
     *
     * @param[in] event L'evento che ha attivato l'azione di modifica del preferito.
     *
     * @pre Il contatto selezionato nella `favoriteListView` deve essere non nullo.
     * @post La lista dei contatti preferiti viene aggiornata con il nuovo stato
     * del contatto selezionato. I dati vengono sincronizzati con la rubrica e
     * i preferiti vengono salvati nei file.
     */
    @FXML
    private void toggleFavorite(ActionEvent event) {
        // Ottieni il contatto selezionato
        Contatto selectedContatto = favoriteListView.getSelectionModel().getSelectedItem();

        if (selectedContatto == null) {
            System.out.println("Nessun contatto selezionato.");
            return;
        }

        // Cambia lo stato del preferito
        if (selectedContatto.isFav()) {
            selectedContatto.setFav(false); // Aggiorna il valore booleano
            // Aggiorna sia nella lista principale che nei preferiti
            for (Contatto contatto : addressBook.getContactList()) {
                if (contatto.equals(selectedContatto)) {
                    contatto.setFav(selectedContatto.isFav()); // Sincronizza il valore isFav nella rubrica principale
                    break;
                }
                }
                SalvaCaricaRubrica.saveAddressBook(addressBook.getContactList());
                SalvaCaricaPreferiti.saveFavoritesAddressBook(favoriteList);
                addressBook.removeFromFavorites(selectedContatto); // Rimuovi dai preferiti

                System.out.println("Rimosso dai preferiti: " + selectedContatto);
            }else{
            selectedContatto.setFav(true); // Aggiorna il valore booleano
            SalvaCaricaRubrica.saveAddressBook(addressBook.getContactList());
            SalvaCaricaPreferiti.saveFavoritesAddressBook(favoriteList);
            addressBook.addToFavorites(selectedContatto); // Rimuovi dai preferiti
        }

            // Aggiorna l'icona in base al nuovo stato
            updateFavoriteIcon(selectedContatto);

        }

    }
