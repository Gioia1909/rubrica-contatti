/**
 * @file InterfacciaAggiungiController.java
 * @brief Controller per la gestione dell'interfaccia grafica per l'aggiunta di contatti.
 * 
 * Questa classe gestisce gli eventi e le interazioni dell'utente per aggiungere un nuovo contatto
 * alla rubrica. Include la validazione dei dati inseriti, la gestione di duplicati e l'aggiornamento
 * della rubrica persistente.
 * 
 * @date 07/12/2024
 * @author Gruppo09
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import it.university.group9.rubricacontattigroup9.exceptions.CognomeNonValidoException;
import it.university.group9.rubricacontattigroup9.exceptions.EmailNonValidaException;
import it.university.group9.rubricacontattigroup9.exceptions.NomeNonValidoException;
import it.university.group9.rubricacontattigroup9.exceptions.NumeroNonValidoException;
import it.university.group9.rubricacontattigroup9.validators.CognomeValidator;
import it.university.group9.rubricacontattigroup9.validators.ContattoValidator;
import it.university.group9.rubricacontattigroup9.validators.EmailValidator;
import it.university.group9.rubricacontattigroup9.validators.NomeValidator;
import it.university.group9.rubricacontattigroup9.validators.NumeroValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @class InterfacciaAggiungiController
 * @brief Controller per la gestione dell'interfaccia grafica di aggiunta contatti.
 */
public class InterfacciaAggiungiController implements Initializable {
/**
     * @brief Riferimento al controller dell'interfaccia principale.
     */
    private InterfacciaUtenteController interfacciaUtenteController;  
    
    /** @name Componenti FXML */
    ///@{
    @FXML private Button addButton;       /**< Bottone per aggiungere un nuovo contatto. */
    
    @FXML private Button cancelButton;    /**< Bottone per tornare all'interfaccia principale. */

    @FXML private TextField nameField;    /**< Campo di testo per il nome del contatto. */
    
    @FXML  private TextField surnameField; /**< Campo di testo per il cognome del contatto. */
    
    @FXML private TextField email1Field;  /**< Campo di testo per la prima email del contatto. */
    
    @FXML private TextField email2Field;  /**< Campo di testo per la seconda email del contatto. */
   
    @FXML private TextField email3Field;  /**< Campo di testo per la terza email del contatto. */

    @FXML private TextField number1Field;   /**< Campo di testo per il primo numero di telefono del contatto. */
    
    @FXML private TextField number2Field;   /**< Campo di testo per il secondo numero di telefono del contatto. */
    
    @FXML private TextField number3Field;   /**< Campo di testo per il terzo numero di telefono del contatto. */

      @FXML private TextField noteField;        /**< Campo di testo per le note del contatto. */
    ///@}
    
    /**
     * @brief Imposta il riferimento al controller dell'interfaccia principale.
     * @param[in] controller Riferimento al controller principale.
     */
       public void setInterfacciaUtenteController(InterfacciaUtenteController controller) {
        this.interfacciaUtenteController = controller;
    }
      
       
    /**
     * @brief Torna all'interfaccia principale.
     * 
     * Questo metodo chiude la finestra corrente e ritorna alla schermata principale dell'applicazione.
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
     * @brief Aggiunge un nuovo contatto alla lista e chiude la finestra di aggiunta contatti aggiornando la rubrica
     * 
     * Questo metodo permette di aggiungere un nuovo contatto con nome, cognome, numeri di telefono,
     * email e note alla rubrica. Valida i dati inseriti, verifica la presenza di duplicati e aggiorna
     * la rubrica sia nella lista in memoria che nel file di salvataggio.
     * 
     * @pre I parametri da inserire nel contatto non devono essere vuoti 
     * @post Viene aggiunto un contatto in rubrica
     *
     * @param[in] event Evento del mouse che ha scatenato l'azione.
     *
     * @throws IOException Se si verifica un errore durante il salvataggio della rubrica su file.
     * @throws NomeNonValidoException Se il nome fornito non è valido.
     * @throws CognomeNonValidoException Se il cognome fornito non è valido.
     * @throws NumeroNonValidoException Se uno dei numeri di telefono forniti non è valido.
     * @throws EmailNonValidaException Se una delle email fornite non è valida.
     * 
     */
    @FXML
    void addContact(ActionEvent event) throws IOException, NomeNonValidoException, CognomeNonValidoException, NumeroNonValidoException, EmailNonValidaException {    
          String nome = nameField.getText();
          NomeValidator.validateName(nome);

          String cognome = surnameField.getText();
          CognomeValidator.validateSurname(cognome);

          String note = noteField.getText();

          List<String> numeri = new LinkedList<>();

          List<String> emails = new LinkedList<>();

          List<TextField> numeroFields = Arrays.asList(number1Field, number2Field, number3Field);
        
          for(TextField numero : numeroFields){
              if(!numero.getText().trim().isEmpty()){
                NumeroValidator.validatePhoneNumber(numero.getText().trim());
            if(ContattoValidator.isNumeroDuplicato(interfacciaUtenteController.getListaContatti(), numero.getText().trim())){
                  Alert alertNumber= new Alert(AlertType.CONFIRMATION, "Il numero: " + numero.getText() + " già esiste, vuoi comunque aggiungerlo?", ButtonType.YES, ButtonType.NO);

                  alertNumber.showAndWait();

                  if(alertNumber.getResult() == ButtonType.NO){
                    
                     continue;
                   }
                 for(TextField numero2 : numeroFields){
                     
              if(ContattoValidator.isContattoDuplicato(interfacciaUtenteController.getListaContatti(), nome, cognome, numero2.getText())){
                  String contatto= nome + " " + cognome;
                      Alert alertContact = new Alert(AlertType.CONFIRMATION, "Il contatto: " + contatto + " già esiste, vuoi comunque aggiungerlo?", ButtonType.YES, ButtonType.NO);
                        alertContact.showAndWait();

                        if (alertContact.getResult() == ButtonType.NO) {
                               continue; 
                            }
                        
                  }
              
              
            }
              }
              numeri.add(numero.getText().trim());
              }
          }
            // Aggiungi numeri se non vuoti
            if (!number1Field.getText().isEmpty()) numeri.add(number1Field.getText().trim());
            if (!number2Field.getText().isEmpty()) numeri.add(number2Field.getText().trim());
            if (!number3Field.getText().isEmpty()) numeri.add(number3Field.getText().trim());
            String contatto= nome + " " + cognome;
            List<TextField> emailFields= Arrays.asList(email1Field, email2Field, email3Field);
            for(TextField email : emailFields){
                if(!email.getText().trim().isEmpty())
                EmailValidator.validateEmail(email.getText());
            }
         
            // Aggiungi email se non vuote
            if (!email1Field.getText().isEmpty()) emails.add(email1Field.getText().trim());
            if (!email2Field.getText().isEmpty()) emails.add(email2Field.getText().trim());
            if (!email3Field.getText().isEmpty()) emails.add(email3Field.getText().trim());
           

         Contatto nuovoContatto = new Contatto(nome,cognome,numeri,emails,note);
      
         interfacciaUtenteController.getListaContatti().add(nuovoContatto);
         interfacciaUtenteController.ordinaContatti(); 
         //aggiornamento file 
        SalvaCaricaRubrica.salvaRubrica((ObservableList<Contatto>) interfacciaUtenteController.getListaContatti());
           switchToInterfaccia(event);
       
    }
       
    /**
     * @brief Inizializza il controller.
     * 
     * Metodo eseguito automaticamente per configurare il controller all'avvio. 
     * 
     * @param[in] url URL di inizializzazione.
     * @param[in] rb Risorsa per la localizzazione.
     */ 
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
    }    
    
}
