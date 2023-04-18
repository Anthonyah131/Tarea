/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import cr.ac.una.tarea.model.Empresa;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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

    Empresa empresa;
    Image logo;
    List<Node> requeridos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCedulaJuridica.setTextFormatter(Formato.getInstance().cedulaFormat(50));
        txtTelefono.setTextFormatter(Formato.getInstance().integerFormat());
        txtAnioFundacion.setTextFormatter(Formato.getInstance().integerFormat());
        empresa = new Empresa();
        nuevoEmpresa();
        indicarRequeridos();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnBuscarLogo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            logo = new Image("file:" + file.getAbsolutePath());
            imgLogo.setImage(logo);
        }
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
        } else {
            empresa = new Empresa(empresa);
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
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Categoria", getStage(), invalidos);
            } else {
                Boolean banderaNuevo = true;
                ObservableList<Empresa> empresas = (ObservableList<Empresa>) AppContext.getInstance().get("EmpresasLista");
                if (empresa.getId() != null) {
                    for (Empresa empre : empresas) {
                        if (Objects.equals(empre.getId(), empresa.getId())) {
                            if (logo != null) {
                                empresa.setLogo(logo);
                            }
                            empre.setEmpresa(empresa);
                            banderaNuevo = false;
                        }
                    }
                }

                if (banderaNuevo) {
                    Long contador[] = (Long[]) AppContext.getInstance().get("Contador");
                    contador[2]++;
                    empresa.setId(contador[2]);
                    empresa.setLogo(logo);
                    empresas.add(empresa);
                }
                nuevoEmpresa();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Empresa", getStage(), "Empresa actualizado correctamente.");
            }
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

    private void bindEmpresa(Boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bind(empresa.id);
        }
        txtNombre.textProperty().bindBidirectional(empresa.nombre);
        txtCedulaJuridica.textProperty().bindBidirectional(empresa.cedulaJuridica);
        txtTelefono.textProperty().bindBidirectional(empresa.telefono);
        txtEmail.textProperty().bindBidirectional(empresa.email);
        txtAnioFundacion.textProperty().bindBidirectional(empresa.anioFundacion);
        imgLogo.imageProperty().bindBidirectional(empresa.logo);
    }

    private void unbindEmpresa() {
        txtId.textProperty().unbind();
        txtNombre.textProperty().unbindBidirectional(empresa.nombre);
        txtCedulaJuridica.textProperty().unbindBidirectional(empresa.cedulaJuridica);
        txtTelefono.textProperty().unbindBidirectional(empresa.telefono);
        txtEmail.textProperty().unbindBidirectional(empresa.email);
        txtAnioFundacion.textProperty().unbindBidirectional(empresa.anioFundacion);
        imgLogo.imageProperty().unbindBidirectional(empresa.logo);
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

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombre, txtCedulaJuridica, txtAnioFundacion, txtEmail));
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
