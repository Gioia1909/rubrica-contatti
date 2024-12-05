/**
 * @file EmailNonValidaException.java
 * @brief Classe che gestisce le eccezioni relative a un'email non valida.
 * 
 * Questa classe estende l'eccezione `Exception` per segnalare errori relativi a un'email non valida.
 *
 * @author Gruppo09
 * @date 05/12/2024
 * @version 1.0
 */
package it.university.group9.rubricacontattigroup9.exceptions;

/**
 * @brief Eccezione per un'email non valida.
 * 
 * Questa classe viene lanciata quando un'email non è valida, come nel caso in cui
 * non soddisfi il formato di un'email valido.
 */
public class EmailNonValidaException extends Exception {
    /**
     * @brief Costruttore della classe EmailNonValidaException.
     * 
     * Questo costruttore crea una nuova istanza dell'eccezione con un messaggio personalizzato.
     * 
     * @param message Il messaggio di errore.
     */
    public EmailNonValidaException(String message) {
        super(message);
    }
}
