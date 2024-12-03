/**
 * @file RubricaContatti.java
 * @brief Contiene l'implementazione della classe RubricaContatti per la
 * gestione di contatti.
 *
 * Questa classe permette di aggiungere, rimuovere, modificare e cercare
 * contatti all'interno di una rubrica.
 *
 * @author Gruppo09
 * @date 3 Dicembre 2024
 * @version 1.0
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.util.LinkedList;
import java.util.List;

/**
 * @brief La classe RubricaContatti gestisce una lista di contatti.
 *
 * La classe implementa l'interfaccia Rubrica e fornisce funzionalità per
 * aggiungere, rimuovere, modificare e cercare contatti.
 */
public class RubricaContatti implements Rubrica {

    private List<Contatto> contatti;    /** < Lista di contatti nella rubrica*/


    /**
     * @brief Costruttore della classe RubricaContatti.
     *
     * Inizializza la lista dei contatti come una LinkedList vuota.
     *
     * @param contatti Lista di contatti da inizializzare.
     */
    public RubricaContatti(List<Contatto> contatti) {
        this.contatti = new LinkedList<>();
    }

    /**
     * @brief Aggiunge un nuovo contatto alla rubrica.
     *
     * Questo metodo aggiunge il contatto specificato alla lista dei contatti.
     *
     * @param[in] contatto Il contatto da aggiungere alla rubrica.
     */
    @Override
    public void aggiungiContatto(Contatto contatto) {
        contatti.add(contatto);
    }

    /**
     * @brief Rimuove un contatto dalla rubrica dato il suo identificatore.
     *
     * Questo metodo cerca e rimuove il contatto dalla rubrica che corrisponde
     * all'identificatore fornito.
     *
     * @param[in] identificatore L'identificatore del contatto da rimuovere.
     * @return true se il contatto è stato rimosso con successo, false
     * altrimenti.
     */
    @Override
    public boolean rimuoviContatto(String identificatore) {
        // Implementazione del metodo di rimozione
        return false;
    }

    /**
     * @brief Modifica un contatto esistente nella rubrica.
     *
     * Questo metodo cerca un contatto tramite il suo identificatore e lo
     * sostituisce con il nuovo contatto fornito.
     *
     * @param[in] identificatore L'identificatore del contatto da modificare.
     * @param[in] nuovoContatto Il nuovo contatto che sostituirà il contatto
     * esistente.
     * @return true se il contatto è stato modificato con successo, false
     * altrimenti.
     */
    @Override
    public boolean modificaContatto(String identificatore, Contatto nuovoContatto) {
        // Implementazione del metodo di modifica
        return false;
    }

    /**
     * @brief Cerca i contatti che corrispondono al parametro di ricerca.
     *
     * Questo metodo restituisce una lista di contatti che corrispondono al
     * parametro di ricerca fornito.
     *
     * @param[in] parametro Il parametro di ricerca per identificare i contatti
     * (nome, email, etc.).
     * @return Una lista di contatti che soddisfano il parametro di ricerca.
     */
    @Override
    public List<Contatto> cercaContatto(String parametro) {
        // Implementazione del metodo di ricerca
        return new LinkedList<>();
    }

}
