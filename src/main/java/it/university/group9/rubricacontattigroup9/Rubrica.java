/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.util.List;

/**
 *
 * @author ari19
 */
public interface Rubrica {
    public void aggiungiContatto(Contatto contatto);
    public boolean rimuoviContatto(String identificatore);
    public boolean modificaContatto(String identificatore, Contatto nuovoContatto); 
    public List<Contatto> cercaContatto(String parametro);   
    
}
