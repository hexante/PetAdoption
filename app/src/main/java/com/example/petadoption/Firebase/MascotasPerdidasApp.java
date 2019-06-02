package com.example.petadoption.Firebase;

public class MascotasPerdidasApp {

    String IdSolicitud, Usuario, NombreMascota, FotoMacota, DescripcionPerdida, TelefonoUsuario;

    public MascotasPerdidasApp() {
    }

    public MascotasPerdidasApp(String idSolicitud, String usuario, String nombreMascota, String fotoMacota, String descripcionPerdida, String telefonoUsuario) {
        IdSolicitud = idSolicitud;
        Usuario = usuario;
        NombreMascota = nombreMascota;
        FotoMacota = fotoMacota;
        DescripcionPerdida = descripcionPerdida;
        TelefonoUsuario = telefonoUsuario;
    }


    public String getIdSolicitud() {
        return IdSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        IdSolicitud = idSolicitud;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getNombreMascota() {
        return NombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        NombreMascota = nombreMascota;
    }

    public String getFotoMacota() {
        return FotoMacota;
    }

    public void setFotoMacota(String fotoMacota) {
        FotoMacota = fotoMacota;
    }

    public String getDescripcionPerdida() {
        return DescripcionPerdida;
    }

    public void setDescripcionPerdida(String descripcionPerdida) {
        DescripcionPerdida = descripcionPerdida;
    }

    public String getTelefonoUsuario() {
        return TelefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        TelefonoUsuario = telefonoUsuario;
    }
}