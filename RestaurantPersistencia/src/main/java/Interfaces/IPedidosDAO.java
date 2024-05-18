/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;


import Entidades.DetallePedido;
import Entidades.Pedido;
import Persistencia.PersistenciaException;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public interface IPedidosDAO {

    ObjectId obtenerUltimoIdPedido();

     public String verificarEstado(int mesa, String nombreSala);

    public List<Pedido> listarPedidos(String nombreSala, int numMesa) throws PersistenciaException;

    public void registrarPedidoYPedidosDetalle(Pedido pedido) throws PersistenciaException;

    boolean actualizarEstado(ObjectId idPedido) throws PersistenciaException;

    public Pedido verPedido(String nombreSala, int numMesa) throws PersistenciaException;

    List<DetallePedido> listarDetallesPedido(ObjectId pedidoId) throws PersistenciaException;

}
