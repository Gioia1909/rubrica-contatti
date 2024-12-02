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
public class RubricaContatti implements Rubrica {

    private List<Contatto> contatti;

    public RubricaContatti(List<Contatto> contatti) {
        this.contatti = new LinkedList<>();
    }

    @Override
    public void aggiungiContatto(Contatto contatto) {
        contatti.add(contatto);
    }

}
