package cr.ac.una.tarea;

import cr.ac.una.tarea.model.Categoria;
import cr.ac.una.tarea.model.Cliente;
import cr.ac.una.tarea.model.Empresa;
import cr.ac.una.tarea.model.Itinerario;
import cr.ac.una.tarea.model.Tour;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        ObservableList<Itinerario> itinerarios = FXCollections.observableArrayList();

        Cliente cliente1 = new Cliente(1L, "Juan", "Perez", "123456789", "123456789", "juanperez@example.com", LocalDate.of(1984, 3, 1));
        clientes.add(cliente1);
        Cliente cliente2 = new Cliente(2L,"Maria", "Rodriguez", "987654321", "123456789", "maria@example.com", LocalDate.of(1992, 7, 25));
        clientes.add(cliente2);
        Cliente cliente3 = new Cliente(3L,"Pedro", "Gomez", "456789123", "123456789", "pedro@example.com", LocalDate.of(2005, 4, 12));
        clientes.add(cliente3);
        Cliente cliente4 = new Cliente(4L,"Laura", "Hernandez", "321654987", "123456789", "laura@example.com", LocalDate.of(1999, 2, 8));
        clientes.add(cliente4);
        Cliente cliente5 = new Cliente(5L,"Carlos", "Jimenez", "741852963", "123456789", "carlos@example.com", LocalDate.of(2002, 8, 10));
        clientes.add(cliente5);
        
        Itinerario itinerario1 = new Itinerario(1L,"San José", LocalDateTime.of(2023, 4, 1, 10, 0), LocalDateTime.of(2023, 4, 2, 18, 0), 8, 1, 9.928069, -84.090729);
        itinerarios.add(itinerario1);
        Itinerario itinerario2 = new Itinerario(2L, "Puerto Viejo", LocalDateTime.of(2023, 4, 3, 10, 0), LocalDateTime.of(2023, 4, 5, 18, 0), 2, 2, 9.654015, -82.757444);
        itinerarios.add(itinerario2);
        Itinerario itinerario3 = new Itinerario(3L, "Arenal", LocalDateTime.of(2023, 5, 1, 10, 0), LocalDateTime.of(2023, 5, 3, 18, 0), 2, 3, 10.463287, -84.703027);
        itinerarios.add(itinerario3);
        Itinerario itinerario4 = new Itinerario(4L, "Manuel Antonio", LocalDateTime.of(2023, 6, 1, 10, 0), LocalDateTime.of(2023, 6, 2, 18, 0), 8, 4, 9.394163, -84.136010);
        itinerarios.add(itinerario4);
        Itinerario itinerario5 = new Itinerario(5L, "Tortuguero", LocalDateTime.of(2023, 7, 1, 10, 0), LocalDateTime.of(2023, 7, 4, 18, 0), 3, 5, 10.544650, -83.505056);
        itinerarios.add(itinerario5);
        
        Categoria categoria1 = new Categoria(1L, "Aventura", "Tours para los amantes de la adrenalina");
        categorias.add(categoria1);
        Categoria categoria2 = new Categoria(2L, "Playa", "Tours para disfrutar del mar y la arena");
        categorias.add(categoria2);
        Categoria categoria3 = new Categoria(3L, "Naturaleza", "Tours para observar la fauna y flora del país");
        categorias.add(categoria3);
        Categoria categoria4 = new Categoria(4L, "Cultura", "Tours para conocer la historia y tradiciones del país");
        categorias.add(categoria4);
        Categoria categoria5 = new Categoria(5L, "Gastronomía", "Tours para degustar la comida típica del país");
        categorias.add(categoria5);

        Empresa empresa1 = new Empresa(1L, "Empresa A", "123456789", "logo1.jpg", "2222-2222", "empresaA@ejemplo.com", 2000);
        empresas.add(empresa1);
        Empresa empresa2 = new Empresa(2L, "Empresa B", "987654321", "logo2.jpg", "3333-3333", "empresaB@ejemplo.com", 1990);
        empresas.add(empresa2);
        Empresa empresa3 = new Empresa(3L,"Empresa C", "456789123", "logo3.jpg", "4444-4444", "empresaC@ejemplo.com", 2005);
        empresas.add(empresa3);
        Empresa empresa4 = new Empresa(4L, "Empresa D", "321654987", "logo4.jpg", "5555-5555", "empresaD@ejemplo.com", 1995);
        empresas.add(empresa4);
        Empresa empresa5 = new Empresa(5L, "Empresa E", "789123456", "logo5.jpg", "6666-6666", "empresaE@ejemplo.com", 2008);
        empresas.add(empresa5);

        // Instancia 1
        Tour tour1 = new Tour(1L, "Tour de Arenal", empresa5, categoria1, 50000L, LocalDate.of(2023, 4, 15), LocalDate.of(2023, 4, 20), itinerario1, 50L);
        tour1.setClientes(clientes);
        tours.add(tour1);

        // Instancia 2
        Tour tour2 = new Tour(2L, "Tour de Tortuguero", empresa5, categoria1, 60000L, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 5), itinerario1, 30L);
        tour1.setClientes(clientes);
        tours.add(tour2);

        // Instancia 3
        Tour tour3 = new Tour(3L, "Tour de Manuel Antonio", empresa5, categoria1, 75000L, LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 15), itinerario1, 20L);
        tours.add(tour3);

        // Instancia 4
        Tour tour4 = new Tour(4L, "Tour de Monteverde", empresa5, categoria1, 80000L, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5), itinerario1, 25L);
        tours.add(tour4);

        // Instancia 5
        Tour tour5 = new Tour(5L, "Tour de Guanacaste", empresa5, categoria1, 100000L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 10), itinerario1, 40L);
        tours.add(tour5);

        // Instancia 6
        Tour tour6 = new Tour(6L, "Tour de Limón", empresa5, categoria1, 90000L, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 5), itinerario1, 30L);
        tour1.setClientes(clientes);
        tours.add(tour6);

        // Instancia 7
        Tour tour7 = new Tour(7L, "Tour de Nicoya", empresa5, categoria1, 95000L, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 7), itinerario1, 35L);
        tours.add(tour7);

        // Instancia 8
        Tour tour8 = new Tour(8L, "Tour de Osa", empresa5, categoria1, 120000L, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 10), itinerario1, 50L);
        tour1.setClientes(clientes);
        tours.add(tour8);

        // Instancia 9
        Tour tour9 = new Tour(9L, "Tour de Sarapiquí", empresa5, categoria1, 55000L, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5), itinerario1, 20L);
        tours.add(tour9);

        // Instancia 10
        Tour tour10 = new Tour(10L, "Tour de Jacó", empresa5, categoria1, 70000L, LocalDate.of(2023, 11, 15), LocalDate.of(2023, 11, 20), itinerario1, 25L);
        tour1.setClientes(clientes);
        tours.add(tour10);

        // Instancia 11
        Tour tour11 = new Tour(11L, "Tour a la Selva", empresa5, categoria1, 60000L, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 7), itinerario1, 20L);
        tours.add(tour11);

        // Instancia 12      
        Tour tour12 = new Tour(12L, "Tour de las Aves", empresa5, categoria1, 50000L, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 7), itinerario1, 15L);
        tours.add(tour12);

        // Instancia 13       
        Tour tour13 = new Tour(13L, "Tour de la Montaña", empresa5, categoria1, 80000L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), itinerario1, 10L);
        tours.add(tour13);

        // Instancia 14     
        Tour tour14 = new Tour(14L, "Tour de los Volcanes", empresa5, categoria1, 75000L, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 7), itinerario1, 12L);
        tour1.setClientes(clientes);
        tours.add(tour14);

        // Instancia 15      
        Tour tour15 = new Tour(15L, "Tour de la Playa", empresa5, categoria1, 90000L, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 7), itinerario1, 25L);
        tours.add(tour15);

        AppContext.getInstance().set("ToursLista", tours);
        AppContext.getInstance().set("CategoriasLista", categorias);
        AppContext.getInstance().set("ClientesLista", clientes);
        AppContext.getInstance().set("EmpresasLista", empresas);
        AppContext.getInstance().set("ItinerariosLista", itinerarios);

        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image("cr/ac/una/tarea/resources/PuraVidaLogo1.png"));
        stage.setTitle("Tarea");
        FlowController.getInstance().goViewInWindow("InicioView");
    }

    public static void main(String[] args) {
        launch();
    }

}
