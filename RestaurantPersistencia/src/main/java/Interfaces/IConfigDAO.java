/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Config;
import Persistencia.PersistenciaException;

/**
 *
 * @author Carlo
 */
public interface IConfigDAO {
 
    /**
     *
     * @param conf
     * @throws PersistenciaException
     */
    public void modificarDatos(Config conf) throws PersistenciaException;

    /**
     *
     * @return
     * @throws PersistenciaException
     */
    public Config obtenerDatosEmpresa() throws PersistenciaException;
    
    public void registrarDatos(Config conf) throws PersistenciaException;

    
}
