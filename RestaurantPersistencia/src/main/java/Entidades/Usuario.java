/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class Usuario implements Serializable {

    private ObjectId id;
    private String nombre;
    private String usuario;
    private String pass;
    private String rol;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con todos los atributos
    public Usuario(ObjectId id, String nombre, String usuario, String pass, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
        this.rol = rol;
    }

    // Getters y setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", usuario='" + usuario + '\''
                + ", pass='" + pass + '\''
                + ", rol='" + rol + '\''
                + '}';
    }

}
