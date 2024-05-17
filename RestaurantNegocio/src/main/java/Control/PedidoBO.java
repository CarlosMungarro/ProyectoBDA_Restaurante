/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.PedidosDAO;
import DTO.DetallePedidoDTO;
import DTO.PedidoDTO;
import Entidades.DetallePedido;
import Entidades.Pedido;
import Interfaces.IPedidosDAO;
import Persistencia.PersistenciaException;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class PedidoBO {
    
    
   private static PedidoBO instance;
    private PedidosDAO pedidosDAO;

    private PedidoBO(MongoDatabase database) {
        pedidosDAO = PedidosDAO.getInstance(database);
    }

    public static PedidoBO getInstance(MongoDatabase database) {
        if (instance == null) {
            instance = new PedidoBO(database);
        }
        return instance;
    }

    public ObjectId obtenerUltimoIdPedido() {
        return pedidosDAO.obtenerUltimoIdPedido();
    }

    public ObjectId verificarEstado(int mesa, ObjectId id_sala) {
        return pedidosDAO.verificarEstado(mesa, id_sala);
    }

    public List<PedidoDTO> listarPedidos() throws PersistenciaException {
        try {
            List<Pedido> listaPedidos = pedidosDAO.listarPedidos();
            List<PedidoDTO> pedidosDTO = new ArrayList<>();
            for (Pedido pedido : listaPedidos) {
            pedidosDTO.add(convertirADTO(pedido));
        }
        return pedidosDTO;
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al listar los pedidos: " + e.getMessage(), e);
        }
    }
    
    
    
    

    public void registrarPedidoYPedidosDetalle(PedidoDTO pedidoDTO) throws PersistenciaException {
        try {
            // Convertir de DTO a entidad antes de registrar el pedido y sus detalles
            Pedido pedido = convertirAEntidad(pedidoDTO);
            pedidosDAO.registrarPedidoYPedidosDetalle(pedido);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al registrar el pedido y sus detalles: " + e.getMessage(), e);
        }
    }

    public boolean actualizarEstado(ObjectId idPedido) throws PersistenciaException {
        try {
            return pedidosDAO.actualizarEstado(idPedido);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al actualizar el estado del pedido: " + e.getMessage(), e);
        }
    }

    public PedidoDTO verPedido(ObjectId idPedido) throws PersistenciaException {
        try {
            Pedido pedido = pedidosDAO.verPedido(idPedido);
            // Convertir de entidad a DTO antes de devolver el pedido
            return convertirADTO(pedido);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al obtener el pedido: " + e.getMessage(), e);
        }
    }

    public List<DetallePedidoDTO> listarDetallesPedido(ObjectId pedidoId) throws PersistenciaException {
        try {
            List<DetallePedido> listaDetallesPedido = pedidosDAO.listarDetallesPedido(pedidoId);
            // Convertir de entidad a DTO antes de devolver la lista de detalles del pedido
            return convertirDetallesADTO(listaDetallesPedido);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al listar los detalles del pedido: " + e.getMessage(), e);
        }
    }
    
    // Método para convertir de entidad a DTO (Pedido)
    private PedidoDTO convertirADTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        // Otros atributos, si es necesario
        return pedidoDTO;
    }
    
    // Método para convertir de entidad a DTO (Detalles de Pedido)
    private List<DetallePedidoDTO> convertirDetallesADTO(List<DetallePedido> listaDetallesPedido) {
        List<DetallePedidoDTO> listaDTO = new ArrayList<>();
        for (DetallePedido detallePedido : listaDetallesPedido) {
            DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();
            // Otros atributos, si es necesario
            listaDTO.add(detallePedidoDTO);
        }
        return listaDTO;
    }
    
    // Método para convertir de DTO a entidad (Pedido)
    private Pedido convertirAEntidad(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        // Otros atributos, si es necesario
        return pedido;
    }
    
}
