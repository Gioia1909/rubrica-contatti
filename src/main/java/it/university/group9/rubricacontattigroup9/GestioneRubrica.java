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
 *
 * @author Gruppo09
 */
public interface GestioneRubrica {
    
    public ObservableList<Contatto> getContactList();
    public ObservableList<Contatto> getFavoriteList();
    public void addContact(String name, String surname, List<String> numbers, List<String> emails, String note, String selectedGender) throws CampoNonValidoException;
    public void editContact(Contatto oldContact, String name, String surname, List<String> numbers, List<String> emails, String note, String selectedGender) throws CampoNonValidoException ;
    public void deleteContact(Contatto contact);
    public ObservableList<Contatto> searchContact(String param);
    public void addToFavorites(Contatto contact);
    public void removeFromFavorites(Contatto contact);
    public ObservableList<Contatto> searchFavoriteContact(String param);
    
}
