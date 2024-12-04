package it.university.group9.rubricacontattigroup9;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        listViewPreferiti.setItems(preferitiList); // la list view si aggiorna quando la lista dei preferiti si aggoorna
        
        
        //configurazioen CellFactory --> Visualizzazione di un singolo campo della Lista 
        listViewPreferiti.setCellFactory(lv -> new ListCell<Contatto>(){ //riceve un pararametro lv chr crea un oggetto ListCell
            //ogni cella della ListView è un'istanza ListCell
            
            //update item viene chiamata quando una cella deve essere aggiornata o popolata, qui definiamo l'effettiva visualizzazione
            
            @Override
            protected void updateItem(Contatto contatto, boolean empty){
                //contatto è l'elemento da visualizzare, empty dice che la cella è vuota
                
                if(empty || contatto == null){
                    setText(null); // Pulisce il testo della cella se vuota
                    setGraphic(null); // Pulisce il contenuto grafico della cella
                }else{
                    setGraphic(creaLabelContatto(contatto));
                }
            }
        });
    }
    
    

    @FXML
    private void editAction(ActionEvent event) {
    }

    @FXML
    private void addPrefButton(ActionEvent event) throws IOException {
        if(contactList!=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelezionaContattiDaRubrica.fxml"));
            //si crea un oggetto FXMLLoader per caricare il file FXML, con getClass e Resource prendiamo il file 
            
            Parent root = loader.load(); //legge il contenuto del file FXML e ritorna un oggetto parent come scena
            
            //creo un oggetto SelezionaContattiDaRubrica per passargli qualcosa nel "costruttore"
            SelezionaContattiDaRubricaController popupController = loader.getController();
            //così accediamo ai metodi 
            
            popupController.setContacts(contactList, preferitiList); //passiamo la lista della rubrica e quella dei preferiti da aggiornare
            
            
            Scene scene = addPrefButton.getScene(); // Ottieni la scena corrente dal pulsante che ha generato l'azione
            scene.setRoot(root); // Imposta il nuovo root, sostituisce la scena corrente con l'aatra
            
        }else{
            System.out.println("La lista dei contatti non è disponibile.");
        }
       
    }
    
    private Label creaLabelContatto(Contatto contatto) {
        Label label = new Label();
        label.setText(contatto.getNome() + " " + contatto.getCognome()); //visualizzazione del nome e cognome
  
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10; -fx-background-color: #f0f0f0;");
        label.setWrapText(false); //non va a capo
        return label;
    }  
}