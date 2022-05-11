/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import static poiupv.controladors.MenuController.userActual;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ModificarPerfilController implements Initializable {

    @FXML
    private TextField contrasenyaUser;
    @FXML
    private TextField CorreuUser;
    @FXML
    private DatePicker dataUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        contrasenyaUser.setText(MenuController.userActual.getPassword());
        CorreuUser.setText(MenuController.userActual.getEmail());
        dataUser.setValue(MenuController.userActual.getBirthdate());
    }    

    @FXML
    private void guardarCambios(ActionEvent event) {
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
}
