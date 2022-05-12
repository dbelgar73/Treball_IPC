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
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
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
        usuari.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
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
        correu.focusedProperty().addListener((observable, oldValue, newValue)->{ 
            
            email = correu.getText();
            errorCorreu.setVisible(false);
            
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
        contrassenya.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
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
        

    }   
    
    @FXML
    private void registrarse(ActionEvent event) throws NavegacionDAOException{
        Navegacion navegacio = Navegacion.getSingletonNavegacion();
        usr = usuari.getText();
        email = correu.getText();
        pswd = contrassenya.getText();
        avatar = imagePerfil.getImage();
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
                            //TANCA LA FINESTRA DEL REGISTRE
                            Node n = (Node)event.getSource();
                            n.getScene().getWindow().hide();   
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


