/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
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
        nuevoCliente();
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
        busquedaController.SetResultado();
        if (cliente == null) {
            cliente = new Cliente();
        }
        unbindCliente();
        bindCliente(false);
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Cliente", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoCliente();
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            Boolean banderaNuevo = true;
            ObservableList<Cliente> clientes = (ObservableList<Cliente>) AppContext.getInstance().get("ClientesLista");
            if (cliente.getId() != null) {
                for (Cliente cli : clientes) {
                    if (Objects.equals(cli.getId(), cliente.getId())) {
                        cli = cliente;
                        banderaNuevo = false;
                    }
                }
            }

            if (banderaNuevo) {
                Long contador[] = (Long[]) AppContext.getInstance().get("Contador");
                contador[1]++;
                cliente.setId(contador[1]);
                clientes.add(cliente);
            }
            nuevoCliente();
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Cliente", getStage(), "Cliente actualizado correctamente.");

        } catch (Exception ex) {
            Logger.getLogger(MantCatViewController.class.getName()).log(Level.SEVERE, "Error guardando el Cliente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Cliente", getStage(), "Ocurrio un error guardando el Cliente.");
        }
    }

    @FXML
    private void onActionJfxBtnEliminar(ActionEvent event) {
        try {
            if (cliente.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Cliente", getStage(), "Debe cargar el Cliente que desea eliminar.");
            } else {
                ObservableList<Cliente> clientes = (ObservableList<Cliente>) AppContext.getInstance().get("ClientesLista");
//                for (Cliente cli : clientes) {
                for(int i = 0; i < clientes.size(); i++) {
//                    if (Objects.equals(cli.getId(), cliente.getId())) {
//                        clientes.remove(cli);
//                    }
                    if (Objects.equals(clientes.get(i).getId(), cliente.getId())) {
                        clientes.remove(clientes.get(i));
                    }
                }
                nuevoCliente();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Cliente", getStage(), "Cliente eliminado correctamente.");
            }
        } catch (Exception ex) {
            Logger.getLogger(MantClientesViewController.class.getName()).log(Level.SEVERE, "Error eliminando el Cliente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Cliente", getStage(), "Ocurrio un error eliminando el Cliente.");
        }
    }

    @FXML
    private void onActionJfxBtnCancelar(ActionEvent event) {
    }

    private void bindCliente(Boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bind(cliente.id);
        }
        txtNombre.textProperty().bindBidirectional(cliente.nombre);
        txtApellidos.textProperty().bindBidirectional(cliente.apellido);
        txtCedula.textProperty().bindBidirectional(cliente.cedula);
        txtTelefono.textProperty().bindBidirectional(cliente.telefono);
        txtCorreo.textProperty().bindBidirectional(cliente.correo);
        dpCliente.valueProperty().bindBidirectional(cliente.fechaNacimiento);

    }

    private void unbindCliente() {
        txtId.textProperty().unbind();
        txtNombre.textProperty().unbindBidirectional(cliente.nombre);
        txtApellidos.textProperty().unbindBidirectional(cliente.apellido);
        txtCedula.textProperty().unbindBidirectional(cliente.cedula);
        txtTelefono.textProperty().unbindBidirectional(cliente.telefono);
        txtCorreo.textProperty().unbindBidirectional(cliente.correo);
        dpCliente.valueProperty().unbindBidirectional(cliente.fechaNacimiento);
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
