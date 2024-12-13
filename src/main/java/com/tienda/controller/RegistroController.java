
package com.tienda.controller;

import com.tienda.domain.Usuario;
import com.tienda.services.RegistroService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/registro") // ruta
public class RegistroController {
    
    
    @GetMapping ("/nuevo") // ruta
    public String nuevo(Model model, Usuario usuario) {
        
        return "/registro/nuevo"; // archivo html
    }
    
    @Autowired
    private RegistroService registroService;
    
    @PostMapping("/crear")
    public String crear(Model model, Usuario usuario) throws MessagingException {
        
        model = registroService.crear(model, usuario);
        
        return "/registro/salida";
    }
    
    @GetMapping("/activacion/{username}/{password}")
    public String activacion(Model model, 
            @PathVariable("username") String username,
            @PathVariable("password") String password) {
        
        // parametros para buscar a un usuario con ese username y password del path en la base de datos
        model = registroService.activar(model, username, password);
        
        // si encuentra esos parametros, va a abrir una pantalla para poner su telefono, y foto
        if (model.containsAttribute("usuario")) {
           return "/registro/activa"; // para terminar el registro
        } else {
            // si no lo encuentra, va a salir un mensaje de un error 
            return "/registro/salida";
        }
       
    }
    
    @PostMapping("/habilitar")
    public String habilitar(Model model, Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        registroService.habilitar(usuario, imagenFile);
        return "redirect:/";
    }
            
}
