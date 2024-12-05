/**
 * @file NumeroNonValidoException.java
 * @brief Classe che gestisce le eccezioni relative a un numero di telefono non valido.
 * 
 * Questa classe estende l'eccezione `Exception` per segnalare errori relativi a un numero di telefono non valido.
 *
 * @author Gruppo09
 * @date 2024-12-05
 * @version 1.0
 */
package it.university.group9.rubricacontattigroup9.exceptions;

/**
 * @brief Eccezione per un numero di telefono non valido.
 * 
 * Questa classe viene lanciata quando un numero di telefono non è valido, come nel caso in cui
 * non soddisfi i requisiti di formato.
 */
public class NumeroNonValidoException extends Exception {
    /**
     * @brief Costruttore della classe NumeroNonValidoException.
     * 
     * Questo costruttore crea una nuova istanza dell'eccezione con un messaggio personalizzato.
     * 
     * @param message Il messaggio di errore.
     */
    public NumeroNonValidoException(String message) {
        super(message);
    }
}
