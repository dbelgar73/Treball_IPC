/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author david
 */
public class IniciSesioController implements Initializable {

    @FXML
    private Label MissatgeUsuari;
    @FXML
    private Label MissatgeContrasenya;
    @FXML
    private TextField usuari;
    @FXML
    private Button iniciarSessio;
    @FXML
    private PasswordField contrassenya;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void iniciarSesio(ActionEvent event) {
       ((Stage)usuari.getScene().getWindow()).close();
        try {
            Stage estageActual = new Stage();
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("IniciSesio.fxml"));
            Parent root = miCargador.load();
            Scene scene = new Scene(root);
            estageActual.setScene(scene);
            estageActual.initModality(Modality.APPLICATION_MODAL);
            estageActual.show();
        } 
        catch (IOException e) {e.printStackTrace();}
        
        
    }

    @FXML
    private void Registrarse(ActionEvent event) {
        
        Stage estageActual = new Stage();
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("Registre.fxml"));
            Parent root = miCargador.load();
            Scene scene = new Scene(root);
            estageActual.setScene(scene);
            estageActual.initModality(Modality.APPLICATION_MODAL);
            estageActual.show();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
