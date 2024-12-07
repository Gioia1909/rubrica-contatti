/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.university.group9.rubricacontattigroup9.InputOutput;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.university.group9.rubricacontattigroup9.Contatto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @brief Classe per la gestione del salvataggio e caricamento della rubrica in formato JSON.
 * 
 * Questa classe fornisce i metodi per salvare una lista di contatti in un file JSON 
 * e per caricarla da un file JSON esistente. 
 *
 * @author Gruppo09
 * @date 07/12/2024
 */
public class SalvaCaricaRubrica implements Serializable{
    //file che si creerà 
    private static final String file = "rubrica.json"; 
    
     /**
     * @brief Salva la rubrica su un file JSON.
     * 
     * Questo metodo converte la ObservableListi n una lista di tipo List.
     * e la serializza in un file JSON utilizzando la libreria Jackson.
     * Se il salvataggio ha successo, verrà stampato un messaggio di conferma, altrimenti verrà gestito l'errore.
     * 
     * @param[in] rubrica La lista di contatti da salvare nel file JSON.
     * 
     * @pre rubrica non deve essere null.
     * @post Il file JSON rubrica.json viene creato o sovrascritto con i dati contenuti in rubrica
     * @throws IOException Se si verifica un errore durante il salvataggio del file.
     */
    
    public static void salvaRubrica(ObservableList<Contatto> rubrica) { //ObservableList<Contatto> rubrica Lista dei contatti da 
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
     * @brief Carica la rubrica da un file JSON se esiste.
     * 
     * Questo metodo carica i dati da un file JSON e li converte in una ObservableList
     * Se il file non esiste, viene restituita una lista vuota e viene stampato un messaggio di errore.
     * 
     * @return Una ObservableList che contenente i contatti caricati oppure una lista vuota 
     * se il file non esiste o si verifica un errore durante il caricamento.
     * 
     * @pre Il file rubrica.json deve essere presente nel percorso specificato per il corretto funzionamento.
     * @post Se il file esiste, i contatti vengono caricati nella rubrica, altrimenti viene restituita una rubrica vuota.
     * @throws IOException Se si verifica un errore durante il caricamento del file.
     */
    public static ObservableList<Contatto> caricaRubrica(){
        ObjectMapper mapper = new ObjectMapper();
        
        File filepath = new File(file); //crea un oggetto file che punta al percorso con rubricapreferiti.json
        if (!filepath.exists()) {
            System.out.println("File " + file + " non trovato. Creazione di una rubrica vuota.");
            return FXCollections.observableArrayList();
        }
        
        try{
            List <Contatto> deserializedList = mapper.readValue(filepath, new TypeReference<List<Contatto>>(){}); 
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
