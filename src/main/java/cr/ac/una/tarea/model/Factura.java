/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ANTHONY
 */
public class Factura {
    private int id;
    private Date fecha;
    private double montoTotal;
    private List<List<Tour>> toursComprados;
    private Cliente cliente;

    public Factura(int id, Date fecha, double montoTotal, List toursComprados, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.toursComprados = toursComprados;
        this.cliente = cliente;
    }

    // Métodos getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<List<Tour>> getToursComprados() {
        return toursComprados;
    }

    public void setToursComprados(List<List<Tour>> toursComprados) {
        this.toursComprados = toursComprados;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método para calcular el total de la factura
    public double calcularTotal() {
        double total = 0;
        for (List<Tour> list: toursComprados) {
            for(Tour tour : list)
                total += tour.getPrecio();
        }
        return total;
    }
}

