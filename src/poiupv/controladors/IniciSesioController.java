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
    private void iniciarSesio(ActionEvent event) throws NavegacionDAOException {
        //variables
        String nickName = usuari.getText();//usuari
        String password = contrassenya.getText();//contrassenya
        Navegacion navegacio = Navegacion.getSingletonNavegacion();//necesari per a cridar a metodes de la classe Navegacion
        //Autenticació de usuari
        if(navegacio.exitsNickName(nickName) == false){//si no existeix l'usuari mostra error
            MissatgeUsuari.setVisible(true);//mostra label visible-------------------
        }
        else{//si existix l'usuari
            if(navegacio.loginUser(nickName, password)!= null){//si esta tot correcte, usuari i contrassenya
                //canvia de finsetra
                ((Stage)usuari.getScene().getWindow()).close();
                try {
                    Stage estageActual = new Stage();
                    FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Menu.fxml"));
                    Parent root = miCargador.load();
                    Scene scene = new Scene(root);
                    estageActual.setScene(scene);
                    estageActual.initModality(Modality.APPLICATION_MODAL);
                    estageActual.show();
                } 
                catch (IOException e) {e.printStackTrace();} 
            }
            else{
                //mostra l'error de inici de sessió
                MissatgeUsuari.setVisible(true);//mostra label visible-------------------
                MissatgeContrasenya.setVisible(true);//mostra label visible-------------------
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
