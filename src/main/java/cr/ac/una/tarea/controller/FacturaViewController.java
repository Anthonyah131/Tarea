package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import cr.ac.una.tarea.model.Carrito;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.FlowController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.layout.AnchorPane;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class FacturaViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane rootFacturaView;
    @FXML
    private VBox vboxFactura;
    @FXML
    private VBox vboxTours;
    @FXML
    private Label lbTotal;
    @FXML
    private ImageView imgLogo;
    @FXML
    private JFXButton jfxBtnDescargarPdf;
    @FXML
    private ColumnConstraints gpNombre;
    @FXML
    private ColumnConstraints gpCantidad;
    @FXML
    private ColumnConstraints gpPrecioU;
    @FXML
    private ColumnConstraints gpPrecioT;
    @FXML
    private GridPane gridFactura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void initialize() {
        imgLogo.setImage(new javafx.scene.image.Image("cr/ac/una/tarea/resources/PuraVidaLogo1.png"));
    }

    @FXML
    private void onActionJfxBtnDescargarPdf(ActionEvent event) {
        descargarFactura();
    }

    public void descargarFactura() {
        try {
            // Crear objeto Document de ITextPDF y especificar tamaño de página
            Document documento = new Document(PageSize.A4);
            // Crear objeto PdfWriter de ITextPDF
            PdfWriter.getInstance(documento, new FileOutputStream("factura.pdf"));
            // Abrir documento para escribir en él
            documento.open();
            // Agregar componentes de la interfaz al PDF
            WritableImage image = vboxFactura.snapshot(new SnapshotParameters(), null);
            File output = new File("factura.png");

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", output);
            // Obtener las dimensiones de la página y la imagen
            float pageWidth = documento.getPageSize().getWidth();
            float pageHeight = documento.getPageSize().getHeight();
            BufferedImage bufferedImage = ImageIO.read(output);
            float imageWidth = bufferedImage.getWidth();
            float imageHeight = bufferedImage.getHeight();
            // Calcular el factor de escala para la imagen
            float scale = Math.min(pageWidth / imageWidth, pageHeight / imageHeight);
            // Crear un objeto Image de iTextPDF y establecer la imagen redimensionada
            Image imagen = Image.getInstance(output.getPath());
            imagen.scaleToFit(imageWidth * scale, imageHeight * scale);
            // Establecer la alineación de la imagen como centrada
            imagen.setAlignment(Image.ALIGN_CENTER);
            // Agregar la imagen al documento
            documento.add(imagen);
            // Cerrar documento
            documento.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void cargarFactura(Carrito carrito) {
        // Recorrer los tours del carrito
        gridFactura.getChildren().clear();
        gridFactura.setAlignment(Pos.CENTER);

        gridFactura.setGridLinesVisible(true);

        Label lbTituloNombre = new Label("Nombre");
        GridPane.setHalignment(lbTituloNombre, HPos.CENTER);
        GridPane.setValignment(lbTituloNombre, VPos.CENTER);

        Label lbTituloCantidad = new Label("Cantidad");
        GridPane.setHalignment(lbTituloCantidad, HPos.CENTER);
        GridPane.setValignment(lbTituloCantidad, VPos.CENTER);

        Label lbTituloPrecioU = new Label("Precio Unitario");
        GridPane.setHalignment(lbTituloPrecioU, HPos.CENTER);
        GridPane.setValignment(lbTituloPrecioU, VPos.CENTER);

        Label lbTituloPrecioT = new Label("Precio Total");
        GridPane.setHalignment(lbTituloPrecioT, HPos.CENTER);
        GridPane.setValignment(lbTituloPrecioT, VPos.CENTER);

        lbTituloNombre.getStyleClass().add("label-titulo-Factura");
        lbTituloCantidad.getStyleClass().add("label-titulo-Factura");
        lbTituloPrecioU.getStyleClass().add("label-titulo-Factura");
        lbTituloPrecioT.getStyleClass().add("label-titulo-Factura");

        gridFactura.add(lbTituloNombre, 0, 0);
        gridFactura.add(lbTituloCantidad, 1, 0);
        gridFactura.add(lbTituloPrecioU, 2, 0);
        gridFactura.add(lbTituloPrecioT, 3, 0);

        for (int i = 0; i < carrito.getTours().size(); i++) {
            Tour tour = new Tour((Tour) carrito.getTours().get(i)[0]);
            int cantidad = (int) carrito.getTours().get(i)[1];

            Label lbNombre = new Label(tour.getNombre());
            GridPane.setHalignment(lbNombre, HPos.CENTER);
            GridPane.setValignment(lbNombre, VPos.CENTER);

            Label lbCantidad = new Label(Integer.toString(cantidad));
            GridPane.setHalignment(lbCantidad, HPos.CENTER);
            GridPane.setValignment(lbCantidad, VPos.CENTER);

            Label lbPrecioU = new Label(tour.getPrecio().toString());
            GridPane.setHalignment(lbPrecioU, HPos.CENTER);
            GridPane.setValignment(lbPrecioU, VPos.CENTER);

            Label lbPrecioT = new Label(tour.getCompraTotal().toString());
            GridPane.setHalignment(lbPrecioT, HPos.CENTER);
            GridPane.setValignment(lbPrecioT, VPos.CENTER);

            lbNombre.getStyleClass().add("label-tour-Factura");
            lbCantidad.getStyleClass().add("label-tour-Factura");
            lbPrecioU.getStyleClass().add("label-tour-Factura");
            lbPrecioT.getStyleClass().add("label-tour-Factura");

            // Agregar las etiquetas a la fila
            GridPane.setConstraints(lbNombre, 0, i + 1);
            GridPane.setConstraints(lbCantidad, 1, i + 1);
            GridPane.setConstraints(lbPrecioU, 2, i + 1);
            GridPane.setConstraints(lbPrecioT, 3, i + 1);
            gridFactura.getChildren().addAll(lbNombre, lbCantidad, lbPrecioU, lbPrecioT);
        }
        lbTotal.setText("Total: " + carrito.getTotal());
    }
}
