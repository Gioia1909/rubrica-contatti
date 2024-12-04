/**
 * @file RubricaPreferiti.java
 * @brief Classe che rappresenta una rubrica per contatti preferiti.
 *
 * La classe implementa l'interfaccia Rubrica, fornendo funzionalità di gestione
 * dei contatti preferiti, inclusa l'aggiunta, la rimozione e la modifica.
 *
 *
 * @author Gruppo09
 * @version 2.0
 * @date 04/12/2024
 */
package it.university.group9.rubricacontattigroup9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @class RubricaPreferiti
 * @brief Classe per gestire una rubrica di contatti preferiti.
 *
 * Utilizza una lista per memorizzare i contatti preferiti.
 */
public class RubricaPreferiti implements Rubrica {

    /**
     * Lista di contatti preferiti.
     */
    List<Contatto> contatti;

   /**
     * @brief Costruttore di default.
     * 
     * Inizializza la lista di contatti preferiti come una LinkedList vuota.
     */
    public RubricaPreferiti() {
        contatti = new LinkedList<>();
    }

/**
     * @brief Aggiunge un contatto alla rubrica.
     * 
     * @pre Il contatto non deve essere null.
     * @post Il contatto viene aggiunto alla lista dei contatti preferiti.
     * 
     * @param[in] contatto Il contatto da aggiungere alla rubrica.
     */
    @Override
    public void aggiungiContatto(Contatto contatto) {
        //TODO: Implementazione del metodo di aggiunta (gestione duplicati da completare)
        contatti.add(contatto);
    }

    /**
     * @brief Rimuove un contatto dalla rubrica.
     * 
     * @pre Il contatto identificato deve esistere nella lista.
     * @post Il contatto viene rimosso dalla lista, se presente.
     * 
     * @param[in] identificatore L'identificatore univoco del contatto da rimuovere.
     * @return true se il contatto è stato rimosso con successo, false altrimenti.
     */
    @Override
    public boolean rimuoviContatto(String identificatore) {
        //TODO: Da implementare
        return false;
    }

    /**
     * @brief Modifica un contatto esistente nella rubrica preferiti.
     *
     * Questo metodo cerca un contatto tramite il suo identificatore e lo
     * modifica con il nuovo contatto fornito.
     *
     * @param[in] identificatore L'identificatore del contatto da modificare.
     * @param[in] nuovoContatto Il nuovo contatto che sostituirà il contatto esistente.
     * @return true se il contatto è stato modificato con successo, false
     * altrimenti.
     */
    @Override
    public boolean modificaContatto(String identificatore, Contatto nuovoContatto ) {
        //TODO: Da implementare
        return false;
    }

    /**
     * @brief Cerca i contatti preferiti che corrispondono al parametro di
     * ricerca.
     *
     * Questo metodo restituisce una lista di contatti che corrispondono al
     * parametro di ricerca fornito (ad esempio, nome, numero di telefono,
     * ecc.).
     *
     * @param[in] parametro Il parametro di ricerca per identificare i contatti (nome, numero, mail).
     * @return Una lista di contatti che soddisfano il parametro di ricerca.
     */
    @Override
    public List<Contatto> cercaContatto(String parametro) {
        //TODO: Da implementare
        return null;
    }

}
