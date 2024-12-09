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
    private ImageView searchButtonImage; 


    @FXML
    private Button favoriteButton;
    /**
     * <Bottone per visualizzare i contatti preferiti.
     */
    @FXML
    private ListView<Contatto> myListView;
    /**
     * <Lista per visualizzare i contatti.
     */
    @FXML
    private Button primaryButton;
    /**
     * <Bottone principale per operazioni varie.
     */
    @FXML
    private TextField textBar;
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
    ///@}
    @FXML
    private ScrollBar scrollBar;
    @FXML
    private Label defaultText;
    /**
     * <Label Testo di Default
     */
    ///@}
    private ObservableList<Contatto> contactList;

    /**
     * <Lista Osservabile dei Contatti
     */
    /**
     * @brief Inizializza i componenti e imposta la lista di contatti
     *
     * Questo metodo viene eseguito automaticamente quando l'interfaccia utente
     * viene caricata. Carica la lista dei contatti utilizzando il metodo
     * `SalvaCaricaRubrica.caricaRubrica()`. Configura la `ListView` per
     * visualizzare correttamente i contatti, mostrando solo il cognome e il
     * nome. Aggiunge un listener alla `ListView` per reagire alla selezione di
     * un contatto, aggiornando i campi di testo con i dettagli del contatto
     * selezionato
     *
     * @param[in] location URL della risorsa utilizzata per risolvere i percorsi
     * relativi.
     * @param[in] resources Risorse utilizzate per localizzare i componenti.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactList = SalvaCaricaRubrica.caricaRubrica();
        myListView.setItems(contactList);
        configureListView();
    }

    private void configureListView() {
        myListView.setCellFactory(listView -> new ListCell<Contatto>() {
            @Override
            protected void updateItem(Contatto contatto, boolean empty) {
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
                updateContactDetails(contattoSelezionato);
            }
        });
    }

    private void updateContactDetails(Contatto contattoSelezionato) {
            defaultText.setVisible(false);
    deleteButton.setVisible(true);

    // Gestione nome e cognome
    if (contattoSelezionato.getNome().isEmpty() && nameField.getText().isEmpty()) {
        nameField.setVisible(false);
    } else {
        nameField.setVisible(true);
        nameField.setText(contattoSelezionato.getNome());
    }

    if (contattoSelezionato.getCognome().isEmpty() && surnameField.getText().isEmpty()) {
        surnameField.setVisible(false);
    } else {
        surnameField.setVisible(true);
        surnameField.setText(contattoSelezionato.getCognome());
    }

    // Gestione dei numeri di telefono
    List<String> numeri = contattoSelezionato.getNumeri();
    
    if (numeri.size() > 0) {
        number1Field.setVisible(true);
        number1Field.setText(numeri.get(0));
    } else if (number1Field.getText().isEmpty()) {
        number1Field.setVisible(false);
    }

    if (numeri.size() > 1) {
        number2Field.setVisible(true);
        number2Field.setText(numeri.get(1));
    } else{
        number2Field.setVisible(false);
    }

    if (numeri.size() > 2) {
        number3Field.setVisible(true);
        number3Field.setText(numeri.get(2));
    } else{
        number3Field.setVisible(false);
    }

    // Gestione delle email
    List<String> emails = contattoSelezionato.getEmails();

    if (emails.size() > 0) {
        email1Field.setVisible(true);
        email1Field.setText(emails.get(0));
    } else{
        email1Field.setVisible(false);
    }

    if (emails.size() > 1) {
        email2Field.setVisible(true);
        email2Field.setText(emails.get(1));
    } else{
        email2Field.setVisible(false);
    }

    if (emails.size() > 2) {
        email3Field.setVisible(true);
        email3Field.setText(emails.get(2));
    } else{
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
     * @brief Restituisce la lista di tutti i contatti.
     *
     * Questo metodo recupera e restituisce la lista dei contatti memorizzati
     * nel sistema. La lista è rappresentata come una collezione di oggetti del
     * tipo `Contatto`. Se non sono presenti contatti, viene restituita una
     * lista vuota.
     *
     * @return Una lista di oggetti `Contatto`, che rappresenta tutti i contatti
     * disponibili.
     */
    public List<Contatto> getListaContatti() {
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
     *
     * @post La lista dei contatti è ordinata correttamente.
     */
    public void ordinaContatti() {
        // Utilizza il metodo sort() per ordinare direttamente la lista
        FXCollections.sort(contactList);  // Contatto deve implementare Comparable<Contatto>
    }

    /**
     * @brief Passa alla schermata dei contatti preferiti.
     *
     * @throws IOException Se non riesce a caricare la nuova schermata.
     * @see MenuPreferitiController
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
    public void deleteContact(MouseEvent event) {
        int selezionato = myListView.getSelectionModel().getSelectedIndex();
        if (selezionato >= 0) {
            myListView.getItems().remove(selezionato);

            // Salva i contatti aggiornati nel file
            SalvaCaricaRubrica.salvaRubrica(contactList);
        }
    }

    /**
     * @brief Cerca un contatto nella lista in base al testo inserito nella
     * barra di ricerca.
     *
     * Questo metodo filtra la lista dei contatti in base al testo inserito
     * nella barra di ricerca `textBar`. La ricerca viene effettuata
     * considerando il nome, il cognome e il numero di telefono del contatto.
     *
     * @pre La lista di contatti contactList è già popolata.
     * @post La myListView mostra la lista filtrata di contatti che soddisfano i criteri di ricerca.
     *
     * @param[in] event Evento associato al bottone di ricerca.
     */
    @FXML
    public void searchContact(ActionEvent event) {
        String searchQuery = textBar.getText().toLowerCase().trim();
        //searchQuery è una stringa che contiene il testo digitato; 
        //textBar.getText(), recupera il testo che ha digitato l'utente; 
        // toLowerCase() rende tutto minuscolo per avere un ricerca case insensitive; 
        //trim() rimuove spazi bianchi all'inizio o alla fine del testo; 

        //Se la barra di ricerca è vuota, ripristina la lista completa
        if (searchQuery.isEmpty()) {
            myListView.setItems(contactList);       //la lista viene ripristinata per mostrare tutti gli elementi della contactList  
            return;
        }

        //Filtra la lista basandosi sul nome, cognome o numeri di telefono
        ObservableList<Contatto> filteredList = FXCollections.observableArrayList();    //lista che contiene i contatti con i criteri di ricerca inseriti
        for (Contatto contatto : contactList) {         //Scorre tutti gli oggetti Contatto presenti nella lista contactList
            if (contatto.getNome().toLowerCase().contains(searchQuery) //converte il nome del contatto in minuscolo per una ricerca case insensitive e controlla se il nome contiene la sottostringa searchQuery
                    || contatto.getCognome().toLowerCase().contains(searchQuery) //stessa cosa del nome 
                    || contatto.getNumeri().stream().anyMatch(num -> num.contains(searchQuery))) {
                // contatto.getNumeri() restituisce una lista di numeri associati al contatto, 
                //stream() trasforma la lista in uno stream, in modo da avere una sequenza su cui poter applicare operazioni 
                //anyMatch() verifica se almeno uno dei numeri soddisfa la condizione 
                //num -> num.contains(searchQuery) Per ogni elemento num (numero di telefono) nello stream, verifica se contiene la stringa searchQuery
                filteredList.add(contatto); //Se il contatto soddisfa almeno una delle condizioni, viene aggiunto alla lista filteredList
            }
        }

        //Aggiorna la ListView con la lista filtrata 
        myListView.setItems(filteredList);

//Mostra un avviso se nessun contatto corrisponde ai criteri di ricerca
        if (filteredList.isEmpty()) {
            showErrorDialog("Errore", "Nessun contatto trovato");
        }
    }


    /*
     * mostra i banner di errore
     */
    private void showErrorDialog(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
}
