package com.ulpsoft.tp3.model;

import android.net.Uri;

import java.io.Serializable;

public class Usuario implements Serializable {

    private long dni;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private String avatar;

    public Usuario() {
    }

    public Usuario(long dni, String nombre, String apellido, String mail, String password, Uri avatar) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.password = password;
        this.avatar = avatar != null ? avatar.toString() : null;  // Guardar Uri como String
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public Uri getAvatar() {
        return avatar != null ? Uri.parse(avatar) : null;
    }

    public void setAvatar(Uri avatar) {
        this.avatar = avatar != null ? avatar.toString() : null;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "DNI=" + dni +
                ", Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
