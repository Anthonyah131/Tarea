/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.model.Itinerario;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.Mensaje;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class ItinerariosViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane rootItinerariosView;
    @FXML
    private TableView<Itinerario> tbvItinerarios;

    Tour tour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
        tbvItinerarios.getColumns().clear();
        tbvItinerarios.getItems().clear();

        TableColumn<Itinerario, String> tbcIdIti = new TableColumn<>("Id");
        tbcIdIti.setPrefWidth(25);
        tbcIdIti.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getId().toString()));
        
        TableColumn<Itinerario, String> tbcOrdenIti = new TableColumn<>("Orden");
        tbcOrdenIti.setPrefWidth(50);
        tbcOrdenIti.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getOrden()));

        TableColumn<Itinerario, String> tbcLugarIti = new TableColumn<>("Lugar");
        tbcLugarIti.setPrefWidth(100);
        tbcLugarIti.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getLugar()));

        TableColumn<Itinerario, String> tbcFsalida = new TableColumn<>("Fecha Salida");
        tbcFsalida.setPrefWidth(100);
        tbcFsalida.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getFechaSalida().toString()));

        TableColumn<Itinerario, String> tbcDuracionIti = new TableColumn<>("Duración");
        tbcDuracionIti.setPrefWidth(70);
        tbcDuracionIti.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getDuracionEnLugar()));

        TableColumn<Itinerario, String> tbcFLlegada = new TableColumn<>("Fecha Llegada");
        tbcFLlegada.setPrefWidth(100);
        tbcFLlegada.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getFechaLlegada().toString()));

        tbvItinerarios.getColumns().add(tbcIdIti);
        tbvItinerarios.getColumns().add(tbcOrdenIti);
        tbvItinerarios.getColumns().add(tbcLugarIti);
        tbvItinerarios.getColumns().add(tbcFsalida);
        tbvItinerarios.getColumns().add(tbcDuracionIti);
        tbvItinerarios.getColumns().add(tbcFLlegada);
        tbvItinerarios.refresh();

        cargarItinerarios();
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
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Itinerarios", getStage(), "Error cargando los Itinerarios");
        }
    }

    public void setTour(Tour tou) {
        this.tour = tou;
    }

}
