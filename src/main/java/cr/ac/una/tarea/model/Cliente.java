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
public class Cliente {

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public SimpleStringProperty apellido;
    public SimpleStringProperty cedula;
    public SimpleStringProperty telefono;
    public SimpleStringProperty correo;
    public ObjectProperty<LocalDate> fechaNacimiento;
    
    public Cliente() {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.apellido = new SimpleStringProperty();
        this.cedula = new SimpleStringProperty();
        this.telefono = new SimpleStringProperty();
        this.correo = new SimpleStringProperty();
        this.fechaNacimiento = new SimpleObjectProperty();
    }
    
    public Cliente(Long id, String nombre, String apellido, String cedula, String telefono, String correo, LocalDate fechaNacimiento) {
        this();
        this.id.set(id.toString());
        this.nombre.set(nombre);
        this.apellido.set(apellido);
        this.cedula.set(cedula);
        this.telefono.set(telefono);
        this.correo.set(correo);
        if (fechaNacimiento != null) {
            this.fechaNacimiento.set(fechaNacimiento);
        } else {
            this.fechaNacimiento.set(null);
        }
    }
    
    public Cliente(Cliente cliente) {
        this();
        this.id.set(cliente.getId().toString());
        this.nombre.set(cliente.getNombre());
        this.apellido.set(cliente.getApellido());
        this.cedula.set(cliente.getCedula());
        this.telefono.set(cliente.getTelefono());
        this.correo.set(cliente.getCorreo());
        if (fechaNacimiento != null) {
            this.fechaNacimiento.set(cliente.getFechaNacimiento());
        } else {
            this.fechaNacimiento.set(null);
        }
    }
    public void setCliente(Cliente cliente) {
        this.nombre.set(cliente.getNombre());
        this.apellido.set(cliente.getApellido());
        this.cedula.set(cliente.getCedula());
        this.telefono.set(cliente.getTelefono());
        this.correo.set(cliente.getCorreo());
        if (fechaNacimiento != null) {
            this.fechaNacimiento.set(cliente.getFechaNacimiento());
        } else {
            this.fechaNacimiento.set(null);
        }
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
    
    public String getApellido() {
        return apellido.get();
    }
    
    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }
    
    public String getCedula() {
        return cedula.get();
    }
    
    public void setCedula(String cedula) {
        this.cedula.set(cedula);
    }
    
    public String getTelefono() {
        return telefono.get();
    }
    
    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }
    
    public String getCorreo() {
        return correo.get();
    }
    
    public void setCorreo(String correo) {
        this.correo.set(correo);
    }
    
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento.get();
    }
    
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento.set(fechaNacimiento);
    }
    
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
