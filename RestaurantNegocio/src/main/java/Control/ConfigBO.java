/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.ConfigDAO;
import DTO.ConfigDTO;
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

    private ConfigBO() {
        configDAO = new ConfigDAO(); 
    }

    public static ConfigBO getInstance() {
        if (instance == null) {
            instance = new ConfigBO();
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

    public void modificarDatos(ConfigDTO configDTO) throws PersistenciaException {
        try {
            // Convertir el DTO a entidad antes de modificar los datos de la empresa
            Config config = convertirAEntidad(configDTO);
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
    
    // Método para convertir entidad a DTO
    private ConfigDTO convertirADTO(Config config) {
        // Aquí debes crear un objeto ConfigDTO y establecer sus atributos con los valores de la entidad Config
        ConfigDTO configDTO = new ConfigDTO();
        configDTO.setRuc(config.getRuc());
        configDTO.setNombre(config.getNombre());
        configDTO.setTelefono(config.getTelefono());
        configDTO.setDireccion(config.getDireccion());
        configDTO.setMensaje(config.getMensaje());
        return configDTO;
    }
    
    private Config convertirAEntidad(ConfigDTO configDTO) {
        // Crear un objeto Config y establecer sus atributos con los valores del DTO ConfigDTO
        Config config = new Config();        
        config.setRuc(configDTO.getRuc());
        config.setNombre(configDTO.getNombre());
        config.setTelefono(configDTO.getTelefono());
        config.setDireccion(configDTO.getDireccion());
        config.setMensaje(configDTO.getMensaje());
        return config;
    }
    
    
}
