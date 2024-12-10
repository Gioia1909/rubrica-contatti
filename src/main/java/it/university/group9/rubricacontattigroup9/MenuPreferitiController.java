/**
 * @file MenuPreferitiController.java
 * @brief Controller per la gestione del menu dei contatti preferiti.
 *
 * @author Gruppo09
 * @date 05/12/2024
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import it.university.group9.rubricacontattigroup9.exceptions.ContattoGiaAggiuntoException;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuPreferitiController implements Initializable {

    /**
     * @brief Lista grafica dei contatti preferiti
     */
    @FXML
    private ListView<Contatto> listViewPreferiti;

    /**
     * @brief Bottone per la modifica
     */
    @FXML
    private Button editButton;

    /**
     * @brief Bottone per aggiungere un contatto ai preferiti
     */
    @FXML
    private Button addPrefButton;

    /**
     * @brief Bottone secondario
     */
    @FXML
    private Button secondaryButton;

    /**
     * @brief Lista dei contatti preferiti
     */
    private ObservableList<Contatto> preferitiList;

    /**
     * @brief Lista di tutti i contatti
     */
    private ObservableList<Contatto> contactList; // Riferimento alla lista utenti
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchBar;
    @FXML
    private Label nameField;
    @FXML
    private Label surnameField;
    @FXML
    private Label number1Field;
    @FXML
    private Label number2Field;
    @FXML
    private Label number3Field;
    @FXML
    private Label email1Field;
    @FXML
    private Label email2Field;
    @FXML
    private Label email3Field;
    @FXML
    private Label noteField;
    @FXML
    private Label defaultText;
    @FXML
    private Button delButton;

    /**
     * @brief Cambia la scena all'interfaccia utente.
     *
     * @param[in] event L'evento che ha generato l'azione di switch.
     * @throws IOException Se il caricamento della scena fallisce.
     */
    @FXML
    private void switchToInterfaccia() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaUtente.fxml"));
            Parent root = loader.load();

            // Ottieni il controller
            InterfacciaUtenteController controller = loader.getController();

            // Passa la lista di contatti al nuovo controller
            controller.setContactList(this.contactList);

            // Cambia la scena
            Stage stage = (Stage) listViewPreferiti.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento dell'interfaccia utente: " + e.getMessage());
            e.printStackTrace();
        }
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
        preferitiList = FXCollections.observableArrayList(SalvaCaricaPreferiti.caricaRubricaPreferiti());
        listViewPreferiti.setItems(preferitiList);
        ordinaContatti();
        configurePreferitiListView();

        System.out.println("Preferiti caricati senza duplicati: " + preferitiList);
        listViewPreferiti.getSelectionModel().selectedItemProperty().addListener((observable, contattoPrecedente, contattoSelezionato) -> {
            if (contattoSelezionato != null) {
                // Aggiorna le label con i dati del contatto selezionato
                updateContactDetails(contattoSelezionato);
            }
        });
    }

/**
 * @brief Configura la visualizzazione della lista dei contatti preferiti nella ListView.
 * 
 * @pre La `ListView` deve essere inizializzata correttamente e pronta per la configurazione.
 * @post La `ListView` dei contatti preferiti è configurata per visualizzare i contatti con il formato "Cognome Nome".
 */
    private void configurePreferitiListView() {
        listViewPreferiti.setCellFactory(listView -> new ListCell<Contatto>() {
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
    }

//Non sappiamo se la implementeremo
   
/**
 * @brief Gestisce l'azione di modifica di un contatto selezionato dalla lista dei preferiti.
 * 
 * @param event L'evento che ha attivato l'azione di modifica.
 * 
 * @pre Il contatto selezionato nella `ListView` deve essere non nullo.
 * @post Viene aperta una nuova finestra di modifica con i dettagli del contatto selezionato, pronti per essere modificati.
 */
    @FXML
    private void editAction(ActionEvent event) throws IOException {
        Contatto selectedContact = listViewPreferiti.getSelectionModel().getSelectedItem();
        if(selectedContact!=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiModifica.fxml"));
            Parent root = loader.load();
            InterfacciaAggiungiModificaController modificaController = loader.getController();
            modificaController.initializeForEdit(selectedContact, preferitiList);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * @brief Gestisce l'apertura di una finestra popup per la selezione di un
     * contatto.
     *
     * Questo metodo carica un file FXML per il popup, crea una nuova finestra
     * (Stage)
     *
     * La finestra popup blocca l'interazione con altre finestre finché non
     * viene chiusa.
     *
     *
     * @pre Il file FXML "SelezionaContattiDaRubrica.fxml" deve essere presente
     * e valido.
     * @post Una nuova finestra popup viene aperta, e l'utente non può
     * interagire con altre finestre fino alla chiusura del popup.
     *
     * @throws IOException Se il file FXML non può essere caricato o se si
     * verifica un errore durante la creazione della finestra.
     */
    private void handleOpenPopup() {
        try {
            // Carica il file FXML per il popup
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelezionaContattiDaRubrica.fxml"));
            Parent root = loader.load();

            // Crea una nuova finestra (Stage) per il popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Seleziona Contatto");
            popupStage.setScene(new Scene(root, 336, 400)); // Imposta le dimensioni precise
            popupStage.setResizable(false); // Blocca il ridimensionamento
            popupStage.initModality(Modality.APPLICATION_MODAL); // Blocca interazioni con altre finestre
            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void addPrefButton(ActionEvent event) throws IOException, ContattoGiaAggiuntoException {
        if (contactList != null) {
            //debug
            System.out.println("ContactList: " + contactList);
            System.out.println("PreferitiList: " + preferitiList);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelezionaContattiDaRubrica.fxml"));
            //si crea un oggetto FXMLLoader per caricare il file FXML, con getClass e Resource prendiamo il file 

            Parent root = loader.load(); //legge il contenuto del file FXML e ritorna un oggetto parent come scena

            //creo un oggetto SelezionaContattiDaRubrica per passargli qualcosa nel "costruttore" --> setContacts
            SelezionaContattiDaRubricaController popupController = loader.getController();
            //così accediamo ai metodi 

            popupController.setContacts(contactList, preferitiList); //passiamo la lista della rubrica e quella dei preferiti da aggiornare

            Scene scene = addPrefButton.getScene(); // Ottieni la scena corrente dal pulsante che ha generato l'azione
            // Crea una nuova finestra (Stage) per il popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Seleziona Contatto");
            popupStage.setScene(new Scene(root, 336, 400)); // Imposta le dimensioni precise
            popupStage.setResizable(false); // Blocca il ridimensionamento
            popupStage.initModality(Modality.APPLICATION_MODAL); // Blocca interazioni con altre finestre
            popupStage.show();
            //scene.setRoot(root); // Imposta il nuovo root, sostituisce la scena corrente con l'altra
            ordinaContatti();
        } else {
            System.out.println("La lista dei contatti non è disponibile.");
        }

    }
/**
 * @brief Gestisce l'azione di ricerca dei contatti nella lista preferiti.
 * 
 * @param event L'evento che ha attivato l'azione di ricerca.
 * 
 * @pre La barra di ricerca contiene il testo da cercare.
 * @post La `ListView` dei preferiti viene aggiornata con i contatti che corrispondono alla ricerca.
 */
    @FXML
    private void searchAction(ActionEvent event) {
        String searchQuery = searchBar.getText().toLowerCase().trim();

        if (searchQuery.isEmpty()) {
            listViewPreferiti.setItems(preferitiList);       
            return;
        }
        //Filtra la lista basandosi sul nome, cognome o numeri di telefono
        ObservableList<Contatto> filteredList = FXCollections.observableArrayList();    //lista che contiene i contatti con i criteri di ricerca inseriti
        for (Contatto contatto : preferitiList) {
            String nomePulito = contatto.getNome().trim().toLowerCase();
            String cognomePulito = contatto.getCognome().trim().toLowerCase();
            
            if(nomePulito.contains(searchQuery) || cognomePulito.contains(searchQuery)){
                filteredList.add(contatto);
            }
                
                for (String numero : contatto.getNumeri()){
                    if(numero.contains(searchQuery)){
                        filteredList.add(contatto);
                    }
                }
        }
        //Aggiorna la ListView con la lista filtrata 
        listViewPreferiti.setItems(filteredList);

        if (filteredList.isEmpty()) {
            showErrorDialog("Errore", "Nessun contatto trovato");
        }
        
    }
    
/**
 * @brief Mostra una finestra di dialogo di errore con un messaggio personalizzato,una finestra di dialogo di tipo errore con 
 * un titolo e un messaggio.
 * 
 * @param titolo Il titolo della finestra di dialogo.
 * @param messaggio Il contenuto del messaggio da visualizzare nella finestra di dialogo.
 * 
 * @post Viene mostrata una finestra di dialogo con il titolo e il messaggio forniti.
 */
    private void showErrorDialog(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
    
/**
 * @brief Ordina i contatti nella lista dei preferiti in base all'ordine naturale dei contatti, 
 *
 * 
 * @post La lista dei preferiti viene ordinata.
 */
    private void ordinaContatti(){
        FXCollections.sort(preferitiList);
    }

/**
 * @brief Gestisce l'azione di eliminazione di un contatto dalla lista preferiti.
 * 
 * @param event L'evento che ha attivato l'azione di eliminazione.
 * 
 * @pre Deve essere selezionato un contatto dalla `ListView`.
 * @post Il contatto selezionato viene rimosso dalla `ListView` e i contatti aggiornati vengono salvati nel file.
 */
    @FXML
    private void deleteAction(ActionEvent event) {
        int selezionato = listViewPreferiti.getSelectionModel().getSelectedIndex();
        if (selezionato >= 0) {
            listViewPreferiti.getItems().remove(selezionato);

            // Salva i contatti aggiornati nel file
            SalvaCaricaPreferiti.salvaRubricaPreferiti(preferitiList);
        }
    
    }
    
    
/**
 * @brief Aggiorna i dettagli di un contatto selezionato in base ai dati del contatto selezionato. 
 * Se un campo è vuoto, il relativo campo di input sarà nascosto.
 * 
 * @param contattoSelezionato Il contatto di cui aggiornare i dettagli.
 * 
 * @pre Il contatto selezionato deve essere non nullo.
 * @post I campi di input vengono aggiornati con i dettagli del contatto selezionato.
 */
    private void updateContactDetails(Contatto contattoSelezionato) {
            defaultText.setVisible(false);
            delButton.setVisible(true);

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
    
    

}