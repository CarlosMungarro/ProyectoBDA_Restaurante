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

    private PedidoBO() {
        pedidosDAO = new PedidosDAO();
    }

    public static PedidoBO getInstance() {
        if (instance == null) {
            instance = new PedidoBO();
        }
        return instance;
    }

    public ObjectId obtenerUltimoIdPedido() {
        return pedidosDAO.obtenerUltimoIdPedido();
    }

    public String verificarEstado(int mesa, String nombreSala) throws PersistenciaException {
        return pedidosDAO.verificarEstado(mesa, nombreSala);
    }

    public List<PedidoDTO> listarPedidos(String nombreSala, int numMesa) throws PersistenciaException {
        try {
            // Obtener la lista de pedidos específicos de la sala y la mesa desde el DAO
            List<Pedido> listaPedidos = pedidosDAO.listarPedidos(nombreSala, numMesa);

            // Crear una lista para almacenar los pedidos DTO
            List<PedidoDTO> pedidosDTO = new ArrayList<>();

            // Convertir cada pedido a su respectivo DTO correspondiente y agregarlo a la lista de DTO
            for (Pedido pedido : listaPedidos) {
                PedidoDTO pedidoDTO = convertirADTO(pedido);
                pedidosDTO.add(pedidoDTO);
            }

            // Devolver la lista de pedidos DTO
            return pedidosDTO;
        } catch (PersistenciaException e) {
            // Manejar cualquier excepción y relanzarla
            throw new PersistenciaException("Error al listar los pedidos: " + e.getMessage(), e);
        }

    }

    public List<Pedido> listarPedidoss() throws PersistenciaException {
        try {
            // Llamada al DAO para obtener la lista de pedidos
            return pedidosDAO.listarPedidos();
        } catch (PersistenciaException e) {
            // Manejo de excepciones
            throw new PersistenciaException("Error al listar los pedidos: " + e.getMessage(), e);
        }
    }

    public ObjectId obtenerIdPedidoPorMesaYEstado(int numMesa, String estado) throws PersistenciaException {
        try {
            return pedidosDAO.obtenerIdPedidoPorMesaYEstado(numMesa, estado);
        } catch (PersistenciaException ex) {
            throw new PersistenciaException("Error al obtener el ID del pedido por número de mesa y estado: " + ex.getMessage(), ex);
        }
    }

public void registrarPedidoYPedidosDetalle(PedidoDTO pedidoDto, List<DetallePedidoDTO> detalles) throws PersistenciaException {
    try {
        // Convertir PedidoDTO a Pedido
        Pedido pedido = convertirPedidoDTO(pedidoDto);

        // Si hay detalles, los agregamos al pedido
        if (detalles != null && !detalles.isEmpty()) {
            List<DetallePedido> detallesPedido = convertirDetallesPedidoDTO(detalles);
            pedido.setDetalles(detallesPedido);
        }

        // Llamar al método en el DAO para registrar el pedido
        pedidosDAO.registrarPedidoYPedidosDetalle(pedido);
    } catch (PersistenciaException e) {
        throw new PersistenciaException("Error al registrar el pedido y sus detalles: " + e.getMessage(), e);
    }
}






    // Métodos para convertir DTOs a entidades
    private Pedido convertirPedidoDTO(PedidoDTO pedidoDto) {
        Pedido pedido = new Pedido();
        // Copiar los atributos
        pedido.setNombreSala(pedidoDto.getNombreSala());
        pedido.setNumMesa(pedidoDto.getNumMesa());
        pedido.setFecha(pedidoDto.getFecha());
        pedido.setEstado(pedidoDto.getEstado());
        pedido.setTotal(pedidoDto.getTotal());
        pedido.setUsuario(pedidoDto.getUsuario());
        // Puedes copiar más atributos aquí si es necesario
        return pedido;
    }

    private List<DetallePedido> convertirDetallesPedidoDTO(List<DetallePedidoDTO> detallesDto) {
        List<DetallePedido> detallesPedido = new ArrayList<>();
        for (DetallePedidoDTO detalleDto : detallesDto) {
            DetallePedido detallePedido = new DetallePedido();
            
            detallePedido.setNombre(detalleDto.getNombre());
            detallePedido.setCantidad(detalleDto.getCantidad());
            detallePedido.setPrecio(detalleDto.getPrecio());
            detallePedido.setComentario(detalleDto.getComentario());
            // Agregar el detalle a la lista
            detallesPedido.add(detallePedido);
        }
        return detallesPedido;
    }

    public boolean actualizarEstado(ObjectId idPedido) throws PersistenciaException {
        try {
            return pedidosDAO.actualizarEstado(idPedido);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al actualizar el estado del pedido: " + e.getMessage(), e);
        }
    }

   public PedidoDTO verPedido(String nombreSala, int numMesa) throws PersistenciaException {
    try {
        return pedidosDAO.obtenerPedidoPorSalaYMesa(nombreSala, numMesa);
    } catch (Exception e) {
        throw new PersistenciaException("Error al obtener el pedido: " + e.getMessage(), e);
    }
}

   
   public void finalizarPedido(String idPedido) throws PersistenciaException {
    try {
        pedidosDAO.actualizarEstadoPedido(idPedido, "FINALIZADO");
    } catch (Exception e) {
        throw new PersistenciaException("Error al finalizar el pedido: " + e.getMessage(), e);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private List<DetallePedidoDTO> convertirDetallesAPedidoDTO(List<DetallePedido> detalles) {
        List<DetallePedidoDTO> detallesDTO = new ArrayList<>();
        for (DetallePedido detalle : detalles) {
            detallesDTO.add(new DetallePedidoDTO(detalle.getNombre(), detalle.getPrecio(), detalle.getCantidad(), detalle.getComentario()));
        }
        return detallesDTO;
    }

    public List<DetallePedido> convertirDetallesPedidoDTOaEntidades(List<DetallePedidoDTO> detallesPedidoDTO) {
        List<DetallePedido> detallesPedido = new ArrayList<>();
        for (DetallePedidoDTO detallePedidoDTO : detallesPedidoDTO) {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setNombre(detallePedidoDTO.getNombre());
            detallePedido.setPrecio(detallePedidoDTO.getPrecio());
            detallePedido.setCantidad(detallePedidoDTO.getCantidad());
            detallePedido.setComentario(detallePedidoDTO.getComentario());
            detallesPedido.add(detallePedido);
        }
        return detallesPedido;
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
