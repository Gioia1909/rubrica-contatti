/**
 * @file InterfacciaAggiungiModificaController.java
 * @brief Controller per la gestione dell'interfaccia grafica per l'aggiunta e
 * la modifica dei contatti.
 *
 * Questa classe gestisce gli eventi e le interazioni dell'utente per aggiungere
 * e modificare contatti. Include la validazione dei dati inseriti, la gestione
 * di duplicati e l'aggiornamento della rubrica persistente.
 * @author Gruppo09
 * 
 * @see GestioneRubrica
 * @see Contatto
 * @see ContattoValidator
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.*;
import it.university.group9.rubricacontattigroup9.exceptions.*;
import it.university.group9.rubricacontattigroup9.validators.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InterfacciaAggiungiModificaController implements Initializable {

    @FXML
    private Button addButton, cancelButton, editButton;
    @FXML
    private TextField nameField, surnameField, email1Field, email2Field, email3Field;
    @FXML
    private TextField number1Field, number2Field, number3Field, noteField;

    private boolean isEditing = false;
    private Contatto existingContact;
    private GestioneRubrica addressBook;
    private ObservableList<Contatto> contactList;
    private String selectedGender ="default";

    /**
     * @brief Riferimento al controller dell'interfaccia principale.
     */
    private InterfacciaUtenteController userInterfaceController;
    @FXML
    private Button bttnHelp;
    @FXML
    private Button maleButton;
    @FXML
    private Button womanButton;
    @FXML
    private ImageView maleImageView;
    @FXML
    private ImageView femaleImageView;
    @FXML
    private Button defaultButton;
    @FXML
    private ImageView defaultImageView;

    /**
     * @brief Inizializza la finestra per modificare un contatto esistente.
     *
     * @param[in] contact Contatto da modificare.
     * @param[in] addressBook riferimento al'interfaccia GestioneRubrica
     * @param[in] contactList Lista dei contatti
     * @pre `contact` non deve essere null.
     * @post La finestra è configurata per modificare i dati del contatto
     * fornito.
     */
    public void initializeForEdit(GestioneRubrica addressBook, Contatto contact, ObservableList<Contatto> contactList) {
        this.addressBook = addressBook; // Salva il riferimento alla Rubrica
        this.contactList = contactList;
        this.existingContact = contact;
        populateFields(contact);
        addButton.setVisible(false);
        editButton.setVisible(true);
    }

    /**
     * @brief Inizializza la finestra per aggiungere un contatto.
     *
     * @param[in] addressBook riferimento al'interfaccia GestioneRubrica
     * @param[in] contactList Lista dei contatti
     * @post La finestra è configurata per modificare i dati del contatto
     * fornito.
     */
    public void initializeForAdd(GestioneRubrica addressBook, ObservableList<Contatto> contactList) {
        this.addressBook = addressBook; // Salva il riferimento alla Rubrica
        this.contactList = contactList;
        addButton.setVisible(true);
        editButton.setVisible(false); // Nascondi il bottone di modifica
    }

    /**
     * @brief Inizializza l'interfaccia utente.
     *
     * @param url URL della risorsa utilizzata per risolvere il percorso
     * relativo.
     * @param rb ResourceBundle per le risorse localizzate.
     *
     * @post I listener degli eventi per i pulsanti "Aggiungi" e "Modifica" sono
     * configurati.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButton.setOnAction(event -> addAction(event));
        editButton.setOnAction(event -> editAction(event));
    }

    /**
     * @brief Aggiunge un nuovo contatto alla lista e chiude la finestra di
     * aggiunta contatti aggiornando la rubrica
     *
     * @post Viene aggiunto un contatto nella lista
     *
     * @param[in] event Evento del mouse che ha scatenato l'azione.
     *
     * @throws IOException Se si verifica un errore durante il salvataggio della
     * rubrica su file.
     * @throws CampoNonValidoException Se i campi forniti non sono validi.
     */
    @FXML
    public void addAction(ActionEvent event) {
        try {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();

            List<String> emails = collectEmails();
            List<String> numbers = collectNumbers();
            String note = noteField.getText().trim();

            addressBook.addContact(name, surname, numbers, emails, note, selectedGender);
            closeWindow();

        } catch (CampoNonValidoException e) {
            handleValidationError(e.getMessage());
        }

    }

    /**
     * @brief Gestisce gli errori di validazione mostrando un messaggio di
     * errore all'utente.
     *
     *
     * @param[in] message Il messaggio di errore da mostrare all'utente. 
     */
    private void handleValidationError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore di Validazione");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * @brief Modifica i dati di un contatto esistente nella rubrica.
     *
     * @pre Il contatto deve esistere nella rubrica
     * @post contatto modificato correttamente
     * 
     * @param[in] event L'evento ActionEvent generato dal click sul pulsante
     * "Modifica".
     * 
     * @throws CampoNonValidoException Se i campi modificati non sono validi
     */
    @FXML
    protected void editAction(ActionEvent event) {
        try {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            List<String> numbers = collectNumbers();
            List<String> emails = collectEmails();
            String note = noteField.getText().trim();

            addressBook.editContact(existingContact, name, surname, numbers, emails, note, selectedGender);
            // Aggiorna la lista filtrata se vengo da una ricerca 
            //updateFilteredList();

            closeWindow();

        } catch (CampoNonValidoException e) {
            handleValidationError(e.getMessage());
        }
    }

/**
 * @brief Verifica se la stringa è null, se si la setta vuota
 *
 * @param[in] csValue La stringa da verificare.
 * @return Una stringa vuota se l'input è null, altrimenti la stessa stringa di input.
 */
    private String checkField(String csValue) {
        if (csValue == null) {
            return "";
        }
        return csValue;
    }

/**
 * @brief Colleziona i numeri di telefono del contatto e li verifica
 *
 * @return Una lista di numeri di telefono.
 * @throws CampoNonValidoException Se nessun numero di telefono è stato inserito.
 */
    private List<String> collectNumbers() throws CampoNonValidoException {
        List<String> numbers = new ArrayList<>(Arrays.asList(
                checkField(number1Field.getText()),
                checkField(number2Field.getText()),
                checkField(number3Field.getText())
        ));
        if (numbers.isEmpty()) {
            throw new CampoNonValidoException("Devi inserire almeno un numero di telefono.");
        }

        ContattoValidator.validatePhoneNumber(numbers);
        return numbers;
    }

/**
 * @brief Colleziona le emails contatto e le verifica
 *
 * @return Una lista di emails.
 * @throws CampoNonValidoException Se nessuna email è stata inserita.
 */
    private List<String> collectEmails() {
        return new ArrayList<>(Arrays.asList(
                checkField(email1Field.getText()),
                checkField(email2Field.getText()),
                checkField(email3Field.getText())
        ));
    }

/**
 * @brief Popola i campi dell'interfaccia utente con le informazioni di un contatto.
 * 
 *
 * @param[in] contact Il contatto da cui estrarre le informazioni per popolare i campi.
 */
    private void populateFields(Contatto contact) {
        nameField.setText(contact.getName());
        surnameField.setText(contact.getSurname());
        noteField.setText(contact.getNote());

        List<String> numbers = contact.getNumbers();
        if (numbers.size() > 0) {
            number1Field.setText(numbers.get(0));
        }
        if (numbers.size() > 1) {
            number2Field.setText(numbers.get(1));
        }
        if (numbers.size() > 2) {
            number3Field.setText(numbers.get(2));
        }

        List<String> emails = contact.getEmails();
        if (emails.size() > 0) {
            email1Field.setText(emails.get(0));
        }
        if (emails.size() > 1) {
            email2Field.setText(emails.get(1));
        }
        if (emails.size() > 2) {
            email3Field.setText(emails.get(2));
        }

    }

/**
 * @brief Annulla l'operazione corrente e chiude la finestra.
 *
 *
 * @param[in] event L'evento che attiva l'azione di annullamento.
 * @post La finestra viene chiusa senza salvare le modifiche.
 */
    @FXML
    private void cancelOperation(ActionEvent event) {
        closeWindow();
    }

/**
 * @brief Chiude la finestra corrente. 
 * @post La finestra viene chiusa.
 */
    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
    

/**
 * @brief Popola automaticamente i campi dell'interfaccia utente con valori di esempio predefiniti.
 * 
 *
 * @param[in] event L'evento che attiva l'azione di compilazione automatica.
 * @post I campi dell'interfaccia utente vengono riempiti con valori di esempio.
 */
    @FXML
    private void doFillValue(ActionEvent event) {
        if (nameField == null) {
            return;
        }
        nameField.setText("Nome1");
        surnameField.setText("Cognome1");
        email1Field.setText("Email1@g.com");
        email2Field.setText("Email2@g.com");
        email3Field.setText("Email3@g.com");
        number1Field.setText("1234567890");
        number2Field.setText("2345678901");
        number3Field.setText("3456789012");
        noteField.setText("Note");
    }

/**
 * @brief Imposta il genere selezionato su "maschio" e evidenzia il pulsante corrispondente.
 *
 * @param[in] event L'evento che attiva la selezione del genere maschile.
 * @post Il genere selezionato viene impostato su "maschio" e il pulsante viene evidenziato con il colore di sfondo blu chiaro.
 */
    @FXML
    private void setMale(ActionEvent event) {
        selectedGender = "male";
        maleButton.setStyle("-fx-background-color: lightblue;"); // Evidenzia il pulsante
        womanButton.setStyle(""); // Rimuovi evidenziazione dall'altro
        defaultButton.setStyle("");
    }

/**
 * @brief Imposta il genere selezionato su "femmina" e evidenzia il pulsante corrispondente.
 * 
 * @param[in] event L'evento che attiva la selezione del genere femminile.
 * @post Il genere selezionato viene impostato su "femmina" e il pulsante viene evidenziato con il colore di sfondo rosa chiaro.
 */
    @FXML
    private void setWoman(ActionEvent event) {
        selectedGender = "female";
        womanButton.setStyle("-fx-background-color: lightpink;"); // Evidenzia il pulsante
        maleButton.setStyle(""); // Rimuovi evidenziazione dall'altro
        defaultButton.setStyle("");
    }
    
/**
 * @brief Imposta il genere selezionato su "default" e evidenzia il pulsante corrispondente.
 *
 * @param[in] event L'evento che attiva la selezione del genere predefinito.
 * @post Il genere selezionato viene impostato su "default" e il pulsante viene evidenziato con il colore di sfondo grigio.
 */
    @FXML
    private void setDefault(ActionEvent event) {
        selectedGender = "default";
        defaultButton.setStyle("-fx-background-color: grey;"); // Evidenzia il pulsante
        womanButton.setStyle(""); // Rimuovi evidenziazione dall'altro
        maleButton.setStyle(""); // Rimuovi evidenziazione dall'altro
    }

}
