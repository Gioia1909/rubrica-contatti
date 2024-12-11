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
    private ListView<Contatto> listViewPreferiti;
    @FXML
    private Button editButton, addPrefButton, deleteButton, searchButton;
    @FXML
    private ImageView editImageView, deleteImageView;
    
    @FXML
    private TextField searchBar;
    @FXML
    private Label nameField, surnameField, number1Field, number2Field, number3Field;
    @FXML
    private Label email1Field, email2Field, email3Field, noteField, defaultText;

    private ObservableList<Contatto> preferitiList;
    private ObservableList<Contatto> contactList;
    private Button viewAddButton;

    @FXML
    private ListView<Contatto> myListView;
    
    public void updateContactDetails(Contatto selectedContact) {
        defaultText.setVisible(false);
        deleteButton.setVisible(true);
        deleteImageView.setVisible(true);
        editButton.setVisible(true);
        editImageView.setVisible(true);
        
        
        nameField.setVisible(true);
        surnameField.setVisible(true);
        
        nameField.setText(selectedContact.getName());
        surnameField.setText(selectedContact.getSurname());
        
        visibleNumberDetails(selectedContact);
        visibleEmailDetails(selectedContact);
        
        if(!selectedContact.getNote().isEmpty()){
            noteField.setVisible(true);
            noteField.setText(selectedContact.getNote());
        }else noteField.setVisible(false);

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
        if(emails.size() > 0){
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
}
