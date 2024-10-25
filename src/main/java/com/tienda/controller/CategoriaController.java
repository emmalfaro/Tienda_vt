
package com.tienda.controller;

import com.tienda.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categoria") // ruta
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    
    
    @GetMapping ("/listado") // ruta
    public String listado(Model model) {
        
        var lista=categoriaService.getCategorias(false);
        
        model.addAttribute("categorias", lista); // los objetos dentro del () tienen que ser igual a el html "fragmentos"
        model.addAttribute("totalCategorias", lista.size()); // lista.size para ver la cantidad de elementos 
        
        return "/categoria/listado"; // archivo html
    }
    
}
