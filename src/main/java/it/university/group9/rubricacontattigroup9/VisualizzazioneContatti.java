package it.university.group9.rubricacontattigroup9;

import java.util.Collections;
import java.util.List;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @brief Classe astratta per visualizzare i dettagli del contatto nell'interfaccia grafica.
 * Gestisce l'aggiornamento, la cancellazione e il reset delle informazioni del contatto.
 * 
 * @author Gruppo09
 */
public abstract class VisualizzazioneContatti {

    // Componenti FXML dell'interfaccia utente
    @FXML
    private Button editButton;

    @FXML
    private ToggleButton favoriteButton;
    @FXML
    private ImageView favoriteImageView, profilePicImageView;
    @FXML
    private Label nameField, surnameField, number1Field, number2Field, number3Field;
    @FXML
    private Label email1Field, email2Field, email3Field, noteField, defaultText;

    @FXML
    private Label phoneLabel, emailLabel, noteLabel;


    /**
     * @brief Aggiorna i dettagli del contatto visualizzato in base al contatto selezionato.
     * Se nessun contatto è selezionato, cancella i campi.
     *
     * @param[in] selectedContact il contatto i cui dettagli verranno visualizzati
     *
     * @post I dettagli del contatto vengono aggiornati nei campi dell'interfaccia utente.
     */
    public void updateContactDetails(Contatto selectedContact) {
        // Verifica iniziale
        System.out.println("Verifica campi FXML:");
        System.out.println("number1Field: " + (number1Field == null ? "null" : "OK"));
        System.out.println("nameField: " + (nameField == null ? "null" : "OK"));
        System.out.println("surnameField: " + (surnameField == null ? "null" : "OK"));

        // Se nessun contatto è selezionato, pulisce i campi e termina
        if (selectedContact == null) {
            clearFields();
            return;
        }

        // Verifica che tutti i campi FXML siano inizializzati
        if (number1Field == null || nameField == null || surnameField == null) {
            System.err.println("Errore: uno o più campi FXML non sono stati inizializzati.");
            return;
        }

        // Nasconde il testo predefinito e mostra i pulsanti principali
        defaultText.setVisible(false);
        editButton.setVisible(true);
        favoriteButton.setVisible(true);
        favoriteImageView.setVisible(true);

        // Aggiorna nome
        if (selectedContact.getName() != null) {
            nameField.setText(selectedContact.getName());
            nameField.setVisible(true);
        } else {
            nameField.setText("");
            nameField.setVisible(false);
        }

        // Aggiorna cognome
        if (selectedContact.getSurname() != null) {
            surnameField.setText(selectedContact.getSurname());
            surnameField.setVisible(true);
        } else {
            surnameField.setText("");
            surnameField.setVisible(false);
        }

        // Aggiorna numeri di telefono
        List<String> numbers = selectedContact.getNumbers();
        if (numbers == null) {
            numbers = Collections.emptyList();
        }

        phoneLabel.setVisible(!numbers.isEmpty()); // Mostra/ nasconde l'etichetta telefono

        if (numbers.size() > 0) {
            number1Field.setText(numbers.get(0));
            number1Field.setVisible(true);
            number1Field.setManaged(true);
        } else {
            number1Field.setText("");
            number1Field.setVisible(false);
        }

        if (numbers.size() > 1) {
            number2Field.setText(numbers.get(1));
            number2Field.setVisible(true);
            number2Field.setManaged(true);
        } else {
            number2Field.setText("");
            number2Field.setVisible(false);
        }

        if (numbers.size() > 2) {
            number3Field.setText(numbers.get(2));
            number3Field.setVisible(true);
            number3Field.setManaged(true);
        } else {
            number3Field.setText("");
            number3Field.setVisible(false);
        }

        // Aggiorna email
        List<String> emails = selectedContact.getEmails();
        if (emails == null) {
            emails = Collections.emptyList();
        }

        if(emails.get(0) == "" && emails.get(1) == "" && emails.get(2) == ""){
            emailLabel.setVisible(false);
        }else emailLabel.setVisible(true);
        if (emails.size() > 0) {
            email1Field.setText(emails.get(0));
            email1Field.setVisible(true);
            email1Field.setManaged(true);
            //emailLabel.setVisible(true); // Mostra/ nasconde l'etichetta email
        } 

        if (emails.size() > 1) {
            email2Field.setText(emails.get(1));
            email2Field.setVisible(true);
            email2Field.setManaged(true);
        } else {
            email2Field.setVisible(false);
        }

        if (emails.size() > 2) {
            email3Field.setText(emails.get(2));
            email3Field.setVisible(true);
            email3Field.setManaged(true);
        } 
    

        // Aggiorna nota
        if (!selectedContact.getNote().equals("")) {
            noteField.setText(selectedContact.getNote());
            noteField.setVisible(true);
            noteLabel.setVisible(true);
        } else {
            noteField.setVisible(false);
            noteLabel.setVisible(false);
        }

        // Aggiorna immagine profilo
        if (selectedContact.getGen().equals("default")) {
            profilePicImageView.setImage(new Image(getClass().getResourceAsStream("user.png")));
        } else if ("male".equals(selectedContact.getGen())) {
            profilePicImageView.setImage(new Image(getClass().getResourceAsStream("man.png")));
        } else {
            profilePicImageView.setImage(new Image(getClass().getResourceAsStream("woman.png")));
        }
        profilePicImageView.setVisible(true);
    }

    /**
     * @brief Pulisce tutti i campi della GUI.
     * 
     * @pre I campi devono essere visibili e correttamente settati per evitare visibilità errata.
     * @post Tutti i campi dell'interfaccia utente vengono resettati e il messaggio predefinito viene visualizzato.
     */
    private void clearFields() {
        nameField.setText("");
        nameField.setVisible(false);

        surnameField.setText("");
        surnameField.setVisible(false);

        number1Field.setText("");
        number1Field.setVisible(false);

        number2Field.setText("");
        number2Field.setVisible(false);

        number3Field.setText("");
        number3Field.setVisible(false);

        email1Field.setText("");
        email1Field.setVisible(false);

        email2Field.setText("");
        email2Field.setVisible(false);

        email3Field.setText("");
        email3Field.setVisible(false);

        noteField.setText("");
        noteField.setVisible(false);

        defaultText.setVisible(true); // Mostra il messaggio predefinito
    }

    protected void resetContactDetails() {
        defaultText.setVisible(true); // Mostra il testo di default
        editButton.setVisible(false); // Nascondi il pulsante modifica
        favoriteButton.setVisible(false);
        favoriteImageView.setVisible(false);
        emailLabel.setVisible(false);
        noteLabel.setVisible(false);
        phoneLabel.setVisible(false);

        profilePicImageView.setVisible(false);
        nameField.setVisible(false);
        surnameField.setVisible(false);
        number1Field.setVisible(false);
        number1Field.setManaged(false);
        number2Field.setVisible(false);
        number2Field.setManaged(false);
        number3Field.setVisible(false);
        number3Field.setManaged(false);
        email1Field.setVisible(false);
        email1Field.setManaged(false);
        email2Field.setVisible(false);
        email2Field.setManaged(false);
        email3Field.setVisible(false);
        email3Field.setManaged(false);
        noteField.setVisible(false);

    }

}
