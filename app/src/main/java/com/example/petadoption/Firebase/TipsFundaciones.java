package com.example.petadoption.Firebase;

public class TipsFundaciones {

    private String Idfundacion,Idtip,Nombretip,descripciontip,autor,imagentip;

    public TipsFundaciones( String idtip,String idfundacion, String nombretip, String descripciontip, String imagentip,String autor) {
        Idfundacion = idfundacion;
        Idtip = idtip;
        Nombretip = nombretip;
        this.descripciontip = descripciontip;
        this.autor = autor;
        this.imagentip = imagentip;

    }

    public TipsFundaciones() {
    }

    public String getIdfundacion() {
        return Idfundacion;
    }

    public void setIdfundacion(String idfundacion) {
        Idfundacion = idfundacion;
    }

    public String getIdtip() {
        return Idtip;
    }

    public void setIdtip(String idtip) {
        Idtip = idtip;
    }

    public String getNombretip() {
        return Nombretip;
    }

    public void setNombretip(String nombretip) {
        Nombretip = nombretip;
    }

    public String getDescripciontip() {
        return descripciontip;
    }

    public void setDescripciontip(String descripciontip) {
        this.descripciontip = descripciontip;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagentip() {
        return imagentip;
    }

    public void setImagentip(String imagentip) {
        this.imagentip = imagentip;
    }
}
