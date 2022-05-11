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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    @FXML
    private Label errorJaRegistrat;
    public static Image avatar;
    public  static String usr;
    public static String email;
    public static String pswd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        usuari.focusedProperty().addListener((observable, oldValue, newValue)->{ 
      
            if(!User.checkNickName(usr)){//error en l'usuari
                errorUsuari.setVisible(true);
            }
            else{
                errorUsuari.setVisible(false);
                
                
                
            }
        });
        correu.focusedProperty().addListener((observable, oldValue, newValue)->{ 
            
            email = correu.getText();
            errorCorreu.setVisible(false);
            
            if(!User.checkEmail(email)){//error en el correu
                errorCorreu.setVisible(true);
                contrassenya.setDisable(true);
            }
            else{ 
                errorCorreu.setVisible(false);
                contrassenya.setDisable(false);
            }
        });
//LISTENERS PER A LA SELECCIO DE PERFIL
        avatarDefaultSel.setSelected(true);
        avatar = avatarDefault.getImage();
        avatar1Sel.setDisable(true);
        avatar2Sel.setDisable(true);
        avatar3Sel.setDisable(true);
        avatar4Sel.setDisable(true);
        avatarDefaultSel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (!isNowSelected) { 
                    avatar1Sel.setDisable(false);
                    avatar2Sel.setDisable(false);
                    avatar3Sel.setDisable(false);
                    avatar4Sel.setDisable(false);
                }
                else{
                    avatar1Sel.setDisable(true);
                    avatar2Sel.setDisable(true);
                    avatar3Sel.setDisable(true);
                    avatar4Sel.setDisable(true);
                }
        }
        });
        avatar1Sel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if(!isNowSelected){//avatar 1 seleccionat
                    avatar = avatar1.getImage();
                    avatarDefaultSel.setDisable(false);
                    avatar2Sel.setDisable(false);
                    avatar3Sel.setDisable(false);
                    avatar4Sel.setDisable(false);
                }
                else{
                    avatarDefaultSel.setDisable(true);
                    avatar2Sel.setDisable(true);
                    avatar3Sel.setDisable(true);
                    avatar4Sel.setDisable(true);
                }
            }
        });
        avatar2Sel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if(!isNowSelected){//avatar 2 seleccionat
                    avatar = avatar2.getImage();
                    avatarDefaultSel.setDisable(false);
                    avatar1Sel.setDisable(false);
                    avatar3Sel.setDisable(false);
                    avatar4Sel.setDisable(false);
                }
                else{
                    avatarDefaultSel.setDisable(true);
                    avatar1Sel.setDisable(true);
                    avatar3Sel.setDisable(true);
                    avatar4Sel.setDisable(true);
                }
            }
        });
        avatar3Sel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if(!isNowSelected){//avatar 3 seleccionat
                    avatar = avatar3.getImage();
                    avatarDefaultSel.setDisable(false);
                    avatar1Sel.setDisable(false);
                    avatar2Sel.setDisable(false);
                    avatar4Sel.setDisable(false);
                }
                else{
                    avatarDefaultSel.setDisable(true);
                    avatar2Sel.setDisable(true);
                    avatar1Sel.setDisable(true);
                    avatar4Sel.setDisable(true);
                }
            }
        });
        avatar4Sel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if(!isNowSelected){//avatar 4 seleccionat
                    avatar = avatar4.getImage();
                    avatarDefaultSel.setDisable(false);
                    avatar1Sel.setDisable(false);
                    avatar2Sel.setDisable(false);
                    avatar3Sel.setDisable(false);
                }
                else{
                    avatarDefaultSel.setDisable(true);
                    avatar2Sel.setDisable(true);
                    avatar3Sel.setDisable(true);
                    avatar1Sel.setDisable(true);
                }
            }
        });
        
        
        
    }   
    
    @FXML
    private void registrarse(ActionEvent event) throws NavegacionDAOException{
        Navegacion navegacio = Navegacion.getSingletonNavegacion();
        usr = usuari.getText();
        email = correu.getText();
        pswd = contrassenya.getText();
        
        //SELECCIÓ D'Avatar
//        Image avatar = avatarDefault.getImage();
        
        
        //COMPROBA LES DADES INTRODUIDES
        //per a quan l'error siga altre que no seguixca
        
        if(!User.checkPassword(pswd)){//error en la contrassenya
            errorContrassenya.setVisible(true);
        }
        else{ 
            errorContrassenya.setVisible(false);
        }
        
         //DATA DE NAIXEMENT
        LocalDate birthDate = this.daypicker.getValue();//obte el valor seleccionat en el datePicker 
        //System.out.println("RESULTAT birthDate" +"\n"+ birthDate);//Visualitza resultat intermig
        String aux = errorEdat.getText();
        if(birthDate == null){
            errorEdat.setText("Error, data no introduida. S'ha de polsar intro per a comfirmar-la");
            errorEdat.setVisible(true);
        }
        else{
            errorEdat.setVisible(false);
            errorEdat.setText(aux);
            //Obte la data de ara
            int year, month, day = 0;
            LocalDate now = LocalDate.now();
            //calcula la diferencia (Data de hui - any de naixement) = edat
            int dataHui = now.getYear() + now.getDayOfYear();//calcula la data de hui
            int naixement = birthDate.getYear() + birthDate.getDayOfYear();
            
            int edat = dataHui - naixement ;
            if(edat < 16){//es menor de 16 anys
                errorEdat.setVisible(true);//mostra error edat 
            }
            else{
                errorEdat.setVisible(false);//amaga el error de la edat
            }
        }
        
        if(User.checkNickName(usr) && User.checkEmail(email) && User.checkPassword(pswd)){
            //TOT CORRECTE, REGISTRA
            try{navegacio.registerUser(usr,email,pswd,avatar,birthDate);}
            catch(NavegacionDAOException e){
                errorJaRegistrat.setVisible(true);
            }
            //VES A LA FINESTRA DE INICI DE SESIO I TANCA ESTA FINESTRA
            try {
                FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/IniciSesio.fxml"));
                Parent root = miCargador.load();
                Scene scene = new Scene(root);
                Stage estageActual = new Stage();
                estageActual.setResizable(true);
                estageActual.setScene(scene);
                estageActual.initModality(Modality.APPLICATION_MODAL);
                estageActual.show();
                //TANCA LA FINESTRA DEL REGISTRE
                Node n = (Node)event.getSource();
                n.getScene().getWindow().hide();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }         
    }
    
    @FXML
    private void cancelar(ActionEvent event){
        Node n = (Node)event.getSource();
        n.getScene().getWindow().hide();
    }
}
