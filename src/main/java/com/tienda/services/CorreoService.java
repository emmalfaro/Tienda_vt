package com.tienda.services;

import jakarta.mail.MessagingException;

public interface CorreoService {
    public void enviarCorreoHTML(
            String to,
            String asunto,
            String contenido
    ) throws MessagingException;
}
