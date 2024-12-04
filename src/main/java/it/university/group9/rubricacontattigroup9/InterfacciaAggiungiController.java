/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ari19
 */
public class InterfacciaAggiungiController implements Initializable {

    private InterfacciaUtenteController interfacciaUtenteController;  // Riferimento al controller di InterfacciaUtente
    
    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField email1Field;

    @FXML
    private TextField email2Field;

    @FXML
    private TextField email3Field;

    @FXML
    private TextField number1Field;

    @FXML
    private TextField number2Field;

    @FXML
    private TextField number3Field;
    
      @FXML
    private TextField noteField;
    
    
       public void setInterfacciaUtenteController(InterfacciaUtenteController controller) {
        this.interfacciaUtenteController = controller;
    }
      
      @FXML
    void switchToInterfaccia(MouseEvent event) {
         App.setRoot("InterfacciaUtente");

    }
 
     
    @FXML
    void addContact(MouseEvent event) {
          App.setRoot("InterfacciaUtente");
    }
       
       
       
       
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
