/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import it.university.group9.rubricacontattigroup9.exceptions.*;
import it.university.group9.rubricacontattigroup9.validators.*;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ari19
 */
public class Rubrica implements GestioneRubrica {

    private ObservableList<Contatto> contactList;
    

    public Rubrica() {
        this.contactList = FXCollections.observableArrayList();
    }

    public ObservableList<Contatto> getContact() {
        return contactList;
    }
    
    /**
     * @brief Aggiunge un contatto alla rubrica.
     * 
     * @param name Nome del contatto.
     * @param surname Cognome del contatto.
     * @param numbers Lista di numeri di telefono del contatto.
     * @param emails Lista di email del contatto.
     * @param note Note associate al contatto.
     * @throws CampoNonValidoException Se uno dei campi non è valido.
     * @throws IOException Se si verifica un errore durante il salvataggio.
     */
    @Override
    public void addContact(String name, String surname, List<String> numbers, List<String> emails, String note)
            throws CampoNonValidoException, IOException {

        ContattoValidator.validateName(name);
        ContattoValidator.validateSurname(surname);
        for (String number : numbers) {
            ContattoValidator.validatePhoneNumber(number);
        }
        for (String email : emails) {
            ContattoValidator.validateEmail(email);
        }

        if (ContattoValidator.isContactDuplicate(contactList, name, surname, numbers)) {
            throw new CampoNonValidoException("Contatto duplicato");
        }

        Contatto newContact = new Contatto(name, surname, numbers, emails, note);
        contactList.add(newContact);
        saveContacts();
    }

    /**
     * @brief Modifica un contatto esistente.
     * 
     * @param oldContact Il contatto da modificare.
     * @param name Nuovo nome del contatto.
     * @param surname Nuovo cognome del contatto.
     * @param numbers Nuovi numeri di telefono del contatto.
     * @param emails Nuove email del contatto.
     * @param note Nuove note associate al contatto.
     * @throws CampoNonValidoException Se uno dei campi non è valido.
     * @throws IOException Se si verifica un errore durante il salvataggio.
     */
    @Override
    public void editContact(Contatto oldContact, String name, String surname, List<String> numbers, List<String> emails, String note)
            throws CampoNonValidoException, IOException {

        ContattoValidator.validateName(name);
        ContattoValidator.validateSurname(surname);
        for (String number : numbers) {
            ContattoValidator.validatePhoneNumber(number);
        }
        for (String email : emails) {
            ContattoValidator.validateEmail(email);
        }

        int index = contactList.indexOf(oldContact);
        if (index != -1) {
            Contatto updatedContact = new Contatto(name, surname, numbers, emails, note);
            contactList.set(index, updatedContact);
            saveContacts();
        }
    }
    /**
     * @brief Rimuove un contatto dalla rubrica.
     * 
     * @param contact Il contatto da rimuovere.
     * @throws IOException Se si verifica un errore durante il salvataggio.
     */
    
    @Override
    public void deleteContact(Contatto contact) throws IOException{
        contactList.remove(contact);
        saveContacts();
    }
    

    public ObservableList<Contatto> searchContact(String param) {
        ObservableList<Contatto> result = FXCollections.observableArrayList();
        for (Contatto contact : contactList) {
            if (contact.getName().toLowerCase().contains(param.toLowerCase())
                    || contact.getSurname().toLowerCase().contains(param.toLowerCase())) {
                result.add(contact);
            }
        }
        return result;

    }
    /**
     * @brief Salva i contatti sul file JSON.
     * 
     * @throws IOException Se si verifica un errore durante il salvataggio.
     */
    private void saveContacts() throws IOException {
        SalvaCaricaRubrica.saveAddressBook(contactList);
    }
    
    
}
