/**
 * @file GestioneRubricaControler Interfaccia per la gestione delle azioni relative alla rubrica contatti.
 * Definisce i metodi principali per l'eliminazione, modifica, ricerca e gestione degli errori nella rubrica.
 *
 * @author Gruppo09
 * 
 * @see InterfacciaUtenteController
 */
package it.university.group9.rubricacontattigroup9;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public interface GestioneRubricaController {

    /**
     * @brief Gestisce l'azione di eliminazione di un contatto dalla rubrica.
     *
     * Se un contatto è selezionato nella ListView, questo metodo lo rimuove
     * dalla rubrica, ripristina le etichette dei dettagli e aggiorna la lista
     * dei contatti visualizzati.
     *
     * @post Il contatto selezionato è stato rimosso dalla lista dei contatti e
     * la lista aggiornata è stata salvata nel file.
     *
     * @param[in] event Evento del mouse che ha attivato l'azione, il click sul
     * bottone
     *
     */
    public void deleteAction(ActionEvent event);

    /**
     *
     * @brief Gestisce il passaggio alla schermata di modifica di un contatto
     * selezionato.
     *
     *
     * @throws IOException Se si verifica un errore durante il caricamento della
     * scena FXML.
     *
     * @post La finestra di modifica viene visualizzata con il contatto
     * selezionato caricato nel relativo controller.
     *
     * @see InterfacciaAggiungiModificaController
     */
    public void editAction(ActionEvent event) throws IOException;

    /**
     * @brief Gestisce l'azione di ricerca dei contatti nella rubrica.
     *
     * Se la barra di ricerca è vuota, viene restituita la lista completa dei
     * contatti, altrimenti chiama la searchContact Se il testo di ricerca non
     * corrisponde a nessun contatto, viene mostrato un messaggio di errore.
     *
     * @pre La `contactListView` deve essere inizializzata e contenere la lista
     * dei contatti.
     *
     * @post Se il testo di ricerca è vuoto, viene mostrata la lista completa;
     * altrimenti, viene mostrata la lista filtrata in base al termine di
     * ricerca. Se il testo di ricerca non corrisponde a nessun contatto, viene
     * mostrato un messaggio di errore.
     *
     * @param[in] event Evento associato al bottone di ricerca, il click
     */
    public void searchAction(ActionEvent event);

    /**
     * @brief Mostra una finestra di dialogo con un messaggio di errore.
     *
     * @param title Il titolo della finestra di dialogo.
     * @param message Il messaggio da visualizzare nella finestra di dialogo.
     *
     */
    public void showErrorDialog(String titolo, String messaggio);

}
