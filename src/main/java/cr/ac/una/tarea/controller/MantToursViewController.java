/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private JFXComboBox<?> jfxCbxEmpresa;
    @FXML
    private JFXComboBox<?> jfxCbxCategoria;
    @FXML
    private DatePicker dpFechaSalida;
    @FXML
    private DatePicker dpFechaRegreso;
    @FXML
    private TextField txtDuracion;
    @FXML
    private JFXButton jfxBtnClientes;
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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnClientes(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBuscarFotos(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnBuscar(ActionEvent event) {
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaTours();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(),true);
        Tour tour = (Tour) busquedaController.getResultado();
        if (tour != null) {
            System.out.println(tour.getNombre());
        }
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

}
