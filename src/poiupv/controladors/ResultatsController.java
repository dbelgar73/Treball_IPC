/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv.controladors;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Session;
import poiupv.Poi;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ResultatsController implements Initializable {

    @FXML
    private Label encerts;
    @FXML
    private Label fallades;
    @FXML
    private Label sesio;
    @FXML
    private ListView<Session> listSesions;
    
    public List<Session> llistaSesions;
    private ObservableList<Session> datos = null;
    private int hits;
    private int faults;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String data = null;
        llistaSesions = (Poi.userActual).getSessions();
        
        datos = FXCollections.observableList(llistaSesions);
        listSesions.setItems(datos);
        listSesions.getSelectionModel().selectedIndexProperty(). addListener(  (o, oldVal, newVal) -> { 
            hits = (listSesions.getSelectionModel().getSelectedItem()).getHits();
            faults = (listSesions.getSelectionModel().getSelectedItem()).getFaults();
            if (newVal.intValue() == -1) {
                encerts.setText(Integer.toString(0));
                fallades.setText(Integer.toString(0));
            }
            else{
                
            }
             });
    }    

    @FXML
    private void tancarBoto(ActionEvent event) {
        Node n = (Node)event.getSource();
        n.getScene().getWindow().hide();
    }
    
}
