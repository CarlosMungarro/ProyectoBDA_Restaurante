/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;


import Entidades.Plato;
import Persistencia.PersistenciaException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Carlo
 */
public interface IPlatosDAO {

    boolean Registrar(Plato plato) throws PersistenciaException;

    List<Plato> Listar(String valor, String fecha) throws PersistenciaException;

    boolean Eliminar(String id) throws PersistenciaException;

    boolean modificarPlato(String nombreOriginal, Plato plato) throws PersistenciaException;
}
