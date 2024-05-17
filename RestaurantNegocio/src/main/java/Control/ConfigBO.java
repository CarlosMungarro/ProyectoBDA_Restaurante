/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.ConfigDAO;
import Entidades.Config;
import Interfaces.IConfigDAO;
import Persistencia.PersistenciaException;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Carlo
 */
public class ConfigBO {
    
    private static ConfigBO instance;
    private ConfigDAO configDAO;

    private ConfigBO(MongoDatabase database) {
        configDAO = new ConfigDAO(database);
    }

    public static ConfigBO getInstance(MongoDatabase database) {
        if (instance == null) {
            instance = new ConfigBO(database);
        }
        return instance;
    }

    public void registrarDatos(Config config) throws PersistenciaException {
        try {
            configDAO.registrarDatos(config);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al registrar los datos de la empresa: " + e.getMessage(), e);
        }
    }

    public void modificarDatos(Config config) throws PersistenciaException {
        try {
            configDAO.modificarDatos(config);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al modificar los datos de la empresa: " + e.getMessage(), e);
        }
    }

    public Config obtenerDatosEmpresa() throws PersistenciaException {
        try {
            return configDAO.obtenerDatosEmpresa();
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al obtener los datos de la empresa: " + e.getMessage(), e);
        }
    }
    
}
