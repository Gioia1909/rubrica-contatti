/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ari19
 */
public class Contatto {

    private String nome;
    private String cognome;
    private List<String> numeri;
    private List<String> email;
    private String note;

    public Contatto(String nome, String cognome, List<String> numeri, List<String> emails, String note) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeri = numeri;
        this.email = new ArrayList<>();
        this.note = new ArrayList<>();
    }

}
