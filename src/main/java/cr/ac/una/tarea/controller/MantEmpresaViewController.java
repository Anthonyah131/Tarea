/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Empresa;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class MantEmpresaViewController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCedulaJuridica;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAnioFundacion;
    @FXML
    private ImageView imgLogo;
    @FXML
    private JFXButton jfxBtnBuscarLogo;
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

    Empresa empresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCedulaJuridica.setTextFormatter(Formato.getInstance().cedulaFormat(50));
        txtTelefono.setTextFormatter(Formato.getInstance().integerFormat());
        txtEmail.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtAnioFundacion.setTextFormatter(Formato.getInstance().integerFormat());
        empresa = new Empresa();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnBuscarLogo(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnBuscar(ActionEvent event) {
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaEmpresa();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
        empresa = (Empresa) busquedaController.getResultado();
        cargarEmpresa();
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
    
    private void bindEmpresa(Boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bind(new SimpleStringProperty(empresa.getId().toString()));
        }
        txtNombre.textProperty().bindBidirectional(new SimpleStringProperty(empresa.getNombre()));
        txtCedulaJuridica.textProperty().bindBidirectional(new SimpleStringProperty(empresa.getCedulaJuridica()));
        txtTelefono.textProperty().bindBidirectional(new SimpleStringProperty(empresa.getTelefono().toString()));
        txtEmail.textProperty().bindBidirectional(new SimpleStringProperty(empresa.getEmail()));
        txtAnioFundacion.textProperty().bindBidirectional(new SimpleStringProperty(empresa.getAnioFundacion().toString()));


    }

    private void unbindEmpresa() {
        txtId.textProperty().unbind();
        txtNombre.textProperty().unbindBidirectional(empresa.getNombre());
        txtCedulaJuridica.textProperty().unbindBidirectional(empresa.getCedulaJuridica());
        txtTelefono.textProperty().unbindBidirectional(empresa.getTelefono());
        txtEmail.textProperty().unbindBidirectional(empresa.getEmail());
        txtAnioFundacion.textProperty().unbindBidirectional(empresa.toString());
    }

    private void nuevoEmpresa() {
        unbindEmpresa();
        empresa = new Empresa();
        bindEmpresa(true);
        txtId.clear();
        txtId.requestFocus();
    }

    private void cargarEmpresa() {

        if (empresa != null) {
            unbindEmpresa();
            bindEmpresa(false);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Empresa", getStage(), "Erro al Cargar Empresa");
        }
    }

}
