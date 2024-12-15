package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import it.university.group9.rubricacontattigroup9.validators.ContattoValidator;
import it.university.group9.rubricacontattigroup9.validators.GestioneDuplicati;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @file Rubrica.java
 * @brief Implementazione di metodi per la gestione dei contatti e dei preferiti
 * in una rubrica.
 *
 * @see GestioneRubrica
 */
public class Rubrica implements GestioneRubrica {

    private ObservableList<Contatto> contactList;
    private ObservableList<Contatto> favoriteList;

    public Rubrica() {
        // Inizializziamo le observables
        this.contactList = SalvaCaricaRubrica.loadAddressBook();
        this.favoriteList = SalvaCaricaPreferiti.loadFavoritesAddressBook();
        System.out.println("Lista iniziale dei contatti: " + contactList.size());
        System.out.println("Lista iniziale dei preferiti: " + favoriteList.size());
    }

    /**
     * @brief Imposta la lista dei contatti.
     *
     * @param contactList La lista osservabile di contatti da impostare.
     */
    public void setContactList(ObservableList<Contatto> contactList) {
        this.contactList = contactList;
    }

    /**
     * @brief Imposta la lista dei contatti preferiti.
     *
     * Questo metodo imposta la lista dei contatti preferiti a quella fornita
     * come parametro. Viene utilizzato per aggiornare la lista dei contatti
     * preferiti della rubrica.
     *
     * @param favoriteList La lista osservabile di contatti preferiti da
     * impostare.
     */
    public void setFavoriteList(ObservableList<Contatto> favoriteList) {
        this.favoriteList = favoriteList;
    }

    /**
     * @see GestioneRubrica
     */
    @Override
    public ObservableList<Contatto> getContactList() {
        return contactList;
    }

    /**
     * @see GestioneRubrica
     */
    public ObservableList<Contatto> getFavoriteList() {
        return favoriteList;
    }

    /**
     * @see GestioneRubrica
     */
    @Override
    public void addContact(String name, String surname, List<String> numbers, List<String> emails, String note, String selectedGender)
            throws CampoNonValidoException {

        ContattoValidator.validateName(name);
        ContattoValidator.validateSurname(surname);
        ContattoValidator.validateEmail(emails);
        ContattoValidator.validatePhoneNumber(numbers);
        ContattoValidator.validateFields(name, surname, numbers);
        // Verifichiamo che la lista dei numeri sia benformata

        Contatto newContact = new Contatto(name, surname, numbers, emails, note, selectedGender);
        // Controllo duplicati prima dell'aggiunta
        if (!GestioneDuplicati.isAddValid(newContact, contactList)) {
            throw new CampoNonValidoException("Il contatto è già presente nella rubrica.");
        }
        contactList.add(newContact);
        FXCollections.sort(contactList);
        saveData();
    }

    /**
     * @see GestioneRubrica
     */
    @Override
    public void editContact(Contatto oldContact, String name, String surname, List<String> numbers, List<String> emails, String note, String selectedGender)
            throws CampoNonValidoException {

        ContattoValidator.validateName(name);
        ContattoValidator.validateSurname(surname);
        ContattoValidator.validateEmail(emails);
        ContattoValidator.validatePhoneNumber(numbers);
        ContattoValidator.validateFields(name, surname, numbers);
        Contatto updatedContact = new Contatto(name, surname, numbers, emails, note, selectedGender);
        // Controllo validità della modifica
        if (!GestioneDuplicati.isModifyValid(oldContact, updatedContact, contactList)) {
            throw new CampoNonValidoException("Le modifiche generano un duplicato nella rubrica.");
        }
        synchronizeContacts(oldContact, updatedContact);
        // Aggiornamento del contatto
        //int index = contactList.indexOf(oldContact);
        //contactList.set(index, updatedContact);

        saveData();
    }

    /**
     * @see GestioneRubrica
     */
    @Override
    public ObservableList<Contatto> searchContact(String param) {
        return contactList.stream()
                .filter(contact -> matchesContact(contact, param))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    /**
     * @brief Sincronizza un contatto aggiornato in entrambe le liste (contatti
     * e preferiti).
     *
     *
     * @param oldContact Il contatto originale da aggiornare.
     * @param updatedContact Il contatto con i dati aggiornati.
     *
     * @see savaData()
     *
     */
    public void synchronizeContacts(Contatto oldContact, Contatto updatedContact) {
        int favoriteIndex = favoriteList.indexOf(oldContact);
        int contactIndex = contactList.indexOf(oldContact);
        updatedContact.setFav(oldContact.isFav());

        if (favoriteIndex != -1) {
            favoriteList.set(favoriteIndex, updatedContact);
        }
        if (contactIndex != -1) {
            contactList.set(contactIndex, updatedContact);
        }

        // Forza il salvataggio dopo la sincronizzazione
        saveData();

    }

    /**
     * @see GestioneRubrica
     */
    @Override
    public void deleteContact(Contatto contact) {
        if (contactList.remove(contact)) {
            favoriteList.remove(contact);
            saveData();
        }
    }

    /**
     * @see GestioneRubrica
     */
    public void addToFavorites(Contatto contact) {
        if (contactList.contains(contact) && !favoriteList.contains(contact)) {
            favoriteList.add(contact);
            contact.setFav(true);
            FXCollections.sort(favoriteList);
            saveData();
        }
    }

    /**
     * @see GestioneRubrica
     */
    public void removeFromFavorites(Contatto contact) {
        if (favoriteList.remove(contact)) {
            contact.setFav(false);
            saveData();
        }
    }

    /**
     * @see GestioneRubrica
     */
    public ObservableList<Contatto> searchFavoriteContact(String param) {
        return favoriteList.stream()
                .filter(contact -> matchesContact(contact, param))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    /**
     * @brief Verifica se un contatto corrisponde ai parametri di ricerca.
     *
     * Questo metodo confronta il nome, cognome, numeri di telefono ed email del
     * contatto con il parametro di ricerca fornito, restituendo `true` se c'è
     * una corrispondenza, altrimenti `false`. La ricerca è insensibile al
     * maiuscolo/minuscolo.
     *
     * @param contact Il contatto da confrontare.
     * @param param Il parametro di ricerca (nome, cognome, numero di telefono o
     * email).
     * @return `true` se il contatto corrisponde al parametro, altrimenti
     * `false`.
     */
    private boolean matchesContact(Contatto contact, String param) {
        String lowerParam = param.toLowerCase();
        return contact.getName().toLowerCase().contains(lowerParam)
                || contact.getSurname().toLowerCase().contains(lowerParam)
                || contact.getNumbers().stream().anyMatch(num -> num.contains(param))
                || contact.getEmails().stream().anyMatch(email -> email.contains(param));
    }

    /**
     * @brief Salva i dati dei contatti nella rubrica e in quella dei preferiti
     *
     * @see SalvaCaricaRubrica
     * @see SalvaCaricaPreferiti
     */
    private void saveData() {
        SalvaCaricaRubrica.saveAddressBook(contactList);
        SalvaCaricaPreferiti.saveFavoritesAddressBook(favoriteList);
    }

}
