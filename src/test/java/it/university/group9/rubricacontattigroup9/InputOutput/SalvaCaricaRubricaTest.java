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
 * @author imacpro
 */
public class SalvaCaricaRubricaTest {

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
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of salvaRubrica method, of class SalvaCaricaRubrica.
     */
    @Test
    public void testSalvaRubrica() {
        System.out.println("salvaRubrica");
        ObservableList<Contatto> rubrica = FXCollections.observableArrayList(new Contatto("Debora", "Villano", Arrays.asList("3923816991"), Arrays.asList("deboravillano1@gmail.com"), "Nota Debbi"));
        SalvaCaricaRubrica.salvaRubrica(rubrica);
        // Verifica che non vengano lanciate eccezioni durante il salvataggio
        assertDoesNotThrow(() -> SalvaCaricaRubrica.salvaRubrica(rubrica));
    }

    /**
     * Test of caricaRubrica method, of class SalvaCaricaRubrica.
     */
    @Test
    public void testCaricaRubrica() {
        System.out.println("caricaRubrica");
        ObservableList<Contatto> rubrica = FXCollections.observableArrayList(new Contatto("Debora", "Villano", Arrays.asList("3923816991"), Arrays.asList("deboravillano1@gmail.com"), "Nota Debbi"));
        ObservableList<Contatto> result = SalvaCaricaRubrica.caricaRubrica();
        assertNotNull(result);
        assertEquals(rubrica.size(), result.size());
        // Compare the content of the loaded rubrica with expectedRubrica
        for (int i = 0; i < rubrica.size(); i++) {
            Contatto expectedContact = rubrica.get(i);
            Contatto actualContact = result.get(i);
            assertEquals(expectedContact.getNome(), actualContact.getNome());
            assertEquals(expectedContact.getCognome(), actualContact.getCognome());
            assertEquals(expectedContact.getNumeri(), actualContact.getNumeri());
            assertEquals(expectedContact.getEmails(), actualContact.getEmails());
            assertEquals(expectedContact.getNote(), actualContact.getNote());
        }
    }

}
