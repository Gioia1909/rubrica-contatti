package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.exceptions.ContattoGiaAggiuntoException;
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
        // Rimuovi duplicati utilizzando un HashSet
        preferitiList = FXCollections.observableArrayList(new HashSet<>(SalvaCaricaPreferiti.caricaRubricaPreferiti()));
        listViewPreferiti.setItems(preferitiList);
        configurePreferitiListView();

        System.out.println("Preferiti caricati senza duplicati: " + preferitiList);
    }

    private void configurePreferitiListView() {
        listViewPreferiti.setCellFactory(listView -> new ListCell<Contatto>() {
            @Override
            protected void updateItem(Contatto contatto, boolean empty) {
                super.updateItem(contatto, empty);
                if (empty || contatto == null) {
                    setText(null);
                } else {
                    // Mostra solo il cognome e il nome
                    setText(contatto.getCognome() + " " + contatto.getNome());
                }
            }
        });
    }

    
//Non sappiamo se la implementeremo
     
    @FXML
    private void editAction(ActionEvent event) {
    }

    
   /**
    * @brief Gestisce l'apertura di una finestra popup per la selezione di un contatto.
    * 
    * Questo metodo carica un file FXML per il popup, crea una nuova finestra (`Stage`) 
    *  
    * La finestra popup blocca l'interazione con altre finestre finché non viene chiusa. 
    * 
    *
    * @pre Il file FXML "SelezionaContattiDaRubrica.fxml" deve essere presente e valido.
    * @post Una nuova finestra popup viene aperta, e l'utente non può interagire con altre finestre fino alla chiusura del popup.
    * 
    * @throws IOException Se il file FXML non può essere caricato o se si verifica un errore durante la creazione della finestra.
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
     * @throws ContattoGiaAggiuntoException Se il contatto è già presente nei preferiti
     */
    @FXML
    private void addPrefButton(ActionEvent event) throws IOException, ContattoGiaAggiuntoException {
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
    
    
}