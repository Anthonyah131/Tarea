/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANTHONY
 */
public class Tour {
    private Long id;
    private String nombre;
    private Empresa empresa;
    private Categoria categoria;
    private Long precio;
    private LocalDate fechaSalida;
    private LocalDate fechaRegreso;
    private Itinerario itinerario;
    private Long cuposTotales;
    private Long cuposDisponibles;
    private List<Cliente> clientes;

    public Tour(Long id, String nombre,Empresa empresa, Categoria categoria, Long precio, LocalDate fechaSalida, LocalDate fechaRegreso, Itinerario itinerario, Long cuposTotales) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.categoria = categoria;
        this.precio = precio;
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
        this.itinerario = itinerario;
        this.cuposTotales = cuposTotales;
        this.cuposDisponibles = cuposTotales;
        this.clientes = new ArrayList<>();
    }
    
    public Tour() {
        this.clientes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(LocalDate fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public Long getCuposTotales() {
        return cuposTotales;
    }

    public void setCuposTotales(Long cuposTotales) {
        this.cuposTotales = cuposTotales;
    }

    public Long getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(Long cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
