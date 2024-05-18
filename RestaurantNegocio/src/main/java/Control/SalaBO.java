/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.SalasDAO;
import DTO.SalaDTO;
import Entidades.Sala;
import Interfaces.ISalasDAO;
import Persistencia.PersistenciaException;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class SalaBO {
    
  private static SalaBO instance;
    private SalasDAO salasDAO;

    private SalaBO() {
        salasDAO = new SalasDAO(); 
    }

    public static SalaBO getInstance() {
        if (instance == null) {
            instance = new SalaBO();
        }
        return instance;
    }
    
    public boolean registrarSala(SalaDTO salaDTO) throws PersistenciaException {
        try {
            Sala sala = convertirASala(salaDTO);
            // Validaciones de negocio antes de registrar la sala, si es necesario
            return salasDAO.RegistrarSala(sala);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al registrar la sala: " + e.getMessage(), e);
        }
    }
    
    public List<SalaDTO> listarSalas() throws PersistenciaException {
        try {
            List<Sala> salas = salasDAO.Listar();
            List<SalaDTO> salasDTO = new ArrayList<>();
            for (Sala sala : salas) {
                salasDTO.add(convertirASalaDTO(sala));
            }
            return salasDTO;
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al listar las salas: " + e.getMessage(), e);
        }
    }
    
    public boolean eliminarSala(String nombreSala) throws PersistenciaException {
    try {
        return salasDAO.Eliminar(nombreSala);
    } catch (PersistenciaException e) {
        throw new PersistenciaException("Error al eliminar la sala: " + e.getMessage(), e);
    }
}
    
    public boolean modificarSala(String nombreOriginal, SalaDTO salaDTO) throws PersistenciaException {
    try {
        Sala sala = convertirASala(salaDTO);
        return salasDAO.modificarSala(nombreOriginal, sala);
    } catch (PersistenciaException e) {
        throw new PersistenciaException("Error al modificar la sala: " + e.getMessage(), e);
    }
}
    
    public SalaDTO obtenerSalaPorId(ObjectId idSala) throws PersistenciaException {
        try {
            Sala sala = salasDAO.obtenerSalaPorId(idSala);
            return convertirASalaDTO(sala);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al obtener la sala por ID: " + e.getMessage(), e);
        }
    }
    
    public SalaDTO obtenerSalaPorNombre(String nombreSala) throws PersistenciaException {
        try {
            Sala sala = salasDAO.obtenerSalaPorNombre(nombreSala);
            return convertirASalaDTO(sala);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al obtener la sala por nombre: " + e.getMessage(), e);
        }
    }
    
    private Sala convertirASala(SalaDTO salaDTO) {
    Sala sala = new Sala();
    sala.setNombre(salaDTO.getNombre());
    sala.setMesas(salaDTO.getMesas());
    // Otras conversiones de atributos...
    return sala;
}

private SalaDTO convertirASalaDTO(Sala sala) {
    SalaDTO salaDTO = new SalaDTO();
    salaDTO.setNombre(sala.getNombre());
    salaDTO.setMesas(sala.getMesas());
    // Otras conversiones de atributos...
    return salaDTO;
}
    
    
}
