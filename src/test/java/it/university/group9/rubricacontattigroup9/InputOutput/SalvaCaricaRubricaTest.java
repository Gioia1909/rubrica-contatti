/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.InputOutput;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.util.Arrays;
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
 * @author Gruppo09
 */
public class SalvaCaricaRubricaTest {

    private ObservableList<Contatto> rubrica; // Variabile di classe

    public SalvaCaricaRubricaTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        // Inizializzazione della rubrica da usare nei test
        rubrica = FXCollections.observableArrayList(
            new Contatto("Debora", "Villano", Arrays.asList("3923816991"), Arrays.asList("deboravillano1@gmail.com"), "Nota Debbi")
        );
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSalvaRubrica() {
        System.out.println("salvaRubrica");
        // Salva la rubrica
        SalvaCaricaRubrica.saveAddressBook(rubrica);
        // Verifica che non vengano lanciate eccezioni durante il salvataggio
        assertDoesNotThrow(() -> SalvaCaricaRubrica.saveAddressBook(rubrica));
    }

    @Test
    public void testCaricaRubrica() {
        System.out.println("caricaRubrica");
        // Carica la rubrica e confronta i risultati
        ObservableList<Contatto> result = SalvaCaricaRubrica.loadAddressBook();
        assertNotNull(result);
        assertEquals(rubrica.size(), result.size());
        // Confronta il contenuto della rubrica caricata con quella salvata
        for (int i = 0; i < rubrica.size(); i++) {
            Contatto expectedContact = rubrica.get(i);
            Contatto actualContact = result.get(i);
            assertEquals(expectedContact.getName(), actualContact.getName());
            assertEquals(expectedContact.getSurname(), actualContact.getSurname());
            assertEquals(expectedContact.getNumbers(), actualContact.getNumbers());
            assertEquals(expectedContact.getEmails(), actualContact.getEmails());
            assertEquals(expectedContact.getNote(), actualContact.getNote());
        }
    }
}
