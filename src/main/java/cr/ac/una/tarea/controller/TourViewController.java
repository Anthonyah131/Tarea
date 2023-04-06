/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.Carrusel3D;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class TourViewController extends Controller implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private Label lbTitulo;
    @FXML
    private AnchorPane apTours;
    @FXML
    private JFXTextField txtFechaSalida;
    @FXML
    private JFXTextField txtFechaLLegada;
    @FXML
    private HBox hboxMapa;
    @FXML
    private JFXTextField txtDsiponibles;
    @FXML
    private JFXButton jfxBtnItinerario;
    @FXML
    private JFXTextField txtPrecio;
    @FXML
    private JFXButton jfxBtnMenos;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton jfxBtnMas;
    @FXML
    private JFXButton jfxBtnAgregar;
    @FXML
    private ImageView imgAgregar;
    @FXML
    private AnchorPane rootTourView;
    
    public Carrusel3D carrusel;
    Tour tour;
    public Boolean compraBandera = false;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgAgregar.setImage(new Image("cr/ac/una/tarea/resources/Carrito2.png"));
        // TODO
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnItinerario(ActionEvent event) {
    }

    @FXML
    private void onActionJfxBtnMenos(ActionEvent event) {
        int n = Integer.parseInt(txtCantidad.getText());
        n--;
        if(n < 1)
            n = 1;
        txtCantidad.setText("" + n);
    }

    @FXML
    private void onActionJfxBtnMas(ActionEvent event) {
        int n = Integer.parseInt(txtCantidad.getText());
        n++;
        if(n < 1)
            n = 1;
        txtCantidad.setText("" + n);
    }

    @FXML
    private void onActionJfxBtnAgregar(ActionEvent event) {
        int comprar = Integer.parseInt(txtCantidad.getText());
        int dispo = Integer.parseInt(txtDsiponibles.getText());
        if(comprar <= dispo) {
            compraBandera = true;
            this.getStage().close();
        } else {
            txtCantidad.setText("1");
        }
    }

    void cargarTour(Tour tour) {
        this.tour = new Tour(tour);
        compraBandera = false;
        lbTitulo.setText(tour.getNombre());
        imgLogo.setImage(tour.getEmpresa().getLogo());
        txtFechaSalida.setText(tour.getFechaSalida().toString());
        txtFechaLLegada.setText(tour.getFechaRegreso().toString());
        txtDsiponibles.setText(tour.getCuposDisponibles().toString());
        txtPrecio.setText(tour.getPrecio().toString());
        txtCantidad.setText("1");
        
        apTours.getChildren().clear();
        carrusel = new Carrusel3D(tour.getFotos(), apTours);

        carrusel.inicializarCarrusel();
        carrusel.startCarrusel();
    }

    public Tour getToursCompra() {
        return tour;
    }
    
    public int getToursCantidad() {
        return Integer.parseInt(txtCantidad.getText());
    }
}
