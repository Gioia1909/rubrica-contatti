/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.util.List;

/**
 * @file Rubrica.java
 * @brief Interfaccia per la gestione di una rubrica di contatti.
 * 
 * Questa interfaccia definisce i metodi principali per gestire i contatti in una rubrica.
 * I metodi inseriti includono l'aggiunta, la rimozione, la modifica e la ricerca di contatti.
 * 
 * @author Gruppo09
 * @date 03/12/024
 * 
 */
public interface Rubrica {

    /**
     * @brief Aggiunge un nuovo contatto alla rubrica
     * 
     * @param[in] contatto Il contatto da aggiungere alla rubrica.
     */
    public void aggiungiContatto(Contatto contatto);

    /**
     * @brief Rimuove un contatto dalla rubrica
     * 
     * @param[in] identificatore Identificare del contatto da rimuovere.
     * @return true se il contatto è stato rimosso con successo, false altrimenti.
     */
    public boolean rimuoviContatto(String identificatore);

    /**
     * @brief Modifica un contatto esistente nella rubrica
     * 
     * @param[in] identificatore Identificatore del contatto da modificare.
     * @param[in] nuovoContatto Parametri del contatto che sostituiranno quelli esistenti
     * @return true se il contatto è stato modificato con successo, false altrimenti.
     */
    public boolean modificaContatto(String identificatore, Contatto nuovoContatto);

    /**
     * @brief Cerca contatti nella rubrica in base a un parametro specifico.
     * 
     * @param[in] parametro Il parametro di ricerca (nome, cognome, ecc.).
     * @return Una lista di contatti che corrispondono al parametro di ricerca.
     */
    public List<Contatto> cercaContatto(String parametro);
}
