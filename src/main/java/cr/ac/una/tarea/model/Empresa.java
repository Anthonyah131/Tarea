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
public class Empresa {

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public SimpleStringProperty cedulaJuridica;
    public SimpleStringProperty logo;
    public SimpleStringProperty telefono;
    public SimpleStringProperty email;
    public SimpleStringProperty anioFundacion;

    public Empresa(Long id, String nombre, String cedulaJuridica, String logo, Long telefono, String email, Long anioFundacion) {
        this();
        this.id.set(id.toString());
        this.nombre.set(nombre);
        this.cedulaJuridica.set(cedulaJuridica);
        this.logo.set(logo);
        this.telefono.set(telefono.toString());
        this.email.set(email);
        this.anioFundacion.set(anioFundacion.toString());
    }

    public Empresa() {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.cedulaJuridica = new SimpleStringProperty();
        this.logo = new SimpleStringProperty();
        this.telefono = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.anioFundacion = new SimpleStringProperty();
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

    public String getCedulaJuridica() {
        return cedulaJuridica.get();
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica.set(cedulaJuridica);
    }

    public String getLogo() {
        return logo.get();
    }

    public void setLogo(String logo) {
        this.logo.set(logo);
    }

    public Long getTelefono() {
        if (id.get() != null && !id.get().isEmpty()) {
            return Long.valueOf(id.get());
        } else {
            return null;
        }
    }

    public void setTelefono(Long telefono) {
        this.telefono.set(telefono.toString());
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public Long getAnioFundacion() {
        if (id.get() != null && !id.get().isEmpty()) {
            return Long.valueOf(id.get());
        } else {
            return null;
        }
    }

    public void setAnioFundacion(Long anioFundacion) {
        this.anioFundacion.set(anioFundacion.toString());
    }
}
