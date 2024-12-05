/**
 * @file NomeNonValidoException.java
 * @brief Classe che gestisce le eccezioni relative a un nome non valido.
 * 
 * Questa classe estende l'eccezione `Exception` per segnalare errori relativi a un nome non valido.
 *
 * @author Gruppo09
 * @date 05/12/2024
 * @version 1.0
 */
package it.university.group9.rubricacontattigroup9.exceptions;

/**
 * @brief Eccezione per un nome non valido.
 * 
 * Questa classe viene lanciata quando un nome non è valido, come nel caso in cui
 * sia nullo o vuoto.
 */
public class NomeNonValidoException extends Exception {
    /**
     * @brief Costruttore della classe NomeNonValidoException.
     * 
     * Questo costruttore crea una nuova istanza dell'eccezione con un messaggio personalizzato.
     * 
     * @param message Il messaggio di errore.
     */
    public NomeNonValidoException(String message) {
        super(message);
    }
}