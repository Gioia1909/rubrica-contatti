/**
 * @file InterfacciaAggiungiModificaController.java
 * @brief Controller per la gestione dell'interfaccia grafica per l'aggiunta e
 * la modifica dei contatti.
 *
 * Questa classe gestisce gli eventi e le interazioni dell'utente per aggiungere
 * e modificare contatti. Include la validazione dei dati inseriti, la gestione
 * di duplicati e l'aggiornamento della rubrica persistente.
 * @version 2.0
 * @date 11/12/2024
 * @author Gruppo09
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
    private Rubrica addressBook;
    private ObservableList<Contatto> contactList;

    /**
     * @brief Riferimento al controller dell'interfaccia principale.
     */
    private InterfacciaUtenteController userInterfaceController;
    @FXML
    private Button bttnHelp;

    /**
     * @brief Inizializza la finestra per modificare un contatto esistente.
     *
     * @param contact Contatto da modificare.
     * @param addressBook La lista osservabile dei contatti della rubrica.
     *
     * @pre `contact` e `addressBook` devono essere non null.
     * @post La finestra è configurata per modificare i dati del contatto
     * fornito.
     */
    public void initializeForEdit(Rubrica addressBook, Contatto contact, ObservableList<Contatto> contactList) {
        this.addressBook = addressBook; // Salva il riferimento alla Rubrica
        this.contactList = contactList;
        this.existingContact = contact;
        populateFields(contact);
        addButton.setVisible(false);
        editButton.setVisible(true);
    }

    public void initializeForAdd(Rubrica addressBook, ObservableList<Contatto> contactList) {
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
     * Questo metodo permette di aggiungere un nuovo contatto con nome, cognome,
     * numeri di telefono, email e note alla rubrica. Valida i dati inseriti,
     * verifica la presenza di duplicati e aggiorna la rubrica sia nella lista
     * in memoria che nel file di salvataggio.
     *
     * @pre I parametri da inserire nel contatto non devono essere vuoti
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
         
            addressBook.addContact(name, surname, numbers, emails, note);
            closeWindow();

        } catch (CampoNonValidoException e) {
            handleValidationError(e.getMessage());
        }

    }

    /**
     * @brief Gestisce gli errori di validazione mostrando un messaggio di
     * errore all'utente.
     *
     * Questo metodo visualizza una finestra di dialogo di tipo alert con un
     * messaggio di errore, consentendo all'utente di comprendere quale problema
     * si è verificato durante il processo di validazione dei dati.
     *
     * @param[in] message Il messaggio di errore da mostrare all'utente. Non
     * deve essere null o vuoto.
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
     * @param[in] event L'evento ActionEvent generato dal click sul pulsante
     * "Modifica".
     */
    @FXML
    protected void editAction(ActionEvent event) {
        try {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            List<String> numbers = collectNumbers();
            List<String> emails = collectEmails();
            String note = noteField.getText().trim();
            
            addressBook.editContact(existingContact, name, surname, numbers, emails, note);
            // Aggiorna la lista filtrata se vengo da una ricerca 
            //updateFilteredList();

            closeWindow();

        } catch (CampoNonValidoException e) {
            handleValidationError(e.getMessage());
        }
    }

    private String checkField(String csValue) {
        if (csValue == null) {
            return "";
        }
        return csValue;
    }

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

    private List<String> collectEmails() {
        return new ArrayList<>(Arrays.asList(
                checkField(email1Field.getText()),
                checkField(email2Field.getText()),
                checkField(email3Field.getText())
        ));
    }

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

    @FXML
    private void cancelOperation(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

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

}
