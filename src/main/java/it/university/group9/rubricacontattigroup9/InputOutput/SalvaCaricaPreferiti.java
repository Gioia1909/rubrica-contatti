/**
 * @file SalvaCaricaPreferiti.java
 * @brief Classe per la gestione del salvataggio e caricamento della rubrica preferiti.
 * 
 * Questa classe fornisce i metodi per salvare una lista di contatti (rubrica) in un file JSON 
 * e per caricarla da un file JSON esistente.
 * 
 * @author Gruppo09
 * @date 11/12/2024
 * @see Contatto
 */
package it.university.group9.rubricacontattigroup9.InputOutput;

import it.university.group9.rubricacontattigroup9.Contatto;
import java.io.*;
import javafx.collections.ObservableList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import javafx.collections.FXCollections;

public class SalvaCaricaPreferiti implements Serializable {
    
    private static final String file = "rubricapreferiti.json"; //nome del file che si creerà
    
    private static final String fileCSV = "rubricapreferiti.CSV"; //nome del file che si creerà
    
    /**
     * @brief Salva la rubrica su file JSON.
     *
     * Questo metodo salva la rubrica fornita come parametro in un file JSON utilizzando la libreria Jackson.
     * La lista di contatti viene convertita in un formato serializzabile e scritta nel file specificato.
     * 
     * @param[in] addressBook La lista osservabile di contatti da salvare nel file JSON.
     * 
     * @pre addressBook non deve essere null.
     * @post Il file JSON viene creato o sovrascritto con i dati contenuti in addressBook.
     * @throws IOException Se si verifica un errore durante il processo di salvataggio.
     * 
     * In caso di errore durante la scrittura del file, viene stampato un messaggio di errore
     * e viene tracciata l'eccezione con uno stack trace.
     * 
     * @see ObjectMapper
     */
    public static void saveFavoritesAddressBook(ObservableList<Contatto> addressBook) { //ObservableList<Contatto> rubrica Lista dei contatti da 
        //ObjectMapper trasforma gli oggetti in file JSON (serializzazione)
        ObjectMapper mapper = new ObjectMapper(); //https://javadoc.io/doc/com.fasterxml.jackson.core/jackson-databind/2.9.8/com/fasterxml/jackson/databind/ObjectMapper.html
        
        try{
            //Jackson non può serializzare un'osservable, quindi deve diventare una stringa
            List <Contatto> serializableList = new ArrayList<>(addressBook);  //crea una nuova lista con gli elementi di rubrica
            mapper.writeValue(new File(file), serializableList); //accetta il file e l'oggetto da scrivere sopra
            System.out.println("Rubrica salvata correttamente in " + file);

        }catch(IOException e){
            System.err.println("Errore durante il salvataggio della rubrica: " + e.getMessage());
               e.printStackTrace();
        }
    }
    
     /**
     * @brief Carica la rubrica da un file JSON, se esiste.
     * 
     * @pre Il file rubricapreferiti.json deve essere presente nel percorso specificato per il corretto funzionamento.
     * @post Se il file esiste, i contatti vengono caricati nella rubrica, altrimenti viene restituita una rubrica vuota.
     *
     *  @return Una ObservableList che contenente i contatti caricati, oppure una lista vuota se il file non esiste 
     *  o se si dovesser verificare un errore durante il caricamento.
     * 
     *  @throws IOException Se si verifica un errore durante il caricamento del file.
     */
    public static ObservableList<Contatto> loadFavoritesAddressBook(){
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
            return FXCollections.observableArrayList();
        }    
    }
    
    public static void exportToCSV(ObservableList<Contatto> addressBook) throws IOException{
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileCSV)))){
            // Intestazione del CSV
            pw.println("NOME;COGNOME;TELEFONO;EMAIL");
            
            for(Contatto contatto : addressBook){
                String numeri = String.join("\n", contatto.getNumbers());
                String email = String.join("\n", contatto.getEmails());
                
                pw.println(contatto.getName() + ";" + contatto.getSurname() + ";" + numeri + ";" + email);
            }
            
        }
        System.out.println("Rubrica esportata correttamente in " + fileCSV);
    }
    
    
}
