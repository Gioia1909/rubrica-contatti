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
     * @param number Il numero di telefono da verificare.
     * @return true se il numero è già presente, false altrimenti.
     */
    public static boolean isNumberDuplicate(List<Contatto> contacts, String number) {
        for (Contatto contact : contacts) {
            for (String num : contact.getNumbers()) {
                if (num.equals(number)) {
                    return true; // Numero già presente
                }
            }
        }
        return false; // Numero non trovato
    }

    /**
     * @brief Verifica se un contatto è duplicato.
     *
     * Controlla se esiste un contatto con lo stesso nome, cognome e numero.
     *
     * @param contacts La lista di contatti da esaminare.
     * @param name Il nome del contatto.
     * @param surname Il cognome del contatto.
     * @param number Il numero di telefono del contatto.
     * @return true se il contatto esiste già, false altrimenti.
     */
    public static boolean isContactDuplicate(List<Contatto> contacts, String name, String surname) {
        String  lowerName= name.trim().toLowerCase();
        String  lowerSurname= surname.trim().toLowerCase();

        for (Contatto contact : contacts) {
            String contactN = contact.getName().trim().toLowerCase();
            String contactS = contact.getSurname().trim().toLowerCase();
            if (contactN.equals(lowerName) && contactS.equals(lowerSurname)) {
                return true; // Contatto già esistente
            }
        }
        return false; // Contatto non trovato
    }

    public static boolean isEmailDuplicate(List<Contatto> contacts, String email) {
        for (Contatto contact : contacts) {
            for (String em : contact.getEmails()) {
                if (em.equals(email)) {
                    return true; // Email già presente
                }
            }
        }
        return false; // Email non trovata
    }

public static void validateName(String name) throws CampoNonValidoException {
      if (name == null || name.trim().isEmpty() || !Character.isAlphabetic(name.charAt(0))) {
            throw new CampoNonValidoException("Nome");
        }
    }

public static void validateSurname(String surname) throws CampoNonValidoException {
        if (surname == null || surname.trim().isEmpty() || !Character.isAlphabetic(surname.charAt(0))){    //surname.trim() elimina eventuali spazi all'inizio o alla fine della stringa per evitare che un 
                                                                                    //surnome apparentemente vuoto (ma con spazi) sia considerato valido.
            throw new CampoNonValidoException("Cognome");
        }
    }

public static void validatePhoneNumber(String phoneNumber) throws CampoNonValidoException {
        String cleanedPhoneNumber = phoneNumber.replaceAll("\\s", "");
        if ( !cleanedPhoneNumber.matches("^[+]?[0-9]{10,15}$")) {
            /* Possono iniziare con un "+" (opzionale).
            Contengono solo cifre da 0 a 9.
            Hanno una lunghezza compresa tra 10 e 15 caratteri.*/
            throw new CampoNonValidoException("Numero di telefono");
        }
    }

public static void validateEmail(String email) throws CampoNonValidoException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new CampoNonValidoException("Email");
        }
    }

}
