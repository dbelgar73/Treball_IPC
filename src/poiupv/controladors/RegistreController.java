/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;

/**
 * FXML Controller class
 *
 * @author david
 */
public class RegistreController implements Initializable {

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
    @FXML
    private ImageView avatarDefault;
    @FXML
    private RadioButton avatarDefaultSel;
    @FXML
    private ImageView avatar1;
    @FXML
    private RadioButton avatar1Sel;
    @FXML
    private ImageView avatar2;
    @FXML
    private RadioButton avatar2Sel;
    @FXML
    private ImageView avatar3;
    @FXML
    private RadioButton avatar3Sel;
    @FXML
    private ImageView avatar4;
    @FXML
    private RadioButton avatar4Sel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void registrarse(ActionEvent event) throws NavegacionDAOException{
        String usr = usuari.getText();
        String email = correu.getText();
        String pswd = contrassenya.getText();
        //Avatar
        Image avatar = avatarDefault.getImage();
        Navegacion navegacio = Navegacion.getSingletonNavegacion();
        //COMPROBA LES DADES INTRODUIDES
        if(!User.checkNickName(usr)){//error en l'usuari
            errorUsuari.setVisible(true);
        }
        else{
            errorUsuari.setVisible(false);
        }//per a quan l'error siga altre que no seguixca
        if(!User.checkEmail(email)){//error en el correu
            errorCorreu.setVisible(true);
        }
        else{ 
            errorCorreu.setVisible(false);
        }
        if(!User.checkPassword(pswd)){//error en la contrassenya
            errorContrassenya.setVisible(true);
        }
        else{ 
            errorContrassenya.setVisible(false);
        }
        //Data de naixement
        int edat = 0;
        LocalDate birthDate = daypicker.getValue();
        if(birthDate != null){
            //Obte la data de ara
            int year, month, day = 0;
            LocalDate now = LocalDate.now();
            //calcula la diferencia (Data de hui - any de naixement) = edat
            int dataHui = now.getYear() + now.getDayOfYear();//calcula la data de hui
            int naixement = birthDate.getYear() + birthDate.getDayOfYear();
            edat = dataHui - naixement ;
            if(edat < 16){//es menor de 16 anys
                errorEdat.setVisible(true);//mostra error edat 
            }
            else{
                errorEdat.setVisible(false);//amaga el error de la edat
            }
        }
        if(User.checkNickName(usr) && User.checkEmail(email) && User.checkPassword(pswd)){
            //TOT CORRECTE, REGISTRA
            navegacio.registerUser(usr,email,pswd,avatar,birthDate);
            //VES A LA FINESTRA DE INICI DE SESIO I TANCA ESTA FINESTRA
            try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Menu.fxml"));
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
            //TANCA LA FINESTRA DEL REGISTRE
            Node n = (Node)event.getSource();
            n.getScene().getWindow().hide();
        }
        
        
            
    }
    
    @FXML
    private void cancelar(ActionEvent event){
        Node n = (Node)event.getSource();
        n.getScene().getWindow().hide();
    }
}
