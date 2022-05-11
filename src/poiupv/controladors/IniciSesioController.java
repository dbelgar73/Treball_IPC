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
import javafx.beans.value.ObservableValue;
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
import model.User;
import static poiupv.controladors.RegistreController.usr;


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
    public static String nickName ;
    public static String password;
    public static Navegacion navegacio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuari.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            usr = usuari.getText();
            if(!User.checkNickName(usr)){//error en l'usuari
                MissatgeUsuari.setVisible(true);
            }
            else{
                MissatgeUsuari.setVisible(false);
 
            }
        });
    }    
    
    @FXML
    private void iniciarSesio(ActionEvent event) throws NavegacionDAOException {
        //variables
        nickName = usuari.getText();//usuari
        password = contrassenya.getText();//contrassenya
        navegacio = Navegacion.getSingletonNavegacion();//necesari per a cridar a metodes de la classe Navegacion
        //Autenticació de usuari
        if(navegacio.exitsNickName(nickName) == false){//si no existeix l'usuari mostra error
            MissatgeUsuari.setVisible(true);//mostra label visible-------------------
        }
        else{//si existix l'usuari
            if(navegacio.loginUser(nickName, password)!= null){//si esta tot correcte, usuari i contrassenya
                //canvia de finsetra
                ((Stage)usuari.getScene().getWindow()).close();
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/poiupv/vistes/Menu.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("Bandeja de entrada");
                    stage.setScene(scene);
                    stage.show();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }     
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
