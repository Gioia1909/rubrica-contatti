/**
 * @file InterfacciaAggiungiController.java
 * @brief Controller per la gestione dell'interfaccia grafica per l'aggiunta di
 * contatti.
 *
 * Questa classe gestisce gli eventi e le interazioni dell'utente per aggiungere
 * un nuovo contatto alla rubrica. Include la validazione dei dati inseriti, la
 * gestione di duplicati e l'aggiornamento della rubrica persistente.
 *
 * @date 07/12/2024
 * @author Gruppo09
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import it.university.group9.rubricacontattigroup9.exceptions.*;
import it.university.group9.rubricacontattigroup9.validators.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ObservableList<Contatto> addressBook;
    /**
     * @brief Riferimento al controller dell'interfaccia principale.
     */
    private InterfacciaUtenteController userInterfaceController;

    
    public Button getAddButton(){
        return addButton; 
    }
    
    public void setAddButton(Button addButton){
        this.addButton=addButton; 
    }
    
    public Button getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }

       public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    
    public TextField getNameField() {
        return nameField;
    }
     
    
    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }


    
    
    
    public TextField getSurnameField() {
        return surnameField;
    }

     public void setSurnameField(TextField surnameField) {
        this.surnameField = surnameField;
    }

     
    
     
     public TextField getEmail1Field() {
        return email1Field;
    }
    
     public void setEmail1Field(TextField email1Field) {
        this.email1Field = email1Field;
    }


   
     
     public TextField getEmail2Field() {
        return email2Field;
    }

     public void setEmail2Field(TextField email2Field) {
        this.email2Field = email2Field;
    }

   
     
     public TextField getEmail3Field() {
        return email3Field;
    }
    
    public void setEmail3Field(TextField email3Field) {
        this.email3Field = email3Field;
    }

    
    public TextField getNumber1Field() {
        return number1Field;
    }
    
    public void setNumber1Field(TextField number1Field) {
        this.number1Field = number1Field;
    }

    

    public TextField getNumber2Field() {
        return number2Field;
    }

    public void setNumber2Field(TextField number2Field) {
        this.number2Field = number2Field;
    }

    
    public TextField getNumber3Field() {
        return number3Field;
    }

    public void setNumber3Field(TextField number3Field) {
        this.number3Field = number3Field;
    }

    public TextField getNoteField() {
        return noteField;
    }
    
    public void setNoteField(TextField noteField) {
        this.noteField = noteField;
    }

    public boolean isIsEditing() {
        return isEditing;
    }
    
    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }


    public Contatto getExistingContact() {
        return existingContact;
    }
    
    public void setExistingContact(Contatto existingContact) {
        this.existingContact = existingContact;
    }


    public ObservableList<Contatto> getAddressBook() {
        return addressBook;
    }
    
    public void setAddressBook(ObservableList<Contatto> addressBook) {
        this.addressBook = addressBook;
    }


    public InterfacciaUtenteController getInterfacciaUtenteController() {
        return userInterfaceController;
    }
    
    public void setInterfacciaUtenteController(InterfacciaUtenteController controller) {
        this.userInterfaceController = controller;
    }
    /**
     * @brief Imposta il riferimento al controller dell'interfaccia principale.
     * @param[in] controller Riferimento al controller principale.
     * @post Viene aggiornato il riferimento al controller principale.
     */
    
    //parte di add
    
    
    public void initializeForAdd(ObservableList<Contatto> addressBook) {
        this.addressBook = addressBook;
        addButton.setVisible(true);
        editButton.setVisible(false);
    }


    public void initializeForEdit(Contatto contact, ObservableList<Contatto> addressBook) {
        this.addressBook = addressBook;
        this.existingContact = contact;
        populateFields(contact);
        addButton.setVisible(false);
        editButton.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButton.setOnAction(event -> {

            addContact(event);

        });

        editButton.setOnAction(event -> editContact(new ActionEvent()));
    }
    /**
     * @brief Torna all'interfaccia principale.
     *
     * Questo metodo chiude la finestra corrente e ritorna alla schermata
     * principale dell'applicazione.
     *
     * @param[in] event Evento del mouse che ha scatenato l'azione.
     * @throws IOException Se non è possibile caricare la scena.
     * @post La finestra corrente viene chiusa e l'applicazione torna alla
     * schermata principale.
     * @see InterfacciaUtenteController
     *
     */
    @FXML
    public void switchToInterfaccia(ActionEvent event) throws IOException {
        closeWindow();
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
     * @throws CampoNonValidoException se i campi forniti non sono valido.
     * non è valido.
     * @throws EmailNonValidaException Se una delle email fornite non è valida.
     *
     * @see NomeValidator, CognomeValidator, NumeroValidator, EmailValidator,
     * SalvaCaricaRubrica
     *
     */
    @FXML
    public void addContact(ActionEvent event) {
        try {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();

            if (name.isEmpty() || surname.isEmpty()) {
                handleValidationError("Nome e cognome sono obbligatori.");
                return;
            }

            ContattoValidator.validateName(name);
            ContattoValidator.validateSurname(surname);

            List<String> numbers = collectValidNumbers();
            if (numbers.isEmpty()) {
                handleValidationError("Deve essere inserito almeno un numero di telefono valido.");
                return;
            }

            List<String> emails = collectValidEmails();
            String note = noteField.getText().trim();

            if (ContattoValidator.isContattoDuplicato(userInterfaceController.getListContact(), name, surname)) {
                if (!requestConfirmation("Contatto Duplicato", "Un contatto con lo stesso nome e cognome esiste già. Vuoi comunque aggiungerlo?")) {
                    return;
                }
            }

            Contatto nuovoContatto = new Contatto(name, surname, numbers, emails, note);
            userInterfaceController.getListContact().add(nuovoContatto);
            userInterfaceController.sortContact();
            SalvaCaricaRubrica.salvaRubrica(userInterfaceController.getListContact());

            closeWindow();

        } catch (CampoNonValidoException e) {
            handleValidationError(e.getMessage());
        }
    }
    
    @FXML
    protected void editContact(ActionEvent event) {
        try {
            String name = nameField.getText().trim();
            ContattoValidator.validateName(name);

            String surname = surnameField.getText().trim();
            ContattoValidator.validateSurname(surname);

            List<String> numbers = collectValidNumbers();
            List<String> emails = collectValidEmails();

            String note = noteField.getText().trim();

            Contatto nuovoContatto = new Contatto(name, surname, numbers, emails, note);

            int index = addressBook.indexOf(existingContact);
            if (index != -1) {
                addressBook.set(index, nuovoContatto);
            }

            SalvaCaricaRubrica.saveAddressBook(addressBook);
            closeWindow();

        } catch (CampoNonValidoException e) {
            handleValidationError(e.getMessage());
        }
    }


    private List<String> collectValidNumbers() throws CampoNonValidoException {
        List<String> numbers = new ArrayList<>();
        List<TextField> numberFields = Arrays.asList(number1Field, number2Field, number3Field);

        for (TextField field : numberFields) {
            String number = field.getText().trim();
            if (!number.isEmpty()) {
                ContattoValidator.validatePhoneNumber(number);
                numbers.add(number);
            }
        }
        return numbers;
    }

    private List<String> collectValidEmails() throws CampoNonValidoException {
        List<String> emails = new ArrayList<>();
        List<TextField> emailFields = Arrays.asList(email1Field, email2Field, email3Field);

        for (TextField field : emailFields) {
            String email = field.getText().trim();
            if (!email.isEmpty()) {
                ContattoValidator.validateEmail(email);
                emails.add(email);
            }
        }
        return emails;
    }

     private boolean requestConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }


     private void handleValidationError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore di Validazione");
        alert.setContentText(message);
        alert.showAndWait();
    }
     
     private void populateFields(Contatto contatto) {
        nameField.setText(contatto.getName());
        surnameField.setText(contatto.getSurname());
        noteField.setText(contatto.getNote());

        List<String> numbers = contatto.getNumbers();
        if (numbers.size() > 0) {
            number1Field.setText(numbers.get(0));
        }
        if (numbers.size() > 1) {
            number2Field.setText(numbers.get(1));
        }
        if (numbers.size() > 2) {
            number3Field.setText(numbers.get(2));
        }

        List<String> emails = contatto.getEmails();
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


    
      private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
