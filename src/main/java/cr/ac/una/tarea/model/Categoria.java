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

    public Categoria() {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
    }
    
    public Categoria(Categoria categoria) {
        this();
        this.id.set(categoria.getId().toString());
        this.nombre.set(categoria.getNombre());
    }

    public Categoria(Long id, String nombre) {
        this();
        this.id.set(id.toString());
        this.nombre.set(nombre);
    }
    
    public void setCategoria(Categoria categoria) {
        this.nombre.set(categoria.getNombre());
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

    @Override
    public String toString() {
        return nombre.get();
    }

    
}
