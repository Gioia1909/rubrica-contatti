package it.university.group9.rubricacontattigroup9;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InterfacciaUtenteController implements Initializable {

    @FXML
    private Button primaryButton;
    private TextField textf;
    
    @FXML
    private Button searchButton;
    @FXML
    private Button deleteButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
public void initialize(URL location, ResourceBundle resources) {
    if (searchButton != null && searchButton.getScene() != null) {
        searchButton.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }
    
    
}
}
