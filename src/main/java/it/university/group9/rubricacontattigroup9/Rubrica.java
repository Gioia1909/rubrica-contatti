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

    public void setContactList(ObservableList<Contatto> contactList) throws Exception {
        if(contactList == null) throw new Exception("Argomento non valido ");
        this.contactList = contactList;
    }

    public void setFavoriteList(ObservableList<Contatto> favoriteList) {
        if(favoriteList == null);
        this.favoriteList = favoriteList;
    }

    @Override
    public ObservableList<Contatto> getContactList() {
        if(contactList == null) System.out.println("contactList  non valida ");
        return contactList;
    }

    public ObservableList<Contatto> getFavoriteList(){
        if(favoriteList == null);
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
        if (!isAddValid(newContact)) {
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
        if (!isModifyValid(oldContact, updatedContact)) {
            throw new CampoNonValidoException("Le modifiche generano un duplicato nella rubrica.");
        }

        // Aggiornamento del contatto
        int index = contactList.indexOf(oldContact);
        contactList.set(index, updatedContact);
        synchronizeFavorites(oldContact, updatedContact);
        saveData();
    }

    public void synchronizeFavorites(Contatto oldContact, Contatto updatedContact){
        int favoriteIndex = favoriteList.indexOf(oldContact);
        if(favoriteIndex != -1){
            favoriteList.set(favoriteIndex, updatedContact);
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

    public boolean isModifyValid(Contatto oldContact, Contatto updatedContact) {
        for (Contatto contact : contactList) {
            // Salta il contatto che si sta modificando
            if (contact.equals(oldContact)) {
                continue;
            }

            System.out.println("[DEBUG] Confronto contatto aggiornato: " + updatedContact);
            System.out.println("[DEBUG] Contatto esistente: " + contact);

            // Normalizza numeri di telefono
            List<String> normalizedUpdatedNumbers = normalizeNumbers(updatedContact.getNumbers());
            List<String> normalizedContactNumbers = normalizeNumbers(contact.getNumbers());

            // Normalizza emails
            List<String> normalizedUpdatedEmails = normalizeEmails(updatedContact.getEmails());
            List<String> normalizedContactEmails = normalizeEmails(contact.getEmails());

            // Verifica numeri di telefono sovrapposti
            boolean overlappingNumbers = normalizedUpdatedNumbers.stream()
                    .anyMatch(normalizedContactNumbers::contains);
            System.out.println("[DEBUG] Numeri sovrapposti: " + overlappingNumbers);

            // Verifica email sovrapposte
            boolean overlappingEmails = normalizedUpdatedEmails.stream()
                    .anyMatch(normalizedContactEmails::contains);

            System.out.println("[DEBUG] Email aggiornate normalizzate: " + normalizedUpdatedEmails);
            System.out.println("[DEBUG] Email esistenti normalizzate: " + normalizedContactEmails);
            System.out.println("[DEBUG] Sovrapposizione email rilevata: " + overlappingEmails);

            // Verifica nome e cognome
            boolean sameNameAndSurname = contact.getName().equalsIgnoreCase(updatedContact.getName())
                    && contact.getSurname().equalsIgnoreCase(updatedContact.getSurname());
            System.out.println("[DEBUG] Nome e cognome uguali: " + sameNameAndSurname);

            // Rileva duplicato se almeno una condizione è vera
            if (overlappingNumbers || overlappingEmails || sameNameAndSurname) {
                System.out.println("[DEBUG] Duplicato trovato: " + contact);
                return false; // Rilevato duplicato
            }
        }
        return true; // Nessun duplicato trovato

    }

    private boolean isAddValid(Contatto newContact) {
        for (Contatto contact : contactList) {
            System.out.println("[DEBUG] Confronto nuovo contatto: " + newContact + " con contatto esistente: " + contact);

            // Normalizza numeri di telefono
            List<String> normalizedNewNumbers = normalizeNumbers(newContact.getNumbers());
            List<String> normalizedContactNumbers = normalizeNumbers(contact.getNumbers());

            // Verifica numeri di telefono sovrapposti
            boolean overlappingNumbers = normalizedNewNumbers.stream()
                    .anyMatch(normalizedContactNumbers::contains);
            System.out.println("[DEBUG] Numeri sovrapposti: " + overlappingNumbers);

            // Verifica email sovrapposte
            List<String> normalizedNewEmails = normalizeEmails(newContact.getEmails());
            List<String> normalizedContactEmails = normalizeEmails(contact.getEmails());
            boolean overlappingEmails = normalizedNewEmails.stream()
                    .anyMatch(normalizedContactEmails::contains);
            System.out.println("[DEBUG] Email sovrapposte: " + overlappingEmails);

            // Verifica nome e cognome
            boolean sameNameAndSurname = contact.getName().equalsIgnoreCase(newContact.getName())
                    && contact.getSurname().equalsIgnoreCase(newContact.getSurname());
            System.out.println("[DEBUG] Nome e cognome uguali: " + sameNameAndSurname);

            // Rileva duplicato se almeno una condizione è vera
            if (overlappingNumbers || overlappingEmails || sameNameAndSurname) {
                System.out.println("[DEBUG] Duplicato trovato: " + contact);
                return false;
            }
        }
        return true; // Nessun duplicato trovato
    }

    private List<String> normalizeNumbers(List<String> numbers) {
        return numbers.stream()
                .filter(number -> number != null && !number.trim().isEmpty()) // Rimuove email vuote o nulle
                .map(number -> number.replaceAll("\\s+", "").trim())
                .collect(Collectors.toList());
    }

    private List<String> normalizeEmails(List<String> emails) {
        return emails.stream()
                .filter(email -> email != null && !email.trim().isEmpty()) // Rimuove email vuote o nulle
                .map(email -> email.trim().toLowerCase()) // Rimuove spazi e converte a minuscolo
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
