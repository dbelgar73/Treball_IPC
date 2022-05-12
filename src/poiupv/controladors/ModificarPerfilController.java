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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import model.User;
import poiupv.Poi;
import static poiupv.controladors.IniciSesioController.navegacio;

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
        ChoiceAvatar.setValue("Avatar predeterminado");
        imagePerfil.setImage(Poi.userActual.getAvatar());
        
        ChoiceAvatar.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            String res = ChoiceAvatar.getValue();
            if(res != null ){    
                imagePerfil.setImage(new Image(Poi.creadorStringURL(res)));
                
            }
        });
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
            Poi.userActual.setAvatar(imagePerfil.getImage());
        }
        Node n = (Node)event.getSource();
        n.getScene().getWindow().hide();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Node n = (Node)event.getSource();
        n.getScene().getWindow().hide();
    }

    
    
}
