/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    List<Node> requeridos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtCategoria.setTextFormatter(Formato.getInstance().letrasFormat(30));
        categoria = new Categoria();
        nuevoCategoria();
        indicarRequeridos();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnBuscar(ActionEvent event) {
        nuevoCategoria();
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaCategoria();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
        categoria = (Categoria) busquedaController.getResultado();
        busquedaController.SetResultado();
        if (categoria == null) {
            categoria = new Categoria();
        } else {
            categoria = new Categoria(categoria);
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
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Categoria", getStage(), invalidos);
            } else {
                Boolean banderaNuevo = true;
                ObservableList<Categoria> categorias = (ObservableList<Categoria>) AppContext.getInstance().get("CategoriasLista");
                if (categoria.getId() != null) {
                    for (Categoria cat : categorias) {
                        if (Objects.equals(cat.getId(), categoria.getId())) {
                            cat.setCategoria(categoria);
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
            }
        } catch (Exception ex) {
            Logger.getLogger(MantCatViewController.class.getName()).log(Level.SEVERE, "Error guardando la Categoria.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Categoria", getStage(), "Ocurrio un error guardando la Categoria.");
        }
    }

    @FXML
    private void onActionJfxBtnEliminar(ActionEvent event) {
        try {
            Boolean existe = false;
            ObservableList<Tour> tours = (ObservableList<Tour>) AppContext.getInstance().get("ToursLista");
            for (Tour tour : tours) {
                if (tour.getCategoria().getId().equals(categoria.getId())) {
                    existe = true;
                }
            }
            if (!existe) {
                if (categoria.getId() == null) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Categoria", getStage(), "Debe cargar la Categoria que desea eliminar.");
                } else {
                    ObservableList<Categoria> categorias = (ObservableList<Categoria>) AppContext.getInstance().get("CategoriasLista");
                    for (int i = 0; i < categorias.size(); i++) {
                        if (Objects.equals(categorias.get(i).getId(), categoria.getId())) {
                            categorias.remove(categorias.get(i));
                        }
                    }
                    nuevoCategoria();
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Categoria", getStage(), "Categoria eliminado correctamente.");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Empresa", getStage(), "No se puede eliminar una empresa vinculada con un Tour");
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

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtCategoria));
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof TextField && ((TextField) node).getText() == null || ((TextField) node).getText().isEmpty()) {
                if (validos) {
                    invalidos += ((TextField) node).getText();
                } else {
                    invalidos += "," + ((TextField) node).getText();
                }
                
                validos = false;
            } else if (node instanceof JFXPasswordField && !((JFXPasswordField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof DatePicker && ((DatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((DatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((DatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof ComboBox && ((ComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((ComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((ComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }
}
