/**
 * @file CognomeValidator.java
 * @brief Classe che valida i cognomi.
 * 
 * Questa classe contiene il metodo per validare i cognomi. Se un cognome non è valido, 
 * viene lanciata un'eccezione CognomeNonValidoException.
 *
 * @author Gruppo09
 * @date 05/12/2024
 * @version 1.0
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.exceptions.CognomeNonValidoException;

/**
 * @brief Validatore per i cognomi.
 * 
 * Questa classe verifica la validità di un cognome, sollevando un'eccezione se il cognome
 * è nullo o vuoto.
 */
public class CognomeValidator {

    /**
     * @brief Verifica la validità di un cognome.
     * 
     * Se il cognome è nullo o vuoto, viene sollevata un'eccezione CognomeNonValidoException.
     * 
     * @param name Il cognome da validare.
     * @throws CognomeNonValidoException Se il cognome è nullo o vuoto.
     */
    public static void validateName(String name) throws CognomeNonValidoException {
        if (name == null || name.trim().isEmpty()) {    //name.trim() elimina eventuali spazi all'inizio o alla fine della stringa per evitare che un 
                                                                                    //nome apparentemente vuoto (ma con spazi) sia considerato valido.
            throw new CognomeNonValidoException("Cognome non valido");
        }
    }
}
