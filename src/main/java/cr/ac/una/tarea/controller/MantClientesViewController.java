/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Cliente;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    Cliente cliente;
    ObjectProperty<LocalDate> fechaNacimientoProperty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtApellidos.setTextFormatter(Formato.getInstance().letrasFormat(50));
        txtCedula.setTextFormatter(Formato.getInstance().cedulaFormat(15));
        txtTelefono.setTextFormatter(Formato.getInstance().integerFormat());
        txtCorreo.setTextFormatter(Formato.getInstance().letrasFormat(50));
        cliente = new Cliente();
        fechaNacimientoProperty = new SimpleObjectProperty<>(cliente.getFechaNacimiento());
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnBuscar(ActionEvent event) {
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaCliente();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
        cliente = (Cliente) busquedaController.getResultado();
        cargarCliente();
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

    private void bindCliente(Boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bind(new SimpleStringProperty(cliente.getId().toString()));
        }
        txtNombre.textProperty().bindBidirectional(new SimpleStringProperty(cliente.getNombre()));
        txtApellidos.textProperty().bindBidirectional(new SimpleStringProperty(cliente.getApellido()));
        txtCedula.textProperty().bindBidirectional(new SimpleStringProperty(cliente.getCedula()));
        txtTelefono.textProperty().bindBidirectional(new SimpleStringProperty(cliente.getTelefono()));
        txtCorreo.textProperty().bindBidirectional(new SimpleStringProperty(cliente.getCorreo()));
        dpCliente.valueProperty().bindBidirectional(new SimpleObjectProperty(cliente.getFechaNacimiento()));
        fechaNacimientoProperty = new SimpleObjectProperty<>(cliente.getFechaNacimiento());
        dpCliente.valueProperty().bindBidirectional(fechaNacimientoProperty);

    }

    private void unbindCliente() {
        txtId.textProperty().unbind();
        txtNombre.textProperty().unbindBidirectional(cliente.getNombre());
        txtApellidos.textProperty().unbindBidirectional(cliente.getApellido());
        txtCedula.textProperty().unbindBidirectional(cliente.getCedula());
        txtTelefono.textProperty().unbindBidirectional(cliente.getTelefono());
        txtCorreo.textProperty().unbindBidirectional(cliente.getCorreo());
        dpCliente.valueProperty().unbindBidirectional(fechaNacimientoProperty);
    }

    private void nuevoCliente() {
        unbindCliente();
        cliente = new Cliente();
        bindCliente(true);
        txtId.clear();
        txtId.requestFocus();
    }

    private void cargarCliente() {

        if (cliente != null) {
            unbindCliente();
            bindCliente(false);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Cliente", getStage(), "Erro al Cargar Cliente");
        }
    }

}
