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
    private Long telefono;
    private String email;
    private Long anioFundacion;

    public Empresa(Long id, String nombre, String cedulaJuridica, String logo, Long telefono, String email, Long anioFundacion) {
        this.id = id;
        this.nombre = nombre;
        this.cedulaJuridica = cedulaJuridica;
        this.logo = logo;
        this.telefono = telefono;
        this.email = email;
        this.anioFundacion = anioFundacion;
    }
    
    public Empresa() {
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

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(Long anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

