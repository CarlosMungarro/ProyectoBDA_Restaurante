/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class Pedido implements Serializable {

   private ObjectId id; // Utilizamos ObjectId en lugar de int
    private String nombreSala; // Utilizamos String para el nombre de la sala en lugar de Sala
    private int numMesa;
    private Date fecha;
    private double total;
    private String estado;
    private String usuario;
    private List<DetallePedido> detalles;

    // Constructor vacío
    public Pedido() {
    }

    // Constructor con todos los atributos
    public Pedido(ObjectId id, String nombreSala, int numMesa, Date fecha, double total, String estado, String usuario, List<DetallePedido> detalles) {
        this.id = id;
        this.nombreSala = nombreSala;
        this.numMesa = numMesa;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.usuario = usuario;
        this.detalles = detalles;
    }

    // Getters y setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "{\"_id\" : " + id
                + ", \"sala\" : {\"nombre\" : \"" + nombreSala + "\"}"
                + ", \"numMesa\" : " + numMesa
                + ", \"fecha\" : " + fecha
                + ", \"total\" : " + total
                + ", \"estado\" : \"" + estado + "\""
                + ", \"usuario\" : \"" + usuario + "\""
                + ", \"detalles\" : " + detalles
                + "}";
    }

}
