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
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class ClienteViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane rootClienteView;
    @FXML
    private ImageView imgLogo;
    @FXML
    private JFXButton jfxBtnSalir;
    @FXML
    private AnchorPane apTours;
    @FXML
    private JFXButton jfxBtnAnt;
    @FXML
    private JFXButton jfxBtnSig;
    @FXML
    private JFXButton jfxBtnCarrito;
    @FXML
    private ImageView imgCarrito;
    @FXML
    private JFXButton jfxBtnTodos;
    @FXML
    private JFXButton jfxBtnFecha;
    @FXML
    private JFXButton jfxBtnNombre;
    @FXML
    private JFXComboBox<Empresa> jfxcbEmpresa;
    @FXML
    private JFXComboBox<Categoria> jfxcbCategoria;
    @FXML
    private ImageView imgFecha;
    @FXML
    private ImageView imgNombre;

    int tourVista = 0;
    int tourTotal = 0;
    boolean isAnimating = false;
    private ObservableList<Tour> tours = FXCollections.observableArrayList();
    private ObservableList<Empresa> empresas = FXCollections.observableArrayList();
    private ObservableList<Categoria> categorias = FXCollections.observableArrayList();
    Tour tour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void initialize() {
        imgLogo.setImage(new Image("cr/ac/una/tarea/resources/PuraVidaLogo1.png"));
        imgCarrito.setImage(new Image("cr/ac/una/tarea/resources/Carrito2.png"));
        imgFecha.setImage(new Image("cr/ac/una/tarea/resources/Cercana.png"));
        imgNombre.setImage(new Image("cr/ac/una/tarea/resources/Asce.png"));

        apTours.getChildren().clear();

        tourVista = 0;
        tourTotal = 0;

        tours.clear();
        tours.addAll((List<Tour>) AppContext.getInstance().get("ToursLista"));

        limpiarCBX();

        for (int i = 0; i < tours.size(); i += 3) {
            HBox hboxContenedor = new HBox();
            hboxContenedor.setSpacing(10);
            hboxContenedor.setAlignment(Pos.CENTER);
            hboxContenedor.setMaxWidth(Double.MAX_VALUE);
            for (int j = 0; j < 3 && i + j < tours.size(); j++) {
                tour = new Tour(tours.get(i + j));
                if (tour != null) {
                    Long id = tour.getId();
                    VBox vboxContenedor = new VBox();
                    vboxContenedor.setMaxHeight(500);
                    vboxContenedor.setMaxWidth(400);
                    vboxContenedor.setSpacing(10);
                    vboxContenedor.getStyleClass().add("vbox-contenedor");
                    HBox.setHgrow(vboxContenedor, Priority.ALWAYS);
                    vboxContenedor.setAlignment(Pos.CENTER);
                    HBox hboxLogo = new HBox();
                    ImageView imgLogoEmpresa = new ImageView();
                    Label lbNombre = new Label();
                    lbNombre.getStyleClass().add("label-titulo");
                    ImageView imgTour = new ImageView();
                    Label lbFecha = new Label();
                    lbFecha.getStyleClass().add("label-fecha");
                    JFXButton jfxBtnVerTour = new JFXButton();

                    imgLogoEmpresa.setImage(tour.getEmpresa().getLogo());
                    imgLogoEmpresa.setPreserveRatio(true);
                    imgLogoEmpresa.setFitHeight(50);
                    hboxLogo.getChildren().add(imgLogoEmpresa);
                    hboxLogo.setAlignment(Pos.CENTER_LEFT);
                    lbNombre.setText(tour.getNombre());
                    imgTour.setImage(tour.getFotos().get(5));
                    imgTour.setPreserveRatio(true);
                    imgTour.setFitHeight(150);
                    VBox.setVgrow(imgTour, Priority.ALWAYS);
                    lbFecha.setText(tour.getFechaSalida().toString());
                    jfxBtnVerTour.setText("Ver Tour");
                    jfxBtnVerTour.setOnAction(event -> {
                        TourViewController tourController = (TourViewController) FlowController.getInstance().getController("TourView");
                        tourController.cargarTour(tours.stream().filter(t -> Objects.equals(t.getId(), id)).findFirst().get());
                        FlowController.getInstance().goViewInWindowModal("TourView", getStage(), true);
                        tourController.carrusel.stopCarrusel();
                    });

                    vboxContenedor.getChildren().addAll(hboxLogo, lbNombre, imgTour, lbFecha, jfxBtnVerTour);
                    hboxContenedor.getChildren().add(vboxContenedor);
                }
            }
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.getStyleClass().add("stackpane-contenedor");
            stackPane.getChildren().add(hboxContenedor);
            AnchorPane.setTopAnchor(stackPane, 0.0);
            AnchorPane.setBottomAnchor(stackPane, 0.0);
            AnchorPane.setLeftAnchor(stackPane, 0.0);
            AnchorPane.setRightAnchor(stackPane, 0.0);
            apTours.getChildren().add(stackPane);
            tourVista++;
        }
        tourVista--;
        tourTotal = tourVista;

        for (int i = apTours.getChildren().size() - 1; i >= 1; i--) {
            Node node = apTours.getChildren().get(i);
            if (node instanceof StackPane) {
                StackPane pane = (StackPane) node;
                translateAnimation(0.5, pane, 2000);
            }
        }
        tourVista = 0;
    }

    @FXML
    private void onActionJfxBtnSalir(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnAnt(ActionEvent event) {
        if (tourTotal >= 1) {
            if (!isAnimating) {
                isAnimating = true;
                jfxBtnAnt.setDisable(true);
                jfxBtnSig.setDisable(true);
                if (tourVista <= 0) {
                    tourVista = tourTotal;
                    for (int i = 1; i < apTours.getChildren().size(); i++) {
                        Node node = apTours.getChildren().get(i);
                        if (node instanceof StackPane) {
                            StackPane pane = (StackPane) node;
                            translateAnimation(0.5, pane, -2000);
                        }
                    }
                } else {
                    Node node1 = apTours.getChildren().get(tourVista);
                    if (node1 instanceof StackPane) {
                        StackPane pane = (StackPane) node1;
                        translateAnimation(0.5, pane, 2000);
                        tourVista--;
                    }
                }
            }
        }
    }

    @FXML
    private void onActionJfxBtnSig(ActionEvent event) {
        if (tourTotal >= 1) {
            if (!isAnimating) {
                isAnimating = true;
                jfxBtnAnt.setDisable(true);
                jfxBtnSig.setDisable(true);
                if (tourVista >= tourTotal) {
                    tourVista = 0;
                    for (int i = apTours.getChildren().size() - 1; i >= 1; i--) {
                        Node node = apTours.getChildren().get(i);
                        if (node instanceof StackPane) {
                            StackPane pane = (StackPane) node;
                            translateAnimation(0.5, pane, 2000);
                        }
                    }
                } else {
                    Node node = apTours.getChildren().get(tourVista + 1);
                    if (node instanceof StackPane) {
                        StackPane pane = (StackPane) node;
                        translateAnimation(0.5, pane, -2000);
                        tourVista++;
                    }
                }
            }
        }
    }

    @FXML
    private void onActionJfxBtnCarrito(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnTodos(ActionEvent event) {
        filtro("Todos");
    }

    int fechaCont = 0;

    @FXML
    private void onActionJfxBtnFecha(ActionEvent event) {
        if (fechaCont == 0) {
            filtro("Fecha");
            imgFecha.setImage(new Image("cr/ac/una/tarea/resources/Cercana.png"));
            fechaCont++;
        } else if (fechaCont == 1) {
            filtro("FechaLej");
            imgFecha.setImage(new Image("cr/ac/una/tarea/resources/Lejana.png"));
            fechaCont = 0;
        }
    }

    int fechaNombre = 0;

    @FXML
    private void onActionJfxBtnNombre(ActionEvent event) {
        if (fechaNombre == 0) {
            filtro("Nombre");
            imgNombre.setImage(new Image("cr/ac/una/tarea/resources/Asce.png"));
            fechaNombre++;
        } else if (fechaNombre == 1) {
            filtro("NombreDes");
            imgNombre.setImage(new Image("cr/ac/una/tarea/resources/Desce.png"));
            fechaNombre = 0;
        }
    }

    @FXML
    private void onActionJfxCbxEmpresa(ActionEvent event) {
        filtro("Empresa");
    }

    @FXML
    private void onActionJfxCbxCategoria(ActionEvent event) {
        filtro("Categoria");
    }

    public void translateAnimation(double duration, Node node, double width) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setOnFinished(event -> {
            isAnimating = false;
            jfxBtnAnt.setDisable(false);
            jfxBtnSig.setDisable(false);
        });
        translateTransition.setByX(width);
        translateTransition.play();
    }

    private void limpiarCBX() {
        jfxcbEmpresa.getItems().clear();
        jfxcbCategoria.getItems().clear();

        empresas.clear();
        categorias.clear();

        empresas.addAll((List<Empresa>) AppContext.getInstance().get("EmpresasLista"));
        categorias.addAll((List<Categoria>) AppContext.getInstance().get("CategoriasLista"));

        jfxcbEmpresa.setItems(empresas);
        jfxcbCategoria.setItems(categorias);
    }

    private void filtro(String tipo) {
        apTours.getChildren().clear();

        tourVista = 0;
        tourTotal = 0;

        tours.clear();
        tours.addAll((List<Tour>) AppContext.getInstance().get("ToursLista"));

        ObservableList<Tour> toursFiltro = FXCollections.observableArrayList();

        switch (tipo) {
            case "Todos":
                toursFiltro.clear();
                toursFiltro.addAll(tours);
                jfxcbEmpresa.getSelectionModel().clearSelection();
                jfxcbCategoria.getSelectionModel().clearSelection();
                break;
            case "Fecha":
                toursFiltro.clear();
                toursFiltro.addAll(tours.stream()
                        .sorted(Comparator.comparing(Tour::getFechaSalida))
                        .collect(Collectors.toList()));
                jfxcbEmpresa.getSelectionModel().clearSelection();
                jfxcbCategoria.getSelectionModel().clearSelection();
                break;
            case "FechaLej":
                toursFiltro.clear();
                toursFiltro.addAll(tours.stream()
                        .sorted(Comparator.comparing(Tour::getFechaSalida).reversed())
                        .collect(Collectors.toList()));
                jfxcbEmpresa.getSelectionModel().clearSelection();
                jfxcbCategoria.getSelectionModel().clearSelection();
                break;
            case "Nombre":
                toursFiltro.clear();
                toursFiltro.addAll(tours.stream()
                        .sorted(Comparator.comparing(Tour::getNombre))
                        .collect(Collectors.toList()));
                jfxcbEmpresa.getSelectionModel().clearSelection();
                jfxcbCategoria.getSelectionModel().clearSelection();
                break;
            case "NombreDes":
                toursFiltro.clear();
                toursFiltro.addAll(tours.stream()
                        .sorted(Comparator.comparing(Tour::getNombre).reversed())
                        .collect(Collectors.toList()));
                jfxcbEmpresa.getSelectionModel().clearSelection();
                jfxcbCategoria.getSelectionModel().clearSelection();
                break;
            case "Empresa":
                Empresa empresaSeleccionada = jfxcbEmpresa.getValue();
                if (empresaSeleccionada != null) {
                    toursFiltro.clear();
                    toursFiltro = tours.stream()
                            .filter(tour -> Objects.equals(tour.getEmpresa().getId(), empresaSeleccionada.getId()))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList));
                    if (jfxcbEmpresa.getSelectionModel().getSelectedIndex() != -1) {
                        jfxcbCategoria.getSelectionModel().clearSelection();
                    }
                }
                break;
            case "Categoria":
                Categoria categoriaSeleccionada = jfxcbCategoria.getValue();
                if (categoriaSeleccionada != null) {
                    toursFiltro.clear();
                    toursFiltro = tours.stream()
                            .filter(tour -> Objects.equals(tour.getCategoria().getId(), categoriaSeleccionada.getId()))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList));
                    if (jfxcbCategoria.getSelectionModel().getSelectedIndex() != -1) {
                        jfxcbEmpresa.getSelectionModel().clearSelection();
                    }
                }
                break;
            // y así sucesivamente
            default:
            // código a ejecutar si la expresion no coincide con ninguno de los valores anteriores
        }

        for (int i = 0; i < toursFiltro.size(); i += 3) {
            HBox hboxContenedor = new HBox();
            hboxContenedor.setSpacing(10);
            hboxContenedor.setAlignment(Pos.CENTER);
            hboxContenedor.setMaxWidth(Double.MAX_VALUE);
            for (int j = 0; j < 3 && i + j < toursFiltro.size(); j++) {
                tour = new Tour(toursFiltro.get(i + j));
                if (tour != null) {
                    Long id = tour.getId();
                    VBox vboxContenedor = new VBox();
                    vboxContenedor.setMaxHeight(500);
                    vboxContenedor.setMaxWidth(400);
                    vboxContenedor.setSpacing(10);
                    vboxContenedor.getStyleClass().add("vbox-contenedor");
                    HBox.setHgrow(vboxContenedor, Priority.ALWAYS);
                    vboxContenedor.setAlignment(Pos.CENTER);
                    HBox hboxLogo = new HBox();
                    ImageView imgLogoEmpresa = new ImageView();
                    Label lbNombre = new Label();
                    lbNombre.getStyleClass().add("label-titulo");
                    ImageView imgTour = new ImageView();
                    Label lbFecha = new Label();
                    lbFecha.getStyleClass().add("label-fecha");
                    JFXButton jfxBtnVerTour = new JFXButton();

                    imgLogoEmpresa.setImage(tour.getEmpresa().getLogo());
                    imgLogoEmpresa.setPreserveRatio(true);
                    imgLogoEmpresa.setFitHeight(50);
                    hboxLogo.getChildren().add(imgLogoEmpresa);
                    hboxLogo.setAlignment(Pos.CENTER_LEFT);
                    lbNombre.setText(tour.getNombre());
                    imgTour.setImage(tour.getFotos().get(5));
                    imgTour.setPreserveRatio(true);
                    imgTour.setFitHeight(150);
                    VBox.setVgrow(imgTour, Priority.ALWAYS);
                    lbFecha.setText(tour.getFechaSalida().toString());
                    jfxBtnVerTour.setText("Ver Tour");
                    jfxBtnVerTour.setOnAction(event -> {
                        TourViewController tourController = (TourViewController) FlowController.getInstance().getController("TourView");
                        tourController.cargarTour(tours.stream().filter(t -> Objects.equals(t.getId(), id)).findFirst().get());
                        FlowController.getInstance().goViewInWindowModal("TourView", getStage(), true);
                        tourController.carrusel.stopCarrusel();
                    });

                    vboxContenedor.getChildren().addAll(hboxLogo, lbNombre, imgTour, lbFecha, jfxBtnVerTour);
                    hboxContenedor.getChildren().add(vboxContenedor);
                }
            }
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.getStyleClass().add("stackpane-contenedor");
            stackPane.getChildren().add(hboxContenedor);
            AnchorPane.setTopAnchor(stackPane, 0.0);
            AnchorPane.setBottomAnchor(stackPane, 0.0);
            AnchorPane.setLeftAnchor(stackPane, 0.0);
            AnchorPane.setRightAnchor(stackPane, 0.0);
            apTours.getChildren().add(stackPane);
            tourVista++;
        }
        tourVista--;
        tourTotal = tourVista;

        for (int i = apTours.getChildren().size() - 1; i >= 1; i--) {
            Node node = apTours.getChildren().get(i);
            if (node instanceof StackPane) {
                StackPane pane = (StackPane) node;
                translateAnimation(0.5, pane, 2000);
            }
        }
        tourVista = 0;
    }
}
