/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

/**
 *
 * @author ANTHONY
 */

import java.time.LocalDateTime;

public class Itinerario {
    private Tour tour;
    private String lugar;
    private LocalDateTime fechaHoraLlegada;
    private LocalDateTime fechaHoraSalida;
    private int duracionEnLugar;
    private int orden;
    private double coordenadasLatitud;
    private double coordenadasLongitud;

    // Constructor
    public Itinerario(Tour tour, String lugar, LocalDateTime fechaHoraLlegada, LocalDateTime fechaHoraSalida, int duracionEnLugar, int orden, double coordenadasLatitud, double coordenadasLongitud) {
        this.tour = tour;
        this.lugar = lugar;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.fechaHoraSalida = fechaHoraSalida;
        this.duracionEnLugar = duracionEnLugar;
        this.orden = orden;
        this.coordenadasLatitud = coordenadasLatitud;
        this.coordenadasLongitud = coordenadasLongitud;
    }

    // Getters y Setters
    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public int getDuracionEnLugar() {
        return duracionEnLugar;
    }

    public void setDuracionEnLugar(int duracionEnLugar) {
        this.duracionEnLugar = duracionEnLugar;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public double getCoordenadasLatitud() {
        return coordenadasLatitud;
    }

    public void setCoordenadasLatitud(double coordenadasLatitud) {
        this.coordenadasLatitud = coordenadasLatitud;
    }

    public double getCoordenadasLongitud() {
        return coordenadasLongitud;
    }

    public void setCoordenadasLongitud(double coordenadasLongitud) {
        this.coordenadasLongitud = coordenadasLongitud;
    }
}
