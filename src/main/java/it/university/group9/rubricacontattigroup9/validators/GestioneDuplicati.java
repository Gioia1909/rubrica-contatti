/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.validators;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;


/**
 *
 * @author Gruppo9
 */
public class GestioneDuplicati {
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

    private static List<String> normalizeNumbers(List<String> numbers) {
        return numbers.stream()
                .filter(number -> number != null && !number.trim().isEmpty()) // Rimuove email vuote o nulle
                .map(number -> number.replaceAll("\\s+", "").trim())
                .collect(Collectors.toList());
    }

    private static List<String> normalizeEmails(List<String> emails) {
        return emails.stream()
                .filter(email -> email != null && !email.trim().isEmpty()) // Rimuove email vuote o nulle
                .map(email -> email.trim().toLowerCase()) // Rimuove spazi e converte a minuscolo
                .collect(Collectors.toList());
    }
    
}

