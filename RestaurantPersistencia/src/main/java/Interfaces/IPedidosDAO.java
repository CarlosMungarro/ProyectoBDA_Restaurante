/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.DetallePedidoDTO;
import DTO.PedidoDTO;
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

    ObjectId verificarEstado(int mesa, ObjectId id_sala);

    List<Pedido> listarPedidos() throws PersistenciaException;

    public void registrarPedidoYPedidosDetalle(Pedido pedido) throws PersistenciaException;

    boolean actualizarEstado(ObjectId idPedido) throws PersistenciaException;

    Pedido verPedido(ObjectId idPedido) throws PersistenciaException;

    List<DetallePedido> listarDetallesPedido(ObjectId pedidoId) throws PersistenciaException;

}
