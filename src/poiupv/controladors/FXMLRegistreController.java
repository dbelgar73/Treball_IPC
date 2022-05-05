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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author david
 */
public class FXMLRegistreController implements Initializable {

    @FXML
    private TextField usuari;
    @FXML
    private Label errorUsuari;
    @FXML
    private TextField correu;
    @FXML
    private Label errorCorreu;
    @FXML
    private PasswordField contrassenya;
    @FXML
    private Label errorContrassenya;
    @FXML
    private DatePicker daypicker;
    @FXML
    private Label errorEdat;
    @FXML
    private Button botoCancelar;
    @FXML
    private Button botoRegistrarse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
