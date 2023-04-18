/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.model.Cliente;
import cr.ac.una.tarea.model.Empresa;
import cr.ac.una.tarea.model.Itinerario;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.ImageSwitcher;
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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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
    private TabPane tabTour;
    @FXML
    private Tab tbpTour;
    @FXML
    private Tab tbpClientes;
    @FXML
    private TableView<Cliente> tbvClientes;
    @FXML
    private Tab tbpItinerarios;
    @FXML
    private TextField txtIdItinerario;
    @FXML
    private TextField txtLugar;
    @FXML
    private TextField txtDuración;
    @FXML
    private TextField txtOrden;
    @FXML
    private TextField txtLatitud;
    @FXML
    private TextField txtLongitud;
    @FXML
    private DatePicker dpItinerarioSalida;
    @FXML
    private DatePicker dpItinerarioLlegada;
    @FXML
    private TableView<Itinerario> tbvItinerarios;
    @FXML
    private JFXButton jfxBtnGuardarIti;
    @FXML
    private JFXButton jfxBtnNuevoIti;

    Tour tour;
    List<Node> requeridosTour = new ArrayList<>();
    Itinerario itinerario;
    List<Node> requeridosIti = new ArrayList<>();

    ObservableList<Empresa> empresas = FXCollections.observableArrayList();
    ObservableList<Categoria> categorias = FXCollections.observableArrayList();
    ImageSwitcher switcher;
    @FXML
    private Label lbImagenes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPrecio.setTextFormatter(Formato.getInstance().integerFormat());
        txtCuposTotales.setTextFormatter(Formato.getInstance().integerFormat());
        tour = new Tour();
        itinerario = new Itinerario();
        nuevoItinerario();
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

        tbvItinerarios.getColumns().clear();
        tbvItinerarios.getItems().clear();

        TableColumn<Itinerario, String> tbcIdIti = new TableColumn<>("Id");
        tbcIdIti.setPrefWidth(25);
        tbcIdIti.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getId().toString()));

        TableColumn<Itinerario, String> tbcLugarIti = new TableColumn<>("Lugar");
        tbcLugarIti.setPrefWidth(100);
        tbcLugarIti.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getLugar()));

        TableColumn<Itinerario, String> tbcDuracionIti = new TableColumn<>("Duración");
        tbcDuracionIti.setPrefWidth(50);
        tbcDuracionIti.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getDuracionEnLugar()));

        TableColumn<Itinerario, String> tbcOrdenIti = new TableColumn<>("Orden");
        tbcOrdenIti.setPrefWidth(50);
        tbcOrdenIti.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getOrden()));

        TableColumn<Itinerario, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellValueFactory(cd -> new SimpleObjectProperty(cd.getValue() != null));
        tbcEliminar.setCellFactory(cd -> new ButtonCell());

        tbvItinerarios.getColumns().add(tbcIdIti);
        tbvItinerarios.getColumns().add(tbcLugarIti);
        tbvItinerarios.getColumns().add(tbcDuracionIti);
        tbvItinerarios.getColumns().add(tbcOrdenIti);
        tbvItinerarios.getColumns().add(tbcEliminar);
        tbvItinerarios.refresh();

        tbvItinerarios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindItinerario();
                itinerario = new Itinerario(newValue);
                bindItinerario(false);
            } else {
                nuevoItinerario();
            }
        });

        indicarRequeridos();

        imgFotos.setOnDragOver(event -> { // El drag and drop en las imagenes funciona correctamente
            if (event.getGestureSource() != imgFotos && event.getDragboard().hasFiles()) {
                for (File file : event.getDragboard().getFiles()) {
                    String extension = getFileExtension(file);
                    if (extension != null && (extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg"))) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                }
            }
            event.consume();
        });

        lbImagenes.setWrapText(true);

        imgFotos.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                tour.getFotos().clear();
                for (File file : db.getFiles()) {
                    String extension = getFileExtension(file);
                    if (extension != null && (extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg"))) {
                        Image image = new Image(file.toURI().toString());
                        tour.getFotos().add(image);
                        success = true;
                    }
                }
                if (switcher != null) {
                    switcher.stop();
                }
                switcher = new ImageSwitcher(tour.getFotos(), imgFotos);
                switcher.start();
            }
            event.setDropCompleted(success);
            event.consume();
        });

    }

    @Override
    public void initialize() {
        limpiarCBX();
    }

    @FXML
    private void onActionBtnBuscarFotos(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

        List<File> file;
        file = fileChooser.showOpenMultipleDialog(null);
        if (file != null && !file.isEmpty()) {
            tour.getFotos().clear();
            for (File fi : file) {
                tour.getFotos().add(new Image("file:" + fi.getAbsolutePath()));
            }
            if (switcher != null) {
                switcher.stop();
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
            tour = new Tour(tour);
            if (switcher != null) {
                switcher.stop();
            }
            imgFotos.setImage(null);
            jfxCbxCategoria.setValue(tour.categoria);
            jfxCbxEmpresa.setValue(tour.empresa);
            cargarClientes();
            cargarItinerarios();
            switcher = new ImageSwitcher(tour.getFotos(), imgFotos);
            switcher.start();
        } else {
            tour = new Tour();
            itinerario = new Itinerario();
        }
        unbindItinerario();
        bindItinerario(true);
        unbindTour();
        bindTour(false);
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Tour", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            if (switcher != null) {
                switcher.stop();
            }
            imgFotos.setImage(null);
            limpiarCBX();
            nuevoTour();
            nuevoItinerario();
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos(requeridosTour);
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Categoria", getStage(), invalidos);
            } else {
                if (0 < dpFechaRegreso.getValue().compareTo(dpFechaSalida.getValue())) {
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
                                tour.getItinerarios().clear();
                                for (Itinerario ititbv : tbvItinerarios.getItems()) {
                                    tour.getItinerarios().add(ititbv);
                                }
                                tou.setTour(tour);
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
                    if (switcher != null) {
                        switcher.stop();
                    }
                    imgFotos.setImage(null);
                    limpiarCBX();
                    nuevoTour();
                    nuevoItinerario();
                    cargarItinerarios();
                    cargarClientes();
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Tour", getStage(), "Tour actualizado correctamente.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tour", getStage(), "Ocurrio un error con las fechas");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MantToursViewController.class.getName()).log(Level.SEVERE, "Error guardando el Tour.", ex);
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

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return null;
        }
        return name.substring(lastIndexOf + 1);
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

    private void bindItinerario(Boolean nuevo) {
        if (!nuevo) {
            txtIdItinerario.textProperty().bind(this.itinerario.id);
        }
        txtLugar.textProperty().bindBidirectional(this.itinerario.lugar);
        txtDuración.textProperty().bindBidirectional(this.itinerario.duracionEnLugar);
        txtOrden.textProperty().bindBidirectional(this.itinerario.orden);
        txtLatitud.textProperty().bindBidirectional(this.itinerario.coordenadasLatitud);
        txtLongitud.textProperty().bindBidirectional(this.itinerario.coordenadasLongitud);
        dpItinerarioSalida.valueProperty().bindBidirectional(this.itinerario.fechaSalida);
        dpItinerarioLlegada.valueProperty().bindBidirectional(this.itinerario.fechaLlegada);
    }

    private void unbindItinerario() {
        txtIdItinerario.textProperty().unbind();
        txtLugar.textProperty().unbindBidirectional(this.itinerario.lugar);
        txtDuración.textProperty().unbindBidirectional(this.itinerario.duracionEnLugar);
        txtOrden.textProperty().unbindBidirectional(this.itinerario.orden);
        txtLatitud.textProperty().unbindBidirectional(this.itinerario.coordenadasLatitud);
        txtLongitud.textProperty().unbindBidirectional(this.itinerario.coordenadasLongitud);
        dpItinerarioSalida.valueProperty().unbindBidirectional(this.itinerario.fechaSalida);
        dpItinerarioLlegada.valueProperty().unbindBidirectional(this.itinerario.fechaLlegada);
    }

    private void nuevoTour() {
        unbindTour();
        tour = new Tour();
        bindTour(true);
        txtId.clear();
        txtId.requestFocus();
    }

    private void nuevoItinerario() {
        unbindItinerario();
        itinerario = new Itinerario();
        bindItinerario(true);
        txtIdItinerario.clear();
        txtIdItinerario.requestFocus();
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
            for (Cliente cli : tour.getClientes()) {
                clientes.add(cli);
            }
            tbvClientes.setItems(clientes);
            tbvClientes.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Cliente", getStage(), "Error cargando los Clientes");
        }
    }

    @FXML
    private void onSelectionChangedTbpItinerarios(Event event) {
        if (tbpItinerarios.isSelected()) {
            if (tour.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Tour", getStage(), "Debe cargar el Tour del cúal desea ver los Itinerarios.");
                tabTour.getSelectionModel().select(tbpTour);
            }
        }
    }

    private void cargarItinerarios() {
        if (tour != null) {
            ObservableList<Itinerario> itinerarios = FXCollections.observableArrayList();
            for (Itinerario iti : tour.getItinerarios()) {
                itinerarios.add(new Itinerario(iti));
            }
            tbvItinerarios.setItems(itinerarios);
            tbvItinerarios.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Cliente", getStage(), "Error cargando los Clientes");
        }
    }

    @FXML
    private void onActionJfxBtnGuardarIti(ActionEvent event) {
        try {
            String invalidos = validarRequeridos(requeridosIti);
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Categoria", getStage(), invalidos);
            } else {
                if (dpItinerarioSalida.getValue().compareTo(dpFechaSalida.getValue()) >= 0 && dpItinerarioLlegada.getValue().compareTo(dpFechaRegreso.getValue()) <= 0 && 0 < dpItinerarioLlegada.getValue().compareTo(dpItinerarioSalida.getValue())) {
                    Long contador[] = (Long[]) AppContext.getInstance().get("Contador");
                    if (itinerario.getId() == null || !itinerario.getLugar().isEmpty() && !tbvItinerarios.getItems().stream().anyMatch(a -> a.getId().equals(itinerario.getId()))) {
                        contador[4]++;
                        itinerario.setId(contador[4]);
                        tbvItinerarios.getItems().add(itinerario);
                        tbvItinerarios.refresh();
                    } else if (itinerario.getId() != null && tbvItinerarios.getItems().stream().anyMatch(a -> a.getId().equals(itinerario.getId()))) {
                        for (int i = 0; i < tbvItinerarios.getItems().size(); i++) {
                            if (Objects.equals(tbvItinerarios.getItems().get(i).getId(), itinerario.getId())) {
                                tbvItinerarios.getItems().get(i).setItinerario(itinerario);
                            }
                        }
                        tbvItinerarios.refresh();
                    }
                    nuevoItinerario();
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Itinerario", getStage(), "Itinerario actualizado correctamente.");

                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Categoria", getStage(), "Ocurrio un error con las fechas");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MantToursViewController.class.getName()).log(Level.SEVERE, "Error guardando el Itinerario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Itinerario", getStage(), "Ocurrio un error guardando el Itinerario.");
        }
    }

    @FXML
    private void onActionJfxBtnNuevoIti(ActionEvent event
    ) {
        if (new Mensaje().showConfirmation("Limpiar Itinerario", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoItinerario();
        }
    }

    public void indicarRequeridos() {
        requeridosIti.clear();
        requeridosTour.clear();
        requeridosIti.addAll(Arrays.asList(txtNombre, dpItinerarioLlegada, dpItinerarioSalida, txtDuración, txtOrden, txtLatitud, txtLongitud));
        requeridosTour.addAll(Arrays.asList(txtNombre, jfxCbxCategoria, jfxCbxEmpresa, txtPrecio, dpFechaSalida, dpFechaRegreso, txtCuposTotales));
    }

    public String validarRequeridos(List<Node> requeridos) {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXComboBox) {
                if (((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                    if (validos) {
                        invalidos += ((JFXComboBox) node).getPromptText();
                    } else {
                        invalidos += "," + ((JFXComboBox) node).getPromptText();
                    }
                    validos = false;
                }
            } else if (node instanceof JFXPasswordField && !((JFXPasswordField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof DatePicker) {
                if (((DatePicker) node).getValue() == null) {
                    if (validos) {
                        invalidos += ((DatePicker) node).getAccessibleText();
                    } else {
                        invalidos += "," + ((DatePicker) node).getAccessibleText();
                    }
                    validos = false;
                }
            } else if (node instanceof TextField) {
                if (((TextField) node).getText() == null || ((TextField) node).getText().isEmpty()) {
                    if (validos) {
                        invalidos += ((TextField) node).getText();
                    } else {
                        invalidos += "," + ((TextField) node).getText();
                    }

                    validos = false;
                }
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }

    private class ButtonCell extends TableCell<Itinerario, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(100);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");

            cellButton.setOnAction((ActionEvent t) -> {
                Itinerario iti = (Itinerario) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                tbvItinerarios.getItems().remove(iti);
                tbvItinerarios.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

}
