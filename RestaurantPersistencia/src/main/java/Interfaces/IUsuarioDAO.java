/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Usuario;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author Carlo
 */
public interface IUsuarioDAO {

   Usuario log(String usuario, String pass) throws PersistenciaException;

    void Registrar(Usuario reg) throws PersistenciaException;

    List<Usuario> ListarUsuarios() throws PersistenciaException;

    

}
