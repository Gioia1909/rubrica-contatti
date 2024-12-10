/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.InputOutput;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;
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
public class SalvaCaricaPreferitiTest {
    
    public SalvaCaricaPreferitiTest() {
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
     * Test of salvaRubricaPreferiti method, of class SalvaCaricaPreferiti.
     */
    @Test
    public void testSalvaRubricaPreferiti() {
        System.out.println("salvaRubricaPreferiti");
        ObservableList<Contatto> rubrica = FXCollections.observableArrayList(new Contatto("Debora", "Villano", Arrays.asList("3923816991"), Arrays.asList("deboravillano1@gmail.com"), "Nota Debbi"));
        SalvaCaricaPreferiti.salvaRubricaPreferiti(rubrica);
        assertDoesNotThrow(() -> SalvaCaricaPreferiti.salvaRubricaPreferiti(rubrica));       
    }

    /**
     * Test of caricaRubricaPreferiti method, of class SalvaCaricaPreferiti.
     */
    @Test
    public void testCaricaRubricaPreferiti() {
        System.out.println("caricaRubricaPreferiti");
        ObservableList<Contatto> expResult = FXCollections.observableArrayList(new Contatto("Debora", "Villano", Arrays.asList("3923816991"), Arrays.asList("deboravillano1@gmail.com"), "Nota Debbi"));
        ObservableList<Contatto> result = SalvaCaricaPreferiti.caricaRubricaPreferiti();
        // Verifica che la lista caricata contenga gli stessi dati di quella di esempio
        assertNotNull(result); // La lista non deve essere null
        assertEquals(expResult.size(), result.size()); // La dimensione delle liste deve essere uguale
        assertEquals(expResult.get(0).getNome(), result.get(0).getNome()); // Controllo del nome
        assertEquals(expResult.get(0).getCognome(), result.get(0).getCognome()); // Controllo del cognome
        assertEquals(expResult.get(0).getNumeri(), result.get(0).getNumeri()); // Controllo del telefono
        assertEquals(expResult.get(0).getEmails(), result.get(0).getEmails()); // Controllo dell'email
        assertEquals(expResult.get(0).getNote(), result.get(0).getNote()); // Controllo delle note
    }
    
}
