/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ari19
 */
public class RubricaPreferiti implements Rubrica {
    
    List<Contatto> contatti; 
    
    
    public RubricaPreferiti(){
        contatti= new LinkedList<>(); 
    }
    
    
    @Override
    public void aggiungiContatto(Contatto contatto) {// bisogna completare l'aggiunta di contatto se c'è un duplicato
        contatti.add(contatto);
    }

    @Override
    public boolean rimuoviContatto(String identificatore); {
        
    }

    @Override
    public boolean modificaContatto(String identificatore) {
        
    }

    @Override
    public List<Contatto> cercaContatto(String parametro) {

    }

    
    

    
    
}
