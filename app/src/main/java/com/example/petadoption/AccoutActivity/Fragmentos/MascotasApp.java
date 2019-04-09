package com.example.petadoption.AccoutActivity.Fragmentos;

public class MascotasApp {
    String IdMascota, Raza, Color, Edad, Genero, Lesion, DescripLesion, Tamaño, IdFundacion, Estado;


    public MascotasApp() {
    }

    public MascotasApp(String idMascota, String raza, String color, String edad, String genero, String lesion, String descripLesion, String tamaño, String idFundacion, String estado) {
        IdMascota = idMascota;
        Raza = raza;
        Color = color;
        Edad = edad;
        Genero = genero;
        Lesion = lesion;
        DescripLesion = descripLesion;
        Tamaño = tamaño;
        IdFundacion = idFundacion;
        Estado = estado;
    }

    public String getIdMascota() {
        return IdMascota;
    }

    public String getRaza() {
        return Raza;
    }

    public String getColor() {
        return Color;
    }

    public String getEdad() {
        return Edad;
    }

    public String getGenero() {
        return Genero;
    }

    public String getLesion() {
        return Lesion;
    }

    public String getDescripLesion() {
        return DescripLesion;
    }

    public String getTamaño() {
        return Tamaño;
    }

    public String getIdFundacion() {
        return IdFundacion;
    }

    public String getEstado() {
        return Estado;
    }
}