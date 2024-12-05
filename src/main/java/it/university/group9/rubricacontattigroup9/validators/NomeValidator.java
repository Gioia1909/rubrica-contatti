/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.exceptions.NomeNonValidoException;

/**
 *
 * @author Gruppo09
 */
public class NomeValidator {
    public static void validateName(String name) throws NomeNonValidoException {
        if (name == null || name.trim().isEmpty()) {
            throw new NomeNonValidoException("Nome non valido");
        }
    }
}