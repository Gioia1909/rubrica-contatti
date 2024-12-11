

package it.university.group9.rubricacontattigroup9;

import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

/**
 * @file Contatto.java
 * @brief Classe che rappresenta un contatto con un nome, un cognome, da uno a
 * tre numeri di telefono, da una a tre email e delle note.
 * 
 *
 * @author Gruppo09
 * @date 11/12/2024
 * @version
 *
 */
public class Contatto implements Comparable<Contatto> {

    //JsonProperty serve a definire il campo che conterrà questi elementi sul file JSON
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("numbers")
    private List<String> numbers;
    @JsonProperty("emails")
    private List<String> emails;
    @JsonProperty("note")
    private String note;

    /**
     * @brief Costruttore della classe Contatto.
     *
     *
     * @param[in] name Il nome del contatto.
     * @param[in] surname Il cognome del contatto.
     * @param[in] numbers Lista di numeri di telefono associati al contatto.
     * @param[in] emails Lista di email associate al contatto.
     * @param[in] note Note aggiuntive relative al contatto.
     */
    public Contatto(String name, String surname, List<String> numbers, List<String> emails, String note) {
        this.name = name;
        this.surname = surname;
        this.numbers = numbers;
        this.emails = emails;
        this.note = note;
    }

    /**
     * @brief Costruttore predefinito della classe Contatto.
     *
     * Inizializza il contatto con liste vuote per numeri e email.
     */
    public Contatto() {
        this.numbers = new LinkedList<>();
        this.emails = new LinkedList<>();
    }

    /**
     * @brief Metodo che restituisce il nome del contatto.
     *
     * @return il nome del contatto.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Metodo che restituisce il cognome del contatto.
     *
     * @return il cognome del contatto.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @brief Metodo che resituisce la lista dei numeri di telefono del
     * contatto.
     *
     * @return Una lista contenente i numeri di telefono del contatto.
     */
    public List<String> getNumbers() {
        return numbers;
    }

    /**
     * @brief Metodo che restituisce la lista delle email del contatto.
     *
     * @return Una lista contenente le email del contatto.
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     * @brief Metodo che restituisce le note del contatto.
     *
     * @return Le note associate al contatto.
     */
    public String getNote() {
        return note;
    }

    /**
     * @brief Restituisce una rappresentazione testuale dei dati del contatto
     *
     * @return Una stringa contenente i dati del contatto: nome, cognome, numeri
     * di telefono, email e note.
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(name);
        sb.append(" ");
        sb.append(surname);
        for (String number : numbers) {
            sb.append(numbers);
            sb.append(" ");
        }
        for (String email : emails) {
            sb.append(email);
            sb.append(" ");

        }

        sb.append(note);
        return sb.toString();
    }

    /**
     * @brief Confronta due oggetti di tipo Contatto per determinarne l'ordine
     * naturale.
     *
     * Questo metodo ordina i contatti in base al cognome in ordine alfabetico.
     * Se i cognomi sono identici, l'ordinamento prosegue in base al nome,
     * sempre in ordine alfabetico. Il confronto è case-insensitive.
     *
     * @param[in] obj Il contatto da confrontare con l'oggetto corrente.
     * @return Un intero che rappresenta il risultato del confronto: 
     * - Un valore negativo se l'oggetto corrente precede il contatto `obj`. 
     * - Zero se i due contatti sono considerati equivalenti. 
     * - Un valore positivo se l'oggetto corrente segue il contatto `obj`.
     *
     * @pre L'oggetto `obj` non deve essere null.
     * 
     */
    @Override
    public int compareTo(Contatto obj) {
        int result = this.surname.compareToIgnoreCase(obj.surname);
        if (result == 0) {       // se hanno lo stesso cognome
            return this.name.compareToIgnoreCase(obj.name); // Se i cognomi sono uguali, ordina per nome
        }
        return result;
    }

    /**
     * @brief Metodo che determina l'uguaglianza tra due oggetti Contatto. Due
     * contatti sono considerati uguali se nome, cognome, numeri, email e note
     * coincidono.
     * @param[in] o L'oggetto da confrontare.
     * @return True se gli oggetti sono uguali, false se sono diversi.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contatto contatto = (Contatto) o;
        return Objects.equals(name, contatto.name)
                && Objects.equals(surname, contatto.surname)
                && Objects.equals(numbers, contatto.numbers)
                && Objects.equals(emails, contatto.emails)
                && Objects.equals(note, contatto.note);
    }

    /**
     * @brief Calcola il codice hash per l'oggetto corrente.
     *
     * Questo metodo sovrascrive il metodo `hashCode` per generare un codice
     * hash univoco basato su valori nome, cognome, numeri, emails e note.
     *
     * @return Un valore intero che rappresenta il codice hash dell'oggetto
     * corrente.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, surname, numbers, emails, note);
    }

}
