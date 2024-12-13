/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9;

import javafx.collections.ObservableList;

/**
 *
 * @author monto
 */
public interface GestioneRubrica {
    
    public ObservableList<Contatto> getContact();
    public void addContact(Contatto contact);
    public void editContact(Contatto existingContact, Contatto editedContact);
    public void deleteContact(Contatto contact);
    public ObservableList<Contatto> searchContact(String param);
        
}
