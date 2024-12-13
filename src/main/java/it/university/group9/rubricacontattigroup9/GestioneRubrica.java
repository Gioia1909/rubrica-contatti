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
 * @author monto
 */
public interface GestioneRubrica {
    
<<<<<<< Upstream, based on origin/main
    public ObservableList<Contatto> getContactList();
=======
    public ObservableList<Contatto> getContact();
>>>>>>> 8e6c66e Gestione Override
    public void addContact(String name, String surname, List<String> numbers, List<String> emails, String note) throws CampoNonValidoException, IOException;
    public void editContact(Contatto oldContact, String name, String surname, List<String> numbers, List<String> emails, String note) throws CampoNonValidoException, IOException ;
    public void deleteContact(Contatto contact) throws IOException;
    public ObservableList<Contatto> searchContact(String param);
        
}
