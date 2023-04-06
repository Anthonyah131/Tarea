/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Carrito;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class CarritoViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane rootCarritoView;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label lbCarrito;
    @FXML
    private TableView tbvCarrito;
    @FXML
    private JFXButton jfxBtnAtras;
    @FXML
    private ImageView imgAtras;
    @FXML
    private JFXButton jfxBtnComprar;
    @FXML
    private ImageView imgComprar;
    @FXML
    private Label lbTotalCompra;

    Carrito carrito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgLogo.setImage(new Image("cr/ac/una/tarea/resources/PuraVidaLogo1.png"));
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionJfxBtnAtras(ActionEvent event) {
        this.getStage().close();
    }

    @FXML
    private void onActionJfxBtnComprar(ActionEvent event) {
        FacturaViewController facturaController = (FacturaViewController) FlowController.getInstance().getController("FacturaView");
        facturaController.cargarFactura(carrito);
        FlowController.getInstance().goViewInWindowModal("FacturaView", getStage(), true);
    }

    private void cargarTours() {
        ObservableList<Tour> toursCarrito = FXCollections.observableArrayList();
        for(Object[] tour : carrito.getTours()) {
            Tour n = (Tour) tour[0];
            long can = (int) tour[1];
            n.setCantidadCompra(can);
            toursCarrito.add(n);
        }
        lbTotalCompra.setText("Total: " + carrito.getTotal());
        tbvCarrito.setItems(toursCarrito);
        tbvCarrito.refresh();
    }

    void cargarCarrito(Carrito carrito) {
        try {
            this.carrito = carrito;

            tbvCarrito.getColumns().clear();
            tbvCarrito.getItems().clear();
            
            TableColumn<Tour, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
            tbcEliminar.setPrefWidth(60);
            tbcEliminar.setCellFactory(cd -> new ButtonCell());

            TableColumn<Tour, String> tbcId = new TableColumn<>("Id");
            tbcId.setPrefWidth(25);
            tbcId.setCellValueFactory(cd -> cd.getValue().id);

            TableColumn<Tour, String> tbcNombre = new TableColumn<>("Nombre");
            tbcNombre.setPrefWidth(100);
            tbcNombre.setCellValueFactory(cd -> cd.getValue().nombre);
            
            TableColumn<Tour, String> tbcCantidad = new TableColumn<>("Cantidad");
            tbcCantidad.setPrefWidth(80);
            tbcCantidad.setCellValueFactory(cd -> cd.getValue().cantidadCompra);
            
            TableColumn<Tour, String> tbcPrecioU = new TableColumn<>("Precio Uni");
            tbcPrecioU.setPrefWidth(80);
            tbcPrecioU.setCellValueFactory(cd -> cd.getValue().precio);
            
            TableColumn<Tour, String> tbcPrecioT = new TableColumn<>("Total");
            tbcPrecioT.setPrefWidth(80);
            tbcPrecioT.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCompraTotal().toString()));

            TableColumn<Tour, String> tbcEmpresa = new TableColumn<>("Empresa");
            tbcEmpresa.setPrefWidth(80);
            tbcEmpresa.setCellValueFactory(cd -> cd.getValue().getEmpresa().nombre);

            TableColumn<Tour, String> tbcCategoria = new TableColumn<>("Categoria");
            tbcCategoria.setPrefWidth(80);
            tbcCategoria.setCellValueFactory(cd -> cd.getValue().getCategoria().nombre);

            tbvCarrito.getColumns().add(tbcEliminar);
            tbvCarrito.getColumns().add(tbcId);
            tbvCarrito.getColumns().add(tbcNombre);
            tbvCarrito.getColumns().add(tbcCantidad);
            tbvCarrito.getColumns().add(tbcPrecioU);
            tbvCarrito.getColumns().add(tbcPrecioT);
            tbvCarrito.getColumns().add(tbcEmpresa);
            tbvCarrito.getColumns().add(tbcCategoria);
            tbvCarrito.refresh();

            cargarTours();

        } catch (Exception ex) {
            Logger.getLogger(BusquedaViewController.class.getName()).log(Level.SEVERE, "Error consultando los tours", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar tour", getStage(), "Ocurrio un error consultando los tours");
        }
    }

    private class ButtonCell extends TableCell<Tour, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");

            cellButton.setOnAction((ActionEvent t) -> {
                Tour tou = (Tour) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                carrito.eliminarTour(tou);
                tbvCarrito.getItems().remove(tou);
                tbvCarrito.refresh();
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
