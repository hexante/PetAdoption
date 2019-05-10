package com.example.petadoption.AccoutActivity.Fragmentos;

public class MascotasApp {
    String IdMascota, Raza, Color, Edad, Genero, Lesion, DescripLesion, Tamaño, IdFundacion, Estado,Imagen;


    public MascotasApp() {
    }

    public MascotasApp(String idMascota, String raza, String color, String edad, String genero, String lesion, String descripLesion, String tamaño, String idFundacion, String estado, String imagen) {
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
        Imagen = imagen;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public void setIdMascota(String idMascota) {
        IdMascota = idMascota;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public void setColor(String color) {
        Color = color;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public void setLesion(String lesion) {
        Lesion = lesion;
    }

    public void setDescripLesion(String descripLesion) {
        DescripLesion = descripLesion;
    }

    public void setTamaño(String tamaño) {
        Tamaño = tamaño;
    }

    public void setIdFundacion(String idFundacion) {
        IdFundacion = idFundacion;
    }

    public void setEstado(String estado) {
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