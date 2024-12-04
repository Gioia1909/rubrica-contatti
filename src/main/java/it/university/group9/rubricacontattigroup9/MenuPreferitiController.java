package it.university.group9.rubricacontattigroup9;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MenuPreferitiController implements Initializable {

    @FXML
    private TextArea scrittaPreferiti;
    @FXML
    private Button editButton;
    @FXML
    private Button addPrefButton;
    @FXML
    private Button secondaryButton;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void editAction(ActionEvent event) {
    }

    @FXML
    private void addPrefButton(ActionEvent event) {
    }
}