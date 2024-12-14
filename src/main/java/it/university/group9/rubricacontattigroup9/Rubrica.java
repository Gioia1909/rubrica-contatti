package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaRubrica;
import it.university.group9.rubricacontattigroup9.InputOutput.SalvaCaricaPreferiti;
import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import it.university.group9.rubricacontattigroup9.validators.ContattoValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Rubrica implements GestioneRubrica {

    private ObservableList<Contatto> contactList;
    private ObservableList<Contatto> favoriteList;

    public Rubrica() {
        this.contactList = SalvaCaricaRubrica.loadAddressBook();
        this.favoriteList = SalvaCaricaPreferiti.loadFavoritesAddressBook();
        System.out.println("Lista iniziale dei preferiti: " + favoriteList.size());
    }

    public void setContactList(ObservableList<Contatto> contactList) {
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
         ContattoValidator.validateFields(name,surname,numbers);

        Contatto newContact = new Contatto(name, surname, numbers, emails, note);
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
           ContattoValidator.validateFields(name,surname,numbers);

        int index = contactList.indexOf(oldContact);
        if (index != -1) {
            Contatto updatedContact = new Contatto(name, surname, numbers, emails, note);
            contactList.set(index, updatedContact);
            saveData();
        }
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
        }
    }

    public void removeFromFavorites(Contatto contact) {
        if (favoriteList.remove(contact)) {
            contact.setFav(false);
            saveFavorites();
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

    public boolean isEditValid(Contatto oldContact, Contatto updatedContact) {
        for (Contatto contact : contactList) {
            if (contact.equals(oldContact)) continue;

            System.out.println("[DEBUG] Confronto con contatto esistente: " + contact);

            List<String> normalizedUpdatedNumbers = normalizeNumbers(updatedContact.getNumbers());
            List<String> normalizedContactNumbers = normalizeNumbers(contact.getNumbers());

            boolean overlappingNumbers = normalizedUpdatedNumbers.stream()
                    .anyMatch(normalizedContactNumbers::contains);

            boolean sameNameAndSurname = contact.getName().equalsIgnoreCase(updatedContact.getName())
                    && contact.getSurname().equalsIgnoreCase(updatedContact.getSurname());

            if (sameNameAndSurname && overlappingNumbers) {
                System.out.println("[DEBUG] Duplicato trovato: " + contact);
                return false;
            }
        }
        return true;
    }

    private List<String> normalizeNumbers(List<String> numbers) {
        return numbers.stream()
                .map(number -> number.replaceAll("\\s+", "").trim())
                .collect(Collectors.toList());
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
