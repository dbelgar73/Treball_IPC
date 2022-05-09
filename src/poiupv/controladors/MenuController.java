/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author david
 */
public class MenuController implements Initializable {

    @FXML
    private Button tancarSessio;
    @FXML
    private Button modificarPerfil;
    @FXML
    private Button problemes;
    @FXML
    private Button historic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void tancarSessio(MouseEvent event){
        //((Stage)usuari.getScene().getWindow()).close();
        try {
            Stage estageActual = new Stage();
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/IniciSesio.fxml"));
            Parent root = miCargador.load();
            Scene scene = new Scene(root);
            estageActual.setScene(scene);
            estageActual.initModality(Modality.APPLICATION_MODAL);
            estageActual.show();
            } 
        catch (IOException e) {e.printStackTrace();}
    }
    private void modificarPerfil(ActionEvent event){}
    private void problemes(ActionEvent event){}
    private void historic(ActionEvent event){}
    
}
