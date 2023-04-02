/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

    int tourVista = 0;
    int tourTotal = 0;
    boolean isAnimating = false;
    private ObservableList<Tour> tours = FXCollections.observableArrayList();
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
        imgCarrito.setImage(new Image("cr/ac/una/tarea/resources/Carrito.png"));
        
        apTours.getChildren().clear();

        tours.clear();
        tours.addAll((List<Tour>) AppContext.getInstance().get("ToursLista"));

        for (int i = 0; i < tours.size(); i += 3) {
            HBox hboxContenedor = new HBox();
            hboxContenedor.setSpacing(10);
            hboxContenedor.setAlignment(Pos.CENTER);
            hboxContenedor.setMaxWidth(Double.MAX_VALUE);
            for (int j = 0; j < 3 && i + j < tours.size(); j++) {
                tour = new Tour(tours.get(i + j));
                if (tour != null) {
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
                    JFXButton jfxBtnVerTour = new JFXButton();
                    AnchorPane pane = new AnchorPane();
                    ImageView imgTour = new ImageView();

                    pane.setPrefHeight(150);
                    pane.setPrefWidth(150);
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
                    jfxBtnVerTour.setText("Ver Tour");

                    vboxContenedor.getChildren().addAll(hboxLogo, lbNombre, imgTour, jfxBtnVerTour);
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
    }

    @FXML
    private void onActionJfxBtnSalir(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnAnt(ActionEvent event) {
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

    @FXML
    private void onActionJfxBtnSig(ActionEvent event) {
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

    @FXML
    private void onActionJfxBtnCarrito(ActionEvent event) {
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
}
