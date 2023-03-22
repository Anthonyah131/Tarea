/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class MantCatViewController extends Controller implements Initializable {

    @FXML
    private BorderPane rootManCat;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCategoria;
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
    
    Categoria categoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtCategoria.setTextFormatter(Formato.getInstance().letrasFormat(30));
        categoria = new Categoria();
    }    

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnBuscar(ActionEvent event) {
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaCategoria();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(),true);
        categoria = (Categoria) busquedaController.getResultado();
        cargarCategoria();
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
    
    private void bindCategoria(Boolean nuevo) {
        if(!nuevo){
            txtId.textProperty().bind(new SimpleStringProperty(categoria.getId().toString()));
        }
        txtCategoria.textProperty().bindBidirectional(new SimpleStringProperty(categoria.getNombre()));
    }

    private void unbindCategoria() {
        txtId.textProperty().unbind();
        txtCategoria.textProperty().unbindBidirectional(categoria.getNombre());
    }

    private void nuevoCategoria() {
        unbindCategoria();
        categoria = new Categoria();
        bindCategoria(true);
        txtId.clear();
        txtId.requestFocus();
    }
    
    private void cargarCategoria() {

        if (categoria != null) {
            unbindCategoria();
            bindCategoria(false);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empleado", getStage(), "Erro al Cargar empleado");
        }
    }
}
