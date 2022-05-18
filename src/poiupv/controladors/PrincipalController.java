/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import DBAccess.NavegacionDAOException;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Answer;
import model.Problem;
import model.Session;
import poiupv.Poi;
import static poiupv.controladors.IniciSesioController.nickName;


/**
 *
 * @author Sergi
 */
public class PrincipalController implements Initializable {

    @FXML
    private Label posicion;
    @FXML
    private Slider zoom_slider;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private ImageView cartaNautica;
    
    private Group zoomGroup;
    
    
    
    @FXML
    private Label problema;
    @FXML
    private ListView<String> llistaRespostes;
    private ObservableList<String> datos = null;
    @FXML
    private Button validarResposta;
    private Answer respostaSel;
    @FXML
    private Label correctaIncorrecta;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Answer> respostes = (Poi.seleccionat).getAnswers();
        List<String> textrespostes = Poi.respostesText(respostes);
        datos = FXCollections.observableList(textrespostes);
        llistaRespostes.setItems(datos);
        


        //System.out.println(llistaRespostes.toString());
        llistaRespostes.getSelectionModel().selectedIndexProperty(). addListener(  (o, oldVal, newVal) -> { 
            if (newVal.intValue() == -1) {
                respostaSel = null;
                validarResposta.setDisable(true);
            }
            else{
                respostaSel = respostes.get(llistaRespostes.getSelectionModel().getSelectedIndex());
                validarResposta.setDisable(false);
            }
             });
        problema.setText((Poi.seleccionat).getText());
        //==========================================================
        // inicializamos el slider y enlazamos con el zoom
        zoom_slider.setMin(0.5);
        zoom_slider.setMax(1.5);
        zoom_slider.setValue(1.0);
        zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
        
        //=========================================================================
        //Envuelva el contenido de scrollpane en un grupo para que 
        //ScrollPane vuelva a calcular las barras de desplazamiento tras el escalado
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(map_scrollpane.getContent());
        map_scrollpane.setContent(contentGroup);

    }

    @FXML
    private void zoomOut(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + -0.1);
    }

    @FXML
    private void zoomIn(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 0.1);
    }
    
    private void zoom(double scaleValue) {
        //===================================================
        //guardamos los valores del scroll antes del escalado
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        //===================================================
        // escalamos el zoomGroup en X e Y con el valor de entrada
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        //===================================================
        // recuperamos el valor del scroll antes del escalado
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
    }

    @FXML
    private void muestraPosicion(MouseEvent event) {
        posicion.setText("sceneX: " + (int) event.getSceneX() + ", sceneY: " + (int) event.getSceneY() + "\n"
                + "         X: " + (int) event.getX() + ",          Y: " + (int) event.getY());
    }

        
    @FXML
    private void respostaCorrecta(ActionEvent event) throws NavegacionDAOException {
        boolean correcta = respostaSel.getValidity();
        if(correcta){
            Poi.hits++;
            Alert alert = new Alert(AlertType.CONFIRMATION); 
            alert.setTitle("Resposta correcta"); 
            alert.setHeaderText("Resposta Correcta"); 
            alert.setContentText("Felicitats, has encertat la respota.\r Vols realitzar altre problema?");  
            Optional<ButtonType> result = alert.showAndWait(); 
            if (result.isPresent() && result.get() == ButtonType.OK){ 
                try {
                    Node n = (Node)event.getSource();
                    n.getScene().getWindow().hide();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/poiupv/vistes/Menu.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("Bandeja de entrada");
                    stage.setScene(scene);
                    stage.show();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }     
                
            } 
            else { 
                try {
                    Poi.sessio = new Session(Poi.TimeInici,Poi.hits,Poi.faults);
                    Poi.userActual.addSession(Poi.sessio);
                    Poi.setUserActual(null);
                    ((Stage)problema.getScene().getWindow()).close();
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
        }   
        else{
            Poi.faults++;
            Alert alert = new Alert(AlertType.CONFIRMATION); 
            alert.setTitle("Resposta incorrecta"); 
            alert.setHeaderText("Resposta Incorrecta"); 
            alert.setContentText("Resposta ccorrecta: \r" + respostaSel.getText() + "\rVols realitzar altre problema?");  
            Optional<ButtonType> result = alert.showAndWait(); 
            if (result.isPresent() && result.get() == ButtonType.OK){ 
                try {
                    Node n = (Node)event.getSource();
                    n.getScene().getWindow().hide();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/poiupv/vistes/Menu.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("Bandeja de entrada");
                    stage.setScene(scene);
                    stage.show();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }     
                
            } 
            else { 
                try {
                    Poi.sessio = new Session(Poi.TimeInici,Poi.hits,Poi.faults);
                    Poi.userActual.addSession(Poi.sessio);
                    Poi.setUserActual(null);
                    ((Stage)problema.getScene().getWindow()).close();
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
            
        }
        
    }
        
        
        
        
    }
    
    
    
    

