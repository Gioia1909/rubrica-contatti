/**
 * @file ContattoValidator.java
 * @brief Classe che valida i contatti.
 *
 * Questa classe contiene metodi per verificare la duplicazione dei numeri di
 * telefono e dei contatti.
 *
 * @author Gruppo09
 * @date 05/12/2024
 *
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import java.util.List;

/**
 * @brief Validatore per i contatti.
 *
 * Questa classe verifica se un contatto o un numero di telefono è duplicato.
 */
public class ContattoValidator {
    public static void validateName(String name) throws CampoNonValidoException {
        if (!name.isEmpty() && !Character.isAlphabetic(name.charAt(0))) {
            throw new CampoNonValidoException("il formato del nome non è valido");
        }
    }

    public static void validateSurname(String surname) throws CampoNonValidoException {
        if (!surname.isEmpty() && !Character.isAlphabetic(surname.charAt(0))) {    //surname.trim() elimina eventuali spazi all'inizio o alla fine della stringa per evitare che un 
            //surnome apparentemente vuoto (ma con spazi) sia considerato valido.
            throw new CampoNonValidoException("\"Il formato del cognome non è valido");
        }
    }

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

    public static void validateEmail(List<String> emails) throws CampoNonValidoException {
        for (String email : emails) {
            if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                throw new CampoNonValidoException("Il formato della mail non è valido");
            }
        }
    }

    public static void validateFields(String name, String surname, List<String> numbers) throws CampoNonValidoException {
        if (name.isEmpty() && surname.isEmpty()) {
            throw new CampoNonValidoException("Devi inserire almeno un nome o un cognome ");
        }

        //System.out.println("numero 1 :" + numbers.get(0) + "numero 2" + numbers.get(1) + "numero 3 :" + numbers.get(2));
        // Controllo per numeri di telefono
        if (numbers.get(0).equals("") && numbers.get(1).equals("") && numbers.get(2).equals("")) {
            throw new CampoNonValidoException("Devi inserire almeno un numero di telefono.");
        }

    }

}
