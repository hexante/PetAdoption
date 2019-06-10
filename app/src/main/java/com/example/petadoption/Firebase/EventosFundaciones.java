package com.example.petadoption.Firebase;

public class EventosFundaciones {

    String IdEvento, idFundacion,NombreEvent,FotoEvento,Fecha,Descripcion,Lugar;

    public EventosFundaciones(String idEvento, String idfundacion, String nombreEvent,String fotoEvento, String fecha, String descripcion,String lugar) {
        idFundacion = idfundacion;
        IdEvento = idEvento;
        NombreEvent = nombreEvent;
        FotoEvento = fotoEvento;
        Fecha = fecha;
        Descripcion = descripcion;
        Lugar = lugar;
    }

    public EventosFundaciones() {
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getIdEvento() {
        return IdEvento;
    }

    public void setIdEvento(String idEvento) {
        this.IdEvento = idEvento;
    }

    public String getIdFundacion() {
        return idFundacion;
    }

    public void setIdFundacion(String idFundacion) {
        this.idFundacion = idFundacion;
    }

    public String getFotoEvento() {
        return FotoEvento;
    }

    public void setFotoEvento(String fotoEvento) {
        FotoEvento = fotoEvento;
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
