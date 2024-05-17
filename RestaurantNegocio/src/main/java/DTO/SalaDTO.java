/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Carlo
 */
public class SalaDTO {
    
    private String nombre;
    private int mesas;

    // Constructor vacío
    public SalaDTO() {
    }

    // Constructor con todos los atributos
    public SalaDTO(String nombre, int mesas) {
        this.nombre = nombre;
        this.mesas = mesas;
    }

    // Getters y setters
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
