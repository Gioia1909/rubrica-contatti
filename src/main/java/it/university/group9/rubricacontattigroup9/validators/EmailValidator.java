/**
 * @file EmailValidator.java
 * @brief Questa classe contiene il metodo per la validazione di un'email.
 * 
 * La classe fornisce un metodo statico per verificare se un'email è valida secondo un pattern regolare.
 * Se l'email non è valida, viene lanciata un'eccezione di tipo EmailNonValidaException.
 *
 * @author Gruppo09
 * @date 05/12/2024
 * @version 1.0
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.exceptions.EmailNonValidaException;

/**
 * @class EmailValidator
 * @brief Classe per la validazione delle email.
 * 
 * La classe fornisce un metodo per validare una stringa email contro un pattern
 * regolare. Se l'email non è valida, viene generata un'eccezione.
 */
public class EmailValidator {
    /**
     * @brief Valida un indirizzo email.
     * 
     * Questo metodo verifica che l'indirizzo email rispetti un formato valido, 
     * lanciando un'eccezione se il formato non è corretto.
     *
     * @param email L'email da validare.
     * @throws EmailNonValidaException Se l'email non è valida.
     */
    public static void validateEmail(String email) throws EmailNonValidaException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z]+\\.[A-Za-z]{2,}$")) {

            throw new EmailNonValidaException("Email non valida");
        }
    }
}
