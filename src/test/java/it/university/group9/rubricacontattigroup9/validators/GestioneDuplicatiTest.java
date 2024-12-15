/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;


public class GestioneDuplicatiTest {
     private ObservableList<Contatto> contactList;

   
   
   
    @BeforeEach
    public void setUp() {
    }
   
    @AfterEach
    public void tearDown() {
    }
   
    @Test
    public void testIsAddValid() {
        // Caso base: Un contatto iniziale di riferimento
    List<String> numeri1 = new ArrayList<>(Arrays.asList("1234567890"));
    List<String> email1 = new ArrayList<>(Arrays.asList("email1@gmail.com"));
    Contatto contatto1 = new Contatto("Mario", "Rossi", numeri1, email1, " ", "");

    // Caso 1: Contatto con numero di telefono duplicato
    List<String> numeri2 = new ArrayList<>(Arrays.asList("1234567890")); // Numero duplicato
    List<String> email2 = new ArrayList<>(Arrays.asList("email2@gmail.com"));
    Contatto contatto2 = new Contatto("Luigi", "Bianchi", numeri2, email2, " ", "");

    // Caso 2: Contatto con email duplicata
    List<String> numeri3 = new ArrayList<>(Arrays.asList("0987654321"));
    List<String> email3 = new ArrayList<>(Arrays.asList("email1@gmail.com")); // Email duplicata
    Contatto contatto3 = new Contatto("Anna", "Verdi", numeri3, email3, " ", "");

    // Caso 3: Contatto con nome e cognome duplicati
    List<String> numeri4 = new ArrayList<>(Arrays.asList("1122334455"));
    List<String> email4 = new ArrayList<>(Arrays.asList("email4@gmail.com"));
    Contatto contatto4 = new Contatto("Mario", "Rossi", numeri4, email4, " ", ""); // Nome e cognome uguali

    // Caso 4: Contatto con numeri e email univoci (nessun duplicato)
    List<String> numeri5 = new ArrayList<>(Arrays.asList("6677889900"));
    List<String> email5 = new ArrayList<>(Arrays.asList("email5@gmail.com"));
    Contatto contatto5 = new Contatto("Francesca", "Neri", numeri5, email5, " ", "");

    // Caso 5: Contatto con nome duplicato, ma cognome diverso (valido)
    List<String> numeri6 = new ArrayList<>(Arrays.asList("5566778899"));
    List<String> email6 = new ArrayList<>(Arrays.asList("email6@gmail.com"));
    Contatto contatto6 = new Contatto("Mario", "Bianchi", numeri6, email6, " ", ""); // Nome uguale, cognome diverso

    // Caso 6: Contatto con cognome duplicato, ma nome diverso (valido)
    List<String> numeri7 = new ArrayList<>(Arrays.asList("7788990011"));
    List<String> email7 = new ArrayList<>(Arrays.asList("email7@gmail.com"));
    Contatto contatto7 = new Contatto("Giuseppe", "Rossi", numeri7, email7, " ", ""); // Cognome uguale, nome diverso

    // Caso 7: Contatto con numeri parzialmente duplicati
    List<String> numeri8 = new ArrayList<>(Arrays.asList("1234567890", "5566778899")); // Uno duplicato
    List<String> email8 = new ArrayList<>(Arrays.asList("email8@gmail.com"));
    Contatto contatto8 = new Contatto("Luca", "Ferrari", numeri8, email8, " ", "");

    // Caso 8: Contatto completamente diverso (nessun duplicato)
    List<String> numeri9 = new ArrayList<>(Arrays.asList("3344556677"));
    List<String> email9 = new ArrayList<>(Arrays.asList("email9@gmail.com"));
    Contatto contatto9 = new Contatto("Sara", "Conti", numeri9, email9, " ", "");
   
    ObservableList<Contatto> contactList = FXCollections.observableArrayList();
    contactList.add(contatto1);
    boolean result1=GestioneDuplicati.isAddValid(contatto2, contactList);
    assertFalse(result1);

    boolean result2=GestioneDuplicati.isAddValid(contatto3, contactList);
    assertFalse(result2);

    boolean result3=GestioneDuplicati.isAddValid(contatto4, contactList);
    assertFalse(result3);

    boolean result4=GestioneDuplicati.isAddValid(contatto5, contactList);
    assertTrue(result4);

    boolean result5=GestioneDuplicati.isAddValid(contatto6, contactList);
    assertTrue(result5);

    boolean result6=GestioneDuplicati.isAddValid(contatto7, contactList);
    assertTrue(result6);

    boolean result7=GestioneDuplicati.isAddValid(contatto8, contactList);
    assertFalse(result7);

    boolean result8=GestioneDuplicati.isAddValid(contatto9, contactList);
    assertTrue(result8);



   

    }
   
   
    @Test
    public void testModifyValid() {
       // Contatto da modificare
    List<String> numeri1 = new ArrayList<>(Arrays.asList("1234567890"));
    List<String> email1 = new ArrayList<>(Arrays.asList("email1@gmail.com"));
    Contatto oldContact = new Contatto("Mario", "Rossi", numeri1, email1, " ", "");
   
    //contatto in rubrica con cui testare:
    Contatto testContact= new Contatto("Luigi", "Bianchi", Arrays.asList("9988776655"), Arrays.asList("email9@gmail.com"), " ", "");

    // Lista iniziale dei contatti
    ObservableList<Contatto> contactList = FXCollections.observableArrayList();
    contactList.add(oldContact);
    contactList.add(testContact);


    // Caso 1: Contatto aggiornato con nome e cognome duplicati
    List<String> numeri2 = new ArrayList<>(Arrays.asList("0987654321"));
    List<String> email2 = new ArrayList<>(Arrays.asList("email2@gmail.com"));
    Contatto contatto1 = new Contatto("Luigi", "Bianchi", numeri2, email2, " ", ""); // Nome e cognome uguali

    // Caso 2: Contatto aggiornato con numero duplicato
    List<String> numeri3 = new ArrayList<>(Arrays.asList("1234567890", "9988776655")); // Uno duplicato
    List<String> email3 = new ArrayList<>(Arrays.asList("email1@gmail.com"));
    Contatto contatto2 = new Contatto("Mario", "Rossi", numeri3, email3, " ", "");

    // Caso 3: Contatto aggiornato con email duplicata
    List<String> numeri4 = new ArrayList<>(Arrays.asList("6677889900"));
    List<String> email4 = new ArrayList<>(Arrays.asList("email9@gmail.com")); // Email duplicata
    Contatto contatto3 = new Contatto("Mario", "Rossi", numeri4, email4, " ", "");

    // Caso 4: Contatto aggiornato valido (nessun duplicato)
    List<String> numeri5 = new ArrayList<>(Arrays.asList("5566778899"));
    List<String> email5 = new ArrayList<>(Arrays.asList("email5@gmail.com"));
    Contatto contatto4 = new Contatto("Francesca", "Neri", numeri5, email5, " ", "");

    // Caso 5: Contatto aggiornato con cognome uguale (valido)
    List<String> numeri6 = new ArrayList<>(Arrays.asList("3344556677"));
    List<String> email6 = new ArrayList<>(Arrays.asList("email6@gmail.com"));
    Contatto contatto5 = new Contatto("Mario", "Bianchi", numeri6, email6, " ", ""); // Cognome uguale

    // Caso 6: Contatto aggiornato con numero e email del contatto originale (valido)
    Contatto contatto6 = new Contatto("Mario", "Bianchi", Arrays.asList("1234567890"), Arrays.asList("email1@gmail.com"), " ", "");

    // Caso 7: Contatto aggiornato con numero e email parzialmente duplicati
    List<String> numeri8 = new ArrayList<>(Arrays.asList("9988776655", "5566778899")); // Uno duplicato
    List<String> email8 = new ArrayList<>(Arrays.asList("email9@gmail.com", "email8@gmail.com")); // Una duplicata
    Contatto contatto7 = new Contatto("Luca", "Bianchi", numeri8, email8, " ", "");
   
   
   
   

    // Caso 1: Nome e cognome duplicati
    boolean result1 = GestioneDuplicati.isModifyValid(oldContact, contatto1, contactList);
    assertFalse(result1);

    // Caso 2: Numero duplicato
    boolean result2 = GestioneDuplicati.isModifyValid(oldContact, contatto2, contactList);
    assertFalse(result2);

    // Caso 3: Email duplicata
    boolean result3 = GestioneDuplicati.isModifyValid(oldContact, contatto3, contactList);
    assertFalse(result3);

    // Caso 4: Contatto valido
    boolean result4 = GestioneDuplicati.isModifyValid(oldContact, contatto4, contactList);
    assertTrue(result4);

    // Caso 5: Nome diverso, cognome uguale
    boolean result5 = GestioneDuplicati.isModifyValid(oldContact, contatto5, contactList);
    assertTrue(result5);

    // Caso 6: Numero e email originali
    boolean result6 = GestioneDuplicati.isModifyValid(oldContact, contatto6, contactList);
    assertTrue(result6);

    // Caso 7: Numero e email parzialmente duplicati
    boolean result7 = GestioneDuplicati.isModifyValid(oldContact, contatto7, contactList);
    assertFalse(result7);



    }

}