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
// Modello della Rubrica : Edit, Add, Delete

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

    public void setContactList(ObservableList<Contatto> contactList){
        this.contactList = contactList;
    }

    public void setFavoriteList(ObservableList<Contatto> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @Override
    public ObservableList<Contatto> getContactList() {
        return contactList;
    }

    public ObservableList<Contatto> getFavoriteList() {
        return favoriteList;
    }

    @Override
    public void addContact(String name, String surname, List<String> numbers, List<String> emails, String note)
            throws CampoNonValidoException {

        ContattoValidator.validateName(name);
        ContattoValidator.validateSurname(surname);
        ContattoValidator.validateEmail(emails);
        ContattoValidator.validatePhoneNumber(numbers);
        ContattoValidator.validateFields(name, surname, numbers);
        // Verifichiamo che la lista dei numeri sia benformata

        Contatto newContact = new Contatto(name, surname, numbers, emails, note);
        // Controllo duplicati prima dell'aggiunta
        if (!GestioneDuplicati.isAddValid(newContact,contactList)) {
            throw new CampoNonValidoException("Il contatto è già presente nella rubrica.");
        }
        contactList.add(newContact);
        sort(contactList);
        saveData();
    }

    @Override
    public void editContact(Contatto oldContact, String name, String surname, List<String> numbers, List<String> emails, String note)
            throws CampoNonValidoException {

        ContattoValidator.validateName(name);
        ContattoValidator.validateSurname(surname);
        ContattoValidator.validateEmail(emails);
        ContattoValidator.validatePhoneNumber(numbers);
        ContattoValidator.validateFields(name, surname, numbers);
        Contatto updatedContact = new Contatto(name, surname, numbers, emails, note);
        // Controllo validità della modifica
        if (!GestioneDuplicati.isModifyValid(oldContact, updatedContact, contactList)) {
            throw new CampoNonValidoException("Le modifiche generano un duplicato nella rubrica.");
        }
        synchronizeContacts(oldContact, updatedContact);
        // Aggiornamento del contatto
        //int index = contactList.indexOf(oldContact);
        //contactList.set(index, updatedContact);

        saveFavorites();
        saveData();
    }

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
        saveFavorites();
        saveData();

    }

    @Override
    public void deleteContact(Contatto contact) {
        if (contactList.remove(contact)) {
            favoriteList.remove(contact);
            saveData();
        }
    }

    public void addToFavorites(Contatto contact) {
        if (contactList.contains(contact) && !favoriteList.contains(contact)) {
            favoriteList.add(contact);
            contact.setFav(true);
            sort(favoriteList);
            saveFavorites();
            saveData();
        }
    }

    public void removeFromFavorites(Contatto contact) {
        if (favoriteList.remove(contact)) {
            contact.setFav(false);
            saveFavorites();
            saveData();
        }
    }

    @Override
    public ObservableList<Contatto> searchContact(String param) {
        return contactList.stream()
                .filter(contact -> matchesContact(contact, param))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Contatto> searchFavoriteContact(String param) {
        return favoriteList.stream()
                .filter(contact -> matchesContact(contact, param))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private boolean matchesContact(Contatto contact, String param) {
        String lowerParam = param.toLowerCase();
        return contact.getName().toLowerCase().contains(lowerParam)
                || contact.getSurname().toLowerCase().contains(lowerParam)
                || contact.getNumbers().stream().anyMatch(num -> num.contains(param))
                || contact.getEmails().stream().anyMatch(email -> email.contains(param));
    }

    private void saveData() {
        SalvaCaricaRubrica.saveAddressBook(contactList);
        saveFavorites();
    }

    private void saveFavorites() {
        SalvaCaricaPreferiti.saveFavoritesAddressBook(favoriteList);
    }

    private void sort(ObservableList<Contatto> list) {
        FXCollections.sort(list);
    }
}