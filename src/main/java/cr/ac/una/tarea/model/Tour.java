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
import javafx.scene.image.Image;

/**
 *
 * @author ANTHONY
 */
public class Tour {
    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public Empresa empresa;
    public Categoria categoria;
    public SimpleStringProperty precio;
    public ObjectProperty<LocalDate> fechaSalida;
    public ObjectProperty<LocalDate> fechaRegreso;
    public ObjectProperty<Itinerario> itinerario;
    public SimpleStringProperty cuposTotales;
    public SimpleStringProperty cuposDisponibles;
    public List<Cliente> clientes;
    public List<Image> fotos;

    public Tour(Long id, String nombre,Empresa empresa, Categoria categoria, Long precio, LocalDate fechaSalida, LocalDate fechaRegreso, Itinerario itinerario, Long cuposTotales, List fotos) {
        this();
        this.id.set(id.toString());
        this.nombre.set(nombre);
        this.empresa = empresa;
        this.categoria = categoria;
        this.precio.set(precio.toString());
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
        this.itinerario.set(itinerario);
        this.cuposTotales.set(cuposTotales.toString());
        this.cuposDisponibles.set(cuposTotales.toString());
        this.clientes = new ArrayList<>();
        this.fotos = fotos;
    }
    
    public Tour() {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.empresa = new Empresa();
        this.categoria = new Categoria();
        this.precio = new SimpleStringProperty();
        this.fechaSalida = new SimpleObjectProperty();
        this.fechaRegreso = new SimpleObjectProperty();
        this.itinerario = new SimpleObjectProperty();
        this.cuposTotales = new SimpleStringProperty();
        this.cuposDisponibles = new SimpleStringProperty();
        this.clientes = new ArrayList<>();
        this.fotos = new ArrayList<>();
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

    public Long getPrecio() {
        if (this.precio.get() != null && !this.precio.get().isEmpty()) {
            return Long.valueOf(this.precio.get());
        } else {
            return null;
        }
    }

    public void setPrecio(Long precio) {
        this.precio.set(precio.toString());
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
        return itinerario.get();
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario.set(itinerario);
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

    public List<Image> getFotos() {
        return fotos;
    }

    public void setFotos(List<Image> fotos) {
        this.fotos = fotos;
    }
}
