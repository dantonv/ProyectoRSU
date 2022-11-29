package com.anton.proyectorsu.Modelo;

public class Usuario {

    String nombre;
    String apellido;
    String correo;
    String contraseña;
    String constraseña_secundaria;

    public Usuario(String nombre, String apellido, String correo, String contraseña,
                   String constraseña_secundaria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.constraseña_secundaria = constraseña_secundaria;
    }

    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getConstraseña_secundaria() {
        return constraseña_secundaria;
    }

    public void setConstraseña_secundaria(String constraseña_secundaria) {
        this.constraseña_secundaria = constraseña_secundaria;
    }
}
