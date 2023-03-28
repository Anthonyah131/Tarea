/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.model.Cliente;
import cr.ac.una.tarea.model.Empresa;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.ImageSwitcher;
import cr.ac.una.tarea.util.Mensaje;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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
    @FXML
    private TabPane tabTour;
    @FXML
    private Tab tbpTour;
    @FXML
    private Tab tbpClientes;
    @FXML
    private TableView<Cliente> tbvClientes;

    Tour tour;

    ObservableList<Empresa> empresas = FXCollections.observableArrayList();
    ObservableList<Categoria> categorias = FXCollections.observableArrayList();
    ImageSwitcher switcher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPrecio.setTextFormatter(Formato.getInstance().integerFormat());
        txtCuposTotales.setTextFormatter(Formato.getInstance().integerFormat());
        //txtDisponibles.setTextFormatter(Formato.getInstance().integerFormat());
        jfxTxaItinerario.setTextFormatter(Formato.getInstance().letrasFormat(50));
        tour = new Tour();
        nuevoTour();

        categorias.addAll((List<Categoria>) AppContext.getInstance().get("CategoriasLista"));
        empresas.addAll((List<Empresa>) AppContext.getInstance().get("EmpresasLista"));

        jfxCbxCategoria.setItems(categorias);
        jfxCbxEmpresa.setItems(empresas);

        tbvClientes.getColumns().clear();
        tbvClientes.getItems().clear();

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

        tbvClientes.getColumns().add(tbcId);
        tbvClientes.getColumns().add(tbcNombre);
        tbvClientes.getColumns().add(tbcApellidos);
        tbvClientes.getColumns().add(tbcCedula);
        tbvClientes.refresh();
    }

    @Override
    public void initialize() {
        limpiarCBX();
    }

    @FXML
    private void onActionBtnBuscarFotos(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();//Instancia el buscador de archivo
        fileChooser.setTitle("Buscar Imagen");//Le pone un titulo a la ventala del buscador

        //Filtra la busqueda utilizando las extanciones jpg y png
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

        //trae la imagen
        List<File> file = fileChooser.showOpenMultipleDialog(null);
        if (!file.isEmpty()) {
            tour.getFotos().clear();
            for (File fi : file) {
                tour.getFotos().add(new Image("file:" + fi.getAbsolutePath()));
            }
            switcher = new ImageSwitcher(tour.getFotos(), imgFotos);
            switcher.start();
        }
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
            if (switcher != null) {
                switcher.stop();
            }
            imgFotos.setImage(null);
            jfxCbxCategoria.setValue(tour.categoria);
            jfxCbxEmpresa.setValue(tour.empresa);
            cargarClientes();
            switcher = new ImageSwitcher(tour.getFotos(), imgFotos);
            switcher.start();
        } else {
            tour = new Tour();
        }
        unbindTour();
        bindTour(false);
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Tour", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            switcher.stop();
            imgFotos.setImage(null);
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
                            }
                        }
                        for (Categoria cat : categorias) {
                            if (Objects.equals(jfxCbxCategoria.getValue().getId(), cat.getId())) {
                                tour.setCategoria(cat);
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
                    }
                }
                for (Categoria cat : categorias) {
                    if (Objects.equals(jfxCbxCategoria.getValue().getId(), cat.getId())) {
                        tour.setCategoria(cat);
                    }
                }
                contador[3]++;
                tour.setCuposDisponibles(Long.valueOf(txtCuposTotales.getText()));
                tour.setId(contador[3]);
                tours.add(tour);
            }
            switcher.stop();
            imgFotos.setImage(null);
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
                for (int i = 0; i < tours.size(); i++) {
                    if (Objects.equals(tours.get(i).getId(), tour.getId())) {
                        tours.remove(tours.get(i));
                    }
                }
                if (switcher != null) {
                    switcher.stop();
                }
                imgFotos.setImage(null);
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
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaTours();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
        tour = (Tour) busquedaController.getResultado();
        busquedaController.SetResultado();
        limpiarCBX();
        if (tour != null) {
            if (switcher != null) {
                switcher.stop();
            }
            switcher.start();
        } else {
            tour = new Tour();
        }
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

    @FXML
    private void onSelectionChangedTbpClientes(Event event) {
        if (tbpClientes.isSelected()) {
            if (tour.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Tour", getStage(), "Debe cargar el Tour del cúal desea ver los clientes.");
                tabTour.getSelectionModel().select(tbpTour);
            }
        }
    }
    
    private void cargarClientes() {
        if (tour != null) {
            ObservableList<Cliente> clientes = FXCollections.observableArrayList();
            for(Cliente cli : tour.getClientes())
                clientes.add(cli);
            tbvClientes.setItems(clientes);
            tbvClientes.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Cliente", getStage(), "Error cargando los Clientes");
        }
    }

}
