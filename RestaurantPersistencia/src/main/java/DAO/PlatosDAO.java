/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionBD;
import Entidades.Plato;
import Interfaces.IPlatosDAO;
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
public class PlatosDAO implements IPlatosDAO {

     private static PlatosDAO instance;
    private MongoDatabase database;

    public PlatosDAO() {
        this.database = ConexionBD.getDatabase(); 
    }

    public static PlatosDAO getInstance() {
        if (instance == null) {
            instance = new PlatosDAO();
        }
        return instance;
    }

    @Override
    public boolean Registrar(Plato plato) throws PersistenciaException {
        try {
            MongoCollection<Plato> collection = database.getCollection("platos", Plato.class);
            collection.insertOne(plato);
            return true;
        } catch (Exception e) {
            throw new PersistenciaException("Error al registrar el plato: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Plato> Listar(String valor, String fecha) throws PersistenciaException {
        try {
            MongoCollection<Plato> collection = database.getCollection("platos", Plato.class);

            List<Plato> listaPlatos = new ArrayList<>();
            collection.find().into(listaPlatos);

            return listaPlatos;
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar los platos: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean Eliminar(String nombrePlato) throws PersistenciaException {
    try {
        MongoCollection<Plato> collection = database.getCollection("platos", Plato.class);
        DeleteResult result = collection.deleteOne(eq("nombre", nombrePlato));
        return result.getDeletedCount() > 0;
    } catch (Exception e) {
        throw new PersistenciaException("Error al eliminar el plato: " + e.getMessage(), e);
    }
}

    @Override
    public boolean modificarPlato(String nombreOriginal, Plato plato) throws PersistenciaException {
    try {
        MongoCollection<Plato> collection = database.getCollection("platos", Plato.class);
        UpdateResult result = collection.replaceOne(eq("nombre", nombreOriginal), plato);
        
        // Imprimir resultado para depuración
        System.out.println("Documentos modificados: " + result.getModifiedCount());
        
        return result.getModifiedCount() > 0;
    } catch (Exception e) {
        throw new PersistenciaException("Error al modificar el plato: " + e.getMessage(), e);
    }
}

}
