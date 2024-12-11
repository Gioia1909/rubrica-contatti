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


    public Contatto getContattoEsistente() {
        return contattoEsistente;
    }
    
    public void setContattoEsistente(Contatto contattoEsistente) {
        this.contattoEsistente = contattoEsistente;
    }


    public ObservableList<Contatto> getRubrica() {
        return rubrica;
    }
    
    public void setRubrica(ObservableList<Contatto> rubrica) {
        this.rubrica = rubrica;
    }


    public InterfacciaUtenteController getInterfacciaUtenteController() {
        return interfacciaUtenteController;
    }
    
    public void setInterfacciaUtenteController(InterfacciaUtenteController controller) {
        this.interfacciaUtenteController = controller;
    }
    
    public void initializeForAdd(ObservableList<Contatto> rubrica) {
        this.rubrica = rubrica;
        addButton.setVisible(true);
        editButton.setVisible(false);
    }


    public void initializeForEdit(Contatto contatto, ObservableList<Contatto> rubrica) {
        this.rubrica = rubrica;
        this.contattoEsistente = contatto;
        populateFields(contatto);
        addButton.setVisible(false);
        editButton.setVisible(true);
    }

    /**
     * @brief Riferimento al controller dell'interfaccia principale.
     */
    private InterfacciaUtenteController interfacciaUtenteController;

 
    private boolean isEditing = false; //flag per distinguere tra aggiunta e modifica
    private Contatto contattoEsistente; //contatto da modificare se presente
    private ObservableList<Contatto> rubrica; //Lista dei contatti 

    /**
     * @brief Imposta il riferimento al controller dell'interfaccia principale.
     * @param[in] controller Riferimento al controller principale.
     * @post Viene aggiornato il riferimento al controller principale.
     */
    
    //parte di add
    
    public void initializeForAdd(ObservableList<Contatto> rubrica) {
        this.rubrica = rubrica;
        addButton.setVisible(true);
        editButton.setVisible(false); // Nascondi il bottone di modifica
    }
    
    public void setInterfacciaUtenteController(InterfacciaUtenteController controller) {
        this.interfacciaUtenteController = controller;
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
    void switchToInterfaccia(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();        //ottiene lo stage a partire da dove si trova cancelButton  
        stage.close();  // Chiude la finestra
        // Torna alla finestra principale (InterfacciaUtente)

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
    void addContact(ActionEvent event) throws IOException, CampoNonValidoException {
        String nome = nameField.getText();
        ContattoValidator.validateName(nome);

        String cognome = surnameField.getText();
        ContattoValidator.validateSurname(cognome);

        String note = noteField.getText();

        List<String> numeri = new LinkedList<>();
        List<String> emails = new LinkedList<>();
        List<TextField> numeroFields = Arrays.asList(number2Field, number3Field); //numeri facoltativi

        // Gestione primo numero  di telefono obbligatorio
        String primoNumero = number1Field.getText().trim();
        if (primoNumero.isEmpty()) {
            showErrorDialog("Numero obbligatorio", "Il primo numero di telefono è obbligatorio.");
            return;
        }

        ContattoValidator.validatePhoneNumber(primoNumero);
        if (ContattoValidator.isNumeroDuplicato(interfacciaUtenteController.getListaContatti(), primoNumero)) {
            if (!showConfirmationDialog("Numero duplicato", "Il numero " + primoNumero + " già esiste. Vuoi comunque aggiungerlo?")) {
                return;
            }
        }
        numeri.add(primoNumero);

        //gestione numeri di telefono facoltativi
        for (TextField numero : numeroFields) {
            String n = numero.getText().trim(); //prende numero levando gli spazi
            if (!n.isEmpty()) {
                ContattoValidator.validatePhoneNumber(n);
                if (ContattoValidator.isNumeroDuplicato(interfacciaUtenteController.getListaContatti(), n)) {
                    if (!showConfirmationDialog("Numero Duplicato", "Il numero " + numero + " già esiste. Vuoi comunque aggiungerlo?")) {
                        return;
                    }
                }
                numeri.add(n);
            }
        }

        //Gestione Email facoltative       
        List<TextField> emailFields = Arrays.asList(email1Field, email2Field, email3Field);
        for (TextField email : emailFields) {
            String e = email.getText().trim();
            if (!e.isEmpty()) {
                ContattoValidator.validateEmail(e);
                if (ContattoValidator.isEmailDuplicata(interfacciaUtenteController.getListaContatti(), e)) {
                    if (!showConfirmationDialog("Email Duplicata", "L'email " + e + " già esiste. Vuoi comunque aggiungerla?")) {
                        return;
                    }
                }
                emails.add(e);
            }

        }

        //verifica contatto duplicato
        if (ContattoValidator.isContattoDuplicato(interfacciaUtenteController.getListaContatti(), nome, cognome)) {
            if (!showConfirmationDialog("Contatto Duplicato", "Un contatto: " + nome + " " + cognome + " già esiste. Vuoi comunque aggiungerlo?")) {
                switchToInterfaccia(event);
                return;
            }
        }

        Contatto nuovoContatto = new Contatto(nome, cognome, numeri, emails, note);

        interfacciaUtenteController.getListaContatti().add(nuovoContatto);
        interfacciaUtenteController.ordinaContatti();
        //aggiornamento file 
        SalvaCaricaRubrica.salvaRubrica((ObservableList<Contatto>) interfacciaUtenteController.getListaContatti());
        switchToInterfaccia(event);

    }

    /**
 * @brief Mostra una finestra di dialogo di conferma con due opzioni: Sì e No.
 * 
 * @param titolo Il titolo della finestra di dialogo.
 * @param messaggio Il messaggio da visualizzare nella finestra di dialogo.
 * 
 * @pre Il titolo e il messaggio non devono essere nulli o vuoti.
 * @post Viene mostrata una finestra di dialogo e viene restituito il risultato della selezione dell'utente.
 * 
 * @return true se l'utente seleziona "Sì", false se seleziona "No".
 */
    private boolean showConfirmationDialog(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, messaggio, ButtonType.YES, ButtonType.NO);
        alert.setTitle(titolo);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }
    
/**
 * @brief Mostra una finestra di dialogo di errore con un messaggio specificato.
 * 
 * @param titolo Il titolo della finestra di dialogo.
 * @param messaggio Il messaggio da visualizzare nella finestra di dialogo.
 * 
 * @pre Il titolo e il messaggio non devono essere nulli o vuoti.
 * @post Viene mostrata una finestra di dialogo con il messaggio di errore.
 */

    private void showErrorDialog(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

    /**
     * @brief Inizializza il controller.
     *
     * Metodo eseguito automaticamente per configurare il controller all'avvio.
     *
     * @param[in] url URL di inizializzazione.
     * @param[in] rb Risorsa per la localizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    //parte di modifica
/**
 * @brief Inizializza la finestra per la modifica di un contatto esistente.
 * 
 * @param contatto Il contatto da modificare.
 * @param rubrica La rubrica che contiene il contatto esistente.
 * 
 * @pre Il parametro contatto deve essere un oggetto valido e non null.
 * @pre La rubrica deve essere un oggetto ObservableList<Contatto> non null.
 * @post Il form sarà popolato con i dati del contatto esistente.
 * @post Il pulsante "Modifica" sarà visibile, mentre il pulsante "Aggiungi" sarà nascosto.
 */

    public void initializeForEdit(Contatto contatto, ObservableList<Contatto> rubrica) {
        this.rubrica = rubrica;
        this.contattoEsistente = contatto;
        populateFields(contatto);
        addButton.setVisible(false);
        editButton.setVisible(true); // Mostra il bottone di modifica
    }

    /**
 * @brief Modifica i dati di un contatto esistente nella rubrica.
 * 
 * @param event L'evento ActionEvent generato dal click sul pulsante "Modifica".
 * 
 * @pre Tutti i campi del form devono essere validati correttamente prima della modifica.
 * @pre La variabile contattoEsistente deve essere un oggetto valido presente nella rubrica.
 * @post La rubrica sarà aggiornata con i nuovi dati del contatto.
 * @post I dati aggiornati saranno salvati utilizzando il metodo SalvaCaricaRubrica.salvaRubrica.
 * 
 * @throws NomeNonValidoException Se il nome inserito non è valido.
 * @throws CognomeNonValidoException Se il cognome inserito non è valido.
 * @throws NumeroNonValidoException Se uno o più numeri di telefono non sono validi.
 * @throws EmailNonValidaException Se uno o più indirizzi email non sono validi.
 */
    @FXML
    private void editContact(ActionEvent event) {
        try {
            // Recupera e valida i dati dai campi
            String nome = nameField.getText().trim();
            ContattoValidator.validateName(nome);

            String cognome = surnameField.getText().trim();
            ContattoValidator.validateSurname(cognome);

            List<String> numeri = Arrays.asList(
                    number1Field.getText().trim(),
                    number2Field.getText().trim(),
                    number3Field.getText().trim()
            );

            for (String numero : numeri) {
                if (!numero.isEmpty()) {
                    ContattoValidator.validatePhoneNumber(numero);
                }
            }

            List<String> emails = Arrays.asList(
                    email1Field.getText().trim(),
                    email2Field.getText().trim(),
                    email3Field.getText().trim()
            );

            for (String email : emails) {
                if (!email.isEmpty()) {
                    ContattoValidator.validateEmail(email);
                }
            }

            String note = noteField.getText().trim();

            // Creazione del nuovo contatto
            Contatto nuovoContatto = new Contatto(nome, cognome, numeri, emails, note);

            // Trova e sostituisci il contatto esistente
            int index = rubrica.indexOf(contattoEsistente);
            if (index != -1) {
                rubrica.set(index, nuovoContatto);
            }

            // Salva i dati aggiornati
            SalvaCaricaRubrica.salvaRubrica(rubrica);

            // Chiudi la finestra
            closeWindow();

        } catch (CampoNonValidoException ex) {
            showErrorDialog("Errore di Validazione", ex.getMessage());
        }
    }

/**
 * @brief Popola i campi del form con i dati di un contatto esistente.
 * 
 * @param contatto Il contatto i cui dati devono essere inseriti nei campi del form.
 * 
 * @pre Il parametro contatto deve essere un oggetto valido e non null.
 * @post I campi del form saranno riempiti con i dati del contatto:
 *       - Nome e cognome nei rispettivi campi.
 *       - Numeri di telefono e email nei campi appropriati, fino a un massimo di tre per ciascun tipo.
 *       - Note nel campo delle note.
 */
    private void populateFields(Contatto contatto) {
        nameField.setText(contatto.getNome());
        surnameField.setText(contatto.getCognome());
        noteField.setText(contatto.getNote());

        List<String> numeri = contatto.getNumeri();
        if (numeri.size() > 0) number1Field.setText(numeri.get(0));
        if (numeri.size() > 1) number2Field.setText(numeri.get(1));
        if (numeri.size() > 2) number3Field.setText(numeri.get(2));

        List<String> emails = contatto.getEmails();
        if (emails.size() > 0) email1Field.setText(emails.get(0));
        if (emails.size() > 1) email2Field.setText(emails.get(1));
        if (emails.size() > 2) email3Field.setText(emails.get(2));
    }

/**
 * @brief Annulla l'operazione in corso e chiude la finestra corrente.
 * 
 * @pre La finestra deve essere aperta.
 * @post La finestra corrente viene chiusa.
 */
    private void cancelOperation() {
        closeWindow();
    }

    
/**
 * @brief Chiude la finestra corrente dell'interfaccia utente.
 * 
 * @pre Il campo nameField deve essere associato a una scena e a una finestra.
 * @post La finestra corrente viene chiusa, interrompendo l'interazione dell'utente.
 */
    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

}