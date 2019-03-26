package com.example.petadoption;

public class UsuariosApp {
    String idUsuario,Nombres,Apellidos,Departamento,Ciudad,NumeroTeledono,Correo,TipoDocumento,NumeroDocumento,TipoU;

    public UsuariosApp(String idUsuario, String nombres, String apellidos, String departamento, String ciudad, String numeroTeledono, String correo, String tipoDocumento, String numeroDocumento,String tipoU) {
        this.idUsuario = idUsuario;
        Nombres = nombres;
        Apellidos = apellidos;
        Departamento = departamento;
        Ciudad = ciudad;
        NumeroTeledono = numeroTeledono;
        Correo = correo;
        TipoDocumento = tipoDocumento;
        NumeroDocumento = numeroDocumento;
        TipoU = tipoU;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombres() {
        return Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public String getNumeroTeledono() {
        return NumeroTeledono;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public String getTipoU() {
        return TipoU;
    }
//  {
 //      "usuarios":{
 //          IdAutomatico: {
 //              "Nombres":TextoNombre,
 //              "Apellidos":TextoApellido,
 //              "Departamento":TextoDepartamento,
 //              "Ciudad": Textociudad,
 //              "NumeroTelefono": TextoTelefono,
 //              "Correo": TextoCorreo,
 //              "TipoDocumento": TextoTipo,
 //              "NumeroDocuemtno": textoNumero
 //      }}
 //  }
}
