/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;
import poiupv.Poi;
import static poiupv.controladors.RegistreController.usr;


/**
 * FXML Controller class
 *
 * @author david
 */
public class IniciSesioController implements Initializable {

    @FXML
    private TextField usuari;
    @FXML
    private Button iniciarSessio;
    @FXML
    private PasswordField contrassenya;
    @FXML
    private Button botoRegistre;
    @FXML
    private Label MissatgeUsuari;
    public static String nickName ;
    public static String password;
    @FXML
    private Label MissatgeContrasenya;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Poi.hits = 0;
        Poi.faults = 0;
        usuari.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            usr = usuari.getText();
            if(!"".equals(usr) && !User.checkNickName(usr)){//error en l'usuari
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
        Poi.navegacio = Navegacion.getSingletonNavegacion();//necesari per a cridar a metodes de la classe Navegacion
        //Autenticació de usuari
        if(Poi.navegacio.exitsNickName(nickName) == false){//si no existeix l'usuari mostra error
            MissatgeUsuari.setVisible(true);//mostra label visible-------------------
             Alert alert = new Alert(AlertType.ERROR); 
             alert.setTitle("Error"); 
             alert.setHeaderText("Usuari i/o contrassenya no trobats");
             alert.setContentText("Torna-ho a intentar"); 
             alert.showAndWait();
        }
        else{//si existix l'usuari
            if(Poi.navegacio.loginUser(nickName, password)!= null){//si esta tot correcte, usuari i contrassenya
                //canvia de finsetra
                Alert alert = new Alert(AlertType.INFORMATION); 
                alert.setTitle("Iniciant sessió"); 
                alert.setHeaderText("Usuari i contrassenya correctes");
                alert.setContentText("Benvingut: " + nickName); 
                alert.showAndWait();
                try {
                    Poi.setUserActual(Poi.navegacio.getUser(nickName)); //guarda el usuari que ha iniciat sesio en userActual
                    Poi.TimeInici = LocalDateTime.now();
                    Node n = (Node)event.getSource();
                    n.getScene().getWindow().hide();
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
            Scene scene = new Scene(root);
            Stage estageActual = new Stage();
            estageActual.setResizable(true);
            estageActual.setScene(scene);
            estageActual.setTitle("Formulari de registre");
            estageActual.initModality(Modality.APPLICATION_MODAL);
            estageActual.show();
            
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}