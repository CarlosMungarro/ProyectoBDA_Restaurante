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
public class Config implements Serializable {

    private ObjectId id;
    private String ruc;
    private String nombre;
    private String telefono;
    private String direccion;
    private String mensaje;

    // Constructor vacío
    public Config() {
    }

    // Constructor con todos los atributos
    public Config(ObjectId id, String ruc, String nombre, String telefono, String direccion, String mensaje) {
        this.id = id;
        this.ruc = ruc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.mensaje = mensaje;
    }

    // Getters y setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Config{"
                + "id=" + id
                + ", ruc='" + ruc + '\''
                + ", nombre='" + nombre + '\''
                + ", telefono='" + telefono + '\''
                + ", direccion='" + direccion + '\''
                + ", mensaje='" + mensaje + '\''
                + '}';
    }
}
