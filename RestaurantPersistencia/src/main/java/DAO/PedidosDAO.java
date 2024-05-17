/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.DetallePedido;
import Entidades.Pedido;
import Interfaces.IPedidosDAO;
import Persistencia.PersistenciaException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class PedidosDAO implements IPedidosDAO {

   private final MongoDatabase database;
    private static PedidosDAO instance;

    public PedidosDAO(MongoDatabase database) {
        this.database = database;
    }

    public static PedidosDAO getInstance(MongoDatabase database) {
        if (instance == null) {
            instance = new PedidosDAO(database);
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
    public ObjectId verificarEstado(int mesa, ObjectId id_sala) {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);
            Pedido pedidoPendiente = pedidosCollection.find(
                    Filters.and(
                            Filters.eq("numMesa", mesa),
                            Filters.eq("sala.id", id_sala),
                            Filters.eq("estado", "PENDIENTE")
                    )
            ).first();
            if (pedidoPendiente != null) {
                return pedidoPendiente.getId();
            }
        } catch (Exception e) {
            System.out.println("Error al verificar el estado del pedido: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Pedido> listarPedidos() throws PersistenciaException {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);

            // Agregación para realizar el $lookup con la colección de detalles_pedido
            List<Bson> pipeline = Arrays.asList(
                    Aggregates.lookup("detalles_pedido", "_id", "pedido_id", "detalles")
            );

            // Ejecutar la agregación
            AggregateIterable<Pedido> results = pedidosCollection.aggregate(pipeline);

            // Convertir los resultados a una lista de pedidos
            List<Pedido> pedidosConDetalles = new ArrayList<>();
            for (Pedido pedido : results) {
                pedidosConDetalles.add(pedido);
            }

            return pedidosConDetalles;
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
        throw new PersistenciaException("Error al registrar el pedido: " + e.getMessage(), e);
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
    public Pedido verPedido(ObjectId idPedido) throws PersistenciaException {
        try {
            MongoCollection<Pedido> pedidosCollection = database.getCollection("pedidos", Pedido.class);
            return pedidosCollection.find(Filters.eq("_id", idPedido)).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el pedido: " + e.getMessage(), e);
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
}
