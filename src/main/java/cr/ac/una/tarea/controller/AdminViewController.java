/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.util.FlowController;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class AdminViewController extends Controller implements Initializable {

    @FXML
    private BorderPane rootAdmin;
    @FXML
    private ImageView imgLogo;
    @FXML
    private JFXButton jfxBtnSalir;
    @FXML
    private JFXButton jfxBtnCategorias;
    @FXML
    private JFXButton jfxBtnTours;
    @FXML
    private JFXButton jfxBtnClientes;
    @FXML
    private JFXButton jfxBtnEmpresa;
    @FXML
    private JFXButton jfxBtnMenu;
    @FXML
    private JFXButton jfxBtnMenu1;
    @FXML
    private SplitPane splitPane;
    @FXML
    private VBox vBoxIzq;
    @FXML
    private VBox vBoxDer;
    @FXML
    private ImageView imgMenu1;
    @FXML
    private ImageView imgMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            // Busca el SplitPaneDivider
            Optional<Node> divider = splitPane.lookupAll(".split-pane-divider").stream().findFirst();

            // Si se encuentra el divider, elimínalo
            divider.ifPresent(node -> {
                node.setVisible(false);
                node.setManaged(false);
                splitPane.getItems().remove(node);
            });
        });
        vBoxIzq.setVisible(false);
        vBoxDer.setVisible(true);
        Image menuBoton = new Image("cr/ac/una/tarea/resources/MenuIcon.png");
        imgMenu1.setImage(menuBoton);
        imgMenu.setImage(menuBoton);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnSalir(ActionEvent event) {
    }

    @FXML
    private void OnActionJfxBtnCategorias(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("MantCatView", this.getStage(), false);
    }

    @FXML
    private void OnActionJfxBtnTours(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("MantToursView", this.getStage(), false);
    }

    @FXML
    private void OnActionJfxBtnClientes(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("MantClientesView", this.getStage(), false);
    }

    @FXML
    private void OnActionJfxBtnEmpresa(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("MantEmpresaView", this.getStage(), false);
    }

    @FXML
    private void OnActionJfxBtnMenuOcultar(ActionEvent event) {
        splitPane.setDividerPosition(0, 0.0);
        splitPane.setPrefWidth(50);
        vBoxIzq.setVisible(false);
        vBoxDer.setVisible(true);
    }

    @FXML
    private void OnActionJfxBtnMenuMostrar(ActionEvent event) {
        splitPane.setDividerPosition(0, 1.0);
        splitPane.setPrefWidth(250);
        vBoxIzq.setVisible(true);
        vBoxDer.setVisible(false);
    }

}
