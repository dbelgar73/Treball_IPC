/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import DBAccess.NavegacionDAOException;
import java.awt.Color;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.Answer;
import poiupv.Poi;


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
    @FXML
    private ToggleButton botoPunt;
    @FXML
    private ToggleButton botoLine;
    @FXML
    private ToggleButton botoArco;
    @FXML
    private ToggleButton botoText;
    @FXML
    private ColorPicker colorPalet;
    
    
 
    
    private double iniX, iniY,baseX, baseY;
    
    private Color color;
    @FXML
    private ToggleButton botoTransportador;
    @FXML
    private ImageView transportador;
    
    private Line linea;
    private Circle punt;
    private Circle arc;
    private double inicioXArc;
    private TextField texto;
    private Text textoT;
    ToggleButton botoSelec;
    
            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transportador.setDisable(true);
        transportador.setVisible(false);
        List<Answer> respostes = (Poi.seleccionat).getAnswers();
        List<String> textrespostes = Poi.respostesText(respostes);
        datos = FXCollections.observableList(textrespostes);
        llistaRespostes.setItems(datos);
        
       
        
        //color.bind(colorPalet.getValue());
        botoPunt.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(botoPunt.isSelected()){
                botoLine.setSelected(false);
                botoArco.setSelected(false);
                botoText.setSelected(false);                
            }
        });
        botoLine.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(botoLine.isSelected()){
                botoPunt.setSelected(false);
                botoArco.setSelected(false);
                botoText.setSelected(false);                
            }
        });
        botoArco.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(botoArco.isSelected()){
                botoLine.setSelected(false);
                botoPunt.setSelected(false);
                botoText.setSelected(false);                
            }
        });
        botoText.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(botoText.isSelected()){
                botoLine.setSelected(false);
                botoArco.setSelected(false);
                botoPunt.setSelected(false);                
            }
        });
        
    

        
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
            Alert alert = new Alert(AlertType.INFORMATION); 
            alert.setTitle("Resposta correcta"); 
            alert.setHeaderText("Resposta Correcta"); 
            alert.setContentText("Felicitats, has encertat la respota.");  
            alert.showAndWait(); 
            Node n = (Node)event.getSource();
            n.getScene().getWindow().hide();
        }
        else{
            Poi.faults++;
            Alert alert = new Alert(AlertType.INFORMATION); 
            alert.setTitle("Resposta incorrecta"); 
            alert.setHeaderText("Resposta Incorrecta"); 
            alert.setContentText("Resposta ccorrecta: \r" + respostaSel.getText());  
            alert.showAndWait(); 
            Node n = (Node)event.getSource();
            n.getScene().getWindow().hide();   
        } 
    }

    @FXML
    private void MousePressed(MouseEvent event) {
        arc = new Circle(1);
        punt = new Circle(1);
        linea = new  Line(event.getX(), event.getY(),event.getX(), event.getY());
        texto = new TextField();
        textoT = new Text();
        
        
        //crea el punt
        if(botoPunt.isSelected()){
            punt.setFill(colorPalet.getValue());
            punt.setStroke(colorPalet.getValue());
            zoomGroup.getChildren().add(punt);
            punt.setCenterX(event.getX());
            punt.setCenterY(event.getY());
            inicioXArc = event.getX(); 
            botoPunt.setSelected(false);
        }
        //crea la linea
        if(botoLine.isSelected()){
            linea.setFill(colorPalet.getValue());
            linea.setStroke(colorPalet.getValue());
            zoomGroup.getChildren().add(linea);
            botoLine.setSelected(false);
        }
        if(botoArco.isSelected()){
            
            arc.setFill(colorPalet.getValue());
            arc.setStroke(colorPalet.getValue());
            zoomGroup.getChildren().add(arc);
            arc.setCenterX(event.getX());
            arc.setCenterY(event.getY());
            inicioXArc = event.getX(); 
            botoArco.setSelected(false);
        }
        if(botoText.isSelected()){
            zoomGroup.getChildren().add(texto);
            texto.setLayoutX(event.getX());
            texto.setLayoutY(event.getY());
            texto.requestFocus();
            texto.setOnAction(e -> {
                textoT.setX(texto.getLayoutX());
                textoT.setY(texto.getLayoutY());
                textoT.setStyle("-fx-font-family: System; -fx-font-size: 40;");               
                zoomGroup.getChildren().add(textoT);
                zoomGroup.getChildren().remove(texto);
                e.consume();
            });
            botoText.setSelected(false);
        }
        //clicant en el boto dret del ratoli sobre el cercle que volem se borra
        punt.setOnContextMenuRequested(e -> {
            ContextMenu menuContext = new ContextMenu();
            MenuItem borrarItem = new MenuItem("eliminar");
            menuContext.getItems().add(borrarItem);
            borrarItem.setOnAction(ev -> {
                zoomGroup.getChildren().remove((Node)e.getSource());
                ev.consume();
            });
            menuContext.show(punt, e.getSceneX(), e.getSceneY());
            e.consume();
        });
        linea.setOnContextMenuRequested(e -> {
            ContextMenu menuContext = new ContextMenu();
            MenuItem borrarItem = new MenuItem("eliminar");
            menuContext.getItems().add(borrarItem);
            borrarItem.setOnAction(ev -> {
                zoomGroup.getChildren().remove((Node)e.getSource());
                ev.consume();
            });
            menuContext.show(linea, e.getSceneX(), e.getSceneY());
            e.consume();
        });
        arc.setOnContextMenuRequested(e -> {
            ContextMenu menuContext = new ContextMenu();
            MenuItem borrarItem = new MenuItem("eliminar");
            menuContext.getItems().add(borrarItem);
            borrarItem.setOnAction(ev -> {
                zoomGroup.getChildren().remove((Node)e.getSource());
                ev.consume();
            });
            menuContext.show(arc, e.getSceneX(), e.getSceneY());
            e.consume();
        });
        textoT.setOnContextMenuRequested(e -> {
            ContextMenu menuContext = new ContextMenu();
            MenuItem borrarItem = new MenuItem("eliminar");
            menuContext.getItems().add(borrarItem);
            borrarItem.setOnAction(ev -> {
                zoomGroup.getChildren().remove((Node)e.getSource());
                ev.consume();
            });
            menuContext.show(textoT, e.getSceneX(), e.getSceneY());
            e.consume();
        });
    }
    
    @FXML
    private void enableTransp(MouseEvent event){
        botoTransportador.selectedProperty().addListener(  (o, oldVal, newVal) -> { 
            if (newVal != true) {
                transportador.setDisable(true);
                transportador.setVisible(false);
            }
            else{
                transportador.setDisable(false);
                transportador.setVisible(true);
            }
             });
    }
    public double inicioYTrans;
    public double inicioXTrans;
    @FXML
    private void mouTransportador(MouseEvent event){
                if(!transportador.isDisabled()){
                    double despX = event.getSceneX() - iniX;
                    double despY = event.getSceneY() - iniY;
                    transportador.setTranslateX(baseX + despX);
                    transportador.setTranslateY(baseY + despY);
                    event.consume();
                }
    }
    
    @FXML
    private void getIniTrans(MouseEvent event){
        iniX = event.getSceneX();
        iniY = event.getSceneY();
        baseX = transportador.getTranslateX();
        baseY = transportador.getTranslateY();
        event.consume();
    }

    @FXML
    private void MouseDragged(MouseEvent event) {
        linea.setEndX(event.getX());
        linea.setEndY(event.getY());
        
        
        double radio = Math.abs(event.getX() - inicioXArc);
        arc.setRadius(radio);
        
        
        punt.setRadius(radio);
        event.consume();
        
    }
}
