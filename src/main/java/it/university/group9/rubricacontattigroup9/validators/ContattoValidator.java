/**
 * @file ContattoValidator.java
 * @brief Classe che valida i contatti.
 *
 * Questa classe verifica se i dati inseriti sono in un formato valido 
 *
 * @author Gruppo09
 * @see Contatto
 *
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;



public class ContattoValidator {

    /**
     * @brief Verifica che il nome inizi con una lettera alfabetica.
     * @param[in] name Il nome da validare.
     * @throws CampoNonValidoException Se il formato del nome non è valido.
     */
    public static void validateName(String name) throws CampoNonValidoException {
        if (!name.isEmpty() && !Character.isAlphabetic(name.charAt(0))) {
            throw new CampoNonValidoException("il formato del nome non è valido");
        }
    }

    /**
     * @brief Verifica che il cognome inizi con una lettera alfabetica.
     * @param[in] surname Il cognome da validare.
     * @throws CampoNonValidoException Se il formato del cognome non è valido.
     */

    public static void validateSurname(String surname) throws CampoNonValidoException {
        if (!surname.isEmpty() && !Character.isAlphabetic(surname.charAt(0))) {    //surname.trim() elimina eventuali spazi all'inizio o alla fine della stringa per evitare che un 
            //surnome apparentemente vuoto (ma con spazi) sia considerato valido.
            throw new CampoNonValidoException("\"Il formato del cognome non è valido");
        }
    }

    /**
     * @brief Verifica che ogni numero di telefono sia nel formato valido
     * (opzionale "+" seguito da 10-13 cifre).
     *
     * @param[in] numbers La lista di numeri di telefono da validare.
     * @throws CampoNonValidoException Se uno dei numeri di telefono non è
     * valido.
     */
    public static void validatePhoneNumber(List<String> numbers) throws CampoNonValidoException {
        for (String number : numbers) {

            String cleanedPhoneNumber = number.replaceAll("\\s", "");
            if (!cleanedPhoneNumber.matches("^[+]?[0-9]{10,13}$") && !cleanedPhoneNumber.isEmpty()) {
                /* Possono iniziare con un "+" (opzionale).
            Contengono solo cifre da 0 a 9.
            Hanno una lunghezza compresa tra 10 e 13 caratteri.*/
                throw new CampoNonValidoException("Il formato del numero di telefono non è valido");
            }
        }
    }

    /**
     * @brief Verifica che ogni indirizzo email sia nel formato corretto (es.
     * nome@dominio.com).
     *
     * @param[in] emails La lista di email da validare.
     * @throws CampoNonValidoException Se uno degli indirizzi email non è
     * valido.
     */
    public static void validateEmail(List<String> emails) throws CampoNonValidoException {
        for (String email : emails) {
            if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                throw new CampoNonValidoException("Il formato della mail non è valido");
            }
        }
    }

    /**
     * @brief Verifica che almeno un nome o cognome e almeno un numero di
     * telefono siano inseriti.
     *
     * @param[in] name Il nome da validare.
     * @param[in] surname Il cognome da validare.
     * @param[in] numbers La lista di numeri di telefono da controllare.
     * @throws CampoNonValidoException Se non viene inserito almeno un nome o
     * cognome o almeno un numero di telefono.
     */
    public static void validateFields(String name, String surname, List<String> numbers) throws CampoNonValidoException {
        if (name.isEmpty() && surname.isEmpty()) {
            throw new CampoNonValidoException("Devi inserire almeno un nome o un cognome ");
        }
        
        // Controllo per numeri di telefono
        if (numbers.get(0).equals("") && numbers.get(1).equals("") && numbers.get(2).equals("")) {
            throw new CampoNonValidoException("Devi inserire almeno un numero di telefono.");
        }

    }

}
