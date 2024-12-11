/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import java.util.List;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


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
    
    public void updateContactDetails(Contatto contattoSelezionato) {
        defaultText.setVisible(false);
        deleteButton.setVisible(true);
        deleteImageView.setVisible(true);
        editButton.setVisible(true);
        editImageView.setVisible(true);
        
        
        nameField.setVisible(true);
        surnameField.setVisible(true);
        
        nameField.setText(contattoSelezionato.getNome());
        surnameField.setText(contattoSelezionato.getCognome());
        
        visibleNumberDetails(contattoSelezionato);
        visibleEmailDetails(contattoSelezionato);
        
        if(!contattoSelezionato.getNote().isEmpty()){
            noteField.setVisible(true);
            noteField.setText(contattoSelezionato.getNote());
        }else noteField.setVisible(false);

    }
    
    private void visibleNumberDetails(Contatto contattoSelezionato){
        List<String> numeri = contattoSelezionato.getNumeri();
        number1Field.setVisible(true);
        number1Field.setText(numeri.get(0));
        if(numeri.size()>1){
            number2Field.setVisible(true);
            number2Field.setText(numeri.get(1));
            
        }else number2Field.setVisible(false);
        
        if(numeri.size()>2){
            number3Field.setVisible(true);
            number3Field.setText(numeri.get(2));
        }else number3Field.setVisible(false);
        
    }
    
    private void visibleEmailDetails(Contatto contattoSelezionato){
        List<String> emails = contattoSelezionato.getEmails();
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
