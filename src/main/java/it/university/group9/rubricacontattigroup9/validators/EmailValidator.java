/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.exceptions.EmailNonValidaException;

/**
 *
 * @author Gruppo09
 */
public class EmailValidator {
    public static void validateEmail(String email) throws EmailNonValidaException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new EmailNonValidaException("Email non valida");
        }
    }
}
