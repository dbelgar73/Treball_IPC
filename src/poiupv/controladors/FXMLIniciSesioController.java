/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author david
 */
public class FXMLIniciSesioController implements Initializable {

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
    
}
