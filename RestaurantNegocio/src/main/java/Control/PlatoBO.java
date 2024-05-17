/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.PlatosDAO;
import DTO.PlatosDTO;
import Entidades.Plato;
import Interfaces.IPlatosDAO;
import Persistencia.PersistenciaException;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public class PlatoBO {

    private static PlatoBO instance;
    private PlatosDAO platosDAO;

    private PlatoBO() {
        platosDAO = new PlatosDAO(); 
    }

    public static PlatoBO getInstance() {
        if (instance == null) {
            instance = new PlatoBO();
        }
        return instance;
    }

    public boolean registrarPlato(PlatosDTO platoDTO) throws PersistenciaException {
        try {
            // Convertir de DTO a entidad antes de registrar el plato
            Plato plato = convertirAEntidad(platoDTO);
            // Validaciones de negocio antes de registrar el plato, si es necesario
            return platosDAO.Registrar(plato);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al registrar el plato: " + e.getMessage(), e);
        }
    }

    public List<PlatosDTO> listarPlatos(String valor, String fecha) throws PersistenciaException {
        try {
            List<Plato> listaPlatos = platosDAO.Listar(valor, fecha);
            // Convertir de entidad a DTO antes de devolver la lista de platos
            return convertirADTO(listaPlatos);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al listar los platos: " + e.getMessage(), e);
        }
    }

    public boolean eliminarPlato(ObjectId id) throws PersistenciaException {
        try {
            return platosDAO.Eliminar(id);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al eliminar el plato: " + e.getMessage(), e);
        }
    }

    public boolean modificarPlato(PlatosDTO platoDTO) throws PersistenciaException {
        try {
            // Convertir de DTO a entidad antes de modificar el plato
            Plato plato = convertirAEntidad(platoDTO);
            return platosDAO.Modificar(plato);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al modificar el plato: " + e.getMessage(), e);
        }
    }

    // Método para convertir de DTO a entidad
    private Plato convertirAEntidad(PlatosDTO platoDTO) {
        Plato plato = new Plato();
        // Asignar los atributos del DTO a la entidad
        plato.setNombre(platoDTO.getNombre());
        plato.setPrecio(platoDTO.getPrecio());
        // Otros atributos, si es necesario
        return plato;
    }

    // Método para convertir de entidad a DTO
    private List<PlatosDTO> convertirADTO(List<Plato> listaPlatos) {
        List<PlatosDTO> listaDTO = new ArrayList<>();
        for (Plato plato : listaPlatos) {
            PlatosDTO platoDTO = new PlatosDTO();
            // Asignar los atributos de la entidad al DTO
            platoDTO.setNombre(plato.getNombre());
            platoDTO.setPrecio(plato.getPrecio());
            // Otros atributos, si es necesario
            listaDTO.add(platoDTO);
        }
        return listaDTO;
    }

}
