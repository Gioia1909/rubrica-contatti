/**
 * @file CognomeNonValidoException.java
 * @brief Classe che gestisce le eccezioni relative a un cognome non valido.
 * 
 * Questa classe estende l'eccezione `Exception` per segnalare errori relativi a un cognome non valido.
 *
 * @author Gruppo09
 * @date 05/12/2024
 * @version 1.0
 */
package it.university.group9.rubricacontattigroup9.exceptions;

/**
 * @brief Eccezione per un cognome non valido.
 * 
 * Questa classe viene lanciata quando un cognome non è valido, come nel caso in cui
 * sia nullo o vuoto.
 */
public class CognomeNonValidoException extends Exception {
    /**
     * @brief Costruttore della classe CognomeNonValidoException.
     * 
     * Questo costruttore crea una nuova istanza dell'eccezione con un messaggio personalizzato.
     * 
     * @param message Il messaggio di errore 
     */
    public CognomeNonValidoException(String message) {
        super(message);
    }
}