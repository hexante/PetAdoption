package com.example.petadoption;

public class EventosFundaciones {

    String idUsuario,NombreEvent,Fecha,Descripcion;

    public EventosFundaciones(String idUsuario, String nombreEvent, String fecha, String descripcion) {
        this.idUsuario = idUsuario;
        NombreEvent = nombreEvent;
        Fecha = fecha;
        Descripcion = descripcion;
    }

    public EventosFundaciones() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreEvent() {
        return NombreEvent;
    }

    public void setNombreEvent(String nombreEvent) {
        NombreEvent = nombreEvent;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
