/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.model.Empresa;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class MantToursViewController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCuposTotales;
    @FXML
    private TextField txtDisponibles;
    @FXML
    private TextArea jfxTxaItinerario;
    @FXML
    private JFXComboBox<Empresa> jfxCbxEmpresa;
    @FXML
    private JFXComboBox<Categoria> jfxCbxCategoria;
    @FXML
    private DatePicker dpFechaSalida;
    @FXML
    private DatePicker dpFechaRegreso;
    @FXML
    private ImageView imgFotos;
    @FXML
    private JFXButton jfxBtnBuscarFotos;
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
    @FXML
    private JFXButton jfxBtnClientes;

    Tour tour;

    ObservableList<Empresa> empresas = FXCollections.observableArrayList();
    ObservableList<Categoria> categorias = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPrecio.setTextFormatter(Formato.getInstance().integerFormat());
        txtCuposTotales.setTextFormatter(Formato.getInstance().integerFormat());
        txtDisponibles.setTextFormatter(Formato.getInstance().integerFormat());
        jfxTxaItinerario.setTextFormatter(Formato.getInstance().letrasFormat(50));
        tour = new Tour();
        nuevoTour();

        categorias.addAll((List<Categoria>) AppContext.getInstance().get("CategoriasLista"));
        empresas.addAll((List<Empresa>) AppContext.getInstance().get("EmpresasLista"));

        jfxCbxCategoria.setItems(categorias);
        jfxCbxEmpresa.setItems(empresas);

    }

    @Override
    public void initialize() {
        limpiarCBX();
    }

    @FXML
    private void onActionBtnBuscarFotos(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnBuscar(ActionEvent event) {
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaTours();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
        tour = (Tour) busquedaController.getResultado();        
        busquedaController.SetResultado();
        if (tour == null) {
            tour = new Tour();
        }
        limpiarCBX();
        unbindTour();
        bindTour(false);
        
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        limpiarCBX();
        nuevoTour();
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        limpiarCBX();
    }

    @FXML
    private void onActionJfxBtnEliminar(ActionEvent event) {
        limpiarCBX();
    }

    @FXML
    private void onActionJfxBtnCancelar(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnClientes(ActionEvent event) {
    }

    private void bindTour(Boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bind(tour.id);
        }
        txtNombre.textProperty().bindBidirectional(tour.nombre);
        txtPrecio.textProperty().bindBidirectional(tour.precio);
        txtCuposTotales.textProperty().bindBidirectional(tour.cuposTotales);
        txtDisponibles.textProperty().bindBidirectional(tour.cuposDisponibles);
//        jfxTxaItinerario.textProperty().bindBidirectional((Property<String>) tour.itinerario);
        dpFechaSalida.valueProperty().bindBidirectional(tour.fechaSalida);
        dpFechaRegreso.valueProperty().bindBidirectional(tour.fechaRegreso);
        jfxCbxEmpresa.valueProperty().bindBidirectional((Property<Empresa>) tour.empresa);
//        jfxCbxCategoria.valueProperty().bindBidirectional((Property<Categoria>) tour.categoria);
    }

    private void unbindTour() {
        txtId.textProperty().unbind();
        txtNombre.textProperty().unbindBidirectional(tour.nombre);
        txtPrecio.textProperty().unbindBidirectional(tour.precio);
        txtCuposTotales.textProperty().unbindBidirectional(tour.cuposTotales);
        txtDisponibles.textProperty().unbindBidirectional(tour.cuposDisponibles);
        dpFechaSalida.valueProperty().unbindBidirectional(tour.fechaSalida);
        dpFechaRegreso.valueProperty().unbindBidirectional(tour.fechaRegreso);
    }

    private void nuevoTour() {
        unbindTour();
        tour = new Tour();
        bindTour(true);
        txtId.clear();
        txtId.requestFocus();
    }
    
    private void limpiarCBX() {
        jfxCbxCategoria.getItems().clear();
        jfxCbxEmpresa.getItems().clear();
        
        categorias.addAll((List<Categoria>) AppContext.getInstance().get("CategoriasLista"));
        empresas.addAll((List<Empresa>) AppContext.getInstance().get("EmpresasLista"));
        jfxCbxCategoria.setItems(categorias);
        jfxCbxEmpresa.setItems(empresas);
    }

    private void cargarTour() {

        if (tour != null) {
            unbindTour();
            bindTour(false);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Tour", getStage(), "Erro al Cargar Tour");
        }
    }

}
