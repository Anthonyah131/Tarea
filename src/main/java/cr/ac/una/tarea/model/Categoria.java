/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class Categoria {

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public SimpleStringProperty descripcion;

    public Categoria() {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.descripcion = new SimpleStringProperty();
    }

    public Categoria(Long id, String nombre, String descripcion) {
        this();
        this.id.set(id.toString());
        this.nombre.set(nombre);
        this.descripcion.set(descripcion);
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

    public void setNombre(String empNombre) {
        this.nombre.set(empNombre);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

}
