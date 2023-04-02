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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        List<Itinerario> itinerarios = new ArrayList<>();
        List<Image> fotos = new ArrayList<>();
        List<Cliente> cleinetesArrayL = new ArrayList<>();

        Cliente cliente1 = new Cliente(1L, "Juan", "Perez", "123456789", "123456789", "juanperez@example.com", LocalDate.of(1984, 3, 1));
        clientes.add(cliente1);
        cleinetesArrayL.add(cliente1);
        Cliente cliente2 = new Cliente(2L, "Maria", "Rodriguez", "987654321", "123456789", "maria@example.com", LocalDate.of(1992, 7, 25));
        clientes.add(cliente2);
        cleinetesArrayL.add(cliente2);
        Cliente cliente3 = new Cliente(3L, "Pedro", "Gomez", "456789123", "123456789", "pedro@example.com", LocalDate.of(2005, 4, 12));
        clientes.add(cliente3);
        cleinetesArrayL.add(cliente3);
        Cliente cliente4 = new Cliente(4L, "Laura", "Hernandez", "321654987", "123456789", "laura@example.com", LocalDate.of(1999, 2, 8));
        clientes.add(cliente4);
        cleinetesArrayL.add(cliente4);
        Cliente cliente5 = new Cliente(5L, "Carlos", "Jimenez", "741852963", "123456789", "carlos@example.com", LocalDate.of(2002, 8, 10));
        clientes.add(cliente5);
        cleinetesArrayL.add(cliente5);

        Itinerario itinerario1 = new Itinerario(1L, "San José", LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 2), 8L, 1L, "9.928069", "-84.090729");
        itinerarios.add(itinerario1);
        Itinerario itinerario2 = new Itinerario(2L, "Puerto Viejo", LocalDate.of(2023, 4, 3), LocalDate.of(2023, 4, 5), 2L, 2L, "9.654015", "-82.757444");
        itinerarios.add(itinerario2);
        Itinerario itinerario3 = new Itinerario(3L, "Arenal", LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 3), 2L, 3L, "10.463287", "-84.703027");
        itinerarios.add(itinerario3);
        Itinerario itinerario4 = new Itinerario(4L, "Manuel Antonio", LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 2), 8L, 4L, "9.394163", "-84.136010");
        itinerarios.add(itinerario4);
        Itinerario itinerario5 = new Itinerario(5L, "Tortuguero", LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 4), 3L, 5L, "10.544650", "-83.505056");
        itinerarios.add(itinerario5);

        Categoria categoria1 = new Categoria(1L, "Aventura");
        categorias.add(categoria1);
        Categoria categoria2 = new Categoria(2L, "Playa");
        categorias.add(categoria2);
        Categoria categoria3 = new Categoria(3L, "Naturaleza");
        categorias.add(categoria3);
        Categoria categoria4 = new Categoria(4L, "Cultura");
        categorias.add(categoria4);
        Categoria categoria5 = new Categoria(5L, "Gastronomía");
        categorias.add(categoria5);

        Image image1 = new Image("cr/ac/una/tarea/resources/PuraVidaLogo.png");
        Image image2 = new Image("cr/ac/una/tarea/resources/PuraVidaLogo1.png");
        Image image3 = new Image("cr/ac/una/tarea/resources/MenuIcon.png");
        Image image4 = new Image("cr/ac/una/tarea/resources/Playa.jpg");
        Image image5 = new Image("cr/ac/una/tarea/resources/Playa2.jpg");
        Image image6 = new Image("cr/ac/una/tarea/resources/Playa3.jpg");

        fotos.add(image1);
        fotos.add(image2);
        fotos.add(image3);
        fotos.add(image4);
        fotos.add(image5);
        fotos.add(image6);

        Empresa empresa1 = new Empresa(1L, "Empresa A", "123456789", image1, 22222222L, "empresaA@ejemplo.com", 2000L);
        empresas.add(empresa1);
        Empresa empresa2 = new Empresa(2L, "Empresa B", "987654321", image2, 33333333L, "empresaB@ejemplo.com", 1990L);
        empresas.add(empresa2);
        Empresa empresa3 = new Empresa(3L, "Empresa C", "456789123", image3, 44444444L, "empresaC@ejemplo.com", 2005L);
        empresas.add(empresa3);
        Empresa empresa4 = new Empresa(4L, "Empresa D", "321654987", image1, 55555555L, "empresaD@ejemplo.com", 1995L);
        empresas.add(empresa4);
        Empresa empresa5 = new Empresa(5L, "Empresa E", "789123456", image2, 66666666L, "empresaE@ejemplo.com", 2008L);
        empresas.add(empresa5);

        // Instancia 1
        Tour tour1 = new Tour(1L, "Arenal", empresa3, categoria1, 50000L, LocalDate.of(2023, 4, 15), LocalDate.of(2023, 4, 20), new ArrayList<>(), 50L, cleinetesArrayL, fotos);
        tour1.setClientes(clientes);
        tours.add(tour1);

        // Instancia 2
        Tour tour2 = new Tour(2L, "Tortuguero", empresa5, categoria2, 60000L, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 5), new ArrayList<>(), 30L, cleinetesArrayL, fotos);
        tour1.setClientes(clientes);
        tours.add(tour2);

        // Instancia 3
        Tour tour3 = new Tour(3L, "Manuel Antonio", empresa5, categoria3, 75000L, LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 15), new ArrayList<>(), 20L, cleinetesArrayL, fotos);
        tours.add(tour3);

        // Instancia 4
        Tour tour4 = new Tour(4L, "Monteverde", empresa5, categoria1, 80000L, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 5), new ArrayList<>(), 25L, cleinetesArrayL, fotos);
        tours.add(tour4);

        // Instancia 5
        Tour tour5 = new Tour(5L, "Guanacaste", empresa5, categoria5, 100000L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 10), new ArrayList<>(), 40L, cleinetesArrayL, fotos);
        tours.add(tour5);

        // Instancia 6
        Tour tour6 = new Tour(6L, "Limón", empresa2, categoria5, 90000L, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 5), new ArrayList<>(), 30L, cleinetesArrayL, fotos);
        tour1.setClientes(clientes);
        tours.add(tour6);

        // Instancia 7
        Tour tour7 = new Tour(7L, "Nicoya", empresa5, categoria1, 95000L, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 7), new ArrayList<>(), 35L, cleinetesArrayL, fotos);
        tours.add(tour7);

        // Instancia 8
        Tour tour8 = new Tour(8L, "Osa", empresa1, categoria5, 120000L, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 10), new ArrayList<>(), 50L, cleinetesArrayL, fotos);
        tour1.setClientes(clientes);
        tours.add(tour8);

        // Instancia 9
        Tour tour9 = new Tour(9L, "Sarapiquí", empresa5, categoria4, 55000L, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5), new ArrayList<>(), 20L, cleinetesArrayL, fotos);
        tours.add(tour9);

        // Instancia 10
        Tour tour10 = new Tour(10L, "Jacó", empresa3, categoria1, 70000L, LocalDate.of(2023, 11, 15), LocalDate.of(2023, 11, 20), new ArrayList<>(), 25L, cleinetesArrayL, fotos);
        tour1.setClientes(clientes);
        tours.add(tour10);

        // Instancia 11
        Tour tour11 = new Tour(11L, "Selva", empresa2, categoria4, 60000L, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 7), new ArrayList<>(), 20L, cleinetesArrayL, fotos);
        tours.add(tour11);

        // Instancia 12      
        Tour tour12 = new Tour(12L, "Aves", empresa5, categoria3, 50000L, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 7), new ArrayList<>(), 15L, cleinetesArrayL, fotos);
        tours.add(tour12);

        // Instancia 13       
        Tour tour13 = new Tour(13L, "Montaña", empresa2, categoria2, 80000L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), new ArrayList<>(), 10L, cleinetesArrayL, fotos);
        tours.add(tour13);

        // Instancia 14     
        Tour tour14 = new Tour(14L, "Volcanes", empresa5, categoria1, 75000L, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 7), new ArrayList<>(), 12L, cleinetesArrayL, fotos);
        tour1.setClientes(clientes);
        tours.add(tour14);

        // Instancia 15      
        Tour tour15 = new Tour(15L, "Playa", empresa1, categoria3, 90000L, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 7), itinerarios, 25L, cleinetesArrayL, fotos);
        tours.add(tour15);

        Long contador[] = {5L, 5L, 5L, 15L, 5L};

        AppContext.getInstance().set("Contador", contador);
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
