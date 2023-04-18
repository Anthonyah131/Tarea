/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Cliente;
import cr.ac.una.tarea.model.Factura;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

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
    private JFXButton jfxBtnCategorias;
    @FXML
    private JFXButton jfxBtnTours;
    @FXML
    private JFXButton jfxBtnClientes;
    @FXML
    private JFXButton jfxBtnEmpresa;
    @FXML
    private VBox rootDashBoard;

    ObservableList<Tour> tours = FXCollections.observableArrayList();
    ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    ObservableList<Factura> facturas = FXCollections.observableArrayList();
    int cantidadFacturas = 0;

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

    public void cargarDashBoard() { //Crea dinamicamente y carga la informacion del DashBoard
        rootDashBoard.getChildren().clear();

        tours.clear();
        tours.addAll((List<Tour>) AppContext.getInstance().get("ToursLista"));
        clientes.clear();
        clientes.addAll((List<Cliente>) AppContext.getInstance().get("ClientesLista"));
        facturas.clear();
        facturas.addAll((List<Factura>) AppContext.getInstance().get("FacturasLista"));

        cantidadFacturas = facturas.size();

        List<Object[]> toursVendidos = new ArrayList<>();
        List<Object[]> clientesCompra = new ArrayList<>();

        if (facturas != null && !facturas.isEmpty()) {
            for (Factura fac : facturas) {
                for (Object[] tou : fac.getCarrito().getTours()) {
                    Tour tour = (Tour) tou[0];
                    int cantidad = (int) tou[1];
                    if (toursVendidos == null || toursVendidos.isEmpty()) {
                        Object[] tourNuevo = new Object[]{tour, cantidad};
                        toursVendidos.add(tourNuevo);
                    } else {
                        boolean encontrado = false;
                        for (int i = 0; i < toursVendidos.size(); i++) {
                            Tour tourVen = (Tour) toursVendidos.get(i)[0];
                            int cantVen = (int) toursVendidos.get(i)[1];

                            if (tour.getId().equals(tourVen.getId())) {
                                toursVendidos.get(i)[1] = cantVen + cantidad;
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            Object[] tourNuevo = new Object[]{tour, cantidad};
                            toursVendidos.add(tourNuevo);
                        }
                    }
                }

                boolean encontrado = false;
                for (int i = 0; i < clientesCompra.size(); i++) {
                    Cliente cliVen = (Cliente) clientesCompra.get(i)[0];
                    int cantVen = (int) clientesCompra.get(i)[1];

                    if (fac.getCliente().getId().equals(cliVen.getId())) {
                        toursVendidos.get(i)[1] = cantVen + fac.getCarrito().getCantidad();
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    Object[] clienteCompraNuevo = new Object[]{fac.getCliente(), fac.getCarrito().getCantidad()};
                    clientesCompra.add(clienteCompraNuevo);
                }
            }

            Comparator<Object[]> comparador = new Comparator<Object[]>() {
                @Override
                public int compare(Object[] o1, Object[] o2) {
                    int cantidad1 = (int) o1[1];
                    int cantidad2 = (int) o2[1];
                    return Integer.compare(cantidad2, cantidad1);
                }
            };

            Object[][] arrayToursVendidos = toursVendidos.toArray(new Object[0][]);
            Arrays.sort(arrayToursVendidos, comparador);
            toursVendidos = Arrays.asList(arrayToursVendidos);

            Object[][] arrayClientesCompra = clientesCompra.toArray(new Object[0][]);
            Arrays.sort(arrayClientesCompra, comparador);
            clientesCompra = Arrays.asList(arrayClientesCompra);
        }

        Label facturasLabel = new Label("Cantidad de facturas: " + cantidadFacturas);
        facturasLabel.getStyleClass().add("hboxTexto");

        VBox vboxContenedor = new VBox();
        Label clientesLabel = new Label("Clientes que más compraron:");
        clientesLabel.setTextAlignment(TextAlignment.CENTER);
        clientesLabel.getStyleClass().add("hboxTexto");
        vboxContenedor.getStyleClass().add("vboxTexto");
        clientesLabel.setWrapText(true);
        vboxContenedor.getChildren().add(clientesLabel);

        for (int i = 0; i < (clientesCompra.size() > 5 ? 5 : clientesCompra.size()); i++) {
            Cliente cliente = (Cliente) clientesCompra.get(i)[0];
            int cantidad = (int) clientesCompra.get(i)[1];
            Label label = new Label();
            label.setText(cliente.getNombre() + " : " + cantidad);
            label.setTextAlignment(TextAlignment.CENTER);
            label.getStyleClass().add("hboxTexto");
            vboxContenedor.getChildren().add(label);
        }

        HBox hboxTexto1 = new HBox(facturasLabel);
        hboxTexto1.getStyleClass().add("hboxTexto");
        HBox hboxTexto2 = new HBox(vboxContenedor);
        hboxTexto2.getStyleClass().add("hboxTexto");

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < (toursVendidos.size() > 5 ? 5 : toursVendidos.size()); i++) {
            Tour tour = (Tour) toursVendidos.get(i)[0];
            int cantidad = (int) toursVendidos.get(i)[1];
            pieChartData.add(new PieChart.Data(tour.getNombre(), cantidad));
        }
        if (toursVendidos.isEmpty()) {
            pieChartData.add(new PieChart.Data("Ninguna Compra", 0));
        }

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.getStyleClass().add("hboxTexto");

        HBox hboxContenedor = new HBox(pieChart, hboxTexto2);
        hboxContenedor.setSpacing(10);

        rootDashBoard.getChildren().addAll(hboxTexto1, hboxContenedor);
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
}
