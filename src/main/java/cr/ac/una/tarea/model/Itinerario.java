/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.model;

/**
 *
 * @author ANTHONY
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Itinerario {

    public SimpleStringProperty id;
    public SimpleStringProperty lugar;
    public ObjectProperty<LocalDate> fechaLlegada;
    public ObjectProperty<LocalDate> fechaSalida;
    public SimpleStringProperty duracionEnLugar;
    public SimpleStringProperty orden;
    public SimpleStringProperty coordenadasLatitud;
    public SimpleStringProperty coordenadasLongitud;

    public Itinerario(Long id, String lugar, LocalDate fechaLlegada, LocalDate fechaSalida, Long duracionEnLugar, Long orden, String coordenadasLatitud, String coordenadasLongitud) {
        this();
        this.id.set(id.toString());
        this.lugar.set(lugar);
        this.fechaLlegada.set(fechaLlegada);
        this.fechaSalida.set(fechaSalida);
        this.duracionEnLugar.set(duracionEnLugar.toString());
        this.orden.set(orden.toString());
        this.coordenadasLatitud.set(coordenadasLatitud);
        this.coordenadasLongitud.set(coordenadasLongitud);
    }

    public Itinerario(Itinerario itinerario) {
        this();
        this.id.set(itinerario.getId().toString());
        this.lugar.set(itinerario.getLugar());
        this.duracionEnLugar.set(itinerario.getDuracionEnLugar());
        this.orden.set(itinerario.getOrden());
        this.coordenadasLatitud.set(itinerario.getCoordenadasLatitud());
        this.coordenadasLongitud.setValue(itinerario.getCoordenadasLongitud());
        this.fechaSalida.set(itinerario.getFechaSalida());
        this.fechaLlegada.set(itinerario.getFechaLlegada());
    }

    public Itinerario() {
        this.id = new SimpleStringProperty();
        this.lugar = new SimpleStringProperty();
        this.fechaLlegada = new SimpleObjectProperty();
        this.fechaSalida = new SimpleObjectProperty();
        this.duracionEnLugar = new SimpleStringProperty();
        this.orden = new SimpleStringProperty();
        this.coordenadasLatitud = new SimpleStringProperty();
        this.coordenadasLongitud = new SimpleStringProperty();
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

    public String getLugar() {
        return lugar.get();
    }

    public void setLugar(String lugar) {
        this.lugar.set(lugar);
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada.get();
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada.set(fechaLlegada);
    }

    public LocalDate getFechaSalida() {
        return fechaSalida.get();
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }

    public String getDuracionEnLugar() {
        return duracionEnLugar.get();
    }

    public void setDuracionEnLugar(Long duracionEnLugar) {
        this.duracionEnLugar.set(duracionEnLugar.toString());
    }

    public String getOrden() {
        return orden.get();
    }

    public void setOrden(Long orden) {
        this.orden.set(orden.toString());
    }

    public String getCoordenadasLatitud() {
        return coordenadasLatitud.get();
    }

    public void setCoordenadasLatitud(Long coordenadasLatitud) {
        this.coordenadasLatitud.set(coordenadasLatitud.toString());
    }

    public String getCoordenadasLongitud() {
        return coordenadasLongitud.get();
    }

    public void setCoordenadasLongitud(Long coordenadasLongitud) {
        this.coordenadasLongitud.set(coordenadasLongitud.toString());
    }
}
