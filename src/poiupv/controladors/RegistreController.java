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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
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
    private Label errorJaRegistrat;
    public static Image avatar;
    public  static String usr;
    public static String email;
    public static String pswd;
    int edat;
    boolean edatOk;
    public static LocalDate birthDate;
    @FXML
    private ChoiceBox<String> choicePerfil;
    @FXML
    private ImageView imagePerfil;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle clip = new Circle();
        clip.setCenterX(imagePerfil.getX() + imagePerfil.getFitWidth()/2 );
        clip.setCenterY(imagePerfil.getY() + imagePerfil.getFitHeight()/2);
        clip.setRadius(imagePerfil.getFitWidth()/2);
        imagePerfil.setClip(clip);
        choicePerfil.getItems().add("Avatar predeterminado");
        choicePerfil.getItems().add("Avatar 1");
        choicePerfil.getItems().add("Avatar 2");
        choicePerfil.getItems().add("Avatar 3");
        choicePerfil.getItems().add("Avatar 4"); 
        choicePerfil.setValue("Avatar predeterminado");
        imagePerfil.setImage(new Image(creadorStringURL("Avatar predeterminado")));
        
        choicePerfil.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            String res = choicePerfil.getValue();
            if(res != null ){    
                imagePerfil.setImage(new Image(creadorStringURL(res)));
                
            }
        });
        
        //LISTENER CAMP USUARI
        usuari.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            usr = usuari.getText();
            
            if(!"".equals(usr) && !User.checkNickName(usr)){//error en l'usuari
                errorUsuari.setVisible(true);
                botoRegistrarse.setDisable(true);
            }
            else{
                errorUsuari.setVisible(false);
                botoRegistrarse.setDisable(false);
            }
        });
        //LISTENER CAMP CORREU
        correu.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)->{ 
            
            email = correu.getText();
            
            
            if(!"".equals(email) && !User.checkEmail(email)){//error en el correu
                errorCorreu.setVisible(true);
                botoRegistrarse.setDisable(true);
                
            }
            else{ 
                errorCorreu.setVisible(false);
                botoRegistrarse.setDisable(false);
                
            }
        });
        //LISTENER CAMP CONTRASSENYA
        contrassenya.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            pswd = contrassenya.getText();
            errorContrassenya.setVisible(false);
            if(!"".equals(pswd) && !User.checkPassword(pswd)){//error en la contrassenya
                errorContrassenya.setVisible(true);
                botoRegistrarse.setDisable(true);
            }
            else{
                errorContrassenya.setVisible(false);
                botoRegistrarse.setDisable(false);
            }
        });
        //LISTENER CAMP ANIVERSARI
        daypicker.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
        
            birthDate = this.daypicker.getValue();
            if(birthDate == null){

                botoRegistrarse.setDisable(true);
                edatOk = false;
            }
            else{

                //Obte la data de ara
                LocalDate now = LocalDate.now();
                //calcula la diferencia (Data de hui - any de naixement) = edat
                int dataHui = now.getYear();//calcula la data de hui
                int naixement = birthDate.getYear();//calcula la data de naixement
                edat = dataHui - naixement ;
                System.out.println("edat" +edat);

                if(edat < 16){//es menor de 16 anys
                    errorEdat.setVisible(true);//mostra error edat 
                    botoRegistrarse.setDisable(true);
                    edatOk = false;
                }

                else{
                    errorEdat.setVisible(false);//amaga el error de la edat
                    botoRegistrarse.setDisable(false);
                    edatOk=true;
                }
            }
        });
    }   
    
    @FXML
    private void registrarse(ActionEvent event) throws NavegacionDAOException, IOException{
        Navegacion navegacio = Navegacion.getSingletonNavegacion();
        usr = usuari.getText();
        email = correu.getText();
        pswd = contrassenya.getText();
        avatar = imagePerfil.getImage();
         //DATA DE NAIXEMENT
        birthDate = this.daypicker.getValue();//obte el valor seleccionat en el datePicker 
        if(!User.checkNickName(usr)){
             errorUsuari.setVisible(true);
        }
        if(!User.checkEmail(email)){
            errorCorreu.setVisible(true);
        }
        if(!User.checkPassword(pswd)){
            errorContrassenya.setVisible(true);
        }
        
        if(User.checkNickName(usr) && User.checkEmail(email) && User.checkPassword(pswd) && edatOk ==true ){
            //TOT CORRECTE, REGISTRA
            try{
                navegacio.registerUser(usr,email,pswd,avatar,birthDate);
            }
            catch(NavegacionDAOException e){
                errorJaRegistrat.setVisible(true);
               
            }
                
            if(!errorJaRegistrat.isVisible()){
                   
                Alert alert = new Alert(Alert.AlertType.INFORMATION); 
                alert.setTitle("Registre"); 
                alert.setHeaderText("Registre completat amb exit");
                alert.setContentText("Per favor inicia sessió de nou"); 
                alert.showAndWait();
                //TANCA LA FINESTRA DEL REGISTRE
                Node n = (Node)event.getSource();
                n.getScene().getWindow().hide();   
                
            }        
        }
    }         
    
        
    
    
    @FXML
    private void cancelar(ActionEvent event){
            Node n = (Node)event.getSource();
            n.getScene().getWindow().hide();
    }

    private String creadorStringURL(String res) {
        String URL = null;
        switch(res){
            case "Avatar predeterminado":
                URL = "/resources/avatars/default.png";
                break;
            case "Avatar 1":
                URL = "/resources/avatars/avatar1.png";
                break;
            case "Avatar 2":
                URL = "/resources/avatars/avatar2.png";
                break;
            case "Avatar 3":
                URL = "/resources/avatars/avatar3.png";
                break;
            case "Avatar 4":
                URL = "/resources/avatars/avatar4.png";
                break;
        }
        return URL;
    }
}


