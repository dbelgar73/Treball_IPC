/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Answer;
import model.Problem;
import static poiupv.controladors.MenuController.seleccionat;


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
    private ImageView avatarPerfil;
    @FXML
    private Label problema;
    @FXML
    private ListView<Answer> llistaRespostes;
    private ObservableList<Answer> datos = null;
    @FXML
    private Button validarResposta;
    private Answer respostaSel;
    @FXML
    private Label correctaIncorrecta;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Answer> respostes = (MenuController.seleccionat).getAnswers();
        datos = FXCollections.observableList(respostes);
        llistaRespostes.setItems(datos);
        System.out.println(llistaRespostes.toString());
        llistaRespostes.getSelectionModel().selectedIndexProperty(). addListener(  (o, oldVal, newVal) -> { 
            if (newVal.intValue() == -1) {
                respostaSel = null;
                validarResposta.setDisable(true);
            }
            else{
                respostaSel = llistaRespostes.getSelectionModel().getSelectedItem();
                validarResposta.setDisable(false);
            }
             });
//        problema.setText((MenuController.seleccionat).getText());
//        //==========================================================
//        // inicializamos el slider y enlazamos con el zoom
//        zoom_slider.setMin(0.5);
//        zoom_slider.setMax(1.5);
//        zoom_slider.setValue(1.0);
//        zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
//        
//        //=========================================================================
//        //Envuelva el contenido de scrollpane en un grupo para que 
//        //ScrollPane vuelva a calcular las barras de desplazamiento tras el escalado
//        Group contentGroup = new Group();
//        zoomGroup = new Group();
//        contentGroup.getChildren().add(zoomGroup);
//        zoomGroup.getChildren().add(map_scrollpane.getContent());
//        map_scrollpane.setContent(contentGroup);

    }

    

    @FXML
    private void acercaDe(ActionEvent event) {
//        Alert mensaje= new Alert(Alert.AlertType.INFORMATION);
//        mensaje.setTitle("Acerca de");
//        mensaje.setHeaderText("IPC - 2022");
//        mensaje.showAndWait();
    }

    @FXML
    private void zoomOut(ActionEvent event) {
//        double sliderVal = zoom_slider.getValue();
//        zoom_slider.setValue(sliderVal + -0.1);
    }

    @FXML
    private void zoomIn(ActionEvent event) {
//        double sliderVal = zoom_slider.getValue();
//        zoom_slider.setValue(sliderVal += 0.1);
    }
    
    private void zoom(double scaleValue) {
//        //===================================================
//        //guardamos los valores del scroll antes del escalado
//        double scrollH = map_scrollpane.getHvalue();
//        double scrollV = map_scrollpane.getVvalue();
//        //===================================================
//        // escalamos el zoomGroup en X e Y con el valor de entrada
//        zoomGroup.setScaleX(scaleValue);
//        zoomGroup.setScaleY(scaleValue);
//        //===================================================
//        // recuperamos el valor del scroll antes del escalado
//        map_scrollpane.setHvalue(scrollH);
//        map_scrollpane.setVvalue(scrollV);
    }

    @FXML
    private void muestraPosicion(MouseEvent event) {
//        posicion.setText("sceneX: " + (int) event.getSceneX() + ", sceneY: " + (int) event.getSceneY() + "\n"
//                + "         X: " + (int) event.getX() + ",          Y: " + (int) event.getY());
    }

    @FXML
    private void resultados(ActionEvent event) {
    }

    @FXML
    private void modificarPerfil(ActionEvent event) {
    }
    

    @FXML
    private void cerrarSesion(ActionEvent event) {
//        ((Stage)zoom_slider.getScene().getWindow()).close();
//        try {
//            Stage stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("IniciSesio.fxml"));
//            Scene scene = new Scene(root);
//            stage.setTitle("Inicio Sesión");
//            stage.setScene(scene);
//            stage.show();
//            } 
//        catch (IOException e) {
//                e.printStackTrace();
//            }    
    }

    @FXML
    private void respostaCorrecta(ActionEvent event) {
        boolean correcta = respostaSel.getValidity();
        if(correcta){
            correctaIncorrecta.setVisible(true);
            correctaIncorrecta.setText("Resposta correcta");
            correctaIncorrecta.setStyle("-fx-text-inner-color: green;");
        }
        else{
            correctaIncorrecta.setVisible(true);
            correctaIncorrecta.setText("Resposta Incorrecta");
            correctaIncorrecta.setStyle("-fx-text-inner-color: red;");
        }
    }
    
    
}
