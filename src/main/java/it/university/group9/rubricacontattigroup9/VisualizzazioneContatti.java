/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import java.util.List;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;


/**
 *
 * @author Gruppo09
 */
public abstract class VisualizzazioneContatti {
    
    @FXML
    private Button editButton, addToFavorite;
    //deleteButton; // Nascondi il pulsante elimina

    @FXML
    private ImageView editImageView, deleteImageView;
    @FXML
    private Label nameField, surnameField, number1Field, number2Field, number3Field;
    @FXML
    private Label email1Field, email2Field, email3Field, noteField, defaultText;
    
    
    public void updateContactDetails(Contatto selectedContact) {
        if (selectedContact == null) {
        clearFields();
        return;
    }
        defaultText.setVisible(false);
        //deleteButton.setVisible(false); // Nascondi il pulsante elimina
        deleteImageView.setVisible(true);
        editButton.setVisible(true);
        addToFavorite.setVisible(true);
        editImageView.setVisible(true);
        
        // Aggiorna i campi con visibilità
    nameField.setText(selectedContact.getName() != null ? selectedContact.getName() : "");
    nameField.setVisible(true);

    surnameField.setText(selectedContact.getSurname() != null ? selectedContact.getSurname() : "");
    surnameField.setVisible(true);

    number1Field.setText(selectedContact.getNumbers().size() > 0 ? selectedContact.getNumbers().get(0) : "");
    number1Field.setVisible(true);

    number2Field.setText(selectedContact.getNumbers().size() > 1 ? selectedContact.getNumbers().get(1) : "");
    number2Field.setVisible(selectedContact.getNumbers().size() > 1);

    number3Field.setText(selectedContact.getNumbers().size() > 2 ? selectedContact.getNumbers().get(2) : "");
    number3Field.setVisible(selectedContact.getNumbers().size() > 2);

    email1Field.setText(selectedContact.getEmails().size() > 0 ? selectedContact.getEmails().get(0) : "");
    email1Field.setVisible(true);

    email2Field.setText(selectedContact.getEmails().size() > 1 ? selectedContact.getEmails().get(1) : "");
    email2Field.setVisible(selectedContact.getEmails().size() > 1);

    email3Field.setText(selectedContact.getEmails().size() > 2 ? selectedContact.getEmails().get(2) : "");
    email3Field.setVisible(selectedContact.getEmails().size() > 2);

    noteField.setText(selectedContact.getNote() != null ? selectedContact.getNote() : "");
    noteField.setVisible(true);

    defaultText.setVisible(false); // Nasconde il messaggio predefinito
}

    

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

    
    private void visibleNumberDetails(Contatto selectedContact){
        List<String> numbers = selectedContact.getNumbers();
        number1Field.setVisible(true);
        number1Field.setText(numbers.get(0));
        if(numbers.size()>1){
            number2Field.setVisible(true);
            number2Field.setText(numbers.get(1));
            
        }else number2Field.setVisible(false);
        
        if(numbers.size()>2){
            number3Field.setVisible(true);
            number3Field.setText(numbers.get(2));
        }else number3Field.setVisible(false);
        
    }
    
    private void visibleEmailDetails(Contatto selectedContact){
        List<String> emails = selectedContact.getEmails();
        if(!emails.isEmpty()){
            email1Field.setVisible(true);
            email1Field.setText(emails.get(0));
        }else email1Field.setVisible(false);
        
        if(emails.size() > 1){
            email2Field.setVisible(true);
            email2Field.setText(emails.get(1));
        }else email2Field.setVisible(false);
        
        if(emails.size() > 2){
            email3Field.setVisible(true);
            email3Field.setText(emails.get(2));
        }else email3Field.setVisible(false);
        
    }
    
    
    protected void resetContactDetails() {
    defaultText.setVisible(true); // Mostra il testo di default
    //deleteButton.setVisible(false); // Nascondi il pulsante elimina
    deleteImageView.setVisible(false); // Nascondi l'icona di eliminazione
    editButton.setVisible(false); // Nascondi il pulsante modifica
    editImageView.setVisible(false); // Nascondi l'icona di modifica
    addToFavorite.setVisible(false);
    
    nameField.setVisible(false);
    surnameField.setVisible(false);
    number1Field.setVisible(false);
    number2Field.setVisible(false);
    number3Field.setVisible(false);
    email1Field.setVisible(false);
    email2Field.setVisible(false);
    email3Field.setVisible(false);
    noteField.setVisible(false);
}
    
    
}
