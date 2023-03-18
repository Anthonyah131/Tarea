package cr.ac.una.tarea;

import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<Tour> tours = new ArrayList<>();

        // Instancia 1
        Tour tour1 = new Tour("Tour de Arenal", null, 50000, LocalDate.of(2023, 4, 15), LocalDate.of(2023, 4, 20), null, 50);
        tours.add(tour1);

        // Instancia 2
        Tour tour2 = new Tour("Tour de Tortuguero", null, 60000, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 5), null, 30);
        tours.add(tour2);

        // Instancia 3
        Tour tour3 = new Tour("Tour de Manuel Antonio", null, 75000, LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 15), null, 20);
        tours.add(tour3);

        // Instancia 4
        Tour tour4 = new Tour("Tour de Monteverde", null, 80000, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5), null, 25);
        tours.add(tour4);

        // Instancia 5
        Tour tour5 = new Tour("Tour de Guanacaste", null, 100000, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 10), null, 40);
        tours.add(tour5);

        // Instancia 6
        Tour tour6 = new Tour("Tour de Limón", null, 90000, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 5), null, 30);
        tours.add(tour6);

        // Instancia 7
        Tour tour7 = new Tour("Tour de Nicoya", null, 95000, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 7), null, 35);
        tours.add(tour7);

        // Instancia 8
        Tour tour8 = new Tour("Tour de Osa", null, 120000, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 10), null, 50);
        tours.add(tour8);

        // Instancia 9
        Tour tour9 = new Tour("Tour de Sarapiquí", null, 55000, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5), null, 20);
        tours.add(tour9);

        // Instancia 10
        Tour tour10 = new Tour("Tour de Jacó", null, 70000, LocalDate.of(2023, 11, 15), LocalDate.of(2023, 11, 20), null, 25);
        tours.add(tour10);

        // Instancia 11
        Tour tour11 = new Tour("Tour a la Selva", null, 60000, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 7), null, 20);
        tours.add(tour11);

        // Instancia 12      
        Tour tour12 = new Tour("Tour de las Aves", null, 50000, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 7), null, 15);
        tours.add(tour12);

        // Instancia 13       
        Tour tour13 = new Tour("Tour de la Montaña", null, 80000, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), null, 10);
        tours.add(tour13);

        // Instancia 14     
        Tour tour14 = new Tour("Tour de los Volcanes", null, 75000, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 7), null, 12);
        tours.add(tour14);

        // Instancia 15      
        Tour tour15 = new Tour("Tour de la Playa", null, 90000, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 7), null, 25);
        tours.add(tour15);
        
        AppContext.getInstance().set("ToursLista", tours);

        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image("cr/ac/una/tarea/resources/PuraVidaLogo1.png"));
        stage.setTitle("Tarea");
        FlowController.getInstance().goViewInWindow("InicioView");
    }

    public static void main(String[] args) {
        launch();
    }

}
