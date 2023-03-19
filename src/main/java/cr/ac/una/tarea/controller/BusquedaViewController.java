/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class BusquedaViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane rootBusqueda;
    @FXML
    private VBox vbxBusqueda;
    @FXML
    private JFXButton jfxBtnFiltrar;
    @FXML
    private TableView tbvResultados;
    @FXML
    private JFXButton jfxBtnAceptar;

    private EventHandler<KeyEvent> keyEnter;
    private ObservableList<Tour> tours = FXCollections.observableArrayList();
    Object resultado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        keyEnter = ((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                //btnFiltrar;
            }
        });
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnAceptar(ActionEvent event) {
        resultado = tbvResultados.getSelectionModel().getSelectedItem();
        //getStage().getScene().setRoot(new Pane());
        getStage().close();
    }

    @FXML
    private void onMousePressenTbvResultados(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            onActionJfxBtnAceptar(null);
        }
    }

    public Object getResultado() {
        return resultado;
    }

    private void cargarTours(String nombre, String empresa, String categoria, String precio) {
        tours.clear();
        tours.addAll((List<Tour>) AppContext.getInstance().get("ToursLista"));
        if (tours != null) {
            tbvResultados.setItems(tours);
            tbvResultados.refresh();
            for (Tour tour : tours) {
                System.out.println(tour.getNombre() + "Hola ");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empleados", getStage(), "Error cargando los Tours");
        }
    }

    public void busquedaTours() {
        try {
            TextField txtCedula = new TextField();
            txtCedula.setPromptText("Nombre");
            txtCedula.setOnKeyPressed(keyEnter);
            txtCedula.setTextFormatter(Formato.getInstance().cedulaFormat(40));

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Empresa");
            txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
            txtNombre.setOnKeyPressed(keyEnter);

            TextField txtPApellido = new TextField();
            txtPApellido.setPromptText("Primer Apellido");
            txtPApellido.setTextFormatter(Formato.getInstance().letrasFormat(15));

            TextField txtSApellido = new TextField();
            txtSApellido.setPromptText("Segundo Apellido");
            txtSApellido.setTextFormatter(Formato.getInstance().letrasFormat(15));

            vbxBusqueda.getChildren().clear();
            vbxBusqueda.getChildren().add(txtCedula);
            vbxBusqueda.getChildren().add(txtNombre);
            vbxBusqueda.getChildren().add(txtPApellido);
            vbxBusqueda.getChildren().add(txtSApellido);

            tbvResultados.getColumns().clear();
            tbvResultados.getItems().clear();

            TableColumn<Tour, String> tbcId = new TableColumn<>("Nombre");
            tbcId.setPrefWidth(100);
            tbcId.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getNombre()));

            TableColumn<Tour, String> tbcCedula = new TableColumn<>("Empresa");
            tbcCedula.setPrefWidth(100);
            tbcCedula.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getEmpresa().getNombre()));

            TableColumn<Tour, String> tbcNombre = new TableColumn<>("Categoria");
            tbcNombre.setPrefWidth(100);
            tbcNombre.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCategoria().getNombre()));

//            TableColumn<Tour,String> tbcPApellido = new TableColumn<>("Precio");
//            tbcPApellido.setPrefWidth(50);
//            tbcPApellido.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().precio.toString()));
//
//            TableColumn<Tour,String> tbcSApellido = new TableColumn<>("Cupos Disponibles");
//            tbcSApellido.setPrefWidth(50);
//            tbcSApellido.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().cuposDisponibles.toString()));
            tbvResultados.getColumns().add(tbcId);
            tbvResultados.getColumns().add(tbcCedula);
            tbvResultados.getColumns().add(tbcNombre);
//            tbvResultados.getColumns().add(tbcPApellido);
//            tbvResultados.getColumns().add(tbcSApellido);
            tbvResultados.refresh();

            jfxBtnFiltrar.setOnAction((ActionEvent event) -> {
                cargarTours(null, null, null, null);
                System.out.println("Entra");
            });

            cargarTours(null, null, null, null);

        } catch (Exception ex) {
            Logger.getLogger(BusquedaViewController.class.getName()).log(Level.SEVERE, "Error consultando los empleado", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar empleado", getStage(), "Ocurrio un error consultando los empleados");
        }
    }

}
