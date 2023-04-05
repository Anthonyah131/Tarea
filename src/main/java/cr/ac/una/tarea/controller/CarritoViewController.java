/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Carrito;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class CarritoViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane rootCarritoView;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label lbCarrito;
    @FXML
    private TableView<?> tbvCarrito;
    @FXML
    private JFXButton jfxBtnAtras;
    @FXML
    private ImageView imgAtras;
    @FXML
    private JFXButton jfxBtnComprar;
    @FXML
    private ImageView imgComprar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnAtras(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnComprar(ActionEvent event) {
    }

    void cargarCarrito(Carrito carrito) {
    }
    
}
