
package com.tienda.services;

import com.tienda.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface RegistroService {
    
    // inicia el proceso de creacion de un usuario nuevo
    public Model crear(Model model, Usuario usuario) throws MessagingException;
    
    // metodo para activar un usuario
    // busca el usuario a ver si ya existe
    public Model activar(Model model, String username, String password);
    
    // recibe un usuario y la foto del usuario como un multipart
    public void habilitar(Usuario usuario, MultipartFile imageFile);
    
}
