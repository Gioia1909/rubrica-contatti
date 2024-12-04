package it.university.group9.rubricacontattigroup9;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class MenuPreferitiController implements Initializable {
    @FXML
    private ListView<Contatto> listViewPreferiti;
    @FXML
    private Button editButton;
    @FXML
    private Button addPrefButton;
    @FXML
    private Button secondaryButton;
    
    private ObservableList<Contatto> preferitiList;
    private ObservableList<Contatto> contactList; // Riferimento alla lista utenti
    

    @FXML
    private void switchToInterfaccia() throws IOException {
        App.setRoot("InterfacciaUtente");
    }
    
    // Metodo per ricevere la lista utenti
    public void setContactList(ObservableList<Contatto> contactList) {
        this.contactList = contactList;
    }

    public void initialize(URL location, ResourceBundle resources) {
        preferitiList=FXCollections.observableArrayList();
        // Associa la lista degli utenti alla ListView
        listViewPreferiti.setItems(preferitiList); // Preferiti parte vuota
    }
    
    

    @FXML
    private void editAction(ActionEvent event) {
    }

    @FXML
    private void addPrefButton(ActionEvent event) {
    }
    
    private Label creaLabelContatto(Contatto contatto) {
        Label label = new Label();
        label.setText(
            contatto.getNome() + " " + contatto.getCognome() + "\n" +
            String.join(", ", contatto.getNumeri()) + "\n" +
            String.join(", ", contatto.getEmails())
        );
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10; -fx-background-color: #f0f0f0;");
        label.setWrapText(true);
        return label;
    }
    
    
}