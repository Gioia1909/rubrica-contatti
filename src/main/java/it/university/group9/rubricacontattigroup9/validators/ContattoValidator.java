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
     * @param contatti La lista di contatti da esaminare.
     * @param numero Il numero di telefono da verificare.
     * @return true se il numero è già presente, false altrimenti.
     */
    public static boolean isNumeroDuplicato(List<Contatto> contatti, String numero) {
        for (Contatto contatto : contatti) {
            for (String num : contatto.getNumeri()) {
                if (num.equals(numero)) {
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
     * @param contatti La lista di contatti da esaminare.
     * @param nome Il nome del contatto.
     * @param cognome Il cognome del contatto.
     * @param numero Il numero di telefono del contatto.
     * @return true se il contatto esiste già, false altrimenti.
     */
    public static boolean isContattoDuplicato(List<Contatto> contatti, String nome, String cognome) {
        String nomePulito = nome.trim().toLowerCase();
        String cognomePulito = cognome.trim().toLowerCase();

        for (Contatto contatto : contatti) {
            String nomeC = contatto.getNome().trim().toLowerCase();
            String cognomeC = contatto.getCognome().trim().toLowerCase();
            if (nomeC.equals(nomePulito) && cognomeC.equals(cognomePulito)) {
                return true; // Contatto già esistente
            }
        }
        return false; // Contatto non trovato
    }

    public static boolean isEmailDuplicata(List<Contatto> contatti, String email) {
        for (Contatto contatto : contatti) {
            for (String em : contatto.getEmails()) {
                if (em.equals(email)) {
                    return true; // Email già presente
                }
            }
        }
        return false; // Email non trovata
    }

public static void validateEmail(String email) throws CampoNonValidoException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Email Non Valida.");
            alert.setContentText("L'Email Inserita non è Valida");
            alert.showAndWait();
            throw new CampoNonValidoException("Email non valida");
        }
    }

public static void validateName(String name) throws CampoNonValidoException {
        if (name == null || name.trim().isEmpty()|| !Character.isAlphabetic(name.charAt(0))) {    //name.trim() elimina eventuali spazi all'inizio o alla fine della stringa per evitare che un 
                                                                                    //nome apparentemente vuoto (ma con spazi) sia considerato valido.
            throw new CampoNonValidoException("Nome non valido");
        }
    }

public static void validatePhoneNumber(String phoneNumber) throws CampoNonValidoException {
        if (phoneNumber == null || !phoneNumber.matches("^[+]?[0-9]{10,15}$")) {
            /* Possono iniziare con un "+" (opzionale).
            Contengono solo cifre da 0 a 9.
            Hanno una lunghezza compresa tra 10 e 15 caratteri.*/
            errorMessage("Numero Non Valido", "Il numero che vuoi inserire non è valido.");
            throw new CampoNonValidoException("Numero di telefono non valido");
        }
    }

    private static void errorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

public static void validateSurname(String surname) throws CampoNonValidoException {
        if (surname == null || surname.trim().isEmpty() || !Character.isAlphabetic(surname.charAt(0))){    //surname.trim() elimina eventuali spazi all'inizio o alla fine della stringa per evitare che un 
                                                                                    //surnome apparentemente vuoto (ma con spazi) sia considerato valido.
            throw new CampoNonValidoException("Cognome non valido");
        }
    }

}
