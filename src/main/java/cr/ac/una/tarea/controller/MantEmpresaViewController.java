/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Empresa;
import cr.ac.una.tarea.model.Tour;
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
        nuevoEmpresa();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnBuscarLogo(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnBuscar(ActionEvent event) {
        nuevoEmpresa();
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaEmpresa();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
        empresa = (Empresa) busquedaController.getResultado();
        busquedaController.SetResultado();
        if (empresa == null) {
            empresa = new Empresa();
        }
        unbindEmpresa();
        bindEmpresa(false);
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Empresa", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoEmpresa();
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            Boolean banderaNuevo = true;
            ObservableList<Empresa> empresas = (ObservableList<Empresa>) AppContext.getInstance().get("EmpresasLista");
            if (empresa.getId() != null) {
                for (Empresa empre : empresas) {
                    if (Objects.equals(empre.getId(), empresa.getId())) {
                        empre = empresa;
                        banderaNuevo = false;
                    }
                }
            }

            if (banderaNuevo) {
                Long contador[] = (Long[]) AppContext.getInstance().get("Contador");
                contador[2]++;
                empresa.setId(contador[2]);
                empresas.add(empresa);
            }
            nuevoEmpresa();
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Empresa", getStage(), "Empresa actualizado correctamente.");
        } catch (Exception ex) {
            Logger.getLogger(MantCatViewController.class.getName()).log(Level.SEVERE, "Error guardando el Empresa.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Empresa", getStage(), "Ocurrio un error guardando el Empresa.");
        }
    }

    @FXML
    private void onActionJfxBtnEliminar(ActionEvent event) {
        try {
            Boolean existe = false;
            ObservableList<Tour> tours = (ObservableList<Tour>) AppContext.getInstance().get("ToursLista");
            for (Tour tour : tours) {
                if (tour.getEmpresa().getId().equals(empresa.getId())) {
                    existe = true;
                }
            }
            if (!existe) {
                if (empresa.getId() == null) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Empresa", getStage(), "Debe cargar la Empresa que desea eliminar.");
                } else {
                    ObservableList<Empresa> empresas = (ObservableList<Empresa>) AppContext.getInstance().get("EmpresasLista");
                    for (int i = 0; i < empresas.size(); i++) {
                        if (Objects.equals(empresas.get(i).getId(), empresa.getId())) {
                            empresas.remove(empresas.get(i));
                        }
                    }
                    nuevoEmpresa();
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Empresa", getStage(), "Empresa eliminado correctamente.");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Empresa", getStage(), "No se puede eliminar una empresa vinculada con un Tour");
            }
        } catch (Exception ex) {
            Logger.getLogger(MantCatViewController.class.getName()).log(Level.SEVERE, "Error eliminando la Empresa.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Empresa", getStage(), "Ocurrio un error eliminando la Empresa.");
        }
    }

    @FXML
    private void onActionJfxBtnCancelar(ActionEvent event
    ) {
    }

    private void bindEmpresa(Boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bind(empresa.id);
        }
        txtNombre.textProperty().bindBidirectional(empresa.nombre);
        txtCedulaJuridica.textProperty().bindBidirectional(empresa.cedulaJuridica);
        txtTelefono.textProperty().bindBidirectional(empresa.telefono);
        txtEmail.textProperty().bindBidirectional(empresa.email);
        txtAnioFundacion.textProperty().bindBidirectional(empresa.anioFundacion);
    }

    private void unbindEmpresa() {
        txtId.textProperty().unbind();
        txtNombre.textProperty().unbindBidirectional(empresa.nombre);
        txtCedulaJuridica.textProperty().unbindBidirectional(empresa.cedulaJuridica);
        txtTelefono.textProperty().unbindBidirectional(empresa.telefono);
        txtEmail.textProperty().unbindBidirectional(empresa.email);
        txtAnioFundacion.textProperty().unbindBidirectional(empresa.anioFundacion);
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
