/**
 * @file MenuPreferitiController.java
 * @brief Controller per la gestione del menu dei contatti preferiti.
 *
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

public class MenuPreferitiController extends VisualizzazioneContatti  implements Initializable, AddressBookManager  {

    /**
     * @brief Lista grafica dei contatti preferiti
     */
    @FXML
    private ListView<Contatto> listViewFavorites;

    /**
     * @brief Lista dei contatti preferiti
     */
    private ObservableList<Contatto> favoriteList;
    
       /**
     * @brief Lista di tutti i contatti
     */
    private ObservableList<Contatto> contactList; // Riferimento alla lista utenti

    public ListView<Contatto> getListViewFavorites() {
        return listViewFavorites;
    }

    public void setListViewFavorites(ListView<Contatto> listViewFavorites) {
        this.listViewFavorites = listViewFavorites;
    }

    public ObservableList<Contatto> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(ObservableList<Contatto> favoriteList) {
        this.favoriteList = favoriteList;
    }
    
    public ObservableList<Contatto> getContactList() {
        return contactList;
    }
    
       /**
     * @brief Imposta la lista dei contatti.
     *
     *
     * @pre La lista di contatti fornita deve essere valida e non nulla.
     * @post La lista dei contatti viene impostata correttamente nel controller.
     *
     * @param[in] contactList La lista di contatti da impostare.
     */
    public void setContactList(ObservableList<Contatto> contactList) {
        this.contactList = contactList;
    }

    @FXML
    private Button editButton, addButton, deleteButton, searchButton, secondaryButton;

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getAddFavButton() {
        return addButton;
    }

    public void setAddFavButton(Button addFavButton) {
        this.addButton = addFavButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public Button getSecondaryButton() {
        return secondaryButton;
    }

    public void setSecondaryButton(Button secondaryButton) {
        this.secondaryButton = secondaryButton;
    }
 
    @FXML
    private ImageView editImageView, deleteImageView;
    
    @FXML
    private TextField searchBar;
    @FXML
    private Label nameField, surnameField, number1Field, number2Field, number3Field, email1Field, email2Field, email3Field, noteField, defaultText;

    
    public TextField getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(TextField searchBar) {
        this.searchBar = searchBar;
    }

    public Label getNameField() {
        return nameField;
    }

    public void setNameField(Label nameField) {
        this.nameField = nameField;
    }

    public Label getSurnameField() {
        return surnameField;
    }

    public void setSurnameField(Label surnameField) {
        this.surnameField = surnameField;
    }

    public Label getNumber1Field() {
        return number1Field;
    }

    public void setNumber1Field(Label number1Field) {
        this.number1Field = number1Field;
    }

    public Label getNumber2Field() {
        return number2Field;
    }

    public void setNumber2Field(Label number2Field) {
        this.number2Field = number2Field;
    }

    public Label getNumber3Field() {
        return number3Field;
    }

    public void setNumber3Field(Label number3Field) {
        this.number3Field = number3Field;
    }

    public Label getEmail1Field() {
        return email1Field;
    }

    public void setEmail1Field(Label email1Field) {
        this.email1Field = email1Field;
    }

    public Label getEmail2Field() {
        return email2Field;
    }

    public void setEmail2Field(Label email2Field) {
        this.email2Field = email2Field;
    }

    public Label getEmail3Field() {
        return email3Field;
    }

    public void setEmail3Field(Label email3Field) {
        this.email3Field = email3Field;
    }

    public Label getNoteField() {
        return noteField;
    }

    public void setNoteField(Label noteField) {
        this.noteField = noteField;
    }

    public Label getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(Label defaultText) {
        this.defaultText = defaultText;
    }
    
     /**
     * @brief Inizializza il controller e configura la lista dei preferiti
     * nell'elemento ListView. Configura una Label per la visualizzazione delle
     * celle della lista, chiamando la funzione creaLabelContatto.
     *
     * @param[in] location URL di localizzazione del file FXML.
     * @param[in] resources Risorse per la localizzazione.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Rimuovi duplicati utilizzando un HashSet
        favoriteList = FXCollections.observableArrayList(SalvaCaricaPreferiti.caricaRubricaPreferiti());
        listViewFavorites.setItems(favoriteList);
        sortContact();
        configurePreferitiListView();

        listViewFavorites.getSelectionModel().selectedItemProperty().addListener((observable, oldContact, selectedContact) -> {
            if (selectedContact != null) {
                // Aggiorna le label con i dati del contatto selezionato
                super.updateContactDetails(selectedContact);
            }
        });
    }

    /**
     * @brief Configura la ListView per mostrare solo nome e cognome dei
     * contatti
     *
     * @pre La `ListView` deve essere inizializzata correttamente e pronta per
     * la configurazione.
     * @post La `ListView` dei contatti preferiti è configurata per visualizzare
     * i contatti con il formato "Cognome Nome".
     */
    private void configurePreferitiListView() {
        listViewFavorites.setCellFactory(listView -> new ListCell<Contatto>() {
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
    
        
      /**
     * @brief Aggiunge un contatto alla lista dei preferiti.
     *
     * @pre La lista di Contatti non deve essere vuota.
     * @pre L'elemento da aggiungere deve essere presente nella rubrica
     * principale.
     * @post L'elemento aggiunto deve essere visto nella rubrica preferiti.
     * @param[in] event L'evento che ha generato l'azione di aggiunta.
     * @throws IOException Se il caricamento del popup fallisce.
     * @throws ContattoGiaAggiuntoException Se il contatto è già presente nei
     * preferiti
     */
    @FXML
    @Override
    public void addAction(ActionEvent event) throws IOException {
        if (contactList != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelezionaContattiDaRubrica.fxml"));
            //si crea un oggetto FXMLLoader per caricare il file FXML, con getClass e Resource prendiamo il file 
            Parent root = loader.load(); //legge il contenuto del file FXML e ritorna un oggetto parent come scena

             //creo un oggetto SelezionaContattiDaRubrica per passargli qualcosa nel "costruttore" --> setContacts
            SelezionaContattiDaRubricaController popupController = loader.getController();
            //così accediamo ai metodi 
            popupController.setContacts(contactList, favoriteList); //passiamo la lista della rubrica e quella dei preferiti da aggiornare
            // Crea una nuova finestra (Stage) per il popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Seleziona Contatto");
            popupStage.setScene(new Scene(root, 336, 400)); // Imposta le dimensioni precise
            popupStage.setResizable(false); // Blocca il ridimensionamento
            popupStage.initModality(Modality.APPLICATION_MODAL); // Blocca interazioni con altre finestre
            popupStage.show();
            sortContact();
        } else {
            System.out.println("La lista dei contatti non è disponibile.");
        }

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
        int selected = listViewFavorites.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            listViewFavorites.getItems().remove(selected);
            // Salva i contatti aggiornati nel file
            SalvaCaricaPreferiti.salvaRubricaPreferiti(favoriteList);
        }

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
     */
    @FXML
    @Override
    public void editAction(ActionEvent event) throws IOException {
        Contatto selectedContact = listViewFavorites.getSelectionModel().getSelectedItem();
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
     * @param event  L'evento che ha attivato l'azione di ricerca
     * 
     * @pre La barra di ricerca contiene il testo da cercare
     * @post La ListView dei preferiti viene aggiornata con i contatti che corrispondono alla ricerca
     */
    
    @FXML
    @Override
    public void searchAction(ActionEvent event) {
        String searchQuery = searchBar.getText().toLowerCase().trim();

        if (searchQuery.isEmpty()) {
            listViewFavorites.setItems(favoriteList);
            return;
        }

        ObservableList<Contatto> filteredList = FXCollections.observableArrayList();
        for (Contatto contact : favoriteList) {
            String lowerName = contact.getName().trim().toLowerCase();
            String lowerSurname = contact.getSurname().trim().toLowerCase();
            
            if(lowerName.contains(searchQuery) || lowerSurname.contains(searchQuery)){
                filteredList.add(contact);
            }
            
            for( String number : contact.getNumbers()) {
                if (number.contains(searchQuery)) {
                    filteredList.add(contact);
                }
            }
        }

        listViewFavorites.setItems(filteredList);

        if (filteredList.isEmpty()) {
            showErrorDialog("Errore", "Nessun contatto trovato.");
        }
    }
 
      
 
    
    
     /**
     * @brief Ordina i contatti nella lista dei preferiti in base all'ordine
     * naturale dei contatti,
     *
     *
     * @post La lista dei preferiti viene ordinata.
     */
    private void sortContact() {
        FXCollections.sort(favoriteList);
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
    private void showErrorDialog(String titolo, String messaggio) {
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
        controller.setContactList(this.contactList);

        // Cambia la scena
        Scene scene = listViewFavorites.getScene();
        scene.setRoot(root);

    }
  
    
}
