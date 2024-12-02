/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ari19
 */
public class Contatto {

    private String nome;
    private String cognome;
    private List<String> numeri;
    private List<String> emails;
    private String note;

    public Contatto(String nome, String cognome, List<String> numeri, List<String> emails, String note) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeri = new LinkedList<>();
        this.emails = new LinkedList<>();
        this.note = note;
    }
    
    
    public String getNome(){
        return nome;
    }
    
    public String getCognome(){
        return cognome;
    }
    
    public List<String> getNumeri(){
        return numeri;
    }
    
    public List<String> getEmails(){
        return emails;
    }
    
    public String getNote(){
        return note;
    }
    
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