/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import DBAccess.NavegacionDAOException;
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
import model.Navegacion;


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
    @FXML
    private Button botoRegistre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void iniciarSessio(ActionEvent event) throws NavegacionDAOException {
        String nickName = usuari.getText();
        String password = contrassenya.getText();
        Navegacion navegacio = Navegacion.getSingletonNavegacion();
        if(navegacio.exitsNickName(nickName) == false){
            MissatgeUsuari.visibleProperty();
        }
        else{
            if(navegacio.loginUser(nickName, password)!= null){
                ((Stage)usuari.getScene().getWindow()).close();
                try {
                    //Falta editar per a autenticarse i eso
                    Stage estageActual = new Stage();
                    FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/Principal.fxml"));
                    Parent root = miCargador.load();
                    Scene scene = new Scene(root);
                    estageActual.setScene(scene);
                    estageActual.initModality(Modality.APPLICATION_MODAL);
                    estageActual.show();
                } 
                catch (IOException e) {e.printStackTrace();} 
            }
        }    
    }

    @FXML
    private void Registrarse(ActionEvent event) {
        
        
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
    
}
