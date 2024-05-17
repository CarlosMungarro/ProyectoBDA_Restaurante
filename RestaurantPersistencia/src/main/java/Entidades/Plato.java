/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class Plato implements Serializable {

    private ObjectId id;
    private String nombre;
    private double precio;
    private Date fecha;

    // Constructor vacío
    public Plato() {
    }

    // Constructor con todos los atributos
    public Plato(ObjectId id, String nombre, double precio, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Plato{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", precio=" + precio
                + ", fecha=" + fecha
                + '}';
    }
}
