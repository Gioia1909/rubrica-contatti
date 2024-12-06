/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @file InterfacciaAggiungiController.java
 * @brief Controller per la gestione dell'interfaccia di aggiunta di un contatto.
 * 
 * In questa classe si gestiscono gli eventi e le interazioni per l'aggiunta 
 * di un contatto, come l'inserimento del nome, del cognome, delle email, dei numeri di telefono e delle eventuali note.
 * 
 * 
 * 
 * @author Gruppo09
 * 
 * @date 05/12/2024
 */
public class InterfacciaAggiungiController implements Initializable {
/**
     * @brief Riferimento al controller dell'interfaccia principale, InterfacciaUtente
     */
    private InterfacciaUtenteController interfacciaUtenteController;  
    
    /**
     * @brief Bottone per aggiungere un nuovo contatto.
     */
    @FXML
    private Button addButton;
/**
     * @brief Bottone per tornare indietro.
     */
    @FXML
    private Button cancelButton;

    /**
     * @brief Campo di testo per il nome del contatto.
     */
    @FXML
    private TextField nameField;
    /**
     * @brief Campo di testo per il cognome del contatto.
     */
    @FXML
    private TextField surnameField;
    /**
     * @brief Campo di testo per l'email del contatto.
     */
    @FXML
    private TextField email1Field;
    /**
     * @brief Campo di testo per la seconda email del contatto.
     */
    @FXML
    private TextField email2Field;
    /**
     * @brief Campo di testo per la terza email del contatto.
     */
    @FXML
    private TextField email3Field;
    /**
     * @brief Campo di testo per il numero del contatto.
     */
    @FXML
    private TextField number1Field;
    /**
     * @brief Campo di testo per il secondo numero del contatto.
     */
    @FXML
    private TextField number2Field;
    /**
     * @brief Campo di testo per il terzo numero del contatto.
     */
    @FXML
    private TextField number3Field;
    /**
     * @brief Campo di testo per le eventuali note associate al contatto.
     */
      @FXML
    private TextField noteField;
    
    
   /**
     * @brief Imposta il riferimento al controller dell'interfaccia principale .
     * 
     * @param[in] controller Riferimento al controller principale.
     */
      
       public void setInterfacciaUtenteController(InterfacciaUtenteController controller) {
        this.interfacciaUtenteController = controller;
    }
      
       
    /**
     * @brief Torna all'interfaccia principale.
     * 
     * Cambia la scena alla schermata principale quando viene premuto il pulsante di annullamento.
     * 
     * @param[in] event Evento del mouse che ha scatenato l'azione.
     * @throws IOException Se non è possibile caricare la scena.
     */
     @FXML
    void switchToInterfaccia(ActionEvent event) throws IOException {
          Stage stage = (Stage) cancelButton.getScene().getWindow();        //ottiene lo stage a partire da dove si trova cancelButton  
          stage.close();  // Chiude la finestra
        // Torna alla finestra principale (InterfacciaUtente)
      
    }
 
    /**
     * @brief Aggiunge un nuovo contatto alla lista e chiude la finestra di aggiunta contatti
     * 
     * Questo metodo raccoglie i dati inseriti nei campi di testo per creare un nuovo contatto,
     * aggiungendolo alla lista di contatti esistente. Successivamente, aggiorna il file di 
     * rubrica e chiude la finestra di aggiunta.
     * 
     * @param[in] event Evento del mouse che ha scatenato l'azione.
     * @throws IOException Se non è possibile chiudere la finestra o se si verifica un errore durante il salvataggio della rubrica
     */
    
    @FXML
    void addContact(ActionEvent event) throws IOException {    
          String nome = nameField.getText();
          String cognome = surnameField.getText();
          String note = noteField.getText();
          List<String> numeri = new LinkedList<>();
          List<String> emails = new LinkedList<>();
          
    // Aggiungi numeri se non vuoti
    if (!number1Field.getText().isEmpty()) numeri.add(number1Field.getText().trim());
    if (!number2Field.getText().isEmpty()) numeri.add(number2Field.getText().trim());
    if (!number3Field.getText().isEmpty()) numeri.add(number3Field.getText().trim());

    // Aggiungi email se non vuote
    if (!email1Field.getText().isEmpty()) emails.add(email1Field.getText().trim());
    if (!email2Field.getText().isEmpty()) emails.add(email2Field.getText().trim());
    if (!email3Field.getText().isEmpty()) emails.add(email3Field.getText().trim());

         Contatto nuovoContatto = new Contatto(nome,cognome,numeri,emails,note);
      
         interfacciaUtenteController.getListaContatti().add(nuovoContatto);
         //aggiornamento file 
        SalvaCaricaRubrica.salvaRubrica((ObservableList<Contatto>) interfacciaUtenteController.getListaContatti());
           switchToInterfaccia(event);
       
    }
       
       
       
    /**
     * @brief Inizializza il controller.
     * 
     * 
     * 
     * @param[in] url URL di inizializzazione.
     * @param[in] rb Risorsa per la localizzazione.
     */ 
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
    }    
    
}
