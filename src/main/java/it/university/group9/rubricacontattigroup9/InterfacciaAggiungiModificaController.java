/**
 * @file InterfacciaAggiungiModificheController.java
 * @brief Controller per la gestione dell'interfaccia grafica per l'aggiunta e la modifica dei
 * contatti.
 *
 * Questa classe gestisce gli eventi e le interazioni dell'utente per aggiungere e modificare
 * contatti. Include la validazione dei dati inseriti, la
 * gestione di duplicati e l'aggiornamento della rubrica persistente.
 *@version 2.0
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
    
    /**
     * @brief Inizializza la finestra per modificare un contatto esistente.
     * 
     * @param contact Contatto da modificare.
     * @param addressBook La lista osservabile dei contatti della rubrica.
     * 
     * @pre `contact` e `addressBook` devono essere non null.
     * @post La finestra è configurata per modificare i dati del contatto fornito.
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
     * @param url URL della risorsa utilizzata per risolvere il percorso relativo.
     * @param rb ResourceBundle per le risorse localizzate.
     * 
     * @post I listener degli eventi per i pulsanti "Aggiungi" e "Modifica" sono configurati.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.contactList = contactList;
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
     * @throws IOException Se si verifica un errore durante il salvataggio della rubrica su file.
     * @throws CampoNonValidoException Se i campi forniti non sono validi.
     * 
     *
     * @see ContattoValidatore
     * @see SalvaCaricaRubrica
     * @see  userInterfaceController
     *
     */
    @FXML
    public void addAction(ActionEvent event) {
        try {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();

            if (name.isEmpty() || surname.isEmpty()) {
                handleValidationError("Nome e cognome sono obbligatori.");
                return;
            }
            List<String> numbers = collectValidNumbers();
            if (numbers.isEmpty()) {
                handleValidationError("Deve essere inserito almeno un numero di telefono valido.");
                return;
            }

            List<String> emails = collectValidEmails();
            String note = noteField.getText().trim();

            if (ContattoValidator.isContactDuplicate(contactList, name, surname, numbers)) {
                if (!requestConfirmation("Contatto Duplicato", "Un contatto con le stesse informazioni esiste già. Vuoi comunque aggiungerlo?")) {
                    return;
                }
            }
            addressBook.addContact(name, surname, numbers, emails, note);
            closeWindow();

        } catch (CampoNonValidoException e) {
            handleValidationError(e.getMessage());
        }

    }

   
/**
 * @brief Gestisce gli errori di validazione mostrando un messaggio di errore all'utente.
 *
 * Questo metodo visualizza una finestra di dialogo di tipo alert con un messaggio di errore,
 * consentendo all'utente di comprendere quale problema si è verificato durante il processo
 * di validazione dei dati.
 *
 * @param[in] message Il messaggio di errore da mostrare all'utente. Non deve essere null o vuoto.
 *
 * @pre Il parametro `message` deve contenere un messaggio descrittivo dell'errore.
 * @post Viene mostrata una finestra di dialogo con un messaggio di errore all'utente.
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
     *
     * @pre Tutti i campi del form devono essere validati correttamente prima
     * della modifica.
     * @pre La variabile existingContact deve essere un oggetto valido
     * presente nella rubrica.
     * @post La rubrica sarà aggiornata con i nuovi dati del contatto.
     * @post I dati aggiornati saranno salvati utilizzando il metodo
     * SalvaCaricaRubrica.saveAddressBook.
     * 
     * @see ContattoValidator.
     * @see Contatto
     * @see SalvaCaricaRubrica
     *
     * 
     */
    @FXML
    protected void editAction(ActionEvent event) {
        try {
            String name = nameField.getText().trim();

            String surname = surnameField.getText().trim();

            List<String> numbers = collectValidNumbers();
            List<String> emails = collectValidEmails();

            String note = noteField.getText().trim();

            Contatto updatedContact = new Contatto(name, surname, numbers, emails, note);
            
            // Verifica se la modifica genera duplicazioni
            if (!addressBook.isEditValid(existingContact, updatedContact)) {
                handleValidationError("Le modifiche generano un duplicato nella rubrica.");
                return;
            }

            addressBook.editContact(existingContact, name, surname, numbers, emails, note);
            closeWindow();

        } catch (CampoNonValidoException e) {
            handleValidationError(e.getMessage());
        }
    }
/**
 * @brief Raccoglie e valida i numeri di telefono inseriti nei campi di input.
 *
 * Questo metodo legge i valori inseriti nei campi dedicati ai numeri di telefono, 
 * valida ciascun numero utilizzando il metodo `ContattoValidator.validatePhoneNumber`,
 * e restituisce una lista contenente i numeri validi. Se un numero non è valido,
 * viene lanciata un'eccezione.
 *
 * @return Una lista di numeri di telefono validi.
 *
 * @throws CampoNonValidoException Se uno dei numeri forniti non rispetta i criteri di validazione.
 *
 * @pre I campi `number1Field`, `number2Field` e `number3Field` devono essere inizializzati.
 * @post Viene restituita una lista contenente solo numeri di telefono validi (nessun duplicato).
 *
 * @see ContattoValidator
 */

    private List<String> collectValidNumbers() throws CampoNonValidoException {
        List<String> numbers = new ArrayList<>(Arrays.asList(
        number1Field.getText(),
        number2Field.getText(),
        number3Field.getText()
        ));
        ContattoValidator.validatePhoneNumber(numbers);
        return numbers;
    }

  /**
 * @brief Raccoglie e valida gli indirizzi email inseriti nei campi di input.
 *
 * Questo metodo legge i valori inseriti nei campi dedicati agli indirizzi email, 
 * valida ciascun indirizzo utilizzando il metodo `ContattoValidator.validateEmail`,
 * e restituisce una lista contenente gli indirizzi email validi. Se un'email non è valida,
 * viene lanciata un'eccezione.
 *
 * @return Una lista di indirizzi email validi.
 *
 * @throws CampoNonValidoException Se uno degli indirizzi email forniti non rispetta i criteri di validazione.
 *
 * @pre I campi `email1Field`, `email2Field` e `email3Field` devono essere inizializzati.
 * @post Viene restituita una lista contenente solo indirizzi email validi (nessun duplicato).
 *
 * @see ContattoValidator
 */
    private List<String> collectValidEmails() throws CampoNonValidoException {
        List<String> emails = new ArrayList<>(Arrays.asList(
        email1Field.getText(),
        email2Field.getText(),
        email3Field.getText()
        ));
        ContattoValidator.validateEmail(emails);
        return emails;
    }

/**
 * @brief Mostra una finestra di dialogo di conferma per chiedere all'utente di confermare o meno un'azione.
 *
 * Questo metodo visualizza una finestra di dialogo di tipo conferma, contenente il messaggio
 * fornito, e due pulsanti: "Sì" e "No". Restituisce `true` se l'utente ha selezionato "Sì",
 * altrimenti restituisce `false` se l'utente ha selezionato "No".
 *
 * @param[in] title Il titolo della finestra di dialogo.
 * @param[in] message Il messaggio da visualizzare nella finestra di dialogo.
 *
 * @return `true` se l'utente ha confermato l'azione selezionando "Sì", altrimenti `false`.
 *
 * @pre Il metodo mostra una finestra di dialogo all'utente, quindi deve essere invocato in un contesto
 * che consenta la visualizzazione dell'interfaccia utente (ad esempio in un thread di applicazione JavaFX).
 * @post L'utente avrà visto una finestra di dialogo di conferma e avrà scelto se continuare con l'azione.
 */
    private boolean requestConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }



     
     
/**
 * @brief Popola i campi del form con i dati di un contatto esistente.
 *
 * Questo metodo prende un oggetto `Contatto` e compila i campi del form con le informazioni del contatto.
 * I campi come nome, cognome, numeri di telefono, email e note vengono riempiti a partire dai dati
 * presenti nell'oggetto `Contatto`. Ogni tipo di dato viene inserito nel rispettivo campo del form, 
 * con un massimo di tre numeri di telefono e tre email.
 *
 * @param[in] contact Il contatto i cui dati devono essere inseriti nei campi del form. Deve essere un oggetto valido e non null.
 *
 * @pre Il parametro `contact` deve essere un oggetto valido e non null.
 * @post I campi del form saranno riempiti con i dati del contatto:
 * @post Nome e cognome nei rispettivi campi.
 * @post Numeri di telefono nei campi appropriati, fino a un massimo di tre numeri.
 * @post Email nei campi appropriati, fino a un massimo di tre email.
 * @post Note nel campo delle note.
 *
 * @see Contatto
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
     * @brief Annulla l'operazione in corso e chiude la finestra corrente.
     *
     * @pre La finestra deve essere aperta.
     * @post La finestra corrente viene chiusa.
     */
    @FXML
    private void cancelOperation(ActionEvent event) {
        closeWindow();
    }

    /**
     * @brief Chiude la finestra corrente dell'interfaccia utente.
     *
     * @pre Il campo nameField deve essere associato a una scena e a una
     * finestra.
     * @post La finestra corrente viene chiusa, interrompendo l'interazione
     * dell'utente.
     */
    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

}
