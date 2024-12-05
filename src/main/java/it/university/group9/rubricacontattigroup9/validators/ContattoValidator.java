/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.util.List;

/**
 *
 * @author gruppo09
 */
public class ContattoValidator {

    public static boolean isNumeroDuplicato(List<Contatto> contatti, String numero) {
        for (Contatto contatto : contatti) {
            for (String num : contatto.getNumeri()) {
                if (num.equals(numero)) {
                    return true; // Numero già presente
                }
            }
        }
        return false; // Numero non trovato
    }

    public static boolean isContattoDuplicato(List<Contatto> contatti, String nome, String cognome, String numero) {
        for (Contatto contatto : contatti) {
            // Controllo se esiste un contatto con lo stesso nome e cognome
            if (contatto.getNome().equals(nome) && contatto.getCognome().equals(cognome)) {
                // Verifica il numero, se è lo stesso
                for (String num : contatto.getNumeri()) {
                    if (num.equals(numero)) {
                        return true; // Il contatto esiste già
                    }
                }
            }
        }
        return false; // Nessun duplicato trovato
    }
}

