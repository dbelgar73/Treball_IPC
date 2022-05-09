/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.Problem;

/**
 * FXML Controller class
 *
 * @author david
 */
public class MenuController implements Initializable {

    @FXML
    private ListView<Problem> ListaProblems;

    private ObservableList<Problem> datos = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            Navegacion navegacio = Navegacion.getSingletonNavegacion();
            List<Problem> problemas = navegacio.getProblems();
            datos = FXCollections.observableList(problemas);
            ListaProblems.setItems(datos);
        } catch (NavegacionDAOException e) { e.printStackTrace(); }
        
    }    

    @FXML
    private void ModificarPerfil(ActionEvent event) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Registre.fxml"));
            Parent root = miCargador.load();
            RegistreController controlador = miCargador.getController();
            Scene scene = new Scene(root);
            Stage estageActual = new Stage();
            estageActual.setResizable(true);
            estageActual.setScene(scene);
            estageActual.initModality(Modality.APPLICATION_MODAL);
            estageActual.show();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void CerrarSesion(ActionEvent event) {
        ((Stage)ListaProblems.getScene().getWindow()).close();
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("IniciSesio.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Inicio Sesión");
            stage.setScene(scene);
            stage.show();
            } 
        catch (IOException e) {
                e.printStackTrace();
            }    
    }

    @FXML
    private void RealizarProblem(ActionEvent event) {
    }

    @FXML
    private void ProblemAleat(ActionEvent event) {
    }
   
    
}
