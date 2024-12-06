/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.InputOutput;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.io.*;
import javafx.collections.ObservableList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import javafx.collections.FXCollections;

/**
 *
 * @author imacpro
 */
public class SalvaCaricaPreferiti implements Serializable {
    //file che si creerà 
    private static final String file = "rubricapreferiti.json"; //nome del file
    
    /**
     * @brief Salva la rubrica su file JSON.
     *
     * @param rubrica La lista di contatti da salvare
     */
    public static void salvaRubricaPreferiti(ObservableList<Contatto> rubrica) { //ObservableList<Contatto> rubrica Lista dei contatti da 
        //ObjectMapper trasforma gli oggetti in file JSON (serializzazione)
        ObjectMapper mapper = new ObjectMapper(); //https://javadoc.io/doc/com.fasterxml.jackson.core/jackson-databind/2.9.8/com/fasterxml/jackson/databind/ObjectMapper.html
        
        try{
            //Jackson non può serializzare un'osservable, quindi deve diventare una stringa
            List <Contatto> serializableList = new ArrayList<>(rubrica);  //crea una nuova lista con gli elementi di rubrica
            mapper.writeValue(new File(file), serializableList); //accetta il file e l'oggetto da scrivere sopra
            System.out.println("Rubrica salvata correttamente in " + file);

        }catch(IOException e){
            System.err.println("Errore durante il salvataggio della rubrica: " + e.getMessage());
               e.printStackTrace();
        }
    }
    /**
     * Carica la rubrica da un file JSON, se esiste.
     *
     * @return Una ObservableList contenente i contatti caricati, o una lista vuota se il file non esiste.
     */
    
    public static ObservableList<Contatto> caricaRubricaPreferiti() throws ClassNotFoundException{
        ObjectMapper mapper = new ObjectMapper();
        
        File filepath = new File(file); //crea un oggetto file che punta al percorso con rubricapreferiti.json
        if (!filepath.exists()) {
            System.out.println("File " + file + " non trovato. Creazione di una rubrica vuota.");
            return FXCollections.observableArrayList();
        }
        
        try{
            List <Contatto> deserializedList = mapper.readValue(file, new TypeReference<List<Contatto>>(){}); 
            //TypeReference è un'interfaccia particolare di Jackson che permette di restituire il tipo dell'oggetto voluto 
            //{} perché la rendiamo anonima, dato che è un'istanza provvisoria 
            //se non fosse anonima dovremmo implementare i metodi astratti
            return FXCollections.observableArrayList(deserializedList); //conversione in un'Osservabile
        }catch(IOException e){
            System.err.println("Errore durante il caricamento dalla rubrica: " + e.getMessage());
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }    
    }



}