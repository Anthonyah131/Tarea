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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class BusquedaViewController extends Controller implements Initializable { // Crea dinamicamente las busquedas

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
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnAceptar(ActionEvent event) {
        resultado = tbvResultados.getSelectionModel().getSelectedItem();
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

    public void SetResultado() {
        resultado = null;
    }

    private void cargarTours(Long id, String nombre, String empresa, String categoria, String cliente) {
        tours.clear();
        tours.addAll((List<Tour>) AppContext.getInstance().get("ToursLista"));
        if (tours != null) {
            if (id != null) {
                tours.removeIf(c -> !c.getId().equals(id));
            }
            if (!nombre.isEmpty()) {
                tours.removeIf(c -> !c.getNombre().toLowerCase().contains(nombre.toLowerCase()));
            }
            if (!empresa.isEmpty()) {
                tours.removeIf(c -> !c.getEmpresa().getNombre().toLowerCase().contains(empresa.toLowerCase()));
            }
            if (!categoria.isEmpty()) {
                tours.removeIf(c -> !c.getCategoria().getNombre().toLowerCase().contains(categoria.toLowerCase()));
            }
            if (!cliente.isEmpty()) {
                tours.removeIf(tour -> !tour.getClientes().stream().anyMatch(c -> c.getNombre().toLowerCase().contains(cliente.toLowerCase())));
            }

            tbvResultados.setItems(tours);
            tbvResultados.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Tours", getStage(), "Error cargando los Tours");
        }
    }

    private void cargarCategorias(Long id, String nombre) {
        categorias.clear();
        categorias.addAll((List<Categoria>) AppContext.getInstance().get("CategoriasLista"));
        if (categorias != null) {
            if (id != null && !nombre.isEmpty()) {
                categorias.removeIf(c -> !c.getId().equals(id) && !c.getNombre().toLowerCase().contains(nombre.toLowerCase()));
            } else if (id != null) {
                categorias.removeIf(c -> !c.getId().equals(id));
            } else if (!nombre.isEmpty()) {
                categorias.removeIf(c -> !c.getNombre().toLowerCase().contains(nombre.toLowerCase()));
            }

            tbvResultados.setItems(categorias);
            tbvResultados.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Categorias", getStage(), "Error cargando las Categorias");
        }
    }

    private void cargarClientes(Long id, String nombre, String apellidos, String cedula) {
        clientes.clear();
        clientes.addAll((List<Cliente>) AppContext.getInstance().get("ClientesLista"));
        if (clientes != null) {
            if (id != null) {
                clientes.removeIf(c -> !c.getId().equals(id));
            }
            if (!nombre.isEmpty()) {
                clientes.removeIf(c -> !c.getNombre().toLowerCase().contains(nombre.toLowerCase()));
            }
            if (!apellidos.isEmpty()) {
                clientes.removeIf(c -> !c.getApellido().toLowerCase().contains(apellidos.toLowerCase()));
            }
            if (!cedula.isEmpty()) {
                clientes.removeIf(c -> !c.getCedula().toLowerCase().contains(cedula.toLowerCase()));
            }

            tbvResultados.setItems(clientes);
            tbvResultados.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Cliente", getStage(), "Error cargando los Clientes");
        }
    }

    private void cargarEmpresas(Long id, String nombre, String cedula) {
        empresas.clear();
        empresas.addAll((List<Empresa>) AppContext.getInstance().get("EmpresasLista"));
        if (empresas != null) {
            if (id != null) {
                empresas.removeIf(c -> !c.getId().equals(id));
            }
            if (!nombre.isEmpty()) {
                empresas.removeIf(c -> !c.getNombre().toLowerCase().contains(nombre.toLowerCase()));
            }
            if (!cedula.isEmpty()) {
                empresas.removeIf(c -> !c.getCedulaJuridica().toLowerCase().contains(cedula.toLowerCase()));
            }

            tbvResultados.setItems(empresas);
            tbvResultados.refresh();
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

            TextField txtEmpresa = new TextField();
            txtEmpresa.setPromptText("Empresa");

            TextField txtCategoria = new TextField();
            txtCategoria.setPromptText("Categoria");

            TextField txtCliente = new TextField();
            txtCliente.setPromptText("Cliente");

            vbxBusqueda.getChildren().clear();
            vbxBusqueda.getChildren().add(txtId);
            vbxBusqueda.getChildren().add(txtNombre);
            vbxBusqueda.getChildren().add(txtEmpresa);
            vbxBusqueda.getChildren().add(txtCategoria);
            vbxBusqueda.getChildren().add(txtCliente);

            tbvResultados.getColumns().clear();
            tbvResultados.getItems().clear();

            TableColumn<Tour, String> tbcId = new TableColumn<>("Id");
            tbcId.setPrefWidth(25);
            tbcId.setCellValueFactory(cd -> cd.getValue().id);

            TableColumn<Tour, String> tbcNombre = new TableColumn<>("Nombre");
            tbcNombre.setPrefWidth(100);
            tbcNombre.setCellValueFactory(cd -> cd.getValue().nombre);

            TableColumn<Tour, String> tbcEmpresa = new TableColumn<>("Empresa");
            tbcEmpresa.setPrefWidth(100);
            tbcEmpresa.setCellValueFactory(cd -> cd.getValue().getEmpresa().nombre);

            TableColumn<Tour, String> tbcCategoria = new TableColumn<>("Categoria");
            tbcCategoria.setPrefWidth(100);
            tbcCategoria.setCellValueFactory(cd -> cd.getValue().getCategoria().nombre);

            tbvResultados.getColumns().add(tbcId);
            tbvResultados.getColumns().add(tbcNombre);
            tbvResultados.getColumns().add(tbcEmpresa);
            tbvResultados.getColumns().add(tbcCategoria);
            tbvResultados.refresh();

            jfxBtnFiltrar.setOnAction((ActionEvent event) -> {
                if (!txtId.getText().isEmpty() || !txtNombre.getText().isEmpty() || !txtEmpresa.getText().isEmpty() || !txtCategoria.getText().isEmpty() || !txtCliente.getText().isEmpty()) {
                    cargarTours((!txtId.getText().isEmpty() ? Long.valueOf(txtId.getText()) : null), txtNombre.getText(), txtEmpresa.getText(), txtCategoria.getText(), txtCliente.getText());
                } else {
                    cargarTours(null, "", "", "", "");
                }
            });

            cargarTours(null, "", "", "", "");

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
                if (!txtId.getText().isEmpty() || !txtNombre.getText().isEmpty()) {
                    cargarCategorias((!txtId.getText().isEmpty() ? Long.valueOf(txtId.getText()) : null), txtNombre.getText());
                } else {
                    cargarCategorias(null, "");
                }
            });

            cargarCategorias(null, "");

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
            txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(40));

            TextField txtApellidos = new TextField();
            txtApellidos.setPromptText("Apellidos");
            txtApellidos.setTextFormatter(Formato.getInstance().letrasFormat(40));

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
                if (!txtId.getText().isEmpty() || !txtNombre.getText().isEmpty() || !txtApellidos.getText().isEmpty() || !txtCedula.getText().isEmpty()) {
                    cargarClientes((!txtId.getText().isEmpty() ? Long.valueOf(txtId.getText()) : null), txtNombre.getText(), txtApellidos.getText(), txtCedula.getText());
                } else {
                    cargarClientes(null, "", "", "");
                }
            });

            cargarClientes(null, "", "", "");

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
                if (!txtId.getText().isEmpty() || !txtNombre.getText().isEmpty() || !txtCedula.getText().isEmpty()) {
                    cargarEmpresas((!txtId.getText().isEmpty() ? Long.valueOf(txtId.getText()) : null), txtNombre.getText(), txtCedula.getText());
                } else {
                    cargarEmpresas(null, "", "");
                }
            });

            cargarEmpresas(null, "", "");

        } catch (Exception ex) {
            Logger.getLogger(BusquedaViewController.class.getName()).log(Level.SEVERE, "Error consultando las Empresas", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar Empresa", getStage(), "Ocurrio un error consultando las Empresas");
        }
    }
}
