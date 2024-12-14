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

import it.university.group9.rubricacontattigroup9.Contatto;
import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * @brief Validatore per i contatti.
 *
 * Questa classe verifica se un contatto o un numero di telefono è duplicato.
 */
public class ContattoValidator {

    /**
     * @brief Verifica se un numero di telefono è duplicato.
     *
     * Controlla se il numero di telefono è già presente tra i contatti.
     *
     * @param contacts La lista di contatti da esaminare.
     * @param numbers La lista dei numeri di telefono da verificare.
     * @return true se il numero è già presente, false altrimenti.
     */
    public static boolean isNumberDuplicate(ObservableList<Contatto> contacts, Contatto contactToCheck) {
        for (Contatto contact : contacts) {
            // Escludi il contatto stesso dal confronto
            if (contact == contactToCheck) {
                continue;
            }

            // Itera sui numeri del contatto da verificare
            for (String numberToCheck : contactToCheck.getNumbers()) {
                if (numberToCheck == null || numberToCheck.trim().isEmpty()) {
                    continue; // Salta numeri nulli o vuoti
                }

                String normalizedNumberToCheck = numberToCheck.trim().toLowerCase();

                // Itera sui numeri del contatto nella lista
                for (String contactNumber : contact.getNumbers()) {
                    if (contactNumber == null || contactNumber.trim().isEmpty()) {
                        continue; // Salta numeri nulli o vuoti
                    }

                    String normalizedContactNumber = contactNumber.trim().toLowerCase();

                    // Confronta i numeri normalizzati
                    if (normalizedContactNumber.equals(normalizedNumberToCheck)) {
                        return true; // Numero duplicato trovato
                    }
                }
            }
        }
        return false; // Nessun numero duplicato trovato
    }

    /**
     * @brief Verifica se un contatto è duplicato.
     *
     * Controlla se esiste un contatto con lo stesso nome, cognome e numero.
     *
     * @param contacts La lista di contatti da esaminare.
     * @param name Il nome del contatto.
     * @param surname Il cognome del contatto.
     * @param numbers I numeri di telefono del contatto.
     * @return true se il contatto esiste già, false altrimenti.
     */
    public static boolean isContactDuplicate(ObservableList<Contatto> contacts, String name, String surname, List<String> numbers) {
        String lowerName = name.trim().toLowerCase();
        String lowerSurname = surname.trim().toLowerCase();

        for (Contatto contact : contacts) {
            String contactN = contact.getName().trim().toLowerCase();
            String contactS = contact.getSurname().trim().toLowerCase();
            if (contactN.equals(lowerName) && contactS.equals(lowerSurname)) {
                for (String number : numbers) {
                    if (!number.isEmpty() && contact.getNumbers().contains(number.trim())) {
                        return true;
                    }
                }

            }

        }
        return false; // Contatto non duplicato
    }

    /**
     * @brief Verifica se un numero di telefono è duplicato.
     *
     * Controlla se il numero di telefono è già presente tra i contatti.
     *
     * @param contacts La lista di contatti da esaminare.
     * @param emails La lista delle email da verificare.
     * @return true se il numero è già presente, false altrimenti.
     */
    public static boolean isEmailDuplicate(List<Contatto> contacts, List<String> emails) {
        for (Contatto contact : contacts) {
            for (String email : emails) {
                if (email == null || email.trim().isEmpty()) {
                    continue; // Salta email nulle o vuote
                }
                String normalizedEmail = email.trim().toLowerCase();
                for (String contactEmail : contact.getEmails()) {
                    if (contactEmail == null || contactEmail.trim().isEmpty()) {
                        continue; // Salta email nulle o vuote nei contatti
                    }
                    if (contactEmail.trim().toLowerCase().equals(normalizedEmail)) {
                        return true; // Almeno un email duplicata trovato
                    }
                }
            }
        }
        return false;  // Numero non trovato
    }

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
