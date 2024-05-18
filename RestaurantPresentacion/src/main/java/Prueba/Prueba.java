/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Prueba;


import Conexion.ConexionBD;
import DAO.UsuarioDAO;
import GUI.frmLogin;
import Interfaces.IUsuarioDAO;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Carlo
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//
      MongoDatabase database = ConexionBD.getDatabase();
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance(database);
            new frmLogin(usuarioDAO).setVisible(true);
//
    }

}
