/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionBD;
import Entidades.Config;
import Interfaces.IConfigDAO;
import Persistencia.PersistenciaException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 *
 * @author Carlo
 */
public class ConfigDAO implements IConfigDAO {

    
    private final MongoDatabase database;
    private static ConfigDAO instance;

    public ConfigDAO() {
        this.database = ConexionBD.getDatabase(); 
    }

    public static ConfigDAO getInstance() {
        if (instance == null) {
            instance = new ConfigDAO();
        }
        return instance;
    }

    @Override
    public void registrarDatos(Config conf) throws PersistenciaException {
        try {
            MongoCollection<Config> collection = database.getCollection("config", Config.class);
            collection.insertOne(conf);
        } catch (Exception e) {
            throw new PersistenciaException("Error al registrar los datos de la empresa: " + e.getMessage(), e);
        }
    }

    @Override
    public void modificarDatos(Config conf) throws PersistenciaException {
        try {
            MongoCollection<Config> collection = database.getCollection("config", Config.class);
            collection.replaceOne(Filters.eq("_id", conf.getId()), conf);
        } catch (Exception e) {
            throw new PersistenciaException("Error al modificar los datos de la empresa: " + e.getMessage(), e);
        }
    }

    @Override
    public Config obtenerDatosEmpresa() throws PersistenciaException {
        try {
            MongoCollection<Config> collection = database.getCollection("config", Config.class);
            return collection.find().first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los datos de la empresa: " + e.getMessage(), e);
        }
    }
}
