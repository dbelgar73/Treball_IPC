/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Problem;
import poiupv.Poi;

/**
 * FXML Controller class
 *
 * @author david
 */
public class MenuController implements Initializable {

    @FXML
    private ListView<String> ListaProblems;

    private ObservableList<String> datos = null;
    @FXML
    private ImageView avatarPerfil;
    @FXML
    private Button botoRealizar;
    
    
    public int random;
    public List<Problem> problemas;
    
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
        problemas = Poi.navegacio.getProblems();
        datos = FXCollections.observableList(Poi.problemesText(problemas));
        ListaProblems.setItems(datos);
        //Foco en la llista de problemes,guarda en la variable seleccionat el problema elegit
        ListaProblems.getSelectionModel().selectedIndexProperty(). addListener(  (o, oldVal, newVal) -> { 
            if (newVal.intValue() == -1) {
                Poi.seleccionat = null;
                
            }
            else{
                Poi.seleccionat = problemas.get(ListaProblems.getSelectionModel().getSelectedIndex());
            }
        });
    }

    @FXML
    private void ModificarPerfil(ActionEvent event) {
        try {
            //mostra la finestra de modificarPerfil sense tancar la del menu
            //fins que no es faja alguna accio en modificarPerfil no es pot utilizar la finestra de menu
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/ModificarPerfil.fxml"));
            Parent root = miCargador.load();
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
    }

    @FXML
    private void CerrarSesion(ActionEvent event) {
        
        try {
            Poi.setUserActual(null);
            ((Stage)avatarPerfil.getScene().getWindow()).close();
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
         try {
            
            //Canvia la finestra a Principal.fxml
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Principal.fxml"));
            Parent root = miCargador.load();
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
    }

    @FXML
    private void ProblemAleat(ActionEvent event) {
        random = (int) (Math.random() * ( problemas.size()));
        Poi.seleccionat = problemas.get(random);
        try {
            
            //Canvia la finestra a Principal.fxml
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Principal.fxml"));
            Parent root = miCargador.load();
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
    }

    @FXML
    private void MostrarResult(ActionEvent event) {
        try {
            
            //Canvia la finestra a Principal.fxml
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/resultats.fxml"));
            Parent root = miCargador.load();
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

    }
   
}
