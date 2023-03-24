/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class Tour {
    private SimpleStringProperty id;
    private SimpleStringProperty nombre;
    private Empresa empresa;
    private Categoria categoria;
    private SimpleStringProperty precio;
    private ObjectProperty<LocalDate> fechaSalida;
    private ObjectProperty<LocalDate> fechaRegreso;
    private Itinerario itinerario;
    private SimpleStringProperty cuposTotales;
    private SimpleStringProperty cuposDisponibles;
    private List<Cliente> clientes;

    public Tour(Long id, String nombre,Empresa empresa, Categoria categoria, Long precio, LocalDate fechaSalida, LocalDate fechaRegreso, Itinerario itinerario, Long cuposTotales) {
        this();
        this.id.set(id.toString());
        this.nombre.set(nombre);
        this.empresa = empresa;
        this.categoria = categoria;
        this.precio.set(nombre);
        if (this.fechaSalida != null) {
            this.fechaSalida.set(fechaSalida);
        } else {
            this.fechaSalida.set(null);
        }
        if (this.fechaRegreso != null) {
            this.fechaRegreso.set(fechaRegreso);
        } else {
            this.fechaRegreso.set(null);
        }
        this.itinerario = itinerario;
        this.cuposTotales.set(cuposTotales.toString());
        this.cuposDisponibles.set(cuposDisponibles.toString());
        this.clientes = new ArrayList<>();
    }
    
    public Tour() {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.empresa = null;
        this.categoria = null;
        this.precio = new SimpleStringProperty();
        this.fechaSalida = new SimpleObjectProperty();
        this.fechaRegreso = new SimpleObjectProperty();
        this.itinerario = null;
        this.cuposTotales = new SimpleStringProperty();
        this.cuposDisponibles = new SimpleStringProperty();
        this.clientes = new ArrayList<>();
    }

    public Long getId() {
        if (id.get() != null && !id.get().isEmpty()) {
            return Long.valueOf(id.get());
        } else {
            return null;
        }
    }
    
    public void setId(Long Id) {
        this.id.set(Id.toString());
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio.get();
    }

    public void setPrecio(String precio) {
        this.precio.set(precio);
    }

    public LocalDate getFechaSalida() {
        return fechaSalida.get();
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }

    public LocalDate getFechaRegreso() {
        return fechaRegreso.get();
    }

    public void setFechaRegreso(LocalDate fechaRegreso) {
        this.fechaRegreso.set(fechaRegreso);
    }

    public Itinerario getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public Long getCuposTotales() {
        if (this.cuposTotales.get() != null && !this.cuposTotales.get().isEmpty()) {
            return Long.valueOf(this.cuposTotales.get());
        } else {
            return null;
        }
    }

    public void setCuposTotales(Long cuposTotales) {
        this.cuposTotales.set(cuposTotales.toString());
    }

    public Long getCuposDisponibles() {
        if (this.cuposDisponibles.get() != null && !this.cuposDisponibles.get().isEmpty()) {
            return Long.valueOf(this.cuposDisponibles.get());
        } else {
            return null;
        }
    }

    public void setCuposDisponibles(Long cuposDisponibles) {
        this.cuposDisponibles.set(cuposDisponibles.toString());
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
