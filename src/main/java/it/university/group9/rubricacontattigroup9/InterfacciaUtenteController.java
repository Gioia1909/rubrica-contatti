/**
 * @file InterfacciaUtenteController.java
 * @brief Controller per gestire l'interfaccia utente dell'applicazione.
 * @date 05/12/2024
 * @author Gruppo09
 */
package it.university.group9.rubricacontattigroup9;

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


public class InterfacciaUtenteController implements Initializable {

    /**
     * @name Componenti FXML
     */
    ///@{
    
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

    
    private ImageView editImageView;

    
    @FXML
    private ImageView deleteImageView;

   
    /**
     * <Bottone per visualizzare i contatti preferiti.
     */
    @FXML
    private ListView<Contatto> myListView;

   

    /**
     * <Bottone principale per operazioni varie.
     */
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
    private ObservableList<Contatto> contactList;

    
    
    public Button getViewAddButton() {
        return viewAddButton;
    }

    public void setViewAddButton(Button viewAddButton) {
        this.viewAddButton = viewAddButton;
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

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getFavoriteButton() {
        return favoriteButton;
    }

    public void setFavoriteButton(Button favoriteButton) {
        this.favoriteButton = favoriteButton;
    }

     public ListView<Contatto> getListView() {
        return myListView;
    }

    public void setListView(ListView<Contatto> myListView) {
        this.myListView = myListView;
    }

    
    public ImageView getDeleteImageView() {
        return deleteImageView;
    }

    public void setDeleteImageView(ImageView deleteImageView) {
        this.deleteImageView = deleteImageView;
    }

    public ListView<Contatto> getMyListView() {
        return myListView;
    }

    public void setMyListView(ListView<Contatto> myListView) {
        this.myListView = myListView;
    }

    
    
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

    
    public ObservableList<Contatto> getContactList() {
        return contactList;
    }
     /**
     * @brief Questo metodo permette di impostare la lista dei contatti e
     * aggiornare la ListView con nuovi dati
     *
     *
     * @param[in] contactList Lista osservabile dei contatti.
     * @post La lista di contatti è stata aggiornata nel controller e la
     * ListView è stata aggiornata con i nuovi contatti.
     *
     */

    public void setContactList(ObservableList<Contatto> contactList) {
        this.contactList = contactList;
        myListView.setItems(contactList);
    }
    
    /**
     * @brief Inizializza i componenti e imposta la lista di contatti
     *
     * Questo metodo viene eseguito automaticamente quando l'interfaccia utente
     * viene caricata. Carica la lista dei contatti utilizzando il metodo
     * SalvaCaricaRubrica.caricaRubrica(). Configura la ListView per
     * visualizzare correttamente i contatti, mostrando solo il cognome e il
     * nome. Aggiunge un listener alla ListView per reagire alla selezione di un
     * contatto, aggiornando i campi di testo con i dettagli del contatto
     * selezionato
     *
     * @param[in] location URL della risorsa utilizzata per risolvere i percorsi
     * relativi.
     * @param[in] resources Risorse utilizzate per localizzare i componenti.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactList = SalvaCaricaRubrica.loadAddressBook();
        myListView.setItems(contactList);
        configureListView();
    }

    /**
     * @brief Configura la ListView per visualizzare e gestire i contatti.
     *
     *
     * @pre La ListView `myListView` e la lista `contactList` devono essere
     * inizializzate.
     * @post La ListView La ListView viene configurata per visualizzare solo
     * Cognome e Nome del contatto
     */
    private void configureListView() {
        myListView.setCellFactory(listView -> new ListCell<Contatto>() {
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

            myListView.getSelectionModel().selectedItemProperty().addListener((observable, oldContact, selectedContact) -> {
            if (selectedContact != null) {
                super.updateContactDetails(selectedContact);
            }
        });
    }

    public void sortContact() {
        FXCollections.sort(contactList);
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
    protected void addAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
        Parent root = loader.load();

        InterfacciaAggiungiModificaController addController = loader.getController();
        addController.setInterfacciaUtenteController(this);
        addController.initializeForAdd(contactList);

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
    public void deleteAction(ActionEvent event) {
        int selected = myListView.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            contactList.remove(selected);
            SalvaCaricaRubrica.saveAddressBook(contactList);
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
    public void searchAction(ActionEvent event) {
        String searchQuery = searchBar.getText().toLowerCase().trim();
        if (searchQuery.isEmpty()) {
            myListView.setItems(contactList);
            return;
        }

        ObservableList<Contatto> filteredList = FXCollections.observableArrayList();
        for (Contatto contact : contactList) {
            if (contact.getName().toLowerCase().contains(searchQuery)
                    || contact.getSurname().toLowerCase().contains(searchQuery)
                    || contact.getNumbers().stream().anyMatch(num -> num.contains(searchQuery))) {
                filteredList.add(contact);
            }
        }
        myListView.setItems(filteredList);
    }
    
    /**
     * @brief Restituisce la lista di tutti i contatti.
     *
     * Questo metodo recupera e restituisce la lista dei contatti memorizzati
     * nel sistema. La lista è rappresentata come una collezione di oggetti del
     * tipo Contatto. Se non sono presenti contatti, viene restituita una lista
     * vuota.
     *
     * @return Una lista di oggetti Contatto, che rappresenta tutti i contatti
     * disponibili.
     */
    

   

    /**
     * @brief Ordina la lista dei contatti in base al cognome e nome.
     *
     * Questo metodo ordina la lista contactList di contatti prima per cognome,
     * e in caso di parità, per nome. L'ordinamento è fatto in ordine crescente,
     * utilizzando il metodo compareTo definito nella classe Contatto.
     *
     * @post La lista dei contatti è ordinata correttamente.
     */
    public void ordinaContatti() {
        // Utilizza il metodo sort() per ordinare direttamente la lista
        FXCollections.sort(contactList);  // Contatto deve implementare Comparable<Contatto>
    }

    /**
     * @brief Aggiorna i dettagli visualizzati per il contatto selezionato.
     *
     * @param contattoSelezionato Contatto il cui dettaglio verrà visualizzato
     * nei campi.
     *
     * @pre Il contatto selezionato deve essere valido e contenere i dati
     * aggiornati.
     * @post I campi della UI vengono aggiornati per riflettere i dati del
     * contatto selezionato.
     */
    private void updateContactDetails(Contatto contattoSelezionato) {
        defaultText.setVisible(false);
        deleteButton.setVisible(true);
        editButton.setVisible(true);
        editImageView.setVisible(true);
        deleteImageView.setVisible(true);

        // Gestione nome e cognome
        if (contattoSelezionato.getName().isEmpty() && nameField.getText().isEmpty()) {
            nameField.setVisible(false);
        } else {
            nameField.setVisible(true);
            nameField.setText(contattoSelezionato.getName());
        }

        if (contattoSelezionato.getSurname().isEmpty() && surnameField.getText().isEmpty()) {
            surnameField.setVisible(false);
        } else {
            surnameField.setVisible(true);
            surnameField.setText(contattoSelezionato.getSurname());
        }

        // Gestione dei numeri di telefono
        List<String> numbers = contattoSelezionato.getNumbers();

        if (numbers.size() > 0) {
            number1Field.setVisible(true);
            number1Field.setText(numbers.get(0));
        } else if (number1Field.getText().isEmpty()) {
            number1Field.setVisible(false);
        }

        if (numbers.size() > 1) {
            number2Field.setVisible(true);
            number2Field.setText(numbers.get(1));
        } else {
            number2Field.setVisible(false);
        }

        if (numbers.size() > 2) {
            number3Field.setVisible(true);
            number3Field.setText(numbers.get(2));
        } else {
            number3Field.setVisible(false);
        }

        // Gestione delle email
        List<String> emails = contattoSelezionato.getEmails();

        if (emails.size() > 0) {
            email1Field.setVisible(true);
            email1Field.setText(emails.get(0));
        } else {
            email1Field.setVisible(false);
        }

        if (emails.size() > 1) {
            email2Field.setVisible(true);
            email2Field.setText(emails.get(1));
        } else {
            email2Field.setVisible(false);
        }

        if (emails.size() > 2) {
            email3Field.setVisible(true);
            email3Field.setText(emails.get(2));
        } else {
            email3Field.setVisible(false);
        }

        // Gestione delle note
        if (contattoSelezionato.getNote() == null || contattoSelezionato.getNote().isEmpty()) {
            if (noteField.getText().isEmpty()) {
                noteField.setVisible(false);
            }
        } else {
            noteField.setVisible(true);
            noteField.setText(contattoSelezionato.getNote());
        }
    }

  
    /**
     * @brief Passa alla schermata di aggiunta di un nuovo contatto.
     *
     * Questo metodo carica la scena della finestra di aggiunta di un nuovo
     * contatto e la visualizza in una nuova finestra. Inoltre, imposta il
     * controller della nuova finestra per consentire l'intererazione con
     * l'interfaccia principale
     *
     *
     * @throws IOException Se non riesce a caricare la nuova schermata.
     */
    @FXML
    private void switchToAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
        Parent root = loader.load(); // Carica la scena
        // Ottieni il controller della scena di aggiunta contatto
        InterfacciaAggiungiModificaController aggiungiController = loader.getController();
        // Passa l'istanza di InterfacciaUtenteController al controller della schermata di aggiunta
        aggiungiController.setInterfacciaUtenteController(this);
        aggiungiController.initializeForAdd(contactList);

        // Crea una nuova scena e visualizzala
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
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
    private void EditAction() throws IOException {
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
     * @brief Mostra un banner di errore con un titolo e un messaggio
     * personalizzati.
     *
     *
     * @param titolo Il titolo della finestra di dialogo.
     * @param messaggio Il messaggio di errore da visualizzare nella finestra.
     *
     * @pre Il titolo e il messaggio devono essere stringhe non nulle.
     * @post La finestra di dialogo di errore viene visualizzata con il titolo e
     * il messaggio forniti.
     */
    private void showErrorDialog(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

}
