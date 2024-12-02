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
    public Contatto rimuoviContatto();
    public void modificaContatto(); 
    public List<Contatto> cercaContatto(String parametro);    
    
    
    
    
}
