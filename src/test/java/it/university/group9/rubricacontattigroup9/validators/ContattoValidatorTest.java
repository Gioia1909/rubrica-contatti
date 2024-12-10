/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
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
public class ContattoValidatorTest {

    private ObservableList<Contatto> rubrica; // Variabile di classe

    public ContattoValidatorTest() {
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
            new Contatto("Mario", "Rossi", Arrays.asList("1234567890"), Arrays.asList("mario.rossi@gmail.com"), "Nota Mario"),
            new Contatto("Luigi", "Bianchi", Arrays.asList("9876543210"), Arrays.asList("luigi.bianchi@gmail.com"), "Nota Luigi")
        );
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testIsNumeroDuplicato() {
        System.out.println("isNumeroDuplicato");
        assertTrue(ContattoValidator.isNumeroDuplicato(rubrica, "1234567890")); // Numero duplicato
        assertFalse(ContattoValidator.isNumeroDuplicato(rubrica, "1111111111")); // Numero non presente
    }

    @Test
    public void testIsContattoDuplicato() {
        System.out.println("isContattoDuplicato");
        assertTrue(ContattoValidator.isContattoDuplicato(rubrica, "Mario", "Rossi")); // Contatto duplicato
        assertFalse(ContattoValidator.isContattoDuplicato(rubrica, "Anna", "Verdi")); // Contatto non presente
    }

    @Test
    public void testIsEmailDuplicata() {
        System.out.println("isEmailDuplicata");
        assertTrue(ContattoValidator.isEmailDuplicata(rubrica, "mario.rossi@gmail.com")); // Email duplicata
        assertFalse(ContattoValidator.isEmailDuplicata(rubrica, "anna.verdi@gmail.com")); // Email non presente
    }

    @Test
    public void testValidateEmail() {
        System.out.println("validateEmail");
        // Email valida
        assertDoesNotThrow(() -> ContattoValidator.validateEmail("test.email@gmail.com"));
        // Email non valida
        Exception exception = assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validateEmail("test.email"));
        assertEquals("Email non valida", exception.getMessage());
    }

    @Test
    public void testValidateName() {
        System.out.println("validateName");
        // Nome valido
        assertDoesNotThrow(() -> ContattoValidator.validateName("Mario"));
        // Nome non valido
        Exception exception = assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validateName(""));
        assertEquals("Nome non valido", exception.getMessage());
    }

    @Test
    public void testValidatePhoneNumber() {
        System.out.println("validatePhoneNumber");
        // Numero valido
        assertDoesNotThrow(() -> ContattoValidator.validatePhoneNumber("+12345678901"));
        // Numero non valido
        Exception exception = assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validatePhoneNumber("12345"));
        assertEquals("Numero di telefono non valido", exception.getMessage());
    }

    @Test
    public void testValidateSurname() {
        System.out.println("validateSurname");
        // Cognome valido
        assertDoesNotThrow(() -> ContattoValidator.validateSurname("Rossi"));
        // Cognome non valido
        Exception exception = assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validateSurname(""));
        assertEquals("Cognome non valido", exception.getMessage());
    }
}
