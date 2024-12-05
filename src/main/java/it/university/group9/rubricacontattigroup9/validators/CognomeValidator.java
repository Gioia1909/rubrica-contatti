/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.exceptions.CognomeNonValidoException;

/**
 *
 * @author Gruppo09
 */
public class CognomeValidator {
    public static void validateName(String name) throws CognomeNonValidoException {
        if (name == null || name.trim().isEmpty()) {
            throw new CognomeNonValidoException("Cognome non valido");
        }
    }
}
