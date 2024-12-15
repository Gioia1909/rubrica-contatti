/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.university.group9.rubricacontattigroup9;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 *
 * @author Gruppo09
 */
public interface GestioneRubricaController {
    public void deleteAction(ActionEvent event);

    public void editAction(ActionEvent event) throws IOException;

    public void searchAction(ActionEvent event);

    public void showErrorDialog(String titolo, String messaggio); 

}
