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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.input.*;
import javafx.stage.Stage;

public class InterfacciaAggiungiController implements Initializable {

    /**
     * @brief Riferimento al controller dell'interfaccia principale.
     */
    private InterfacciaUtenteController interfacciaUtenteController;

    /**
     * @name Componenti FXML
     */
    ///@{
    @FXML
    private Button addButton;
    /**
     * < Bottone per aggiungere un nuovo contatto.
     */
    @FXML
    private Button cancelButton;
    /**
     * < Bottone per tornare all'interfaccia principale.
     */
    @FXML
    private TextField nameField;
    /**
     * < Campo di testo per il nome del contatto.
     */
    @FXML
    private TextField surnameField;
    /**
     * < Campo di testo per il cognome del contatto.
     */
    @FXML
    private TextField email1Field;
    /**
     * < Campo di testo per la prima email del contatto.
     */
    @FXML
    private TextField email2Field;
    /**
     * < Campo di testo per la seconda email del contatto.
     */
    @FXML
    private TextField email3Field;
    /**
     * < Campo di testo per la terza email del contatto.
     */
    @FXML
    private TextField number1Field;
    /**
     * < Campo di testo per il primo numero di telefono del contatto.
     */
    @FXML
    private TextField number2Field;
    /**
     * < Campo di testo per il secondo numero di telefono del contatto.
     */
    @FXML
    private TextField number3Field;
    /**
     * < Campo di testo per il terzo numero di telefono del contatto.
     */
    @FXML
    private TextField noteField;
    /**
     * < Campo di testo per le note del contatto.
     */
    @FXML
    private Button editButton;
    ///@}

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
     * @throws NomeNonValidoException Se il nome fornito non è valido.
     * @throws CognomeNonValidoException Se il cognome fornito non è valido.
     * @throws NumeroNonValidoException Se uno dei numeri di telefono forniti
     * non è valido.
     * @throws EmailNonValidaException Se una delle email fornite non è valida.
     *
     * @see NomeValidator, CognomeValidator, NumeroValidator, EmailValidator,
     * SalvaCaricaRubrica
     *
     */
    @FXML
    void addContact(ActionEvent event) throws IOException, NomeNonValidoException, CognomeNonValidoException, NumeroNonValidoException, EmailNonValidaException {
        String nome = nameField.getText();
        NomeValidator.validateName(nome);

        String cognome = surnameField.getText();
        CognomeValidator.validateSurname(cognome);

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

        NumeroValidator.validatePhoneNumber(primoNumero);
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
                NumeroValidator.validatePhoneNumber(n);
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
                EmailValidator.validateEmail(e);
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
     * Mostra una finestra di conferma e restituisce true se l'utente sceglie
     * "YES".
     */
    private boolean showConfirmationDialog(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, messaggio, ButtonType.YES, ButtonType.NO);
        alert.setTitle(titolo);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

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

    public void initializeForEdit(Contatto contatto, ObservableList<Contatto> rubrica) {
        this.rubrica = rubrica;
        this.contattoEsistente = contatto;
        populateFields(contatto);
        addButton.setVisible(false);
        editButton.setVisible(true); // Mostra il bottone di modifica
    }

    @FXML
    private void editContact(ActionEvent event) {
        try {
            // Recupera e valida i dati dai campi
            String nome = nameField.getText().trim();
            NomeValidator.validateName(nome);

            String cognome = surnameField.getText().trim();
            CognomeValidator.validateSurname(cognome);

            List<String> numeri = Arrays.asList(
                    number1Field.getText().trim(),
                    number2Field.getText().trim(),
                    number3Field.getText().trim()
            );

            for (String numero : numeri) {
                if (!numero.isEmpty()) {
                    NumeroValidator.validatePhoneNumber(numero);
                }
            }

            List<String> emails = Arrays.asList(
                    email1Field.getText().trim(),
                    email2Field.getText().trim(),
                    email3Field.getText().trim()
            );

            for (String email : emails) {
                if (!email.isEmpty()) {
                    EmailValidator.validateEmail(email);
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

        } catch (NomeNonValidoException | CognomeNonValidoException | NumeroNonValidoException | EmailNonValidaException ex) {
            showErrorDialog("Errore di Validazione", ex.getMessage());
        }
    }

    /**
     * Popola i campi del modulo con i dati del contatto esistente.
     *
     * @param contatto Il contatto da modificare.
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
     * Metodo per annullare la modifica senza salvare.
     */
    private void cancelOperation() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

}