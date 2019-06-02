package com.example.petadoption.Firebase;

public class DatosDeAdopcionApp {
    String IdDatos,Nombres,Telefono,Direccion,Barrio,ActividadEconomica,TuboMascota,Usuario;

    public DatosDeAdopcionApp(String idDatos, String nombres, String telefono, String direccion, String barrio, String actividadEconomica, String tuboMascota, String usuario) {

        IdDatos = idDatos;
        Nombres = nombres;
        Telefono = telefono;
        Direccion = direccion;
        Barrio = barrio;
        ActividadEconomica = actividadEconomica;
        TuboMascota = tuboMascota;
        Usuario = usuario;
    }

    public DatosDeAdopcionApp() {
    }

    public String getIdDatos() {
        return IdDatos;
    }

    public void setIdDatos(String idDatos) {
        IdDatos = idDatos;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String barrio) {
        Barrio = barrio;
    }

    public String getActividadEconomica() {
        return ActividadEconomica;
    }

    public void setActividadEconomica(String actividadEconomica) {
        ActividadEconomica = actividadEconomica;
    }

    public String getTuboMascota() {
        return TuboMascota;
    }

    public void setTuboMascota(String tuboMascota) {
        TuboMascota = tuboMascota;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
}


