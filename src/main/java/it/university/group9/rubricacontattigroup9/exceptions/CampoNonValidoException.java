/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package it.university.group9.rubricacontattigroup9.exceptions;

/**
 * @file CampoNonValidoException.java
 * @brief Classe che gestisce le eccezioni relative a un campo non valido.
 * 
 * Questa classe estende l'eccezione `Exception` per segnalare errori relativi a campi non validi, come un nome o un cognome.
 *
 * @author Gruppo09
 */
public class CampoNonValidoException extends Exception {
    /**
     * @brief Costruttore della classe CampoNonValidoException.
     * 
     * Questo costruttore crea una nuova istanza dell'eccezione con un messaggio personalizzato.
     * 
     * @param msg messaggio d'errore che indica quale campo non è valido
     */
    public CampoNonValidoException(String msg) {
        super(msg);
    }
}
