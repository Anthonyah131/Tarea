package cr.ac.una.tarea;

import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.model.Empresa;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ObservableList<Tour> tours = FXCollections.observableArrayList();
        ObservableList<Empresa> empresas = FXCollections.observableArrayList();
        ObservableList<Categoria> categorias = FXCollections.observableArrayList();

        Categoria categoria1 = new Categoria(1, "Aventura", "Tours para los amantes de la adrenalina");
        categorias.add(categoria1);
        Categoria categoria2 = new Categoria(2, "Playa", "Tours para disfrutar del mar y la arena");
        categorias.add(categoria2);
        Categoria categoria3 = new Categoria(3, "Naturaleza", "Tours para observar la fauna y flora del país");
        categorias.add(categoria3);
        Categoria categoria4 = new Categoria(4, "Cultura", "Tours para conocer la historia y tradiciones del país");
        categorias.add(categoria4);
        Categoria categoria5 = new Categoria(5, "Gastronomía", "Tours para degustar la comida típica del país");
        categorias.add(categoria5);

        Empresa empresa1 = new Empresa("Empresa A", "123456789", "logo1.jpg", "2222-2222", "empresaA@ejemplo.com", 2000);
        empresas.add(empresa1);
        Empresa empresa2 = new Empresa("Empresa B", "987654321", "logo2.jpg", "3333-3333", "empresaB@ejemplo.com", 1990);
        empresas.add(empresa2);
        Empresa empresa3 = new Empresa("Empresa C", "456789123", "logo3.jpg", "4444-4444", "empresaC@ejemplo.com", 2005);
        empresas.add(empresa3);
        Empresa empresa4 = new Empresa("Empresa D", "321654987", "logo4.jpg", "5555-5555", "empresaD@ejemplo.com", 1995);
        empresas.add(empresa4);
        Empresa empresa5 = new Empresa("Empresa E", "789123456", "logo5.jpg", "6666-6666", "empresaE@ejemplo.com", 2008);
        empresas.add(empresa5);

        // Instancia 1
        Tour tour1 = new Tour("Tour de Arenal", empresa5, categoria1, 50000L, LocalDate.of(2023, 4, 15), LocalDate.of(2023, 4, 20), null, 50L);
        tours.add(tour1);

        // Instancia 2
        Tour tour2 = new Tour("Tour de Tortuguero", empresa5, categoria1, 60000L, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 5), null, 30L);
        tours.add(tour2);

        // Instancia 3
        Tour tour3 = new Tour("Tour de Manuel Antonio", empresa5, categoria1, 75000L, LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 15), null, 20L);
        tours.add(tour3);

        // Instancia 4
        Tour tour4 = new Tour("Tour de Monteverde", empresa5, categoria1, 80000L, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5), null, 25L);
        tours.add(tour4);

        // Instancia 5
        Tour tour5 = new Tour("Tour de Guanacaste", empresa5, categoria1, 100000L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 10), null, 40L);
        tours.add(tour5);

        // Instancia 6
        Tour tour6 = new Tour("Tour de Limón", empresa5, categoria1, 90000L, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 5), null, 30L);
        tours.add(tour6);

        // Instancia 7
        Tour tour7 = new Tour("Tour de Nicoya", empresa5, categoria1, 95000L, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 7), null, 35L);
        tours.add(tour7);

        // Instancia 8
        Tour tour8 = new Tour("Tour de Osa", empresa5, categoria1, 120000L, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 10), null, 50L);
        tours.add(tour8);

        // Instancia 9
        Tour tour9 = new Tour("Tour de Sarapiquí", empresa5, categoria1, 55000L, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5), null, 20L);
        tours.add(tour9);

        // Instancia 10
        Tour tour10 = new Tour("Tour de Jacó", empresa5, categoria1, 70000L, LocalDate.of(2023, 11, 15), LocalDate.of(2023, 11, 20), null, 25L);
        tours.add(tour10);

        // Instancia 11
        Tour tour11 = new Tour("Tour a la Selva", empresa5, categoria1, 60000L, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 7), null, 20L);
        tours.add(tour11);

        // Instancia 12      
        Tour tour12 = new Tour("Tour de las Aves", empresa5, categoria1, 50000L, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 7), null, 15L);
        tours.add(tour12);

        // Instancia 13       
        Tour tour13 = new Tour("Tour de la Montaña", empresa5, categoria1, 80000L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), null, 10L);
        tours.add(tour13);

        // Instancia 14     
        Tour tour14 = new Tour("Tour de los Volcanes", empresa5, categoria1, 75000L, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 7), null, 12L);
        tours.add(tour14);

        // Instancia 15      
        Tour tour15 = new Tour("Tour de la Playa", empresa5, categoria1, 90000L, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 7), null, 25L);
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
