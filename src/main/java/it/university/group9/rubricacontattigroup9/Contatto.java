/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.util.LinkedList;
import java.util.List;

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
public class Contatto {

    private String nome;
    private String cognome;
    private List<String> numeri;
    private List<String> emails;
    private String note;
    
/**
     * @brief Costruttore della classe Contatto.
     * 
     * Crea un'istanza della classe Contatto con i parametri forniti.
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
        this.numeri = new LinkedList<>();
        this.emails = new LinkedList<>();
        this.note = note;
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
     * @brief Restituisce i dati del contatto tramite
     * 
     * @return Dati del contatto: nome, cognome, numeri di telefono, email e note.
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
    
    

}