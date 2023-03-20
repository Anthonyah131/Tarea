/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

/**
 *
 * @author ANTHONY
 */

public class Empresa {
    private Long id;
    private String nombre;
    private String cedulaJuridica;
    private String logo;
    private String telefono;
    private String email;
    private int anioFundacion;

    public Empresa(Long id, String nombre, String cedulaJuridica, String logo, String telefono, String email, int anioFundacion) {
        this.id = id;
        this.nombre = nombre;
        this.cedulaJuridica = cedulaJuridica;
        this.logo = logo;
        this.telefono = telefono;
        this.email = email;
        this.anioFundacion = anioFundacion;
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

    public String getCedulaJuridica() {
        return cedulaJuridica;
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

