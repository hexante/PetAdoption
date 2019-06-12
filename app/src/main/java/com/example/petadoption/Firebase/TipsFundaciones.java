package com.example.petadoption.Firebase;

public class TipsFundaciones {

    private String Idfundacion,Idtip,Nombretip,descripciontip,autor,link;

    public TipsFundaciones(String idfundacion, String idtip, String nombretip, String descripciontip, String autor, String link) {
        Idfundacion = idfundacion;
        Idtip = idtip;
        Nombretip = nombretip;
        this.descripciontip = descripciontip;
        this.autor = autor;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
