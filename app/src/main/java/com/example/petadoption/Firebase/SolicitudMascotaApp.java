package com.example.petadoption.Firebase;

public class SolicitudMascotaApp {

    String IdSolicitud,IdUsuario,IdFundacion,IdMascota,EstadoSolicitud;

    public SolicitudMascotaApp(String idSolicitud, String idUsuario, String idFundacion, String idMascota, String estadoSolicitud) {
        IdSolicitud = idSolicitud;
        IdUsuario = idUsuario;
        IdFundacion = idFundacion;
        IdMascota = idMascota;
        EstadoSolicitud = estadoSolicitud;
    }

    public SolicitudMascotaApp() {
    }

    public String getIdSolicitud() {
        return IdSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        IdSolicitud = idSolicitud;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getIdFundacion() {
        return IdFundacion;
    }

    public void setIdFundacion(String idFundacion) {
        IdFundacion = idFundacion;
    }

    public String getIdMascota() {
        return IdMascota;
    }

    public void setIdMascota(String idMascota) {
        IdMascota = idMascota;
    }

    public String getEstadoSolicitud() {
        return EstadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        EstadoSolicitud = estadoSolicitud;
    }
}

