/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionBD;
import DTO.DetallePedidoDTO;
import DTO.PedidoDTO;
import Entidades.DetallePedido;
import Entidades.Pedido;
import Interfaces.IPedidosDAO;
import Persistencia.PersistenciaException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class PedidosDAO implements IPedidosDAO {

    private final MongoDatabase database;
    private static PedidosDAO instance;

    public PedidosDAO() {
        this.database = ConexionBD.getDatabase();
    }

    public static PedidosDAO getInstance() {
        if (instance == null) {
            instance = new PedidosDAO();
        }
        return instance;
    }

    @Override
    public ObjectId obtenerUltimoIdPedido() {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);
            Pedido maxIdPedido = pedidosCollection.find().sort(Sorts.descending("_id")).limit(1).first();
            if (maxIdPedido != null) {
                return maxIdPedido.getId();
            }
            return new ObjectId(); // Si no hay pedidos, devuelve un nuevo ObjectId
        } catch (Exception e) {
            System.out.println("Error al obtener el último id del pedido: " + e.getMessage());
            return null; // En caso de error, devuelve null
        }
    }

    @Override
    public String verificarEstado(int mesa, String nombreSala) {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);
            Pedido pedidoPendiente = pedidosCollection.find(
                    Filters.and(
                            Filters.eq("numMesa", mesa),
                            Filters.eq("nombreSala", nombreSala), // Utiliza el nombre de la sala como filtro
                            Filters.eq("estado", "PENDIENTE")
                    )
            ).first();
            if (pedidoPendiente != null) {
                return pedidoPendiente.getNombreSala(); // Suponiendo que hay un campo "nombre" en la clase Pedido que representa el nombre del pedido
            }
        } catch (Exception e) {
            System.out.println("Error al verificar el estado del pedido: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Pedido> listarPedidos(String nombreSala, int numMesa) throws PersistenciaException {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);

            // Crear un filtro para obtener los pedidos de la sala y la mesa especificadas
            Bson filtro = Filters.and(
                    Filters.eq("estado", "PENDIENTE"),
                    Filters.eq("nombreSala", nombreSala),
                    Filters.eq("numMesa", numMesa)
            );

            // Agregar el filtro para obtener los pedidos que coinciden con el filtro
            AggregateIterable<Pedido> results = pedidosCollection.aggregate(Arrays.asList(
                    Aggregates.match(filtro),
                    Aggregates.lookup("detalles_pedido", "_id", "pedido_id", "detalles")
            ));

            // Convertir los resultados a una lista de pedidos
            List<Pedido> pedidos = new ArrayList<>();
            for (Pedido pedido : results) {
                pedidos.add(pedido);
            }

            return pedidos;
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar los pedidos: " + e.getMessage(), e);
        }
    }

    @Override
public void registrarPedidoYPedidosDetalle(Pedido pedido) throws PersistenciaException {
    try {
        MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);
        pedidosCollection.insertOne(pedido);
        System.out.println("Pedido registrado correctamente.");
    } catch (Exception e) {
        throw new PersistenciaException("Error al registrar el pedido y sus detalles: " + e.getMessage(), e);
    }
}

public PedidoDTO obtenerPedidoPorSalaYMesa(String nombreSala, int numMesa) throws PersistenciaException {
    try {
        MongoCollection<Document> collection = database.getCollection("pedidos");
        Document query = new Document("nombreSala", nombreSala).append("numMesa", numMesa);
        Document result = collection.find(query).first();

        if (result != null) {
            PedidoDTO pedido = new PedidoDTO();
            pedido.setNombreSala(result.getString("nombreSala"));
            pedido.setNumMesa(result.getInteger("numMesa"));
            pedido.setTotal(result.getDouble("total"));
            pedido.setUsuario(result.getString("usuario"));
            pedido.setFecha(result.getDate("fecha"));
            pedido.setEstado(result.getString("estado"));

            List<DetallePedidoDTO> detalles = new ArrayList<>();
            List<Document> detallesDoc = (List<Document>) result.get("detalles");
            for (Document detalleDoc : detallesDoc) {
                DetallePedidoDTO detalle = new DetallePedidoDTO();
                detalle.setNombre(detalleDoc.getString("nombre"));
                detalle.setCantidad(detalleDoc.getInteger("cantidad"));
                detalle.setPrecio(detalleDoc.getDouble("precio"));
                detalle.setComentario(detalleDoc.getString("comentario"));
                detalles.add(detalle);
            }
            pedido.setDetalles(detalles);

            return pedido;
        } else {
            return null; // No se encontró el pedido
        }
    } catch (Exception e) {
        throw new PersistenciaException("Error al obtener el pedido: " + e.getMessage(), e);
    }
}



public void actualizarEstadoPedido(String idPedido, String nuevoEstado) throws PersistenciaException {
    try {
        MongoCollection<Document> collection = database.getCollection("pedidos");
        Document filter = new Document("nombreSala", (idPedido));
        Document update = new Document("$set", new Document("estado", nuevoEstado));
        collection.updateOne(filter, update);
        System.out.println("Estado del pedido actualizado correctamente.");
    } catch (Exception e) {
        throw new PersistenciaException("Error al actualizar el estado del pedido: " + e.getMessage(), e);
    }
}




    @Override
    public boolean actualizarEstado(ObjectId idPedido) throws PersistenciaException {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);
            pedidosCollection.updateOne(Filters.eq("_id", idPedido), Updates.set("estado", "FINALIZADO"));
            return true;
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el estado del pedido: " + e.getMessage(), e);
        }
    }

    @Override
    public Pedido verPedido(String nombreSala, int numMesa) throws PersistenciaException {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);

            // Crear un filtro para obtener el pedido de la sala y mesa especificadas
            Bson filtro = Filters.and(
                    Filters.eq("estado", "PENDIENTE"),
                    Filters.eq("nombreSala", nombreSala),
                    Filters.eq("numMesa", numMesa)
            );

            // Obtener el pedido que coincida con el filtro
            return pedidosCollection.find(filtro).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el pedido: " + e.getMessage(), e);
        }
    }

    public List<DetallePedido> verDetallesPedido(String nombreSala, int numMesa) throws PersistenciaException {
        try {
            Pedido pedido = verPedido(nombreSala, numMesa); // Obtener el pedido por nombre de sala y número de mesa

            if (pedido != null) {
                return pedido.getDetalles(); // Devolver los detalles del pedido
            } else {
                throw new PersistenciaException("No se encontró el pedido con los datos especificados");
            }
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al obtener los detalles del pedido: " + e.getMessage(), e);
        }
    }

    @Override
    public List<DetallePedido> listarDetallesPedido(ObjectId pedidoId) throws PersistenciaException {
        try {
            MongoCollection<DetallePedido> detallesPedidoCollection = database.getCollection("detalles_pedido", DetallePedido.class);
            return detallesPedidoCollection.find(Filters.eq("pedido_id", pedidoId)).into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar los detalles del pedido: " + e.getMessage(), e);
        }
    }

    public List<Pedido> listarPedidos() throws PersistenciaException {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);

            // No aplicamos filtro por estado
            // Obtenemos todos los pedidos
            AggregateIterable<Pedido> results = pedidosCollection.aggregate(Arrays.asList(
                    Aggregates.project(Projections.fields(
                            Projections.excludeId(),
                            Projections.include("nombreSala"),
                            Projections.include("numMesa"),
                            Projections.include("fecha"),
                            Projections.include("total"),
                            Projections.include("estado"),
                            Projections.include("detalles")
                    ))
            ));

            // Convertir los resultados a una lista de pedidos
            List<Pedido> pedidos = new ArrayList<>();
            for (Pedido pedido : results) {
                pedidos.add(pedido);
            }

            return pedidos;
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar los pedidos: " + e.getMessage(), e);
        }
    }

    public ObjectId obtenerIdPedidoPorMesaYEstado(int numMesa, String estado) throws PersistenciaException {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);
            Pedido pedido = pedidosCollection.find(
                    Filters.and(
                            Filters.eq("numMesa", numMesa),
                            Filters.eq("estado", estado)
                    )
            ).first();
            return (pedido != null) ? pedido.getId() : null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el ID del pedido por número de mesa y estado: " + e.getMessage(), e);
        }
    }

}
