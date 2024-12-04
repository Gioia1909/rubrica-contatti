/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author imacpro
 */
public class SelezionaContattiDaRubricaController implements Initializable {

    @FXML
    private TextField searchBar;
    @FXML
    private ListView<Contatto> contactListView;
    @FXML
    private Button addButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button searchButton;
    
    //mantengo riferimento alla lista dei contatti della rubrica
    private ObservableList <Contatto> rubrica;
    
    //contatto selezionato 
    private Contatto selectedContact;
    
    //riferimento alla lista dei preferiti
    private ObservableList <Contatto> rubricaPreferiti;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html#selectionModelProperty--
        //così specifichiamo che la selezione del contatto nella lista è singolo 
        
    }
    
    public void setContacts(ObservableList<Contatto> rubrica, ObservableList <Contatto> rubricaPreferiti){
        this.rubrica=rubrica; //prendo la rubrica normale
        this.rubricaPreferiti =rubricaPreferiti;
        //configurazione filtro per la ricerca
        //creo una lista filtrata, che inizialmente mostyra tutti i contatti, grazie al predicato p->true
        
        
        FilteredList<Contatto> contattiFiltrati = new FilteredList<>(rubrica, p->true);
        //aggiungo un listener alla textProperty della barra di ricerca. Questo si attiva 
        //quando il contenuto della barra di ricerca cambia
        //observable si riferisce alla proprietà osservabile, textProperty
        //oldValue --> valore precedente di textfield
        //newValue --> valore attuale di textfield
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            
            
            contattiFiltrati.setPredicate(contatto ->{ //set predicate accetta un input e restituisce un boolean. 
                //decidiamo se un Contatto deve essere mostrato nella lista filtrata
                //questa operazione viene fatta per tutti i contatti
                
                //contatto-> è una lambda expression, contatto in input
                if(newValue==null || newValue.isEmpty()){
                    return true; //continua a visualizzare la lista della rubrica per intero 
                }
                String lowerCaseFilter = newValue.toLowerCase(); //questo la rende insensitive, mette tutto in minuscolo 
                
                //verifica se la stringa passata da textfield è contenuta dal nome del contatto o dal suo cognome
                return contatto.getNome().toLowerCase().contains(lowerCaseFilter) || contatto.getCognome().toLowerCase().contains(lowerCaseFilter);
            });
        });
        
        //colleghiamo questa lista di contatti filtrata a quella che visualizziamo in questa finestra
        contactListView.setItems(contattiFiltrati);
    }
    
    

    @FXML
    private void handleAddContact(ActionEvent event) throws IOException {
        selectedContact = contactListView.getSelectionModel().getSelectedItem(); // prendo l'elemento selezionato 
        
        if(selectedContact!=null){
            if(!rubricaPreferiti.contains(selectedContact)){
                rubricaPreferiti.add(selectedContact);
                System.out.println("Aggiunto ai preferiti: " + selectedContact.getNome());
            }else{
                 System.out.println("Il contatto è già nei preferiti");
            }
            
        }
        handleClosePopup(event);
        
    }

    @FXML
    private void handleClosePopup(ActionEvent event) throws IOException {
        App.setRoot("MenuPreferiti"); //torniamo al menu dei preferiti 
    }

    /*@FXML Questo non serve se la ricerca è dinamica
    private void handleSearch(ActionEvent event) {
    }*/ 
    
}
