/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class Sala implements Serializable {

      private ObjectId id;
    private String nombre;
    private int mesas;
    

    // Constructor vacío
    public Sala() {
       
    }

    // Constructor con todos los atributos
    public Sala(ObjectId id, String nombre, int mesas) {
        this.id = id;
        this.nombre = nombre;
        this.mesas = mesas;
       
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

    public int getMesas() {
        return mesas;
    }

    public void setMesas(int mesas) {
        this.mesas = mesas;
    }

    
}
