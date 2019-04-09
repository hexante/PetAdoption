package com.example.petadoption;

public class UsuariosApp {
    String idUsuario,Nombres,Apellidos,Departamento,Ciudad,NumeroTelefono,Correo,TipoDocumento,NumeroDocumento,TipoUsuario;

    public UsuariosApp(String idUsuario, String nombres, String apellidos, String departamento, String ciudad, String numeroTelefono, String correo, String tipoDocumento, String numeroDocumento,String tipousuarios) {
        this.idUsuario = idUsuario;
        Nombres = nombres;
        Apellidos = apellidos;
        Departamento = departamento;
        Ciudad = ciudad;
        NumeroTelefono = numeroTelefono;
        Correo = correo;
        TipoDocumento = tipoDocumento;
        NumeroDocumento = numeroDocumento;
        TipoUsuario = tipousuarios;
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

    public String getNumeroTelefono() {
        return NumeroTelefono;
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

    public String getTipoUsuario() {
        return TipoUsuario;
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
