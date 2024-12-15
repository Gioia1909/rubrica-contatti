/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 * @file GestioneRubrica.java
 *
 * @brief Interfaccia per la gestione della rubrica di contatti
 *
 * Definisce i metodi necessari per la gestione dei contatti nella rubrica e
 * nella rubrica dei preferiti, come l'aggiunta, la modifica, l'eliminazione e
 * la ricerca dei contatti.
 *
 */

public interface GestioneRubrica {
    
        /**
     * @brief Restituisce la lista dei contatti.
     * @return Una lista osservabile contenente i contatti.
     */
    public ObservableList<Contatto> getContactList();
    
        /**
     * @brief Restituisce la lista dei contatti preferiti.
     * @return Una lista osservabile contenente i contatti preferiti.
     */
    public ObservableList<Contatto> getFavoriteList();
    
     /**
     * @brief Aggiunge un nuovo contatto alla rubrica se tutti i campi sono validi e il contatto non è un duplicato
     *
     * @param name Il nome del contatto.
     * @param surname Il cognome del contatto.
     * @param numbers Lista di numeri di telefono del contatto.
     * @param emails Lista di indirizzi email del contatto.
     * @param note Note aggiuntive sul contatto.
     * @throw CampoNonValidoException Se uno dei campi forniti non è valido.
     * @see ContattoValidator
     * @see GestioneDuplicati
     */
    public void addContact(String name, String surname, List<String> numbers, List<String> emails, String note, String selectedGender) throws CampoNonValidoException;
    
     /**
     * @brief Modifica un contatto esistente nella rubrica se tutti i campi sono validi e la modifica non genera un duplicato
     *
     * @param oldContact Il contatto originale da modificare.
     * @param name Il nuovo nome del contatto.
     * @param surname Il nuovo cognome del contatto.
     * @param numbers Lista aggiornata di numeri di telefono.
     * @param emails Lista aggiornata di indirizzi email.
     * @param note Note aggiornate sul contatto.
     * @throw CampoNonValidoException Se uno dei nuovi campi forniti non è valido.
     * @see ContattoValidator
     * @see GestioneDuplicati
     */
    
    public void editContact(Contatto oldContact, String name, String surname, List<String> numbers, List<String> emails, String note, String selectedGender) throws CampoNonValidoException ;
    
     /**
     * @brief Elimina un contatto dalla rubrica.
     *
     * @param contact Il contatto da eliminare.
     * 
     * @post il contatto viene eliminato sia dalla rubrica che dalla rubrica dei preferiti
     */
    public void deleteContact(Contatto contact);
    
      /**
     * @brief Cerca un contatto nella rubrica utilizzando un parametro
     * specifico.
     *
     * @param param Il parametro di ricerca (nome, cognome, numero di telefono o email).
     * @return Una lista di contatti che corrispondono al parametro.
     */
    public ObservableList<Contatto> searchContact(String param);
    
       /**
     * @brief Aggiunge un contatto alla lista dei preferiti.
     *
     * @param contact Il contatto da aggiungere ai preferiti.
     * @post il valore del campo "fav" viene settato a true
     */
    public void addToFavorites(Contatto contact);
    
      /**
     * @brief Rimuove un contatto dalla lista dei preferiti.
     *
     * @param contact Il contatto da rimuovere dai preferiti.
     * @post il valore del campo "fav" viene settato a false
     * @see matchesContact
     */
    public void removeFromFavorites(Contatto contact);
    
     /**
     * @brief Cerca un contatto tra i preferiti utilizzando un parametro
     * specifico.
     *
     * @param param Il parametro di ricerca (nome, cognome, numero di telefono o email).
     * @return Una lista osservabile di contatti preferiti che corrispondono al
     * parametro.
     * @see matchesContact()
     */
    public ObservableList<Contatto> searchFavoriteContact(String param);
    
}
