/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Sergi
 */
public class PrincipalController implements Initializable {

    @FXML
    private ImageView avatarPerfil;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private ImageView cartaNautica;
    @FXML
    private Label posicion;
    @FXML
    private Slider zoom_slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void resultados(ActionEvent event) {
    }

    @FXML
    private void modificarPerfil(ActionEvent event) {
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
    }

    @FXML
    private void acercaDe(ActionEvent event) {
    }

    @FXML
    private void muestraPosicion(MouseEvent event) {
    }

    @FXML
    private void zoomOut(ActionEvent event) {
    }

    @FXML
    private void zoomIn(ActionEvent event) {
    }
    
}
