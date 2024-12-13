
package com.tienda.services.impl;

import com.tienda.domain.Usuario;
import com.tienda.service.UsuarioService;
import com.tienda.services.CorreoService;
import com.tienda.services.FirebaseStorageService;
import com.tienda.services.RegistroService;
import jakarta.mail.MessagingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private UsuarioService usuarioService;
    
    @Override
    public Model crear(Model model, Usuario usuario) throws MessagingException {
        String titulo;
        String mensaje;
        
        if (!usuarioService.existeUsuarioPorUsernameOCorreo(usuario.getUsername(), usuario.getCorreo())) {
            // Si no existe el usuario, se va a enviar un correo
            
            usuario.setActivo(false);
            usuario.setPassword(demeClave()); // genera una tira con caracteres aleatorios
            usuarioService.save(usuario, false); // guarda el usuario
            enviarCorreo(usuario); // se le envia el mensaje 
            titulo=mensajes.getMessage("registro.activar", null, Locale.getDefault()); // texto para el titulo
            mensaje=mensajes.getMessage("registro.mensaje.activacion.ok", null, Locale.getDefault()); // texto que le dice que vea el correo
            mensaje = String.format(mensaje, usuario.getCorreo()); // el parametro dentro del mensaje que dice el correo del usuario
        
        } else {
            // Si existe el usuario, se le notifica
            
            titulo=mensajes.getMessage("registro.activar.error", null, Locale.getDefault()); // texto para el titulo
            mensaje=mensajes.getMessage("registro.mensaje.usuario.o.correo", null, Locale.getDefault()); // texto que le dice que ya existe el usuario
            mensaje = String.format(mensaje,
                    usuario.getUsername(),
                    usuario.getCorreo()); // el parametro dentro del mensaje que dice el correo del usuario
        }
            
        
        model.addAttribute("titulo", titulo);
        model.addAttribute("mensaje", mensaje);
        return model;
    }

    @Override
    public Model activar(Model model, String username, String password) {
        Usuario usuario=usuarioService.getUsuarioPorUsernameYPassword(username, password);
        if (usuario!=null) {
            // si lo encontro
            model.addAttribute("usuario", usuario);
        } else {
            // no lo encontro
            model.addAttribute("titulo", "Error");
            model.addAttribute("mensaje", "Error al activar usuario");
        }
        return model;
        
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    
    @Override
    public void habilitar(Usuario usuario, MultipartFile imagenFile) {
        // recibe el usuario que tiene todos los datos mas el telefono y la imagen del usuario
        var encriptador = new BCryptPasswordEncoder();
        usuario.setPassword(encriptador.encode(usuario.getPassword()));
        
        // si se envió una foto:
        if (!imagenFile.isEmpty()) {
            String ruta=firebaseStorageService.cargaImagen(imagenFile, 
                            "usuarios", 
                            usuario.getIdUsuario());
            usuario.setRutaImagen(ruta);
        }
        
        usuarioService.save(usuario, true);
    }

    private String demeClave() {
        String base="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String clave="";
        
        // un for que llene 40 caracteres aleatorios de los que se establecio en base
        // cada vez que se cree un usuario se crea una tira unica con estos caracteres
        for (int i=0;i<40;i++) {
            clave+=base.charAt((int)(Math.random()*base.length()));
        }
        return clave;
    }

    @Autowired
    private MessageSource mensajes; // implementar los textos de message.properties
    
    @Autowired
    private CorreoService correoService;
    
    private void enviarCorreo(Usuario usuario) throws MessagingException {
        
        // recuperar el mensaje del texto del asunto recuperado de message.properties
        String asunto=mensajes.getMessage("registro.mensaje.activacion", null, Locale.getDefault());
        
        // el texto que se quiere enviar
        String mensaje=mensajes.getMessage("registro.correo.activar", null, Locale.getDefault());
        
        // reemplazar los %s que son parametros con el nombre, los apellidos del usuario
        // y en la ruta que tambien se reemplace por el nombre de usuario y la clave que generó
        mensaje = String.format(mensaje, 
                usuario.getNombre(),
                usuario.getApellidos(),
                "localhost",
                usuario.getUsername(),
                usuario.getPassword()
                );
        
        // enviar el correo (CorreoService)
        correoService.enviarCorreoHTML(usuario.getCorreo(), asunto, mensaje);
    }
    


}