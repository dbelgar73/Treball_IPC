/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import DBAccess.NavegacionDAOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.User;
import poiupv.Poi;

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
        contrasenyaUser.setText(Poi.userActual.getPassword());
        CorreuUser.setText(Poi.userActual.getEmail());
        dataUser.setValue(Poi.userActual.getBirthdate());
    }    

    @FXML
    private void guardarCambios(ActionEvent event) throws NavegacionDAOException {
        //noves dades
        String newPass = contrasenyaUser.getText();
        String newEmail = CorreuUser.getText(); 
        LocalDate newBirth = dataUser.getValue();
        LocalDate now = LocalDate.now();
        //calcula la diferencia (Data de hui - any de naixement) = edat
        int dataHui = now.getYear() + now.getDayOfYear();//calcula la data de hui
        int naixement = newBirth.getYear() + newBirth.getDayOfYear();
        int edat = dataHui - naixement ;
        //se comroben que siguen correctes
        if(User.checkPassword(newPass) && User.checkEmail(newEmail) && edat > 16){
            //canviem les dades que hi havien per les noves
            Poi.userActual.setPassword(newPass);
            Poi.userActual.setEmail(newEmail);
            Poi.userActual.setBirthdate(newBirth);
        }
        Node n = (Node)event.getSource();
        n.getScene().getWindow().hide();
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
}
