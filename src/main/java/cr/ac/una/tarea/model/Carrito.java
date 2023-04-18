/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

import cr.ac.una.tarea.util.AppContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ANTHONY
 */
public class Carrito {

    private List<Object[]> tours;
    private int cantidad;

    public Carrito() {
        tours = new ArrayList<>();
    }

    public void agregarTour(Tour tourNuevo, int cantidad) {
        boolean encontrado = false;
        for (Object[] tou : tours) {
            Tour tour = (Tour) tou[0];
            if (tourNuevo.getId().equals(tour.getId())) {
                int canti = (int) tou[1];
                int nuevaCantidad = canti + cantidad;
                tou[1] = nuevaCantidad;
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            Object[] tour = new Object[]{tourNuevo, cantidad};
            tours.add(tour);
        }
    }

    public void eliminarTour(Tour index) {
        ObservableList<Tour> toursLista = FXCollections.observableArrayList();
        toursLista.addAll((List<Tour>) AppContext.getInstance().get("ToursLista"));
        
        for (int i = 0; i < tours.size(); i++) {
            Tour tour = (Tour) tours.get(i)[0];
            if (index.getId().equals(tour.getId())) {
                for(Tour tou : toursLista) {
                    if(Objects.equals(tou.getId(), tour.getId())) {
                        int canti = (int) tours.get(i)[1];
                        long cant = canti;
                        tou.setCuposDisponibles(tou.getCuposDisponibles() + cant);
                    }
                }
                tours.remove(i);
                break;
            }
        }
    }
    
    public int getTour(String nombre) {
        for (int i = 0; i < tours.size(); i++) {
            Tour tour = (Tour) tours.get(i)[0];
            if (nombre.equals(tour.getNombre())) {
                return (int) tours.get(i)[1];
            }
        }
        return 0;
    }

    public List<Object[]> getTours() {
        return tours;
    }

    public int getCantidad() {
        cantidad = 0;
        for (Object[] tour : tours) {
            int n = (int) tour[1];
            cantidad += n;
        }
        return cantidad;
    }

    public double getTotal() {
        double total = 0;
        for(Object[] tour : tours) {
            Tour tou = (Tour) tour[0];
            int cant = (int) tour[1];
            total += tou.getPrecio() * cant;
        }
        return total;
    }
    public void vaciar() {
        tours.clear();
    }
}
