/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANTHONY
 */
public class Carrito {
    private List<Tour> tours;
    private int cantidad;

    public Carrito() {
        tours = new ArrayList<>();
        cantidad = 0;
    }

    public void agregarTour(Tour tour) {
        tours.add(tour);
        cantidad++;
    }

    public void eliminarTour(int index) {
        tours.remove(index);
        cantidad--;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        double total = 0;
        for (Tour tour : tours) {
            total += tour.getPrecio();
        }
        return total;
    }

    public void vaciar() {
        tours.clear();
        cantidad = 0;
    }
}