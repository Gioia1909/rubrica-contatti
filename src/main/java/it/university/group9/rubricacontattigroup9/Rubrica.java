/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ari19
 */
public class Rubrica {

    private ObservableList<Contatto> addressBook;

    public Rubrica() {
        this.addressBook = FXCollections.observableArrayList();
    }

    public ObservableList<Contatto> getContact() {
        return addressBook;
    }

    public void addContact(Contatto contact) {
        addressBook.add(contact);
    }

    public void editContact(Contatto existingContact, Contatto editedContact) {
        int index = addressBook.indexOf(existingContact);
        if (index != -1) {
            addressBook.set(index, editedContact);
        }
    }

    public void deleteContact(Contatto contact) {
        addressBook.remove(contact);
    }

    public ObservableList<Contatto> searchContact(String param) {
        ObservableList<Contatto> result = FXCollections.observableArrayList();
        for (Contatto contact : addressBook) {
            if (contact.getName().toLowerCase().contains(param.toLowerCase())
                    || contact.getSurname().toLowerCase().contains(param.toLowerCase())) {
                result.add(contact);
            }
        }
        return result;

    }
    
}
