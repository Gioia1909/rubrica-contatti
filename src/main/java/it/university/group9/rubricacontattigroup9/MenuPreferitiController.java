package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
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
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @file MenuPreferitiController.java
 * @brief Controller per la gestione del menu dei contatti preferiti.
 * 
 * @author Gruppo09
 * @date 05/12/2024
 */
public class MenuPreferitiController implements Initializable {
    /**
     * @brief Lista grafica dei contatti preferiti
     */
    @FXML
    private ListView<Contatto> listViewPreferiti;
    
    /**
     * @brief Bottone per la modifica
     */
    @FXML
    private Button editButton;
    
    /**
     * @brief Bottone per aggiungere un contatto ai preferiti
     */
    @FXML
    private Button addPrefButton;
    
    /**
     * @brief Bottone secondario
     */
    @FXML
    private Button secondaryButton;
    
    /**
     * @brief Lista dei contatti preferiti
     */
    private ObservableList<Contatto> preferitiList;
    
    /**
     * @brief Lista di tutti i contatti
     */
    private ObservableList<Contatto> contactList; // Riferimento alla lista utenti
    
    /**
     * @brief Cambia la scena all'interfaccia utente.
     * 
     * @param[in] event L'evento che ha generato l'azione di switch.
     * @throws IOException Se il caricamento della scena fallisce.
     */
    @FXML
    private void switchToInterfaccia() {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaUtente.fxml"));
        Parent root = loader.load();
        
        // Ottieni il controller
        InterfacciaUtenteController controller = loader.getController();

        // Passa la lista di contatti al nuovo controller
        controller.setContactList(this.contactList);

        // Cambia la scena
        Stage stage = (Stage) listViewPreferiti.getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        System.err.println("Errore durante il caricamento dell'interfaccia utente: " + e.getMessage());
        e.printStackTrace();
    }
    }
    
    
    /**
     * @brief Imposta la lista dei contatti.
     * 
     * @param[in] contactList La lista di contatti da impostare.
     */
    public void setContactList(ObservableList<Contatto> contactList) {
        this.contactList = contactList;
    }
    
    /**
     * @brief Inizializza il controller e configura la lista dei preferiti nell'elemento ListView.
     * Configura una Label per la visualizzazione delle celle della lista, chiamando la funzione creaLabelContatto.
     * 
     * @param[in] location URL di localizzazione del file FXML.
     * @param[in] resources Risorse per la localizzazione.
     */
    public void initialize(URL location, ResourceBundle resources) {
        preferitiList=SalvaCaricaPreferiti.caricaRubricaPreferiti();
        
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
    
    
//Non sappiamo se la implementeremo
     
    @FXML
    private void editAction(ActionEvent event) {
    }

    /**
     * @brief Aggiunge un contatto alla lista dei preferiti.
     * 
     * @pre
     * La lista di Contatti non deve essere vuota.
     * @pre
     * L'elemento da aggiungere deve essere presente nella rubrica principale.
     * @post 
     * L'elemento aggiunto deve essere visto nella rubrica preferiti.
     * @param[in] event L'evento che ha generato l'azione di aggiunta.
     * @throws IOException Se il caricamento del popup fallisce.
     */
    
    @FXML
    private void handleOpenPopup() {
        try {
            // Carica il file FXML per il popup
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelezionaContattiDaRubrica.fxml"));
            Parent root = loader.load();

            // Crea una nuova finestra (Stage) per il popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Seleziona Contatto");
            popupStage.setScene(new Scene(root, 336, 400)); // Imposta le dimensioni precise
            popupStage.setResizable(false); // Blocca il ridimensionamento
            popupStage.initModality(Modality.APPLICATION_MODAL); // Blocca interazioni con altre finestre
            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addPrefButton(ActionEvent event) throws IOException {
        if(contactList!=null){
            //debug
             System.out.println("ContactList: " + contactList);
             System.out.println("PreferitiList: " + preferitiList);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelezionaContattiDaRubrica.fxml"));
            //si crea un oggetto FXMLLoader per caricare il file FXML, con getClass e Resource prendiamo il file 
            
            Parent root = loader.load(); //legge il contenuto del file FXML e ritorna un oggetto parent come scena
            
            //creo un oggetto SelezionaContattiDaRubrica per passargli qualcosa nel "costruttore" --> setContacts
            SelezionaContattiDaRubricaController popupController = loader.getController();
            //così accediamo ai metodi 
            
            popupController.setContacts(contactList, preferitiList); //passiamo la lista della rubrica e quella dei preferiti da aggiornare
            
            
            Scene scene = addPrefButton.getScene(); // Ottieni la scena corrente dal pulsante che ha generato l'azione
            // Crea una nuova finestra (Stage) per il popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Seleziona Contatto");
            popupStage.setScene(new Scene(root, 336, 400)); // Imposta le dimensioni precise
            popupStage.setResizable(false); // Blocca il ridimensionamento
            popupStage.initModality(Modality.APPLICATION_MODAL); // Blocca interazioni con altre finestre
            popupStage.show();
            
            
            
            
            //scene.setRoot(root); // Imposta il nuovo root, sostituisce la scena corrente con l'altra
            
        }else{
            System.out.println("La lista dei contatti non è disponibile.");
        }
       
    }
    
    /**
     * @brief Crea una label per visualizzare il nome e cognome del contatto.
     * 
     * @param[in] contatto Il contatto di cui creare la label.
     * @return Label La label con il nome e cognome del contatto.
     */
    private Label creaLabelContatto(Contatto contatto) {
        Label label = new Label();
        label.setText(contatto.getNome() + " " + contatto.getCognome()); //visualizzazione del nome e cognome
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10; -fx-background-color: #f0f0f0;");
        label.setWrapText(false); //non va a capo
        return label;
    }  
}