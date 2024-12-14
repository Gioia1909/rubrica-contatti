/**
 * @package it.university.group9.rubricacontattigroup9
 *
 * @file GestioneRubrica
 *
 * @brief Interfaccia che definisce le operazioni principali per la gestione di una rubrica.
 * 
 * @author Gruppo09
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;


public interface GestioneRubrica {
    
    /**
     * @brief Restituisce la lista di tutti i contatti nella rubrica.
     * 
     * @return ObservableList contenente tutti i contatti.
     */
    public ObservableList<Contatto> getContactList();

    /**
     * @brief Aggiunge un nuovo contatto alla rubrica.
     * 
     * @param name Il nome del contatto.
     * @param surname Il cognome del contatto.
     * @param numbers La lista dei numeri di telefono del contatto.
     * @param emails La lista delle email del contatto.
     * @param note Eventuali note relative al contatto.
     * @throws CampoNonValidoException Se uno dei campi non è valido.
     * @throws IOException Se si verifica un errore durante la gestione dei dati.
     *
     * @pre Il contatto non deve già esistere nella rubrica.
     * @post Il contatto viene aggiunto alla rubrica se è valido.
     */
    public void addContact(String name, String surname, List<String> numbers, List<String> emails, String note) throws CampoNonValidoException, IOException;

    /**
     * Modifica un contatto esistente nella rubrica.
     * 
     * @param oldContact Il contatto da modificare.
     * @param name Il nuovo nome del contatto.
     * @param surname Il nuovo cognome del contatto.
     * @param numbers La nuova lista dei numeri di telefono del contatto.
     * @param emails La nuova lista delle email del contatto.
     * @param note Le nuove note relative al contatto.
     * @throws CampoNonValidoException Se uno dei campi non è valido.
     * @throws IOException Se si verifica un errore durante la gestione dei dati.
     *
     * @pre Il contatto da modificare deve esistere nella rubrica.
     * @post Il contatto viene modificato se le modifiche non creano duplicati.
     */
    public void editContact(Contatto oldContact, String name, String surname, List<String> numbers, List<String> emails, String note) throws CampoNonValidoException, IOException ;
    
    /**
     * Elimina un contatto dalla rubrica.
     * 
     * @param contact Il contatto da eliminare.
     * @throws IOException Se si verifica un errore durante la gestione dei dati.
     *
     * @pre Il contatto deve esistere nella rubrica.
     * @post Il contatto viene rimosso dalla rubrica.
     */
    public void deleteContact(Contatto contact) throws IOException;

    /**
     * Ricerca i contatti nella rubrica in base ad un parametro di ricerca.
     * 
     * @param param Il parametro di ricerca (nome, cognome, numero di telefono, email).
     * @return ObservableList contenente i contatti che corrispondono al parametro di ricerca.
     *
     * @pre La rubrica deve essere inizializzata.
     */
    public ObservableList<Contatto> searchContact(String param);
        
}
