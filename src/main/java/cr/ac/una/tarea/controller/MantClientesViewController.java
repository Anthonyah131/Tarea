/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class MantClientesViewController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private DatePicker dpCliente;
    @FXML
    private JFXButton jfxBtnBuscar;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton jfxBtnGuardar;
    @FXML
    private JFXButton jfxBtnEliminar;
    @FXML
    private JFXButton jfxBtnCancelar;

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
    private void onActionJfxBtnBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnEliminar(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnCancelar(ActionEvent event) {
    }
    
}
