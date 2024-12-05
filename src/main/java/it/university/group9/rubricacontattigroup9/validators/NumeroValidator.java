/**
 * @file NumeroValidator.java
 * @brief Questa classe contiene il metodo per la validazione di un numero di telefono.
 * 
 * La classe fornisce un metodo statico per verificare se un numero di telefono è valido,
 * secondo un pattern regolare. Se il numero non è valido, viene lanciata un'eccezione di tipo
 * NumeroNonValidoException.
 *
 * @author Gruppo09
 * @date Dicembre 2024
 * @version 1.0
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.exceptions.NumeroNonValidoException;

/**
 * @class NumeroValidator
 * @brief Classe per la validazione dei numeri di telefono.
 * 
 * La classe fornisce un metodo per validare una stringa che rappresenta un numero di telefono.
 * Se il numero non è valido, viene generata un'eccezione.
 */
public class NumeroValidator {
    /**
     * @brief Valida un numero di telefono.
     * 
     * Questo metodo verifica che il numero di telefono rispetti un formato valido.
     * Il numero deve contenere solo cifre e può iniziare con un "+" opzionale. Inoltre, 
     * deve avere una lunghezza compresa tra 10 e 15 caratteri.
     *
     * @param phoneNumber Il numero di telefono da validare.
     * @throws NumeroNonValidoException Se il numero di telefono non è valido.
     */
    public static void validatePhoneNumber(String phoneNumber) throws NumeroNonValidoException {
        if (phoneNumber == null || !phoneNumber.matches("^[+]?[0-9]{10,15}$")) {    
        /* Possono iniziare con un "+" (opzionale).
            Contengono solo cifre da 0 a 9.
            Hanno una lunghezza compresa tra 10 e 15 caratteri.*/
            throw new NumeroNonValidoException("Numero di telefono non valido");
        }
    }
}