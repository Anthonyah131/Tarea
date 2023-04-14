/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.CoordinateLine;
import com.sothawo.mapjfx.MapLabel;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.MapType;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.WMSParam;
import com.sothawo.mapjfx.XYZParam;
import com.sothawo.mapjfx.event.MapLabelEvent;
import com.sothawo.mapjfx.event.MapViewEvent;
import com.sothawo.mapjfx.event.MarkerEvent;
import com.sothawo.mapjfx.offline.OfflineCache;
import cr.ac.una.tarea.model.Itinerario;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.Carrusel3D;
import cr.ac.una.tarea.util.DisplayShelf;
import cr.ac.una.tarea.util.FlowController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    @FXML
    private BorderPane borderPaneMap;

    private static final Logger logger = LoggerFactory.getLogger(TourViewController.class);

    private static final Coordinate coordCostaRica = new Coordinate(9.7489, -83.7534);

    CoordinateLine coordinateLineIti = null;
    List<Coordinate> coordinates = new ArrayList<>();
    List<Marker> markers = new ArrayList<>();

    private static final WMSParam wmsParam;

    private static final XYZParam xyzParam;

    static {
//        wmsParam = new WMSParam()
//                .setUrl("http://irs.gis-lab.info/?")
//                .addParam("layers", "landsat")
//                .addParam("REQUEST", "GetTile");
//        wmsParam = new WMSParam()
//                .setUrl("http://geonode.wfp.org:80/geoserver/ows")
//                .addParam("layers", "geonode:admin_2_gaul_2015");
        wmsParam = new WMSParam()
                .setUrl("http://ows.terrestris.de/osm/service")
                .addParam("layers", "OSM-WMS");

        xyzParam = new XYZParam()
                .withUrl("https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x})")
                .withAttributions("'Tiles &copy; <a href=\"https://services.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer\">ArcGIS</a>'");
    }

    private MapView mapView;

    public Carrusel3D carrusel;
    public DisplayShelf carrucel1;
    Tour tour;
    public Boolean compraBandera = false;
    @FXML
    private AnchorPane apTours;

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
        if (n < 1) {
            n = 1;
        }
        txtCantidad.setText("" + n);
    }

    @FXML
    private void onActionJfxBtnMas(ActionEvent event) {
        int n = Integer.parseInt(txtCantidad.getText());
        n++;
        if (n < 1) {
            n = 1;
        }
        txtCantidad.setText("" + n);
    }

    @FXML
    private void onActionJfxBtnAgregar(ActionEvent event) {
        int comprar = Integer.parseInt(txtCantidad.getText());
        int dispo = Integer.parseInt(txtDsiponibles.getText());
        if (comprar <= dispo) {
            compraBandera = true;
            this.getStage().close();
        } else {
            txtCantidad.setText("1");
        }
    }

    void cargarTour(Tour tour) throws IOException {
        this.tour = new Tour(tour);
        compraBandera = false;
        lbTitulo.setText(tour.getNombre());
        imgLogo.setImage(tour.getEmpresa().getLogo());
        txtFechaSalida.setText(tour.getFechaSalida().toString());
        txtFechaLLegada.setText(tour.getFechaRegreso().toString());
        txtDsiponibles.setText(tour.getCuposDisponibles().toString());
        txtPrecio.setText(tour.getPrecio().toString());
        txtCantidad.setText("1");

        cargarMapa();

//        carrusel = new Carrusel3D(tour.getFotos(), apTours);
//
//        carrusel.inicializarCarrusel();
//        carrusel.startCarrusel();
        carrucel1 = new DisplayShelf();
        
        carrucel1.mostrarCarrucel(tour.getFotos(), apTours);
    }

    private void cargarMapa() {
        logger.info("starting devtest program...");

        mapView = new MapView();
        borderPaneMap.getChildren().clear();

        // animate pan and zoom with 500ms
        mapView.setAnimationDuration(500);
        borderPaneMap.setCenter(mapView);

        // add WMSParam
        mapView.setWMSParam(wmsParam);

        //add XYZParam
        mapView.setXYZParam(xyzParam);

        // listen to MapViewEvent MAP_RIGHTCLICKED
        mapView.addEventHandler(MapViewEvent.MAP_RIGHTCLICKED, event -> {
            logger.info("MAP_RIGHTCLICKED event at {}", event.getCoordinate());
            event.consume();
        });

        // listen to MapViewEvent MAP_EXTENT
        mapView.addEventHandler(MapViewEvent.MAP_EXTENT, event -> {
            logger.info("MAP_EXTENT event: {}", event.getExtent());
            mapView.setExtent(event.getExtent());
            event.consume();
        });

        // listen to MapViewEvent MAP_BOUNDING_EXTENT
        mapView.addEventHandler(MapViewEvent.MAP_BOUNDING_EXTENT, event -> {
            logger.info("MAP_BOUNDING_EXTENT event: {}", event.getExtent());
            event.consume();
        });

        // listen to MARKER_MOUSEDOWN event.
        mapView.addEventHandler(MarkerEvent.MARKER_MOUSEDOWN, event -> {
            logger.info("MARKER_MOUSEDOWN event: {}", event.getMarker());
            event.consume();
        });
        // listen to MARKER_MOUSEUP event.
        mapView.addEventHandler(MarkerEvent.MARKER_MOUSEUP, event -> {
            logger.info("MARKER_MOUSEUP event: {}", event.getMarker());
            event.consume();
        });
        // listen to MARKER_DOUBLECLICKED event.
        mapView.addEventHandler(MarkerEvent.MARKER_DOUBLECLICKED, event -> {
            logger.info("MARKER_DOUBLECLICKED event: {}", event.getMarker());
            event.consume();
        });
        // listen to MARKER_RIGHTCLICKED event.
        mapView.addEventHandler(MarkerEvent.MARKER_RIGHTCLICKED, event -> {
            logger.info("MARKER_RIGHTCLICKED event: {}", event.getMarker());
            event.consume();
        });
        // listen to MARKER_ENTERED event.
        mapView.addEventHandler(MarkerEvent.MARKER_ENTERED, event -> {
            logger.info("MARKER_ENTERED event: {}", event.getMarker());
            event.consume();
        });
        // listen to MARKER_EXITED event.
        mapView.addEventHandler(MarkerEvent.MARKER_EXITED, event -> {
            logger.info("MARKER_EXITED event: {}", event.getMarker());
            event.consume();
        });
        // listen to MAPLABEL_MOUSEDOWN event.
        mapView.addEventHandler(MapLabelEvent.MAPLABEL_MOUSEDOWN, event -> {
            logger.info("MAPLABEL_MOUSEDOWN event: {}", event.getMapLabel());
            event.consume();
        });
        // listen to MAPLABEL_MOUSEUP event.
        mapView.addEventHandler(MapLabelEvent.MAPLABEL_MOUSEUP, event -> {
            logger.info("MAPLABEL_MOUSEUP event: {}", event.getMapLabel());
            event.consume();
        });
        // listen to MAPLABEL_CLICKED event.
        mapView.addEventHandler(MapLabelEvent.MAPLABEL_CLICKED, event -> {
            logger.info("MAPLABEL_CLICKED event: {}", event.getMapLabel());
            event.consume();
        });
        // listen to MAPLABEL_RIGHTCLICKED event.
        mapView.addEventHandler(MapLabelEvent.MAPLABEL_RIGHTCLICKED, event -> {
            logger.info("MAPLABEL_RIGHTCLICKED event: {}", event.getMapLabel());
            event.consume();
        });
        // listen to MAPLABEL_DOUBLECLICKED event.
        mapView.addEventHandler(MapLabelEvent.MAPLABEL_DOUBLECLICKED, event -> {
            logger.info("MAPLABEL_DOUBLECLICKED event:{}", event.getMapLabel());
            event.consume();
        });
        // listen to MAPLABEL_ENTERED event.
        mapView.addEventHandler(MapLabelEvent.MAPLABEL_ENTERED, event -> {
            logger.info("MAPLABEL_ENTERED event: {}", event.getMapLabel());
            event.consume();
            event.getMapLabel().setCssClass("green-label");
        });
        // listen to MAPLABEL_EXITED event.
        mapView.addEventHandler(MapLabelEvent.MAPLABEL_EXITED, event -> {
            logger.info("MAPLABEL_EXITED event: {}", event.getMapLabel());
            event.consume();
            event.getMapLabel().setCssClass("blue-label");
        });
        // listen to MAP_POINTER_MOVED event
        mapView.addEventHandler(MapViewEvent.MAP_POINTER_MOVED, event -> {
            logger.info("MAP_POINTER_MOVED event: {}", event.getCoordinate());
            event.consume();
        });

        initOfflineCache();
        // add listener for mapView initialization state
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                coordinates.clear();
                markers.clear();
                coordinateLineIti = null;
                if (!this.tour.getItinerarios().isEmpty()) {
                    Collections.sort(this.tour.getItinerarios(), Comparator.comparingInt(Itinerario::getOrdenInt));
                    for (Itinerario iti : this.tour.getItinerarios()) {
                        Coordinate coordenada = new Coordinate(Double.valueOf(iti.getCoordenadasLatitud()), Double.valueOf(iti.getCoordenadasLongitud()));

                        Marker marker = Marker.createProvided(Marker.Provided.BLUE)
                                .setPosition(coordenada)
                                .setRotation(360)
                                .setVisible(true);

                        MapLabel mapLabel = new MapLabel(iti.getOrden() + "." + iti.getLugar())
                                .setCssClass("blue-label")
                                .setPosition(coordenada)
                                .setRotation(360)
                                .setVisible(true);

                        marker.attachLabel(mapLabel);

                        coordinates.add(coordenada);
                        markers.add(marker);
                    }
                    coordinateLineIti = new CoordinateLine(coordinates)
                            .setVisible(true)
                            .setColor(Color.DODGERBLUE)
                            .setWidth(7)
                            .setClosed(true)
                            .setFillColor(Color.web("lawngreen", 0.5));
                }
                Collections.sort(this.tour.getItinerarios(), Comparator.comparingLong(Itinerario::getId));

                if (coordinates.isEmpty()) {
                    mapView.setCenter(coordCostaRica);
                } else {
                    mapView.setCenter(coordinates.get(0));
                }

                mapView.setZoom(6);

                if (coordinateLineIti != null) {
                    mapView.addCoordinateLine(coordinateLineIti);
                }
                if (markers != null) {
                    for (Marker mark : markers) {
                        mapView.addMarker(mark);
                    }
                }
            }
        });

        // set custom css url
        mapView.setCustomMapviewCssURL(getClass().getResource("/custom_mapview.css"));

        // now initialize the mapView
        mapView.setMapType(MapType.OSM);
//        mapView.initialize();
        mapView.initialize(Configuration.builder()
                //            .showZoomControls(false)
                .build());

        logger.debug("application started.");
    }

    public void stop() throws Exception {
        mapView.close();
    }

    private void initOfflineCache() {
        final OfflineCache offlineCache = OfflineCache.INSTANCE;
        //offlineCache.setCacheDirectory(new File("tmpdata/cache"));
        //offlineCache.setCacheDirectory(FileSystems.getDefault().getPath("tmpdata/cache"));
        //offlineCache.setActive(true);
        offlineCache.setNoCacheFilters(Collections.singletonList(".*\\.sothawo\\.com/.*"));

        LinkedList<String> urls = new LinkedList<>();
        urls.add("https://c.tile.openstreetmap.org/14/8572/5626.png");
        urls.add("https://b.tile.openstreetmap.org/14/8571/5626.png");
        urls.add("https://a.tile.openstreetmap.org/14/8572/5625.png");
        urls.add("https://c.tile.openstreetmap.org/14/8571/5625.png");
        urls.add("https://b.tile.openstreetmap.org/14/8570/5625.png");
        urls.add("https://a.tile.openstreetmap.org/14/8572/5625.png");
        urls.add("https://a.tile.openstreetmap.org/14/8570/5626.png");
        urls.add("https://a.tile.openstreetmap.org/14/8571/5627.png");
        urls.add("https://a.tile.openstreetmap.org/14/8573/5626.png");
        urls.add("https://a.tile.openstreetmap.org/14/8574/5627.png");
        urls.add("https://b.tile.openstreetmap.org/14/8571/5626.png");
        urls.add("https://b.tile.openstreetmap.org/14/8573/5625.png");
        urls.add("https://b.tile.openstreetmap.org/14/8572/5627.png");
        urls.add("https://b.tile.openstreetmap.org/14/8574/5626.png");
        urls.add("https://c.tile.openstreetmap.org/14/8572/5626.png");
        urls.add("https://c.tile.openstreetmap.org/14/8570/5627.png");
        urls.add("https://c.tile.openstreetmap.org/14/8574/5625.png");
        urls.add("https://c.tile.openstreetmap.org/14/8573/5627.png");

        offlineCache.preloadURLs(urls, 2);
    }

    public Tour getToursCompra() {
        return tour;
    }

    public int getToursCantidad() {
        return Integer.parseInt(txtCantidad.getText());
    }

    private void onActionBtnCarrucel(ActionEvent event) throws IOException {
//        CarrucelViewController busquedaController = (CarrucelViewController) FlowController.getInstance().getController("CarrucelView");
//        busquedaController.mostrarCarrucel(tour.getFotos());
//        FlowController.getInstance().goViewInWindow("CarrucelView");
    }
}
