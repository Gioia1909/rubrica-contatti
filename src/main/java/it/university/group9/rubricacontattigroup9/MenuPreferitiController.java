/**
 * @file MenuPreferitiController.java
 * @brief Controller per la gestione del menu dei contatti preferiti.
 * @see Contatto
 * @author Gruppo09
 * @date 05/12/2024
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import java.io.IOException;
import java.net.URL;
import java.util.*;
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
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuPreferitiController extends VisualizzazioneContatti implements Initializable, AddressBookManager {

    private Rubrica addressBook;

    /**
     * @brief Lista grafica dei contatti preferiti
     */
    @FXML
    private ListView<Contatto> favoriteListView;

    public ListView<Contatto> getFavoriteListView() {
        return favoriteListView;
    }

    public void setListViewFavorites(ListView<Contatto> listViewFavorites) {
        this.favoriteListView = listViewFavorites;
    }

    @FXML
    private Button editButton, addButton, deleteButton, searchButton, secondaryButton;

    @FXML
    private ImageView editImageView, deleteImageView;

    @FXML
    private TextField searchBar;
    @FXML
    private Label nameField, surnameField, number1Field, number2Field, number3Field, email1Field, email2Field, email3Field, noteField, defaultText;

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
     * nell'elemento ListView. Configura una Label per la visualizzazione delle
     * celle della lista, chiamando la funzione creaLabelContatto.
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
        favoriteList = addressBook.getFavoriteList();
        favoriteListView.setItems(favoriteList);
        configurePreferitiListView();
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
     * @brief Gestisce l'azione di eliminazione di un contatto dalla lista
     * preferiti.
     *
     * @param event L'evento che ha attivato l'azione di eliminazione.
     *
     * @pre Deve essere selezionato un contatto dalla `ListView`.
     * @post Il contatto selezionato viene rimosso dalla `ListView` e i contatti
     * aggiornati vengono salvati nel file.
     */
    @FXML
    @Override
    public void deleteAction(ActionEvent event) {
        Contatto selectedContact = favoriteListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            addressBook.removeFromFavorites(selectedContact);
            favoriteListView.getSelectionModel().clearSelection();
        }
    }

    /**
     * @throws java.io.IOException
     * @brief Gestisce l'azione di modifica di un contatto selezionato dalla
     * lista dei preferiti.
     *
     * @param event L'evento che ha attivato l'azione di modifica.
     *
     * @pre Il contatto selezionato nella `ListView` deve essere non nullo.
     * @post Viene aperta una nuova finestra di modifica con i dettagli del
     * contatto selezionato, pronti per essere modificati.
     */
    @FXML
    @Override
    public void editAction(ActionEvent event) throws IOException {
        Contatto selectedContact = favoriteListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
            Parent root = loader.load();
            InterfacciaAggiungiModificaController modificaController = loader.getController();
            modificaController.initializeForEdit(selectedContact, favoriteList);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * @brief Gestisce l'azione di ricerca dei contatti nella lista preferiti
     *
     * @param event L'evento che ha attivato l'azione di ricerca
     *
     * @pre La barra di ricerca contiene il testo da cercare
     * @post La ListView dei preferiti viene aggiornata con i contatti che
     * corrispondono alla ricerca
     */
    @FXML
    @Override
    public void searchAction(ActionEvent event) {
        String searchQuery = searchBar.getText().toLowerCase().trim();

        if (searchQuery.isEmpty()) {
            favoriteListView.setItems(favoriteList);
            return;
        }
        ObservableList<Contatto> filteredList = addressBook.searchFavoriteContact(searchQuery);

        if (filteredList.isEmpty()) {
            showErrorDialog("Errore", "Nessun contatto trovato.");
        } else {
            favoriteListView.setItems(filteredList);
        }
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
     * @brief Cambia la scena all'interfaccia utente.
     *
     * @param[in] event L'evento che ha generato l'azione di switch.
     * @throws IOException Se il caricamento della scena fallisce.
     */
    @FXML
    private void switchToInterface() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaUtente.fxml"));
        Parent root = loader.load();
        // Ottieni il controller
        InterfacciaUtenteController controller = loader.getController();

        // Passa la lista di contatti al nuovo controller
        controller.setContactList(addressBook.getContactList());
        // Cambia la scena
        Scene scene = favoriteListView.getScene();
        scene.setRoot(root);

    }

}
