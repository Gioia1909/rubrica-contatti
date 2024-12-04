package it.university.group9.rubricacontattigroup9;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class MenuPreferitiController implements Initializable {
    @FXML
    private ListView<Label> listViewPreferiti;
    @FXML
    private Button editButton;
    @FXML
    private Button addPrefButton;
    @FXML
    private Button secondaryButton;

    @FXML
    private void switchToInterfaccia() throws IOException {
        App.setRoot("InterfacciaUtente");
    }

    public void initialize(URL location, ResourceBundle resources) {
    // Se hai una ListView definita nel file FXML:
    if (listViewPreferiti != null) {
        // Puoi aggiungere elementi di esempio alla ListView
        Contatto mario = new Contatto(
        "Mario", 
        "Rossi", 
        Arrays.asList("123456789"), 
        Arrays.asList("mario.rossi@email.com"), 
        "Amico"
        );
        Label labelMario = creaLabelContatto(mario);
        listViewPreferiti.getItems().add(labelMario);
        }
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