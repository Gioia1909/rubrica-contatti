/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.MenuPreferitiController;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author imacpro
 */
public class MenuPreferitiControllerTest {
    
    private MenuPreferitiController instance;
    private ObservableList <Contatto> contactList;
    private ObservableList <Contatto> preferitiList;
    
    
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Inizio del testing per il MenuPreferiti.");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("Test Menu Preferiti Completato");
    }
    
    @BeforeEach
    public void setUp() {
        instance = new MenuPreferitiController();
        contactList = FXCollections.observableArrayList();
        preferitiList = FXCollections.observableArrayList();
        
        instance.setListViewPreferiti(new ListView());
        
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
        contactList = null;
        preferitiList = null;
    }

    /**
     * Test of setContactList method, of class MenuPreferitiController.
     */
    @Test
    public void testSetContactList() {
        System.out.println("setContactList");
        
        //Simulo l'inserimento di alcuni dati da aggiungere alla lista normale
        Contatto contatto1 = new Contatto ("Mario", "Rossi", Arrays.asList("082535203"), Arrays.asList("mario.rossi@gmail.com"), " ");
        Contatto contatto2 = new Contatto ("Luigi", "Bianchi", Arrays.asList("082535204"), Arrays.asList("luigi.bianchi@hotmail.com"), " ");
        contactList.add(contatto1);
        contactList.add(contatto2);
        
        //imposto la lista dei contatti normli nei preferiti
        instance.setContactList(contactList);
        
        //controllo che la lista sia stata impostata correttamente
        assertEquals(contactList, instance.getContactList());
    }

    /**
     * Test of initialize method, of class MenuPreferitiController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        
        Contatto contatto1 = new Contatto ("Mario", "Rossi", Arrays.asList("082535203"), Arrays.asList("mario.rossi@gmail.com"), " ");
        Contatto contatto2 = new Contatto ("Luigi", "Bianchi", Arrays.asList("082535204"), Arrays.asList("luigi.bianchi@hotmail.com"), " ");
        preferitiList.add(contatto1);
        preferitiList.add(contatto2);
        
        //salvo la lista creata 
        SalvaCaricaPreferiti.salvaRubricaPreferiti(preferitiList);
        
        //chiamata metodo da testare 
        instance.setPreferitiList(preferitiList); // Simula il caricamento della lista
        instance.initialize(null, null);
        
        //Controlla che i preferiti 
        assertEquals(preferitiList, instance.getListViewPreferiti().getItems());
    }
    /**
     * Test dell'azione di eleininazione dal menu dei preferiti
     */

    @Test
    public void testDeleteAction(){
        System.out.println("Test deleteAction");
        
        //prepara la lista di preferiti
        Contatto contatto1 = new Contatto ("Mario", "Rossi", Arrays.asList("082535203"), Arrays.asList("mario.rossi@gmail.com"), " ");
        Contatto contatto2 = new Contatto ("Luigi", "Bianchi", Arrays.asList("082535204"), Arrays.asList("luigi.bianchi@hotmail.com"), " ");
        preferitiList.add(contatto1);
        preferitiList.add(contatto2);
        instance.setPreferitiList(preferitiList);
        
        //simulazione Selezione di un contatto e chiamata Delete 
        instance.getListViewPreferiti().getSelectionModel().select(contatto1);
        instance.deleteAction(new ActionEvent());
        
        //verifica che il contatto1 sia stato rimosso 
        assertEquals(1, preferitiList.size(), "Dovrebbe rimanere un solo contatto nei preferiti.");
        assertFalse(preferitiList.contains(contatto1), "Il contatto non è stato rimosso dai preferiti.");
    }
    
    /**
     * Test dell'azione di ricerca con risultati.
     */
    
    @Test
    private void testSearchAction(ActionEvent event) {
        System.out.println("Test searchAction");

        //preparazione Lista preferiti 
        Contatto contatto1 = new Contatto("Mario", "Rossi", Arrays.asList("082535203"), Arrays.asList("mario.rossi@gmail.com"), " ");
        Contatto contatto2 = new Contatto("Luigi", "Bianchi", Arrays.asList("082535204"), Arrays.asList("luigi.bianchi@hotmail.com"), " ");
        Contatto contatto3 = new Contatto("Anna", "Verdi", Arrays.asList("082535205"), Arrays.asList("anna.verdi@gmail.com"), " ");
        
        preferitiList.addAll(contatto1,contatto2,contatto3);
        
        //Imposta la lista preferiti e la list view
        instance.setPreferitiList(preferitiList);
        instance.getListViewPreferiti().setItems(preferitiList);
        
        //simula l'inserimento di una stringa da barra di ricerca
        TextField searchBar = new TextField();
        instance.setSearchBar(searchBar); // Collegamento alla TextField
        searchBar.setText("Luigi"); // Simula il testo inserito nella barra di ricerca
        
        //ricerca
        instance.searchAction(new ActionEvent());
        
        
        //verifica che la lista filtrata contenga solo il contatto corrispondente
        ObservableList <Contatto> filteredList = instance.getListViewPreferiti().getItems();
        assertEquals(1, filteredList.size(), "La lista filtrata dovrebbe contenere un solo elemento.");
        assertTrue(filteredList.contains(contatto2), "Il contatto Luigi Bianchi dovrebbe essere nell'elenco filtrato.");
        
        
    }
    /**
     * 
     * Ricerca di un contatto inesistente
     */
    
    @Test
    private void testSearchActionEmpty(ActionEvent event) {
        System.out.println("Test searchAction");

        //preparazione Lista preferiti 
        Contatto contatto1 = new Contatto("Mario", "Rossi", Arrays.asList("082535203"), Arrays.asList("mario.rossi@gmail.com"), " ");
        Contatto contatto2 = new Contatto("Luigi", "Bianchi", Arrays.asList("082535204"), Arrays.asList("luigi.bianchi@hotmail.com"), " ");
        Contatto contatto3 = new Contatto("Anna", "Verdi", Arrays.asList("082535205"), Arrays.asList("anna.verdi@gmail.com"), " ");
        
        preferitiList.addAll(contatto1,contatto2,contatto3);
        
        //Imposta la lista preferiti e la list view
        instance.setPreferitiList(preferitiList);
        instance.getListViewPreferiti().setItems(preferitiList);
        
        //simula l'inserimento di una stringa da barra di ricerca
        TextField searchBar = new TextField();
        instance.setSearchBar(searchBar); // Collegamento alla TextField
        searchBar.setText(""); // Simula il testo inserito nella barra di ricerca
        
        //ricerca
        instance.searchAction(new ActionEvent());
        
        
        //verifica che la lista filtrata contenga solo il contatto corrispondente
        ObservableList <Contatto> filteredList = instance.getListViewPreferiti().getItems();
        assertTrue(filteredList.isEmpty(), "La lista filtrata dovrebbe essere vuota.");
        assertEquals(preferitiList.size(), filteredList.size(), "La lista filtrata dovrebbe contenere tutti gli elementi.");
    }
    
    
    /**
     * Test dell'azione di ricerca senza risultati.
     */
    @Test
    public void testSearchActionNoResults() {
        System.out.println("testSearchActionNoResults");

        Contatto contatto1 = new Contatto("Mario", "Rossi", Arrays.asList("082535203"), Arrays.asList("mario.rossi@gmail.com"), "");
        Contatto contatto2 = new Contatto("Luigi", "Bianchi", Arrays.asList("082535204"), Arrays.asList("luigi.bianchi@hotmail.com"), "");
        preferitiList.addAll(contatto1, contatto2);
        instance.setPreferitiList(preferitiList);
        instance.getListViewPreferiti().setItems(preferitiList);

        TextField searchBar = new TextField();
        instance.setSearchBar(searchBar);
        searchBar.setText("Giovanni");

        instance.searchAction(new ActionEvent());

        ObservableList<Contatto> filteredList = instance.getListViewPreferiti().getItems();
        assertTrue(filteredList.isEmpty(), "La lista filtrata dovrebbe essere vuota.");
    }
    
    
    
}
