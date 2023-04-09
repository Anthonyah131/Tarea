/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class Factura {
    private SimpleStringProperty id;
    private ObjectProperty<LocalDate> fecha;
    private SimpleStringProperty montoTotal;
    private Carrito carrito;
    private Cliente cliente;

    public Factura(LocalDate fecha, Long montoTotal, Carrito carrito, Cliente cliente) {
        this();
        if (this.fecha != null) {
            this.fecha.set(fecha);
        } else {
            this.fecha.set(null);
        }
        this.montoTotal.set(montoTotal.toString());
        this.carrito = carrito;
        this.cliente = cliente;
    }
    
    public Factura() {
        this.id = new SimpleStringProperty();
        this.fecha = new SimpleObjectProperty();
        this.montoTotal = new SimpleStringProperty();
        this.carrito = new Carrito();
        this.cliente = new Cliente();
    }

    // Métodos getters y setters
    public Long getId() {
        if (id.get() != null && !id.get().isEmpty()) {
            return Long.valueOf(id.get());
        } else {
            return null;
        }
    }

    public void setId(Long id) {
        this.id.set(id.toString());
    }

    public LocalDate getFecha() {
        return fecha.get();
    }

    public void setFecha(LocalDate fecha) {
        this.fecha.set(fecha);
    }

    public Long getMontoTotal() {
        if (montoTotal.get() != null && !montoTotal.get().isEmpty()) {
            return Long.valueOf(montoTotal.get());
        } else {
            return null;
        }
    }

    public void setMontoTotal(Long montoTotal) {
        this.montoTotal.set(montoTotal.toString());
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

