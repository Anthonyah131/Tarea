/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        nuevoCategoria();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnBuscar(ActionEvent event) {
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaCategoria();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
        unbindCategoria();
        categoria = (Categoria) busquedaController.getResultado();
        bindCategoria(false);
        cargarCategoria();
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Categoria", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoCategoria();
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            ObservableList<Categoria> categorias = (ObservableList<Categoria>) AppContext.getInstance().get("CategoriasLista");
            for (Categoria cat : categorias) {
                if (Objects.equals(cat.getId(), categoria.getId())) {
                    cat.setNombre(categoria.getNombre());
                }
            }
            unbindCategoria();
            bindCategoria(false);
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar empleado", getStage(), "Empleado actualizado correctamente.");

        } catch (Exception ex) {
            Logger.getLogger(MantCatViewController.class.getName()).log(Level.SEVERE, "Error guardando el empleado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), "Ocurrio un error guardando el empleado.");
        }
    }

    @FXML
    private void onActionJfxBtnEliminar(ActionEvent event
    ) {
    }

    @FXML
    private void onActionJfxBtnCancelar(ActionEvent event
    ) {
    }

    private void bindCategoria(Boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bind(categoria.id);
        }
        txtCategoria.textProperty().bindBidirectional(categoria.nombre);
    }

    private void unbindCategoria() {
        txtId.textProperty().unbind();
        txtCategoria.textProperty().unbindBidirectional(categoria.nombre);
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

        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empleado", getStage(), "Erro al Cargar empleado");
        }
    }
}
