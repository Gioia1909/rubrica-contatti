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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalvaCaricaPreferitiTest {

    private static final String TEST_FILE = "rubricapreferiti.json";
    private ObservableList<Contatto> testFavorites;

    @BeforeEach
    public void setUp() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE)); // Assicurati che il file non esista prima dei test

    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE)); // Rimuovi il file dopo il test
    }

    /**
     * Test of saveFavoritesAddressBook method, of class SalvaCaricaPreferiti.
     */
    @Test
    public void testSaveFavoritesAddressBook() {
        testFavorites = FXCollections.observableArrayList(
                new Contatto("Arianna", "Paletta", Arrays.asList("123456789"), Arrays.asList("arianna.paletta@gmail.com"), "Nota 1", ""),
                new Contatto("Gioia", "Iannuzzi", Arrays.asList("0987654321"), Arrays.asList("gioia.iannuzzi@gmail.com"), "Nota 2", "")
        );
        SalvaCaricaPreferiti.saveFavoritesAddressBook(testFavorites);
        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "Il file rubricapreferiti.json esiste.");
    }

    /**
     * Test of loadFavoritesAddressBook method, of class SalvaCaricaPreferiti.
     */
    @Test
    public void testLoadFavoritesAddressBook() {
        testFavorites = FXCollections.observableArrayList(
                new Contatto("Arianna", "Paletta", Arrays.asList("123456789"), Arrays.asList("arianna.paletta@gmail.com"), "Nota 1", ""),
                new Contatto("Gioia", "Iannuzzi", Arrays.asList("0987654321"), Arrays.asList("gioia.iannuzzi@gmail.com"), "Nota 2", "")
        );
        SalvaCaricaPreferiti.saveFavoritesAddressBook(testFavorites);
        ObservableList<Contatto> loadedFavorites = SalvaCaricaPreferiti.loadFavoritesAddressBook();
        // Verifica che i dati caricati siano uguali ai dati originali
        assertNotNull(loadedFavorites, "La lista caricata non deve essere nulla.");
        assertEquals(testFavorites.size(), loadedFavorites.size(), "Le liste non hanno lo stesso numero di elementi.");
        // Verifica che ogni contatto sia uguale
        for (int i = 0; i < testFavorites.size(); i++) {
            assertEquals(testFavorites.get(i).getName(), loadedFavorites.get(i).getName());
            assertEquals(testFavorites.get(i).getSurname(), loadedFavorites.get(i).getSurname());
            assertEquals(testFavorites.get(i).getNumbers(), loadedFavorites.get(i).getNumbers());
            assertEquals(testFavorites.get(i).getEmails(), loadedFavorites.get(i).getEmails());
            assertEquals(testFavorites.get(i).getNote(), loadedFavorites.get(i).getNote());
            assertEquals(testFavorites.get(i).isFav(), loadedFavorites.get(i).isFav());
        }

    }
}