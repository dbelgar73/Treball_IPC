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
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import static poiupv.controladors.IniciSesioController.nickName;

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
    public static LocalDate birthDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //LISTENER CAMP USUARI
        usuari.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            usr = usuari.getText();
            if(!User.checkNickName(usr)){//error en l'usuari
                errorUsuari.setVisible(true);
                botoRegistrarse.setDisable(true);
            }
            else{
                errorUsuari.setVisible(false);
                botoRegistrarse.setDisable(false);
            }
        });
        //LISTENER CAMP CORREU
        correu.focusedProperty().addListener((observable, oldValue, newValue)->{ 
            
            email = correu.getText();
            errorCorreu.setVisible(false);
            
            if(!User.checkEmail(email)){//error en el correu
                errorCorreu.setVisible(true);
                botoRegistrarse.setDisable(true);
                
            }
            else{ 
                errorCorreu.setVisible(false);
                botoRegistrarse.setDisable(false);
                
            }
        });
        //LISTENER CAMP CONTRASSENYA
        contrassenya.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            pswd = contrassenya.getText();
            errorContrassenya.setVisible(false);
            if(!User.checkPassword(pswd)){//error en la contrassenya
                errorContrassenya.setVisible(true);
                botoRegistrarse.setDisable(true);
            }
            else{
                errorContrassenya.setVisible(false);
                botoRegistrarse.setDisable(false);
            }
        });
        //LISTENER CAMP ANIVERSARI
        daypicker.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        String aux = errorEdat.getText();
        birthDate = this.daypicker.getValue();
        if(birthDate == null){
            errorEdat.setText("Error, data no introduida. S'ha de polsar intro per a comfirmar-la");
            errorEdat.setVisible(true);
            botoRegistrarse.setDisable(true);
        }
        else{
            errorEdat.setVisible(false);
            errorEdat.setText(aux);
            botoRegistrarse.setDisable(false);
            //Obte la data de ara
            LocalDate now = LocalDate.now();
            //calcula la diferencia (Data de hui - any de naixement) = edat
            int dataHui = now.getYear() + now.getDayOfYear();//calcula la data de hui
            int naixement = birthDate.getYear() + birthDate.getDayOfYear();
            
            int edat = dataHui - naixement ;
            if(edat < 16){//es menor de 16 anys
                errorEdat.setVisible(true);//mostra error edat 
                botoRegistrarse.setDisable(true);
            }
            else{
                errorEdat.setVisible(false);//amaga el error de la edat
            }
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
                if(!avatar4Sel.isSelected() && !avatarDefaultSel.isSelected() && !avatar1Sel.isSelected() && !avatar2Sel.isSelected() && !avatar3Sel.isSelected()){
                    botoRegistrarse.setDisable(true);
                }
                else{
                    botoRegistrarse.setDisable(false);
                }
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
                if(!avatar4Sel.isSelected() && !avatarDefaultSel.isSelected() && !avatar1Sel.isSelected() && !avatar2Sel.isSelected() && !avatar3Sel.isSelected()){
                    botoRegistrarse.setDisable(true);
                }
                else{
                    botoRegistrarse.setDisable(false);
                }
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
                if(!avatar4Sel.isSelected() && !avatarDefaultSel.isSelected() && !avatar1Sel.isSelected() && !avatar2Sel.isSelected() && !avatar3Sel.isSelected()){
                    botoRegistrarse.setDisable(true);
                }
                else{
                    botoRegistrarse.setDisable(false);
                }
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
                if(!avatar4Sel.isSelected() && !avatarDefaultSel.isSelected() && !avatar1Sel.isSelected() && !avatar2Sel.isSelected() && !avatar3Sel.isSelected()){
                    botoRegistrarse.setDisable(true);
                }
                else{
                    botoRegistrarse.setDisable(false);
                }
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
                if(!avatar4Sel.isSelected() && !avatarDefaultSel.isSelected() && !avatar1Sel.isSelected() && !avatar2Sel.isSelected() && !avatar3Sel.isSelected()){
                    botoRegistrarse.setDisable(true);
                }
                else{
                    botoRegistrarse.setDisable(false);
                }
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
         //DATA DE NAIXEMENT
        birthDate = this.daypicker.getValue();//obte el valor seleccionat en el datePicker 
        
        //System.out.println("RESULTAT birthDate" +"\n"+ birthDate);//Visualitza resultat intermig
        
        
            
        
            if(User.checkNickName(usr) && User.checkEmail(email) && User.checkPassword(pswd)){
            //TOT CORRECTE, REGISTRA
                try{navegacio.registerUser(usr,email,pswd,avatar,birthDate);}
                catch(NavegacionDAOException e){
                    errorJaRegistrat.setVisible(true);
                    Alert alert = new Alert(AlertType.CONFIRMATION); 
                    alert.setTitle("Error registre"); 
                    alert.setHeaderText("Error: Usuari ja registrat"); 
                    alert.setContentText("Tornar a intentar amb altre usuari?"); 
                    Optional<ButtonType> result = alert.showAndWait(); 
                    if (result.isPresent() && result.get() == ButtonType.OK){ 
                        System.out.println("OK");
                        try {
                            
                            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Registre.fxml"));
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
                        catch (IOException a) {
                            e.printStackTrace();
                            
                        }
                        
                    } 
                    else { 
                        System.out.println("CANCEL"); 
                        Node n = (Node)event.getSource();
                        n.getScene().getWindow().hide();
                    }
                }
                
                if(!errorJaRegistrat.isVisible()){
                    try {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION); 
                    alert.setTitle("Registre"); 
                    alert.setHeaderText("Registre completat amb exit");
                    alert.setContentText("Per favor  " + nickName + " inicia sessió de nou"); 
                    alert.showAndWait();
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
                
    }         
    
        
    
    
    @FXML
    private void cancelar(ActionEvent event){
        Node n = (Node)event.getSource();
        n.getScene().getWindow().hide();
    }
}


