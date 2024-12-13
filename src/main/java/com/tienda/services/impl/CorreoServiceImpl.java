package com.tienda.services.impl;

import com.tienda.services.CorreoService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class CorreoServiceImpl implements CorreoService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Override
    public void enviarCorreoHTML(String to, 
            String asunto, 
            String contenido) throws MessagingException {
        MimeMessage correo = mailSender.createMimeMessage();
        MimeMessageHelper apoyo = new MimeMessageHelper(correo,true);
        apoyo.setTo(to);
        apoyo.setSubject(asunto);
        apoyo.setText(contenido, true);
        mailSender.send(correo);
    }
    
}
