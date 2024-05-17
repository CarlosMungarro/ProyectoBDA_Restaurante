/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DTO.UsuarioDTO;
import Entidades.Usuario;

/**
 *
 * @author Carlo
 */
public class Convertir {

    public Usuario convertirUsuarioDTOaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setUsuario(usuarioDTO.getUsuario());
        usuario.setPass(usuarioDTO.getPass());
        usuario.setRol(usuarioDTO.getRol());
        return usuario;
    }

}
