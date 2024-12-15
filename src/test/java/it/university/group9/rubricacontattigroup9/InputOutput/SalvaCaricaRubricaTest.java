/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package it.university.group9.rubricacontattigroup9.InputOutput;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ari19
 */
public class SalvaCaricaRubricaTest {
   
    private static final String TEST_FILE = "rubrica.json";
    private static final String TEST_FILE_CSV = "rubrica.csv";
    private ObservableList<Contatto> testContacts;

   
    public SalvaCaricaRubricaTest() {
    }
   
    @BeforeAll
    public static void setUpClass() {
    }
   
    @AfterAll
    public static void tearDownClass() {
    }
   
      @BeforeEach
    public void setUp() throws IOException {
        // Pulizia dei file di test prima di ogni test
        Files.deleteIfExists(Paths.get(TEST_FILE));
        Files.deleteIfExists(Paths.get(TEST_FILE_CSV));
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Rimuovi i file di test dopo ogni test
        Files.deleteIfExists(Paths.get(TEST_FILE));
        Files.deleteIfExists(Paths.get(TEST_FILE_CSV));
    }

    /**
     * Test of saveAddressBook method, of class SalvaCaricaRubrica.
     */  
    @Test
    public void testSaveAddressBook() {
       testContacts = FXCollections.observableArrayList(
                new Contatto("Arianna", "Paletta", Arrays.asList("123456789"), Arrays.asList("arianna.paletta@gmail.com"), "Nota 1"),
                new Contatto("Gioia", "Iannuzzi", Arrays.asList("0987654321"), Arrays.asList("gioia.iannuzzi@gmail.com"), "Nota 2")
        );
        SalvaCaricaRubrica.saveAddressBook(testContacts);
         // Verifica che il file esista
        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "Il file  è stato creato.");
}

    /**
     * Test of loadAddressBook method, of class SalvaCaricaRubrica.
     */
    @Test
    public void testLoadAddressBook() {
            testContacts = FXCollections.observableArrayList(
                new Contatto("Arianna", "Paletta", Arrays.asList("123456789"), Arrays.asList("arianna.paletta@gmail.com"), "Nota 1"),
                new Contatto("Gioia", "Iannuzzi", Arrays.asList("0987654321"), Arrays.asList("gioia.iannuzzi@gmail.com"), "Nota 2")
        );
       
         SalvaCaricaRubrica.saveAddressBook(testContacts);
        ObservableList<Contatto> loadedContact = SalvaCaricaRubrica.loadAddressBook();
        // Verifica che i dati caricati siano uguali ai dati originali
        assertNotNull(loadedContact, "La lista caricata non deve essere nulla.");
        assertEquals(testContacts.size(), loadedContact.size(), "Le liste non hanno lo stesso numero di elementi.");
        // Verifica che ogni contatto sia uguale
        for (int i = 0; i < testContacts.size(); i++) {
            assertEquals(testContacts.get(i).getName(), loadedContact.get(i).getName());
            assertEquals(testContacts.get(i).getSurname(), loadedContact.get(i).getSurname());
            assertEquals(testContacts.get(i).getNumbers(), loadedContact.get(i).getNumbers());
            assertEquals(testContacts.get(i).getEmails(), loadedContact.get(i).getEmails());
            assertEquals(testContacts.get(i).getNote(), loadedContact.get(i).getNote());
            assertEquals(testContacts.get(i).isFav(), loadedContact.get(i).isFav());
    }
    }

    /**
     * Test of exportToCSV method, of class SalvaCaricaRubrica.
     */
    @Test
    public void testExportToCSV() throws Exception {
         testContacts = FXCollections.observableArrayList(
                new Contatto("Arianna", "Paletta", Arrays.asList("123456789"), Arrays.asList("arianna.paletta@gmail.com"), "Nota 1"),
                new Contatto("Gioia", "Iannuzzi", Arrays.asList("0987654321"), Arrays.asList("gioia.iannuzzi@gmail.com"), "Nota 2")
        );
       // Esporta i contatti in un file CSV
        SalvaCaricaRubrica.exportToCSV(testContacts);
         // Verifica che il file CSV sia stato creato
        assertTrue(Files.exists(Paths.get(TEST_FILE_CSV)));        // Verifica i contenuti del file CSV
        List<String> lines = Files.readAllLines(Paths.get(TEST_FILE_CSV));
        assertEquals(3, lines.size()); // Intestazione + 2 righe di contatti
        assertEquals("NOME;COGNOME;TELEFONO;EMAIL", lines.get(0));
        assertEquals("Arianna;Paletta;123456789;arianna.paletta@gmail.com", lines.get(1));
        assertEquals("Gioia;Iannuzzi;0987654321;gioia.iannuzzi@gmail.com", lines.get(2));

   
    }
   
}