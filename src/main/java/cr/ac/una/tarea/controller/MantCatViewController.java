/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.model.Cliente;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        categoria = (Categoria) busquedaController.getResultado();
        busquedaController.SetResultado();
        if (categoria == null) {
            categoria = new Categoria();
        }
        unbindCategoria();
        bindCategoria(false);
        //cargarCategoria();
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
            Boolean banderaNuevo = true;
            ObservableList<Categoria> categorias = (ObservableList<Categoria>) AppContext.getInstance().get("CategoriasLista");
            if (categoria.getId() != null) {
                for (Categoria cat : categorias) {
                    if (Objects.equals(cat.getId(), categoria.getId())) {
                        cat = categoria;
                        banderaNuevo = false;
                    }
                }
            }
            if (banderaNuevo) {
                Long contador[] = (Long[]) AppContext.getInstance().get("Contador");
                contador[0]++;
                categoria.setId(contador[0]);
                categorias.add(categoria);
            }
            nuevoCategoria();
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Categoria", getStage(), "Categoria actualizado correctamente.");

        } catch (Exception ex) {
            Logger.getLogger(MantCatViewController.class.getName()).log(Level.SEVERE, "Error guardando el empleado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Categoria", getStage(), "Ocurrio un error guardando la Categoria.");
        }
    }

    @FXML
    private void onActionJfxBtnEliminar(ActionEvent event) {
        try {
            if (categoria.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Categoria", getStage(), "Debe cargar la Categoria que desea eliminar.");
            } else {
                ObservableList<Categoria> categorias = (ObservableList<Categoria>) AppContext.getInstance().get("CategoriasLista");
//                for (Categoria cat : categorias) {
                for (int i = 0; i < categorias.size(); i++) {
//                    if (Objects.equals(cat.getId(), categoria.getId())) {
//                        categorias.remove(cat);
//                    }
                    if (Objects.equals(categorias.get(i).getId(), categoria.getId())) {
                        categorias.remove(categorias.get(i));
                    }
                }
                nuevoCategoria();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Categoria", getStage(), "Categoria eliminado correctamente.");
            }
        } catch (Exception ex) {
            Logger.getLogger(MantCatViewController.class.getName()).log(Level.SEVERE, "Error eliminando la Categoria.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Categoria", getStage(), "Ocurrio un error eliminando la Categoria.");
        }
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
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Categoria", getStage(), "Erro al Cargar Categoria");
        }
    }
}
