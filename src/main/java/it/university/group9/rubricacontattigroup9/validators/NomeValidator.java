/**
 * @file NomeValidator.java
 * @brief Questa classe contiene il metodo per la validazione del nome.
 * 
 * La classe fornisce un metodo statico per verificare se un nome è valido (non nullo
 * e non vuoto). Se il nome non è valido, viene lanciata un'eccezione di tipo
 * NomeNonValidoException.
 *
 * @author Gruppo09
 * @date 05/12/2024
 * @version 1.0
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.exceptions.NomeNonValidoException;

/**
 * @class NomeValidator
 * @brief Classe per la validazione dei nomi.
 * 
 * La classe fornisce un metodo per validare una stringa che rappresenta un nome.
 * Se il nome è nullo o vuoto, viene generata un'eccezione.
 */
public class NomeValidator {
    /**
     * @brief Valida un nome.
     * 
     * Questo metodo verifica che il nome fornito non sia nullo e che non sia vuoto.
     * Se il nome non è valido, viene generata un'eccezione.
     *
     * @param name Il nome da validare.
     * @throws NomeNonValidoException Se il nome non è valido.
     */
    public static void validateName(String name) throws NomeNonValidoException {
        if (name == null || name.trim().isEmpty()) {    //name.trim() elimina eventuali spazi all'inizio o alla fine della stringa per evitare che un 
                                                                                    //nome apparentemente vuoto (ma con spazi) sia considerato valido.
            throw new NomeNonValidoException("Nome non valido");
        }
    }
}