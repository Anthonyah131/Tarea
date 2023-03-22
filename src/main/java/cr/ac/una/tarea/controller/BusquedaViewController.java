/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.model.Cliente;
import cr.ac.una.tarea.model.Empresa;
import cr.ac.una.tarea.model.Itinerario;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
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
    private ObservableList<Categoria> categorias = FXCollections.observableArrayList();
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private ObservableList<Empresa> empresas = FXCollections.observableArrayList();
    private ObservableList<Itinerario> itinerarios = FXCollections.observableArrayList();
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
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Tours", getStage(), "Error cargando los Tours");
        }
    }
    
    private void cargarCategorias() {
        categorias.clear();
        categorias.addAll((List<Categoria>) AppContext.getInstance().get("CategoriasLista"));
        if (tours != null) {
            tbvResultados.setItems(categorias);
            tbvResultados.refresh();
            for (Categoria categoria : categorias) {
                System.out.println(categoria.getNombre() + "Hola ");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Categorias", getStage(), "Error cargando las Categorias");
        }
    }
    
    private void cargarClientes() {
        clientes.clear();
        clientes.addAll((List<Cliente>) AppContext.getInstance().get("ClientesLista"));
        if (clientes != null) {
            tbvResultados.setItems(clientes);
            tbvResultados.refresh();
            for (Cliente cliente : clientes) {
                System.out.println(cliente.getNombre() + "Hola ");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Cliente", getStage(), "Error cargando los Clientes");
        }
    }
    
    private void cargarEmpresas() {
        empresas.clear();
        empresas.addAll((List<Empresa>) AppContext.getInstance().get("EmpresasLista"));
        if (empresas != null) {
            tbvResultados.setItems(empresas);
            tbvResultados.refresh();
            for (Empresa empresa : empresas) {
                System.out.println(empresa.getNombre() + "Hola ");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Empresa", getStage(), "Error cargando las Empresas");
        }
    }
    
    public void busquedaTours() {
        try {
            TextField txtId = new TextField();
            txtId.setPromptText("Id");
            txtId.setTextFormatter(Formato.getInstance().integerFormat());

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Nombre");
            txtNombre.setOnKeyPressed(keyEnter);
            txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(40));

            TextField txtEmpresa = new TextField();
            txtEmpresa.setPromptText("Empresa");
            txtEmpresa.setTextFormatter(Formato.getInstance().letrasFormat(40));
            txtEmpresa.setOnKeyPressed(keyEnter);

            TextField txtCategoria = new TextField();
            txtCategoria.setPromptText("Categoria");
            txtCategoria.setTextFormatter(Formato.getInstance().letrasFormat(40));

            vbxBusqueda.getChildren().clear();
            vbxBusqueda.getChildren().add(txtId);
            vbxBusqueda.getChildren().add(txtNombre);
            vbxBusqueda.getChildren().add(txtEmpresa);
            vbxBusqueda.getChildren().add(txtCategoria);

            tbvResultados.getColumns().clear();
            tbvResultados.getItems().clear();

            TableColumn<Tour, String> tbcId = new TableColumn<>("Id");
            tbcId.setPrefWidth(25);
            tbcId.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getId().toString()));

            TableColumn<Tour, String> tbcNombre = new TableColumn<>("Nombre");
            tbcNombre.setPrefWidth(100);
            tbcNombre.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getNombre()));

            TableColumn<Tour, String> tbcEmpresa = new TableColumn<>("Empresa");
            tbcEmpresa.setPrefWidth(100);
            tbcEmpresa.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getEmpresa().getNombre()));

            TableColumn<Tour, String> tbcCategoria = new TableColumn<>("Categoria");
            tbcCategoria.setPrefWidth(100);
            tbcCategoria.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCategoria().getNombre()));

            tbvResultados.getColumns().add(tbcId);
            tbvResultados.getColumns().add(tbcNombre);
            tbvResultados.getColumns().add(tbcEmpresa);
            tbvResultados.getColumns().add(tbcCategoria);
            tbvResultados.refresh();

            jfxBtnFiltrar.setOnAction((ActionEvent event) -> {
                cargarTours(null, null, null, null);
                System.out.println("Entra");
            });

            cargarTours(null, null, null, null);

        } catch (Exception ex) {
            Logger.getLogger(BusquedaViewController.class.getName()).log(Level.SEVERE, "Error consultando los tours", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar tour", getStage(), "Ocurrio un error consultando los tours");
        }
    }

    public void busquedaCategoria() {
        try {
            TextField txtId = new TextField();
            txtId.setPromptText("Id");
            txtId.setTextFormatter(Formato.getInstance().integerFormat());

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Nombre");
            txtNombre.setOnKeyPressed(keyEnter);
            txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(40));

            vbxBusqueda.getChildren().clear();
            vbxBusqueda.getChildren().add(txtId);
            vbxBusqueda.getChildren().add(txtNombre);

            tbvResultados.getColumns().clear();
            tbvResultados.getItems().clear();

            TableColumn<Categoria, String> tbcId = new TableColumn<>("Id");
            tbcId.setPrefWidth(25);
            tbcId.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getId().toString()));

            TableColumn<Categoria, String> tbcNombre = new TableColumn<>("Nombre");
            tbcNombre.setPrefWidth(100);
            tbcNombre.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getNombre()));

            tbvResultados.getColumns().add(tbcId);
            tbvResultados.getColumns().add(tbcNombre);
            tbvResultados.refresh();

            jfxBtnFiltrar.setOnAction((ActionEvent event) -> {
                cargarCategorias();
                System.out.println("Entra");
            });

            cargarCategorias();
            
        } catch (Exception ex) {
            Logger.getLogger(BusquedaViewController.class.getName()).log(Level.SEVERE, "Error consultando las Categorias", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar Categoria", getStage(), "Ocurrio un error consultando las Categorias");
        }
    }
    
    public void busquedaCliente() {
        try {
            TextField txtId = new TextField();
            txtId.setPromptText("Id");
            txtId.setTextFormatter(Formato.getInstance().integerFormat());

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Nombre");
            txtNombre.setOnKeyPressed(keyEnter);
            txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(40));

            TextField txtApellidos = new TextField();
            txtApellidos.setPromptText("Apellidos");
            txtApellidos.setTextFormatter(Formato.getInstance().letrasFormat(40));
            txtApellidos.setOnKeyPressed(keyEnter);

            TextField txtCedula = new TextField();
            txtCedula.setPromptText("Cedula");
            txtCedula.setTextFormatter(Formato.getInstance().cedulaFormat(15));

            vbxBusqueda.getChildren().clear();
            vbxBusqueda.getChildren().add(txtId);
            vbxBusqueda.getChildren().add(txtNombre);
            vbxBusqueda.getChildren().add(txtApellidos);
            vbxBusqueda.getChildren().add(txtCedula);

            tbvResultados.getColumns().clear();
            tbvResultados.getItems().clear();

            TableColumn<Cliente, String> tbcId = new TableColumn<>("Id");
            tbcId.setPrefWidth(25);
            tbcId.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getId().toString()));

            TableColumn<Cliente, String> tbcNombre = new TableColumn<>("Nombre");
            tbcNombre.setPrefWidth(100);
            tbcNombre.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getNombre()));

            TableColumn<Cliente, String> tbcApellidos = new TableColumn<>("Apellidos");
            tbcApellidos.setPrefWidth(150);
            tbcApellidos.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getApellido()));

            TableColumn<Cliente, String> tbcCedula = new TableColumn<>("Cedula");
            tbcCedula.setPrefWidth(100);
            tbcCedula.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCedula()));

            tbvResultados.getColumns().add(tbcId);
            tbvResultados.getColumns().add(tbcNombre);
            tbvResultados.getColumns().add(tbcApellidos);
            tbvResultados.getColumns().add(tbcCedula);
            tbvResultados.refresh();

            jfxBtnFiltrar.setOnAction((ActionEvent event) -> {
                cargarClientes();
                System.out.println("Entra");
            });

            cargarClientes();

        } catch (Exception ex) {
            Logger.getLogger(BusquedaViewController.class.getName()).log(Level.SEVERE, "Error consultando los Clientes", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar Cliente", getStage(), "Ocurrio un error consultando los Clientes");
        }
    }
    
    public void busquedaEmpresa() {
        try {
            TextField txtId = new TextField();
            txtId.setPromptText("Id");
            txtId.setTextFormatter(Formato.getInstance().integerFormat());

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Nombre");
            txtNombre.setOnKeyPressed(keyEnter);
            txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(40));

            TextField txtCedula = new TextField();
            txtCedula.setPromptText("Cedula Juridica");
            txtCedula.setTextFormatter(Formato.getInstance().cedulaFormat(15));

            vbxBusqueda.getChildren().clear();
            vbxBusqueda.getChildren().add(txtId);
            vbxBusqueda.getChildren().add(txtNombre);
            vbxBusqueda.getChildren().add(txtCedula);

            tbvResultados.getColumns().clear();
            tbvResultados.getItems().clear();

            TableColumn<Empresa, String> tbcId = new TableColumn<>("Id");
            tbcId.setPrefWidth(25);
            tbcId.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getId().toString()));

            TableColumn<Empresa, String> tbcNombre = new TableColumn<>("Nombre");
            tbcNombre.setPrefWidth(100);
            tbcNombre.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getNombre()));

            TableColumn<Empresa, String> tbcCedula = new TableColumn<>("Cedula");
            tbcCedula.setPrefWidth(100);
            tbcCedula.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCedulaJuridica()));

            tbvResultados.getColumns().add(tbcId);
            tbvResultados.getColumns().add(tbcNombre);
            tbvResultados.getColumns().add(tbcCedula);
            tbvResultados.refresh();

            jfxBtnFiltrar.setOnAction((ActionEvent event) -> {
                cargarEmpresas();
                System.out.println("Entra");
            });

            cargarEmpresas();

        } catch (Exception ex) {
            Logger.getLogger(BusquedaViewController.class.getName()).log(Level.SEVERE, "Error consultando las Empresas", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar Empresa", getStage(), "Ocurrio un error consultando las Empresas");
        }
    }
}
