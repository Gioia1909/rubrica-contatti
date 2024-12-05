/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.exceptions.NumeroNonValidoException;

/**
 *
 * @author Gruppo09
 */
public class NumeroValidator {
    public static void validatePhoneNumber(String phoneNumber) throws NumeroNonValidoException {
        if (phoneNumber == null || !phoneNumber.matches("^[+]?[0-9]{10,15}$")) {    
        /* Possono iniziare con un "+" (opzionale).
            Contengono solo cifre da 0 a 9.
            Hanno una lunghezza compresa tra 10 e 15 caratteri.*/
            throw new NumeroNonValidoException("Numero di telefono non valido");
        }
    }
}