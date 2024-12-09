/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.*;


/**
 * @file Contatto.java
 * @brief Classe che rappresenta un contatto con un nome, un cognome, da uno a tre numeri di telefono, da una a tre email e delle note.
 * 
 * Questa classe è utilizzata per gestire i dati di un contatto in una rubrica.
 * Ogni contatto ha un nome, un cognome, una lista di numeri di telefono, una lista di email e delle note.
 * 
 * @author Gruppo09
 * @date 03/12/2024
 * 
 */
public class Contatto implements Comparable<Contatto> {
    //JsonProperty serve a definire il campo che conterrà questi elementi sul file JSON
    @JsonProperty ("nome")
    private String nome;
    @JsonProperty ("cognome")
    private String cognome;
    @JsonProperty ("numeri")
    private List<String> numeri;
    @JsonProperty ("emails")
    private List<String> emails;
    @JsonProperty ("note")
    private String note;
    
/**
     * @brief Costruttore della classe Contatto.
     * 
     * Crea un'istanza della classe Contatto con i parametri forniti.
     * 
     * 
     * @param[in] nome Il nome del contatto.
     * @param[in] cognome Il cognome del contatto.
     * @param[in] numeri Lista di numeri di telefono associati al contatto.
     * @param[in] emails Lista di email associate al contatto.
     * @param[in] note Note aggiuntive relative al contatto.
     */
    public Contatto(String nome, String cognome, List<String> numeri, List<String> emails, String note) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeri = numeri;
        this.emails = emails;
        this.note = note;
    }
    
     /**
     * @brief Costruttore predefinito della classe Contatto.
     * 
     * Inizializza il contatto con liste vuote per numeri e email.
     */
    
    public Contatto() {
        this.numeri = new LinkedList<>();
        this.emails = new LinkedList<>();
    }
    
    /**
     * @brief Metodo che restituisce il nome del contatto.
     *
     * @return Nome del contatto.
     */
    public String getNome(){
        return nome;
    }
    
    
    /**
     * @brief Metodo che restituisce il cognome del contatto.
     * 
     * @return Cognome del contatto.
     */
    public String getCognome(){
        return cognome;
    }
    
      /**
     * @brief Metodo che resituisce la lista dei numeri di telefono del contatto.
     * 
     * @return Una lista contenente i numeri di telefono del contatto.
     */
    public List<String> getNumeri(){
        return numeri;
    }

 /**
     * @brief Metodo che restituisce la lista delle email del contatto.
     * 
     * @return Una lista contenente le email del contatto.
     */
    
    public List<String> getEmails(){
        return emails;
    }
    
    
    /**
     * @brief Metodo che restituisce le note del contatto.
     * 
     * @return Le note associate al contatto.
     */
    public String getNote(){
        return note;
    }
    
    
    /**
     * @brief Restituisce una rappresentazione testuale dei dati del contatto 
     * 
     * @return Una stringa contenente i dati del contatto: nome, cognome, numeri di telefono, email e note.
     */
    @Override
    public String toString(){
        StringBuffer sb= new StringBuffer();
        sb.append(nome);
        sb.append(" ");
        sb.append(cognome);
        for(String numero : numeri){
            sb.append(numero);
            sb.append(" ");
        }
        for(String email : emails){
            sb.append(email);
            sb.append(" ");
                   
        }
        
        sb.append(note);
        return sb.toString();
    }
    
    
    
    /**
 * @brief Confronta due oggetti di tipo Contatto per determinarne l'ordine naturale.
 * 
 * Questo metodo ordina i contatti in base al cognome in ordine alfabetico. 
 * Se i cognomi sono identici, l'ordinamento prosegue in base al nome, sempre 
 * in ordine alfabetico. Il confronto è case-insensitive.
 * 
 * @param[in] obj Il contatto da confrontare con l'oggetto corrente.
 * @return Un intero che rappresenta il risultato del confronto:
 *         - Un valore negativo se l'oggetto corrente precede il contatto `obj`.
 *         - Zero se i due contatti sono considerati equivalenti.
 *         - Un valore positivo se l'oggetto corrente segue il contatto `obj`.
 * 
 *   @pre L'oggetto `obj` non deve essere null.
 *   
    */
     @Override
    public int compareTo(Contatto obj) {
        int risultato = this.cognome.compareToIgnoreCase(obj.cognome);
        if (risultato == 0) {       // se hanno lo stesso cognome
            return this.nome.compareToIgnoreCase(obj.nome); // Se i cognomi sono uguali, ordina per nome
        }
        return risultato;
    }
    
    /**
 * @brief Confronta l'oggetto corrente con un altro oggetto per verificarne l'uguaglianza.
 * 
 * Questo metodo verifica se l'oggetto passato come parametro è uguale all'oggetto corrente.
 * Quindi due oggetti di tipo Contatto sono considerati uguali se hanno lo stesso riferimento, oppure se appartengno alla stessa classe e i loro attributi sono uguali.
 *
 * 
 * @param[in] o Oggetto da confrontare con l'oggetto corrente.
 * @return true se gli oggetti sono uguali, altrimenti false.
 * 
 * 
 */
    @Override
    
      /**
     * @brief Metodo che determina l'uguaglianza tra due oggetti Contatto.
     * 
     * Due contatti sono considerati uguali se nome, cognome, numeri, email e note coincidono.
     * 
     * @param o L'oggetto da confrontare.
     * @return True se gli oggetti sono uguali, false se sono diversi.
     */
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contatto contatto = (Contatto) o;
    return Objects.equals(nome, contatto.nome) &&
           Objects.equals(cognome, contatto.cognome) &&
           Objects.equals(numeri, contatto.numeri) &&
           Objects.equals(emails, contatto.emails) &&
           Objects.equals(note, contatto.note);
}


   /**
     * @brief Calcola l'hash code dell'oggetto Contatto.
     * 
     * @return L'hash code basato su nome, cognome, numeri, email e note.
     */
@Override
public int hashCode() {
    return Objects.hash(nome, cognome, numeri, emails, note);
}

    
    
}
