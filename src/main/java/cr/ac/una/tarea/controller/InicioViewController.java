/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class InicioViewController extends Controller implements Initializable {

    @FXML
    private BorderPane rootInicio;
    @FXML
    private ImageView imgLogo;
    @FXML
    private JFXButton jfxBtnAdmin;
    @FXML
    private JFXButton jfxBtnUser;
    @FXML
    private HBox hboxheader;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgLogo.setImage(new Image("cr/ac/una/tarea/resources/PuraVidaLogo1.png"));
        hboxheader.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(jfxBtnAdmin, new Insets(0, 10, 0, 0));
        HBox.setHgrow(jfxBtnAdmin, Priority.ALWAYS);
        HBox.setHgrow(jfxBtnUser, Priority.ALWAYS);
        HBox.setHgrow(imgLogo, Priority.NEVER);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnAdmin(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("AdminView");
    }

    @FXML
    private void onActionJfxBtnUser(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("ClienteView");
    }

}
