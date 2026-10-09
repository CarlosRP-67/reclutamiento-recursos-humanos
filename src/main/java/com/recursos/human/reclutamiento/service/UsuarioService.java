/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.recursos.human.reclutamiento.service;

import java.util.List;
import main.java.com.recursos.human.reclutamiento.model.Usuario;
import main.java.com.recursos.human.reclutamiento.repository.UsuarioRepository;
import main.java.com.recursos.human.reclutamiento.security.jbcrypt.BCrypt;

/**
 *
 * @author informatica
 */
public class UsuarioService {
    
    
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public boolean registrarUsuario(String nombre, String apellido, String nombreUsuario, String correo, String contrasenaPlana, int idRol) {
        if (camposInvalidos(nombre, apellido, nombreUsuario, correo, contrasenaPlana)) {
            return false;
        }

        String contrasenaEncriptada = BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt());

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasenaEncriptada);
        usuario.setIdRol(idRol);

        return usuarioRepository.registrar(usuario);
    }

    

    public boolean eliminarUsuario(int idUsuario) {
        if (idUsuario <= 0) return false;
        return usuarioRepository.eliminar(idUsuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.listarTodos();
    }

    private boolean camposInvalidos(String nombre, String apellido, String nombreUsuario, String correo, String contrasena) {
        return nombre == null || nombre.trim().isEmpty() ||
               apellido == null || apellido.trim().isEmpty() ||
               nombreUsuario == null || nombreUsuario.trim().isEmpty() ||
               correo == null || correo.trim().isEmpty() ||
               contrasena == null || contrasena.trim().isEmpty();
    }
}
