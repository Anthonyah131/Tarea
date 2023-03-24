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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Itinerario {

    public SimpleStringProperty id;
    public SimpleStringProperty lugar;
    public ObjectProperty<LocalDate> fechaHoraLlegada;
    public ObjectProperty<LocalDate> fechaHoraSalida;
    public SimpleStringProperty duracionEnLugar;
    public SimpleStringProperty orden;
    public SimpleStringProperty coordenadasLatitud;
    public SimpleStringProperty coordenadasLongitud;

    public Itinerario(Long id, String lugar, LocalDateTime fechaHoraLlegada, LocalDateTime fechaHoraSalida, Long duracionEnLugar, Long orden, String coordenadasLatitud, String coordenadasLongitud) {
        this();
        this.id.set(id.toString());
        this.lugar.set(lugar);
        this.fechaHoraLlegada.set(LocalDate.MIN);
        this.fechaHoraSalida.set(LocalDate.MIN);
        this.duracionEnLugar.set(duracionEnLugar.toString());
        this.orden.set(orden.toString());
        this.coordenadasLatitud.set(coordenadasLatitud);
        this.coordenadasLongitud.set(coordenadasLongitud);
    }
    
    public Itinerario() {
        this.id = new SimpleStringProperty();
        this.lugar = new SimpleStringProperty();
        this.fechaHoraLlegada = new SimpleObjectProperty();
        this.fechaHoraSalida = new SimpleObjectProperty();
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

//    public LocalDateTime getFechaHoraLlegada() {
//        return fechaHoraLlegada.get();
//    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada.set(LocalDate.MIN);
    }

//    public LocalDateTime getFechaHoraSalida() {
//        return fechaHoraSalida.get();
//    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida.set(LocalDate.MIN);
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
