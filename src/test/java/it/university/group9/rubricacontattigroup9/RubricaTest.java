package it.university.group9.rubricacontattigroup9;

import it.university.group9.rubricacontattigroup9.exceptions.CampoNonValidoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RubricaTest {

    private Rubrica rubrica;

    /*@BeforeEach
    public void setUp() {
        rubrica = new Rubrica();
        rubrica.setContactList(FXCollections.observableArrayList());
        rubrica.setFavoriteList(FXCollections.observableArrayList());

        assertNotNull(rubrica.getContactList(), "La lista dei contatti non dovrebbe essere null.");
        assertNotNull(rubrica.getFavoriteList(), "La lista dei preferiti non dovrebbe essere null.");
    }
*/
    @Test
    public void testAddContact_ValidContact_ShouldAddSuccessfully() throws CampoNonValidoException {
        rubrica = new Rubrica();
        rubrica.setContactList(FXCollections.observableArrayList());
        rubrica.setFavoriteList(FXCollections.observableArrayList());
        
        // Arrange
        String name = "Mario";
        String surname = "Rossi";
        List<String> numbers = Arrays.asList("1234567890", "1234567891", "1234567892");
        List<String> emails = Arrays.asList("mario.rossi@gmail.com","mario1.rossi@gmail.com", "mario2.rossi@gmail.com");
        String note = "Test note";

        // Act
        rubrica.addContact(name, surname, numbers, emails, note, "");

        // Assert
        ObservableList<Contatto> contactList = rubrica.getContactList();
        assertEquals(1, contactList.size());
        Contatto addedContact = contactList.get(0);
        assertEquals(name, addedContact.getName());
        assertEquals(surname, addedContact.getSurname());
        assertEquals(numbers, addedContact.getNumbers());
        assertEquals(emails, addedContact.getEmails());
        assertEquals(note, addedContact.getNote());
    }

    @Test
    public void testAddContact_DuplicateContact_ShouldThrowException() {
        rubrica = new Rubrica();
        rubrica.setContactList(FXCollections.observableArrayList());
        rubrica.setFavoriteList(FXCollections.observableArrayList());
        // Arrange
        String name = "Mario";
        String surname = "Rossi";
        List<String> numbers = Arrays.asList("1234567890", "", "");
        List<String> emails = Arrays.asList("mario.rossi@gmail.com", "", "");
        String note = "Test note";

        try {
            rubrica.addContact(name, surname, numbers, emails, note, "");
        } catch (CampoNonValidoException e) {
            fail("Non dovresti ottenere un'eccezione nella prima aggiunta!");
        }

        // Act & Assert
        assertThrows(CampoNonValidoException.class, () ->
                rubrica.addContact(name, surname, numbers, emails, note, ""));
    }

    @Test
    public void testEditContact_ValidEdit_ShouldUpdateContact() throws CampoNonValidoException {
        rubrica = new Rubrica();
        rubrica.setContactList(FXCollections.observableArrayList());
        rubrica.setFavoriteList(FXCollections.observableArrayList());
        // Arrange
        Contatto oldContact = new Contatto("Mario", "Rossi", Arrays.asList("1234567890", "", ""), Arrays.asList("mario.rossi@gmail.com", "", ""), "note vecchie", "");
        rubrica.getContactList().add(oldContact);

        String newName = "Maria";
        String newSurname = "Rossi";
        List<String> newNumbers = Arrays.asList("0987654321", "", "");
        List<String> newEmails = Arrays.asList("jane.smith@example.com", "", "");
        String newNote = "note nuove";

        // Act
        rubrica.editContact(oldContact, newName, newSurname, newNumbers, newEmails, newNote, "");

        // Assert
        ObservableList<Contatto> contactList = rubrica.getContactList();
        assertEquals(1, contactList.size());
        Contatto updatedContact = contactList.get(0);
        assertEquals(newName, updatedContact.getName());
        assertEquals(newSurname, updatedContact.getSurname());
        assertEquals(newNumbers, updatedContact.getNumbers());
        assertEquals(newEmails, updatedContact.getEmails());
        assertEquals(newNote, updatedContact.getNote());
    }

    @Test
    public void testDeleteContact_ExistingContact_ShouldRemoveContact() {
        rubrica = new Rubrica();
        rubrica.setContactList(FXCollections.observableArrayList());
        rubrica.setFavoriteList(FXCollections.observableArrayList());
        
        rubrica = new Rubrica();
        rubrica.setContactList(FXCollections.observableArrayList());
        rubrica.setFavoriteList(FXCollections.observableArrayList());
        // Arrange
        Contatto contact = new Contatto("Mario", "Rossi", Arrays.asList("1234567890"), Arrays.asList("mario.rossi@gmail.com"), "Note Test", "");
        rubrica.getContactList().add(contact);

        // Act
        rubrica.deleteContact(contact);

        // Assert
        assertTrue(rubrica.getContactList().isEmpty());
    }

    @Test
    public void testAddToFavorites_ValidContact_ShouldAddToFavorites() {
        rubrica = new Rubrica();
        rubrica.setContactList(FXCollections.observableArrayList());
        rubrica.setFavoriteList(FXCollections.observableArrayList());
        
        // Arrange
        Contatto contact = new Contatto("Mario", "Rossi", Arrays.asList("1234567890"), Arrays.asList("mario.rossi@gmail.com"), "Note Test", "");
        rubrica.getContactList().add(contact);

        // Act
        rubrica.addToFavorites(contact);

        // Assert
        ObservableList<Contatto> favoriteList = rubrica.getFavoriteList();
        assertEquals(1, favoriteList.size());
        assertTrue(contact.isFav());
    }

    @Test
    public void testRemoveFromFavorites_ExistingFavorite_ShouldRemoveFromFavorites() {
        rubrica = new Rubrica();
        rubrica.setContactList(FXCollections.observableArrayList());
        rubrica.setFavoriteList(FXCollections.observableArrayList());
        
        // Arrange
        Contatto contact = new Contatto("Mario", "Rossi", Arrays.asList("1234567890"), Arrays.asList("mario.rossi@gmail.com"), "Note Test", "");
        rubrica.getContactList().add(contact);
        rubrica.addToFavorites(contact);

        // Act
        rubrica.removeFromFavorites(contact);

        // Assert
        ObservableList<Contatto> favoriteList = rubrica.getFavoriteList();
        assertTrue(favoriteList.isEmpty());
        assertFalse(contact.isFav());
    }

    @Test
    public void testDeleteContact_ShouldRemoveAlsoFromFavorites() {
        rubrica = new Rubrica();
        rubrica.setContactList(FXCollections.observableArrayList());
        rubrica.setFavoriteList(FXCollections.observableArrayList());
        
        // Arrange
        Contatto contact = new Contatto("Mario", "Rossi", Arrays.asList("1234567890"), Arrays.asList("mario.rossi@gmail.com"), "Note Test", "");
        rubrica.getContactList().add(contact);
        rubrica.addToFavorites(contact);

        // Act
        rubrica.deleteContact(contact);

        // Assert
        ObservableList<Contatto> contactList = rubrica.getContactList();
        ObservableList<Contatto> favoriteList = rubrica.getFavoriteList();
        assertTrue(contactList.isEmpty());
        assertTrue(favoriteList.isEmpty());
    }
}
