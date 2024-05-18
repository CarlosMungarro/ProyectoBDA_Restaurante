/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Sala;
import Persistencia.PersistenciaException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public interface ISalasDAO {

    boolean RegistrarSala(Sala sl) throws PersistenciaException;

    List<Sala> Listar() throws PersistenciaException;

    boolean Eliminar(String id) throws PersistenciaException;

    boolean modificarSala(String nombreOriginal, Sala plato) throws PersistenciaException;

    Sala obtenerSalaPorId(ObjectId idSala) throws PersistenciaException;

    Sala obtenerSalaPorNombre(String nombreSala) throws PersistenciaException;
}
