

/**
 * @file GestioneDuplicati.java
 * @brief Classe che verifica se l'aggiunta di un contatto o la modifica sono corrette. 
 *
 *
 * @author Gruppo09
 * @see Contatto
 *
 */package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;

/**
 *
 * @author Gruppo09
 */
public class GestioneDuplicati {
    
    
/**
 * @brief Verifica che un contatto aggiornato non causi duplicati nella lista esistente, controllando numeri di telefono, email e nome e cognome
 * @param[in] oldContact Il contatto originale, prima dell'aggiornamento.
 * @param[in] updatedContact Il contatto aggiornato.
 * @param[in] contactList La lista di contatti esistenti da confrontare.
 * @return true Se il contatto aggiornato è valido (nessun duplicato), false se ci sono duplicati.
 * @throws NullPointerException Se la lista di contatti è nulla.
 */
    public static boolean isModifyValid(Contatto oldContact, Contatto updatedContact, ObservableList<Contatto>contactList) {
        for (Contatto contact : contactList) {
            // Salta il confronto con il contatto originale
            if (contact.equals(oldContact)) {
                continue;
            }

            // Debug
            System.out.println("[DEBUG] Confronto contatto aggiornato: " + updatedContact);
            System.out.println("[DEBUG] Contatto esistente: " + contact);

            // Normalizza numeri di telefono
            List<String> normalizedUpdatedNumbers = normalizeNumbers(updatedContact.getNumbers());
            List<String> normalizedContactNumbers = normalizeNumbers(contact.getNumbers());
            List<String> normalizedOriginalNumbers = normalizeNumbers(oldContact.getNumbers());

            // Verifica numeri di telefono sovrapposti, escludendo quelli già presenti nel contatto originale
            boolean overlappingNumbers = normalizedUpdatedNumbers.stream()
                    .anyMatch(number -> normalizedContactNumbers.contains(number) && !normalizedOriginalNumbers.contains(number));
            System.out.println("[DEBUG] Numeri sovrapposti: " + overlappingNumbers);

            // Normalizza emails
            List<String> normalizedUpdatedEmails = normalizeEmails(updatedContact.getEmails());
            List<String> normalizedContactEmails = normalizeEmails(contact.getEmails());
            List<String> normalizedOriginalEmails = normalizeEmails(oldContact.getEmails());

            // Verifica email sovrapposte, escludendo quelle già presenti nel contatto originale
            boolean overlappingEmails = normalizedUpdatedEmails.stream()
                    .anyMatch(email -> normalizedContactEmails.contains(email) && !normalizedOriginalEmails.contains(email));
            System.out.println("[DEBUG] Email aggiornate normalizzate: " + normalizedUpdatedEmails);
            System.out.println("[DEBUG] Email esistenti normalizzate: " + normalizedContactEmails);
            System.out.println("[DEBUG] Sovrapposizione email rilevata: " + overlappingEmails);

            // Verifica nome e cognome
            boolean sameNameAndSurname = contact.getName().equalsIgnoreCase(updatedContact.getName())
                    && contact.getSurname().equalsIgnoreCase(updatedContact.getSurname());

            // Rileva duplicato se almeno una condizione è vera
            if (sameNameAndSurname || overlappingNumbers || overlappingEmails) {
                System.out.println("[DEBUG] Duplicato trovato: " + contact);
                return false; // Rilevato duplicato
            }
        }
        return true; // Nessun duplicato trovato
    }
    
/**
 * @brief Verifica che un nuovo contatto non causi duplicati nella lista esistente.
 * @param[in] newContact Il nuovo contatto da aggiungere.
 * @param[in] contactList La lista di contatti esistenti da confrontare.
 * @return true Se il nuovo contatto è valido (nessun duplicato), false se ci sono duplicati.
 * @throws NullPointerException Se la lista di contatti è nulla.
 */
    public static boolean isAddValid(Contatto newContact, ObservableList<Contatto>contactList) {
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

/**
 * @brief Rimuove spazi bianchi e valori vuoti o nulli dalla lista di numeri.
 * 
 * @param[in] numbers La lista di numeri di telefono da normalizzare.
 * @return Una lista di numeri di telefono normalizzati.
 */
    private static List<String> normalizeNumbers(List<String> numbers) {
        return numbers.stream()
                .filter(number -> number != null && !number.trim().isEmpty()) // Rimuove email vuote o nulle
                .map(number -> number.replaceAll("\\s+", "").trim())
                .collect(Collectors.toList());
    }
    
    
/**
 * @brief Rimuove spazi bianchi e valori vuoti o nulli dalla lista delle emails.
 * 
 * @param[in] emails La lista delle emails da normalizzare.
 * @return Una lista di emails normalizzate.
 */    
    private static List<String> normalizeEmails(List<String> emails) {
        return emails.stream()
                .filter(email -> email != null && !email.trim().isEmpty()) // Rimuove email vuote o nulle
                .map(email -> email.trim().toLowerCase()) // Rimuove spazi e converte a minuscolo
                .collect(Collectors.toList());
    }
    
}
