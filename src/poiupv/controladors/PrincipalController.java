/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
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
    private ListView<String> llistaRespostes;
    private ObservableList<String> datos = null;
    @FXML
    private Button validarResposta;
    private Answer respostaSel;
    @FXML
    private Label correctaIncorrecta;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Answer> respostes = (MenuController.seleccionat).getAnswers();
        List<String> textrespostes = respostesText(respostes);
        datos = FXCollections.observableList(textrespostes);
        llistaRespostes.setItems(datos);
        System.out.println(llistaRespostes.toString());
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
        problema.setText((MenuController.seleccionat).getText());
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
    private void acercaDe(ActionEvent event) {
        Alert mensaje= new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Acerca de");
        mensaje.setHeaderText("IPC - 2022");
        mensaje.showAndWait();
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
    private void resultados(ActionEvent event) {
        try {
            
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/poiupv/vistes/Resultados.fxml"));
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
    private void modificarPerfil(ActionEvent event) {
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
            Node n = (Node)event.getSource();
            n.getScene().getWindow().hide();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void cerrarSesion(ActionEvent event) {
        ((Stage)zoom_slider.getScene().getWindow()).close();
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("IniciSesio.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Inicio Sesión");
            stage.setScene(scene);
            stage.show();
            } 
        catch (IOException e) {
                e.printStackTrace();
            }    
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
            //correctaIncorrecta.setTextFill(Color.GREEN);
        }
    }
    
    private List<String> respostesText(List<Answer> res){
        int i = res.size();
        List<String> respostes = new ArrayList<>();
        for(int j = 0; j < i; j++){
            Answer answ = res.get(j);
            respostes.add(answ.getText());
        }
        return respostes;
    }
    
    
}
