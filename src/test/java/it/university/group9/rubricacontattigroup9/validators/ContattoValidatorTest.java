/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author monto
 */
public class ContattoValidatorTest {

    /**
     * Test of validateName method, of class ContattoValidator.
     */
    @Test
    public void testValidateName() throws Exception {
        // Creazione di contatti con nome valido e non valido
        List<String> numeri = new ArrayList<>(Arrays.asList("1234567890"));
        List<String> emails = new ArrayList<>(Arrays.asList("email@gmail.com"));
        Contatto validContatto = new Contatto("Luigi", "Montonetti", numeri, emails, " ", "");
        Contatto invalidContatto = new Contatto("1Debora", "Villano", numeri, emails, " ", "");

        // Test per nome valido (non deve sollevare eccezioni)
        assertDoesNotThrow(() -> ContattoValidator.validateName(validContatto.getName()));

        // Test per nome non valido (deve sollevare eccezione CampoNonValidoException)
        assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validateName(invalidContatto.getName()));
    }

    @Test
    public void testValidateSurname() throws Exception {
        // Creazione di contatti con cognome valido e non valido
        List<String> numeri = new ArrayList<>(Arrays.asList("1234567890"));
        List<String> emails = new ArrayList<>(Arrays.asList("email@gmail.com"));
        Contatto validContatto = new Contatto("Luigi", "Montonetti", numeri, emails, " ", "");
        Contatto invalidContatto = new Contatto("Debora", "1Villano", numeri, emails, " ", "");

        // Test per cognome valido (non deve sollevare eccezioni)
        assertDoesNotThrow(() -> ContattoValidator.validateSurname(validContatto.getSurname()));;

        // Test per cognome non valido (deve sollevare eccezione CampoNonValidoException)
        assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validateSurname(invalidContatto.getSurname()));
    }

    @Test
    public void testValidatePhoneNumber() throws Exception {
        // Creazione di numeri di telefono validi e non validi
        List<String> validNumbers = new ArrayList<>(Arrays.asList("1234567890", "+391234567890"));
        List<String> invalidNumbers = new ArrayList<>(Arrays.asList("123", "abc123", "a123bc"));

        // Test per numeri validi (non deve sollevare eccezioni)
        assertDoesNotThrow(() -> ContattoValidator.validatePhoneNumber(validNumbers));

        // Test per numeri non validi (deve sollevare eccezione CampoNonValidoException)
        assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validatePhoneNumber(invalidNumbers));
    }

    @Test
    public void testValidateEmail() throws Exception {
        // Creazione di email valide e non valide
        List<String> validEmails = new ArrayList<>(Arrays.asList("email1@example.com", "email2@example.com"));
        List<String> invalidEmails = new ArrayList<>(Arrays.asList("invalid-email", "email@com"));

        // Test per email valide (non deve sollevare eccezioni)
        assertDoesNotThrow(() -> ContattoValidator.validateEmail(validEmails));

        // Test per email non valide (deve sollevare eccezione CampoNonValidoException)
        assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validateEmail(invalidEmails));
    }

    @Test
    public void testValidateFields() throws Exception {
        // Creazione di contatti con numeri e campi validi e non validi
        List<String> validNumbers = new ArrayList<>(Arrays.asList("1234567890", "", ""));
        List<String> invalidNumbers = new ArrayList<>(Arrays.asList("", "", ""));
        Contatto validContatto = new Contatto("Luigi", "Montonetti", validNumbers, Arrays.asList("email@gmail.com", "", "" ), " ", "");
        Contatto invalidContatto = new Contatto("", "", invalidNumbers, Arrays.asList("email@example.com", "", ""), " ", "");
        Contatto invalidContatto2 = new Contatto("", "", validNumbers, Arrays.asList("email@example.com", "", ""), " ", "");
        Contatto invalidContatto3 = new Contatto("Luigi", "Montonetti", invalidNumbers, Arrays.asList("email@example.com"), " ", "");

        // Test per campi validi (non deve sollevare eccezioni)
        assertDoesNotThrow(() -> ContattoValidator.validateFields(validContatto.getName(), validContatto.getSurname(), validContatto.getNumbers()));

        // Test per campi non validi (deve sollevare eccezione CampoNonValidoException)
        assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validateFields(invalidContatto.getName(), invalidContatto.getSurname(), invalidContatto.getNumbers()));
        assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validateFields(invalidContatto2.getName(), invalidContatto2.getSurname(), invalidContatto.getNumbers()));
        assertThrows(CampoNonValidoException.class, () -> ContattoValidator.validateFields(invalidContatto3.getName(), invalidContatto3.getSurname(),invalidContatto.getNumbers()));
    }
}