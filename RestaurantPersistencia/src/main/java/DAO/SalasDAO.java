/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionBD;
import Entidades.Sala;
import Interfaces.ISalasDAO;
import Persistencia.PersistenciaException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class SalasDAO implements ISalasDAO {

   private final MongoDatabase database;
    private static SalasDAO instance;

    public SalasDAO() {
        this.database = ConexionBD.getDatabase(); 
    }

    public static SalasDAO getInstance() {
        if (instance == null) {
            instance = new SalasDAO();
        }
        return instance;
    }

    @Override
    public boolean RegistrarSala(Sala sl) throws PersistenciaException {
        try {
            MongoCollection<Sala> collection = database.getCollection("salas", Sala.class);
            collection.insertOne(sl);
            return true;
        } catch (Exception e) {
            throw new PersistenciaException("Error al registrar la sala: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Sala> Listar() throws PersistenciaException {
        try {
            MongoCollection<Sala> collection = database.getCollection("salas", Sala.class);
            List<Sala> listaSalas = new ArrayList<>();
            collection.find().iterator().forEachRemaining(listaSalas::add);
            return listaSalas;
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar las salas: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean Eliminar(String nombreSala) throws PersistenciaException {
    try {
        MongoCollection<Sala> collection = database.getCollection("salas", Sala.class);
        DeleteResult result = collection.deleteOne(eq("nombre", nombreSala));
        return result.getDeletedCount() > 0;
    } catch (Exception e) {
        throw new PersistenciaException("Error al eliminar la sala: " + e.getMessage(), e);
    }
    }

    @Override
    public boolean modificarSala(String nombreOriginal, Sala plato) throws PersistenciaException {
    try {
        MongoCollection<Sala> collection = database.getCollection("salas", Sala.class);
        UpdateResult result = collection.replaceOne(eq("nombre", nombreOriginal), plato);
        
        // Imprimir resultado para depuración
        System.out.println("Documentos modificados: " + result.getModifiedCount());
        
        return result.getModifiedCount() > 0;
    } catch (Exception e) {
        throw new PersistenciaException("Error al modificar el plato: " + e.getMessage(), e);
    }
}

    @Override
    public Sala obtenerSalaPorId(ObjectId idSala) throws PersistenciaException {
        try {
            MongoCollection<Sala> collection = database.getCollection("salas", Sala.class);
            return collection.find(eq("_id", idSala)).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la sala por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public Sala obtenerSalaPorNombre(String nombreSala) throws PersistenciaException {
        try {
            MongoCollection<Sala> collection = database.getCollection("salas", Sala.class);
            return collection.find(eq("nombre", nombreSala)).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la sala por nombre: " + e.getMessage(), e);
        }
    }
}


