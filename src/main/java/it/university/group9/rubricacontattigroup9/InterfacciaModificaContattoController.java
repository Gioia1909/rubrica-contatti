package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class InterfacciaModificaContattoController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField number1Field;
    @FXML
    private TextField number2Field;
    @FXML
    private TextField number3Field;
    @FXML
    private TextField email1Field;
    @FXML
    private TextField email2Field;
    @FXML
    private TextField email3Field;
    @FXML
    private TextArea noteField;

    private Contatto contattoModificato;
    private ObservableList<Contatto> rubrica; // Lista della rubrica

    /**
     * Metodo per inizializzare il controller.
     *
     * @param contatto Il contatto da modificare.
     * @param rubrica La lista completa dei contatti.
     */
    public void initialize(Contatto contatto, ObservableList<Contatto> rubrica) {
        // Aggiungi lo stylesheet alla scena direttamente senza verificare la nullità
        Scene scene = root.getScene();
        if (scene != null) {
            scene.getStylesheets().add(getClass().getResource("/it/university/group9/rubricacontattigroup9/style.css").toExternalForm());
        }

        // Verifica che i parametri non siano nulli
        if (contatto == null || rubrica == null) {
            throw new IllegalArgumentException("Contatto o rubrica non possono essere nulli");
        }

        this.contattoModificato = contatto;
        this.rubrica = rubrica;

        // Popola i campi del modulo con i dati del contatto
        if (nameField != null) {
            nameField.setText(contatto.getNome());
        }
        if (surnameField != null) {
            surnameField.setText(contatto.getCognome());
        }
        if (email1Field != null) {
            email1Field.setText(contatto.getEmails() != null && !contatto.getEmails().isEmpty() ? contatto.getEmails().get(0) : "");
        }
        if (email2Field != null) {
            email2Field.setText(contatto.getEmails() != null && contatto.getEmails().size() > 1 ? contatto.getEmails().get(1) : "");
        }
        if (number1Field != null) {
            number1Field.setText(contatto.getNumeri() != null && !contatto.getNumeri().isEmpty() ? contatto.getNumeri().get(0) : "");
        }
        if (number2Field != null) {
            number2Field.setText(contatto.getNumeri() != null && contatto.getNumeri().size() > 1 ? contatto.getNumeri().get(1) : "");
        }
        if (noteField != null) {
            noteField.setText(contatto.getNote() != null ? contatto.getNote() : "");
        }
    }

    /**
     * Metodo per salvare le modifiche al contatto.
     */
    @FXML
    private void saveContact() throws IOException {
        // Creazione di un nuovo oggetto Contatto con i valori aggiornati
        Contatto contattoAggiornato = new Contatto(
                nameField.getText().trim(),
                surnameField.getText().trim(),
                Arrays.asList(
                        number1Field.getText().trim(),
                        number2Field.getText().trim()
                ),
                Arrays.asList(
                        email1Field.getText().trim(),
                        email2Field.getText().trim()
                ),
                noteField.getText().trim()
        );

        // Sostituzione del contatto esistente nella rubrica
        int index = rubrica.indexOf(contattoModificato);
        if (index != -1) {
            rubrica.set(index, contattoAggiornato);
        }

        // Salvataggio della rubrica aggiornata
        SalvaCaricaRubrica.salvaRubrica(rubrica);

        // Chiusura della finestra
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo per annullare la modifica senza salvare.
     */
    @FXML
    private void cancelModification() {
        // Chiude la finestra senza salvare
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
