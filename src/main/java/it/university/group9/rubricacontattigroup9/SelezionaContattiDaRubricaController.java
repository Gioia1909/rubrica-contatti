/**
 * @file SelezionaContattiDaRubricaController.java
 * @brief Controller per la selezione di contatti da una rubrica.
 *
 * Questa classe gestisce l'interfaccia utente e la logica per selezionare
 * contatti dalla rubrica e aggiungerli ai preferiti. Fornisce funzionalità di
 * ricerca dinamica e gestione delle liste di contatti e preferiti.
 * @version 2.0
 * @author Group09
 * @date 11/12/2024
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
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

/**
 * @class SelezionaContattiDaRubricaController
 * @brief Controller per la selezione di contatti dalla rubrica.
 *
 * Questa classe implementa la logica per:
 * - Visualizzare e filtrare i contatti della rubrica.
 * - Aggiungere i contatti selezionati ai preferiti.
 * - Gestire l'interazione con l'interfaccia utente.
 */
public class SelezionaContattiDaRubricaController implements Initializable {

    /**
     * @brief Barra di ricerca per filtrare i contatti.
     */
    @FXML
    private TextField searchBar;

    /**
     * @brief Pulsante per avviare la ricerca manualmente.
     */
    @FXML
    private Button searchButton;

    /**
     * @brief ListView che mostra i contatti della rubrica.
     */
    @FXML
    private ListView<Contatto> contactListView;

    /**
     * @brief Pulsanti per aggiungere il contatto selezionato ai preferiti e per chiudere la finestra del popup.
     */
    @FXML
    private Button addButton, closeButton;

    /**
     * @brief Lista osservabile contenente tutti i contatti della rubrica.
     */
    private ObservableList<Contatto> addressBook;

    /**
     * @brief Lista osservabile contenente i contatti preferiti.
     */
    private ObservableList<Contatto> fAddressBook;

    /**
     * @brief Contatto attualmente selezionato.
     */
    private Contatto selectedContact;

    public TextField getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(TextField searchBar) {
        this.searchBar = searchBar;
    }

    public ListView<Contatto> getContactListView() {
        return contactListView;
    }

    public void setContactListView(ListView<Contatto> contactListView) {
        this.contactListView = contactListView;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(Button closeButton) {
        this.closeButton = closeButton;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public ObservableList<Contatto> getRubrica() {
        return addressBook;
    }

    public void setRubrica(ObservableList<Contatto> addressBook) {
        this.addressBook = addressBook;
    }

    public Contatto getSelectedContact() {
        return selectedContact;
    }

    public void setSelectedContact(Contatto selectedContact) {
        this.selectedContact = selectedContact;
    }

    public ObservableList<Contatto> getfAddressBook() {
        return fAddressBook;
    }


    public void setfAddressBook(ObservableList<Contatto> fAddressBook) {
        this.fAddressBook = fAddressBook;
    }

    /**
     * @brief Inizializza il controller.
     *
     * @param url URL per risorse FXML.
     * @param rb Bundle per la localizzazione.
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
    public void setContacts(ObservableList<Contatto> addressBook, ObservableList<Contatto> fAddressBook) {
        if (addressBook == null || fAddressBook == null) {
            throw new NullPointerException("Le liste rubrica o preferiti sono null!");
        }
        this.addressBook = addressBook; //prendo la rubrica normale
        this.fAddressBook = fAddressBook;

        //configurazione filtro per la ricerca
        //creo una lista filtrata, che inizialmente mostra tutti i contatti, grazie al predicato p->true
        FilteredList<Contatto> filteredContact = new FilteredList<>(addressBook, p -> true); //la filtered list mostra solo quelli con predicato true
        //aggiungo un listener alla textProperty della barra di ricerca.  
        //Questo si attiva quando il contenuto della barra di ricerca cambia

        //observable si riferisce alla proprietà osservabile, textProperty
        //oldValue --> valore precedente di textfield
        //newValue --> valore attuale di textfield
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredContact.setPredicate(contact -> { //set predicate accetta un input e restituisce un boolean. 
                //decidiamo se un Contatto deve essere mostrato nella lista filtrata
                //questa operazione viene fatta per tutti i contatti
                //contatto-> è una lambda expression, contatto in input

                if (newValue == null || newValue.isEmpty()) {
                    return true; //continua a visualizzare la lista della rubrica per intero 
                }
                String lowerCaseFilter = newValue.toLowerCase(); //questo la rende insensitive, mette tutto in minuscolo 

                //verifica se la stringa passata da textfield è contenuta dal nome del contatto o dal suo cognome
                return contact.getName().toLowerCase().contains(lowerCaseFilter) //
                        || contact.getSurname().toLowerCase().contains(lowerCaseFilter)
                        //verifica anche per email o numeri di telefono
                        || contact.getNumbers().stream().anyMatch(num -> num.toLowerCase().contains(lowerCaseFilter))
                        || contact.getEmails().stream().anyMatch(email -> email.toLowerCase().contains(lowerCaseFilter));
            });
        });

        //colleghiamo questa lista di contatti filtrata a quella che visualizziamo in questa finestra
        contactListView.setItems(filteredContact);

        configureContactListView();
        /*Effetto dinamico:
        Ogni volta che il filtro cambia (ad esempio, quando si digita nella barra di ricerca), 
        la FilteredList si aggiorna automaticamente.
        Poiché la ListView è collegata alla FilteredList, anche la ListView si aggiorna automaticamente.
         */
    }


    private void configureContactListView() {
        contactListView.setCellFactory(listView -> new ListCell<Contatto>() {
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
    }

    @FXML
    private void handleAddContact(ActionEvent event) throws IOException {
        selectedContact = contactListView.getSelectionModel().getSelectedItem(); // prendo l'elemento selezionato 

        if (selectedContact != null) {
            if (!fAddressBook.contains(selectedContact)) {
                fAddressBook.add(selectedContact);

                System.out.println("Aggiunto ai preferiti: " + selectedContact.getName());
                FXCollections.sort(fAddressBook); // Ordina la lista preferiti

                // Salva il file
                SalvaCaricaPreferiti.salvaRubricaPreferiti(fAddressBook);
            } else {
                showErrorDialog("Contatto Presente", selectedContact + " è già nei Preferiti.");
            }
        } else {
            System.out.println("Nessun contatto selezionato.");
        }
        handleClosePopup(event);

    }

    @FXML
    private void handleClosePopup(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
