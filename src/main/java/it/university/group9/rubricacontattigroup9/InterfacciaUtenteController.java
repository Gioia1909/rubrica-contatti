package it.university.group9.rubricacontattigroup9;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class InterfacciaUtenteController implements Initializable {
    
    
    
    @FXML
    private Button addButton;

    @FXML
    private ListView<String> myListView;

    @FXML
    private Button primaryButton;

    @FXML
    private TextField textBar;

    @FXML
    private Button searchButton;
    
      
    @FXML
    public void addName(MouseEvent event) {
        myListView.getItems().add(textBar.getText());
    }

    
   
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



