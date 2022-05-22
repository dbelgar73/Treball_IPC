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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.User;
import poiupv.Poi;
import static poiupv.controladors.RegistreController.birthDate;
import static poiupv.controladors.RegistreController.email;
import static poiupv.controladors.RegistreController.pswd;



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
    
    @FXML
    private ImageView imagePerfil;

    @FXML
    private ChoiceBox<String> ChoiceAvatar;
    @FXML
    public Button botoGuardar;
    @FXML
    private Label errorContrassenya;
    @FXML
    private Label errorCorreu;
    @FXML
    private Label errorEdat;
    private boolean edatOk;
    private double edat;
    public String newPass;
    public String newEmail;
    public LocalDate newBirth;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Circle clip = new Circle();
        clip.setCenterX(imagePerfil.getX() + imagePerfil.getFitWidth()/2);
        clip.setCenterY(imagePerfil.getY() + imagePerfil.getFitHeight()/2);
        clip.setRadius(imagePerfil.getFitWidth()/2);
        imagePerfil.setClip(clip);
        ChoiceAvatar.getItems().add("Avatar predeterminado");
        ChoiceAvatar.getItems().add("Avatar 1");
        ChoiceAvatar.getItems().add("Avatar 2");
        ChoiceAvatar.getItems().add("Avatar 3");
        ChoiceAvatar.getItems().add("Avatar 4"); 
        ChoiceAvatar.setValue(null);
        
        
        ChoiceAvatar.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            String res = ChoiceAvatar.getValue();
            if(res != null ){    
                imagePerfil.setImage(new Image(Poi.creadorStringURL(res)));
                
            }
        });
        contrasenyaUser.setText(Poi.userActual.getPassword());
        CorreuUser.setText(Poi.userActual.getEmail());
        dataUser.setValue(Poi.userActual.getBirthdate());
        imagePerfil.setImage(Poi.userActual.getAvatar());
        
        //LISTENER CAMP CORREU
        CorreuUser.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)->{ 
            
            newEmail = CorreuUser.getText();
            
            
            if(!"".equals(newEmail) && !User.checkEmail(newEmail)){//error en el correu
                errorCorreu.setVisible(true);
                botoGuardar.setDisable(true);
                
            }
            else{ 
                errorCorreu.setVisible(false);
                botoGuardar.setDisable(false);
                
            }
        });
        //LISTENER CAMP CONTRASSENYA
        contrasenyaUser.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            newPass= contrasenyaUser.getText();
            errorContrassenya.setVisible(false);
            if(!"".equals(newPass) && !User.checkPassword(newPass)){//error en la contrassenya
                errorContrassenya.setVisible(true);
                botoGuardar.setDisable(true);
            }
            else{
                errorContrassenya.setVisible(false);
                botoGuardar.setDisable(false);
            }
        });
        //LISTENER CAMP ANIVERSARI
        dataUser.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
        
           newBirth= dataUser.getValue();
            if(newBirth == null){

                botoGuardar.setDisable(true);
                edatOk = false;
            }
            else{

                //Obte la data de ara
                LocalDate now = LocalDate.now();
                //calcula la diferencia (Data de hui - any de naixement) = edat
                int dataHui = now.getYear() * 365 + now.getDayOfYear();//calcula la data de hui
                int naixement = newBirth.getYear() * 365 +  newBirth.getDayOfYear();//calcula la data de naixement
                edat = (dataHui - naixement)/365 ;
               

                if(edat < 16){//es menor de 16 anys
                    errorEdat.setVisible(true);//mostra error edat 
                    botoGuardar.setDisable(true);
                    edatOk = false;
                }

                else{
                    errorEdat.setVisible(false);//amaga el error de la edat
                    botoGuardar.setDisable(false);
                    edatOk=true;
                }
            }
        });
    }    

    @FXML
    private void guardarCambios(ActionEvent event) throws NavegacionDAOException, IOException {
        
        
        
       
        //se comproben que siguen correctes
        if(User.checkPassword(newPass) && User.checkEmail(newEmail) && edatOk){
            //canviem les dades que hi havien per les noves
            Poi.userActual.setPassword(newPass);
            Poi.userActual.setEmail(newEmail);
            Poi.userActual.setBirthdate(newBirth);
            Poi.userActual.setAvatar(imagePerfil.getImage());
            ((Stage)botoGuardar.getScene().getWindow()).close();
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Menu.fxml"));
            Parent root = miCargador.load();
            Scene scene = new Scene(root);
            Stage estageActual = new Stage();
            estageActual.setResizable(true);
            estageActual.setScene(scene);
            estageActual.show();
            
        } 
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        Node n = (Node)event.getSource();
        n.getScene().getWindow().hide();
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Menu.fxml"));
        Parent root = miCargador.load();
        Scene scene = new Scene(root);
        Stage estageActual = new Stage();
        estageActual.setResizable(true);
        estageActual.setScene(scene);
        estageActual.show();
    }

    
    
}
