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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        nuevoTour();
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaTours();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
        tour = (Tour) busquedaController.getResultado();
        busquedaController.SetResultado();
        limpiarCBX();
        if (tour != null) {
            jfxCbxCategoria.setValue(tour.categoria);
            jfxCbxEmpresa.setValue(tour.empresa);
        } else {
            tour = new Tour();
        }
        unbindTour();
        bindTour(false);

    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Tour", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            limpiarCBX();
            nuevoTour();
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            Boolean banderaNuevo = true;
            ObservableList<Tour> tours = (ObservableList<Tour>) AppContext.getInstance().get("ToursLista");
            if (tour.getId() != null) {
                for (Tour tou : tours) {
                    if (Objects.equals(tou.getId(), tour.getId())) {
                        for (Empresa empr : empresas) {
                            if (Objects.equals(jfxCbxEmpresa.getValue().getId(), empr.getId())) {
                                tour.setEmpresa(empr);
                                System.out.println("Entra" + empr);
                            }
                        }
                        for (Categoria cat : categorias) {
                            if (Objects.equals(jfxCbxCategoria.getValue().getId(), cat.getId())) {
                                tour.setCategoria(cat);
                                System.out.println("Entra" + cat);
                            }
                        }
                        tou = tour;
                        banderaNuevo = false;
                    }
                }
            }
            if (banderaNuevo) {
                Long contador[] = (Long[]) AppContext.getInstance().get("Contador");
                for (Empresa empr : empresas) {
                    if (Objects.equals(jfxCbxEmpresa.getValue().getId(), empr.getId())) {
                        tour.setEmpresa(empr);
                        System.out.println("Entra" + empr);
                    }
                }
                for (Categoria cat : categorias) {
                    if (Objects.equals(jfxCbxCategoria.getValue().getId(), cat.getId())) {
                        tour.setCategoria(cat);
                        System.out.println("Entra" + cat);
                    }
                }
                contador[3]++;
                tour.setId(contador[3]);
                tours.add(tour);
            }
            for (Tour tou : tours) {
                System.out.println(tou.getEmpresa());
            }
            limpiarCBX();
            nuevoTour();
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Tour", getStage(), "Tour actualizado correctamente.");

        } catch (Exception ex) {
            Logger.getLogger(MantCatViewController.class.getName()).log(Level.SEVERE, "Error guardando el Tour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tour", getStage(), "Ocurrio un error guardando el Tour.");
        }
    }

    @FXML
    private void onActionJfxBtnEliminar(ActionEvent event) {
        try {
            if (tour.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tour", getStage(), "Debe cargar la Tour que desea eliminar.");
            } else {
                ObservableList<Tour> tours = (ObservableList<Tour>) AppContext.getInstance().get("ToursLista");
//                for (Categoria cat : categorias) {
                for (int i = 0; i < tours.size(); i++) {
//                    if (Objects.equals(cat.getId(), categoria.getId())) {
//                        categorias.remove(cat);
//                    }
                    if (Objects.equals(tours.get(i).getId(), tour.getId())) {
                        tours.remove(tours.get(i));
                    }
                }
                nuevoTour();
                limpiarCBX();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Tour", getStage(), "Tour eliminado correctamente.");
            }
        } catch (Exception ex) {
            Logger.getLogger(MantCatViewController.class.getName()).log(Level.SEVERE, "Error eliminando la Tour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tour", getStage(), "Ocurrio un error eliminando la Tour.");
        }
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
        dpFechaSalida.valueProperty().bindBidirectional(tour.fechaSalida);
        dpFechaRegreso.valueProperty().bindBidirectional(tour.fechaRegreso);
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

        categorias = FXCollections.observableArrayList();
        empresas = FXCollections.observableArrayList();

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
