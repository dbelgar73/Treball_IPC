/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Navegacion;
import model.Problem;
import model.User;
import poiupv.Poi;
import static poiupv.controladors.IniciSesioController.navegacio;

/**
 * FXML Controller class
 *
 * @author david
 */
public class MenuController implements Initializable {

    @FXML
    private ListView<Problem> ListaProblems;

    private ObservableList<Problem> datos = null;
    @FXML
    private ImageView avatarPerfil;
    @FXML
    private Button botoRealizar;
    public static Problem seleccionat;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        //AVATAR PERFIL
        avatarPerfil.setImage(Poi.userActual.getAvatar()); //fica el avatar del perfil actual
        Circle clip = new Circle();
        clip.setCenterX(20);
        clip.setCenterY(22);
        clip.setRadius(20);
        avatarPerfil.setClip(clip);
        //LLISTA DE PROBLEMES
        //listener
        ListaProblems.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (ListaProblems.isFocused()) {
                botoRealizar.setDisable(false);   
            }
        });
        //carrega la llista de problemes
        List<Problem> problemas = IniciSesioController.navegacio.getProblems();
        System.out.println(problemas.toString());
        datos = FXCollections.observableList(problemas);
        System.out.println(datos.toString());
        ListaProblems.setItems(datos);
        System.out.println(ListaProblems.toString());
    }    

    @FXML
    private void ModificarPerfil(ActionEvent event) {
        try {
            
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/ModificarPerfil.fxml"));
            Parent root = miCargador.load();
            
            Scene scene = new Scene(root);
            Stage estageActual = new Stage();
            estageActual.setResizable(true);
            estageActual.setScene(scene);
            estageActual.initModality(Modality.APPLICATION_MODAL);
            estageActual.show();
            estageActual.setResizable(false);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void CerrarSesion(ActionEvent event) {
        
        try {
            Poi.setUserActual(null);
            Node n = (Node)event.getSource();
            n.getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/poiupv/vistes/IniciSesio.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Inici Sesio");
            stage.setScene(scene);
            stage.show();
            
            } 
        catch (IOException e) {
                e.printStackTrace();
            }    
    }

    @FXML
    private void RealizarProblem(ActionEvent event) {
        seleccionat = ListaProblems.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Principal.fxml"));
            Parent root = miCargador.load();
            RegistreController controlador = miCargador.getController();
            Scene scene = new Scene(root);
            Stage estageActual = new Stage();
            estageActual.setResizable(true);
            estageActual.setScene(scene);
            estageActual.initModality(Modality.APPLICATION_MODAL);
            estageActual.show();
            Node n = (Node)event.getSource();
            n.getScene().getWindow().hide();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ProblemAleat(ActionEvent event) {
         try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Principal.fxml"));
            Parent root = miCargador.load();
            RegistreController controlador = miCargador.getController();
            Scene scene = new Scene(root);
            Stage estageActual = new Stage();
            estageActual.setResizable(true);
            estageActual.setScene(scene);
            estageActual.initModality(Modality.APPLICATION_MODAL);
            estageActual.show();
            Node n = (Node)event.getSource();
            n.getScene().getWindow().hide();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    
}
