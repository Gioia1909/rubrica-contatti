/**
 * @file ContattoValidator.java
 * @brief Classe che valida i contatti.
 * 
 * Questa classe contiene metodi per verificare la duplicazione dei numeri di telefono
 * e dei contatti.
 *
 * @author Gruppo09
 * @date 05/12/2024
 * 
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.util.List;

/**
 * @brief Validatore per i contatti.
 * 
 * Questa classe verifica se un contatto o un numero di telefono è duplicato.
 */
public class ContattoValidator {

    /**
     * @brief Verifica se un numero di telefono è duplicato.
     * 
     * Controlla se il numero di telefono è già presente tra i contatti.
     * 
     * @param contatti La lista di contatti da esaminare.
     * @param numero Il numero di telefono da verificare.
     * @return true se il numero è già presente, false altrimenti.
     */
    public static boolean isNumeroDuplicato(List<Contatto> contatti, String numero){
        for (Contatto contatto : contatti) { 
            for (String num : contatto.getNumeri()) {
                if (num.equals(numero)) {
                    return true; // Numero già presente
                }
            }
        }
        return false; // Numero non trovato
    }

    /**
     * @brief Verifica se un contatto è duplicato.
     * 
     * Controlla se esiste un contatto con lo stesso nome, cognome e numero.
     * 
     * @param contatti La lista di contatti da esaminare.
     * @param nome Il nome del contatto.
     * @param cognome Il cognome del contatto.
     * @param numero Il numero di telefono del contatto.
     * @return true se il contatto esiste già, false altrimenti.
     */
    public static boolean isContattoDuplicato(List<Contatto> contatti, String nome, String cognome) {
        String nomePulito = nome.trim().toLowerCase();
        String cognomePulito = cognome.trim().toLowerCase();
        
        for (Contatto contatto : contatti) {
            String nomeC = contatto.getNome().trim().toLowerCase();
            String cognomeC = contatto.getCognome().trim().toLowerCase();
            if (nomeC.equals(nomePulito) && cognomeC.equals(cognomePulito)) {
                return true; // Contatto già esistente
            }
        }
        return false; // Contatto non trovato
    }

    
    public static boolean isEmailDuplicata(List<Contatto> contatti, String email){
        for (Contatto contatto : contatti) { 
            for (String em : contatto.getEmails()) {
                if (em.equals(email)) {
                    return true; // Email già presente
                }
            }
        }
        return false; // Email non trovata
    }
    
}

