package it.university.group9.rubricacontattigroup9;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class InterfacciaUtenteController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<String> myListView;

    @FXML
    private Button primaryButton;
    
    @FXML
    private TextField textBar;

    @FXML
    private Label nameField;

    @FXML
    private Label surnameField;

    @FXML
    private Label email1Field;

    @FXML
    private Label email2Field;

    @FXML
    private Label email3Field;

    @FXML
    private Label number1Field;

    @FXML
    private Label number2Field;

    @FXML
    private Label number3Field;

    //metodo provvisorio per vedere la listView
    @FXML
    public void addContact(MouseEvent event) {
        myListView.getItems().add(textBar.getText());
    }

    //metodo provvisorio per vedere la listView
    @FXML
    public void deleteContact(MouseEvent event) {
        int selezionato = myListView.getSelectionModel().getSelectedIndex();
        myListView.getItems().remove(selezionato);
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
