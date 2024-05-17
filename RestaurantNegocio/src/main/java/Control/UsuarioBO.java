/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import Entidades.Usuario;
import Persistencia.PersistenciaException;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlo
 */
public class UsuarioBO {
 
  private static UsuarioBO instance;
    private UsuarioDAO usuarioDAO;

     private UsuarioBO(MongoDatabase database) {
        usuarioDAO = UsuarioDAO.getInstance(database);
    }

    public static UsuarioBO getInstance(MongoDatabase database) {
        if (instance == null) {
            instance = new UsuarioBO(database);
        }
        return instance;
    }

    public UsuarioDTO log(String usuario, String pass) throws PersistenciaException {
        // Lógica de validación, encriptación, etc.
        Usuario usuarioEntity = usuarioDAO.log(usuario, pass);
        if (usuarioEntity != null) {
            return convertirAUsuarioDTO(usuarioEntity);
        }
        return null;
    }

    public void registrarUsuario(UsuarioDTO usuarioDTO) throws PersistenciaException {
        // Lógica de validación, encriptación, etc.
        Usuario usuarioEntity = convertirAUsuario(usuarioDTO);
        usuarioDAO.Registrar(usuarioEntity);
    }

    public List<UsuarioDTO> listarUsuarios() throws PersistenciaException {
        List<Usuario> usuarios = usuarioDAO.ListarUsuarios();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuariosDTO.add(convertirAUsuarioDTO(usuario));
        }
        return usuariosDTO;
    }

    private Usuario convertirAUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setUsuario(usuarioDTO.getUsuario());
        usuario.setPass(usuarioDTO.getPass());
        usuario.setRol(usuarioDTO.getRol());
        return usuario;
    }

    private UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setUsuario(usuario.getUsuario());
        usuarioDTO.setPass(usuario.getPass());
        usuarioDTO.setRol(usuario.getRol());
        return usuarioDTO;
    }
}
    
    
    

