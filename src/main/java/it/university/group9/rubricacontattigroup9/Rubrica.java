package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import it.university.group9.rubricacontattigroup9.validators.ContattoValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rubrica implements GestioneRubrica{

    private ObservableList<Contatto> contactList;
    private ObservableList<Contatto> favoriteList;

    public Rubrica() {
        this.contactList = SalvaCaricaRubrica.loadAddressBook();
        this.favoriteList = SalvaCaricaPreferiti.loadFavoritesAddressBook();
        synchronizeFavorites();
    }

    /**
     * @brief Sincronizza la lista dei preferiti con i contatti principali.
     *
     * Se un contatto nella lista dei preferiti non è più presente nella rubrica principale,
     * viene rimosso automaticamente dai preferiti.
     */
    private void synchronizeFavorites() {
        favoriteList.removeIf(fav -> !contactList.contains(fav));
    }

    /**
     * @brief Restituisce la lista dei contatti.
     * @return Lista osservabile dei contatti principali.
     */
    @Override
    public ObservableList<Contatto> getContactList() {
        return contactList;
    }

    /**
     * @brief Restituisce la lista dei contatti preferiti.
     * @return Lista osservabile dei contatti preferiti.
     */
    public ObservableList<Contatto> getFavoriteList() {
        return favoriteList;
    }

    /**
     * @brief Aggiunge un contatto alla rubrica principale.
     * @throws CampoNonValidoException Se uno dei campi non è valido.
     * @throws IOException Se si verifica un errore durante il salvataggio.
     */

    @Override
    public void addContact(String name, String surname, List<String> numbers, List<String> emails, String note)
            throws CampoNonValidoException {

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
        sort(contactList);
        try {
            saveContacts();
        } catch (IOException ex) {
            System.out.println("Errore nel Salvataggio della Rubrica dopo l'aggiunta di " + newContact);
        }
    }

    /**
     * @brief Modifica un contatto esistente.
     */
    
    @Override
    public void editContact(Contatto oldContact, String name, String surname, List<String> numbers, List<String> emails, String note)
            throws CampoNonValidoException {

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
            try {
                saveFavorites(); // Salva le modifiche
            } catch (IOException ex) {
                System.out.println("Errore nel Salvataggio dei Preferiti dopo la modifica di " + oldContact);
            }
            try {
                saveContacts();
            } catch (IOException ex) {
                System.out.println("Errore nel Salvataggio dei Contatti dopo la modifica di " + oldContact);
            }
        }
    }

    /**
     * @brief Rimuove un contatto dalla rubrica principale e dai preferiti.
     */
    @Override
    public void deleteContact(Contatto contact){
        int index = contactList.indexOf(contact);
        if (index != -1) {
            contactList.remove(contact);
            favoriteList.remove(contact);
            try {
                saveFavorites(); // Salva le modifiche
            } catch (IOException ex) {
                System.out.println("Errore nel Salvataggio dei Preferiti dopo la rimozione di " + contact);
            }
            try {
                saveContacts();
            } catch (IOException ex) {
                System.out.println("Errore nel Salvataggio dei Contatti dopo la rimozione di " + contact);
            }
        }
    }

    /**
     * @brief Aggiunge un contatto ai preferiti.
     */
    public void addToFavorites(Contatto contact){
        if (contactList.contains(contact) && !favoriteList.contains(contact)) {
            favoriteList.add(contact);
            contact.setFav(true); // Imposta il contatto come preferito
            sort(favoriteList);// ordiniamo la lista dei preferiti
            try {
                saveFavorites();
            } catch (IOException ex) {
                System.out.println("Errore nel Salvataggio dei Preferiti dopo l'aggiunta di " + contact);
            }
        }
    }

    /**
     * @brief Rimuove un contatto dai preferiti.
     */
    public void removeFromFavorites(Contatto contact) {
        int index = contactList.indexOf(contact);
        if (index != -1) {
            contact.setFav(false); // Rimuovi lo stato di preferito
            favoriteList.remove(contact);
            try {
                saveFavorites(); // Salva le modifiche
            } catch (IOException ex) {
                System.out.println("Errore nel Salvataggio dei Preferiti dopo la rimozione di " + contact);
            }
        }
    }

    /**
     * @brief Cerca contatti nella rubrica principale.
     */
    @Override
    public ObservableList<Contatto> searchContact(String param) {
        ObservableList<Contatto> result = FXCollections.observableArrayList();
        for (Contatto contact : contactList) {
            if (contact.getName().toLowerCase().contains(param.toLowerCase())
                    || contact.getSurname().toLowerCase().contains(param.toLowerCase()) 
                    || contact.getNumbers().stream().anyMatch(num -> num.contains(param))
                    || contact.getEmails().stream().anyMatch(email -> email.contains(param))) {
                result.add(contact);
            }
        }
        return result;
    }
    
    /**
     * @brief Cerca contatti nella rubrica dei preferiti.
     */
    public ObservableList<Contatto> searchFavoriteContact(String param) {
        ObservableList<Contatto> result = FXCollections.observableArrayList();
        for (Contatto contact : favoriteList) {
            if (contact.getName().toLowerCase().contains(param.toLowerCase())
                    || contact.getSurname().toLowerCase().contains(param.toLowerCase()) 
                    || contact.getNumbers().stream().anyMatch(num -> num.contains(param))
                    || contact.getEmails().stream().anyMatch(email -> email.contains(param))) {
                result.add(contact);
            }
        }
        return result;
    }

    /**
     * @brief Salva i contatti principali sul file JSON.
     */
    private void saveContacts() throws IOException {
        SalvaCaricaRubrica.saveAddressBook(contactList);
    }

    /**
     * @brief Salva i contatti preferiti sul file JSON.
     */
    private void saveFavorites() throws IOException {
        SalvaCaricaPreferiti.saveFavoritesAddressBook(favoriteList);
    }

    private void sort(ObservableList<Contatto> contactList){
        FXCollections.sort(contactList);
    }
    
}
