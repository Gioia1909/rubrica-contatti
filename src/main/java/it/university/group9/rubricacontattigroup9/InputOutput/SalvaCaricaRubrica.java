/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.InputOutput;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author imacpro
 */
public class SalvaCaricaRubrica implements Serializable{
    //file che si creerà 
    private static final String file = "rubrica.csv"; //csv per implementarla come una tabella
    
    //public ObservableList <Contatto> rubrica = FXCollections.observableArrayList(); //creazione di una lkista da caricare su file 
    
    
    public static void salvaRubrica(ObservableList<Contatto> rubrica) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(rubrica);  // Serializza l'oggetto rubrica nel file
            System.out.println("Rubrica serializzata con successo.");
        } catch (IOException e) {
            e.printStackTrace();  // Gestisce eventuali errori di IO
        }
    }
    
    public static ObservableList<Contatto> caricaRubrica() throws ClassNotFoundException{
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            ObservableList <Contatto> rub = (ObservableList <Contatto>) ois.readObject();
            return rub;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null; // Restituisce null se il file non esiste o se si verifica un errore
    }
    
    
    
    
}
