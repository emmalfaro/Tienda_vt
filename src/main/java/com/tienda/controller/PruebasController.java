
package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.domain.Producto;
import com.tienda.services.CategoriaService;
import com.tienda.services.ProductoService;
import com.tienda.services.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pruebas") // ruta
public class PruebasController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService; // pasar lista de categorias
    
    @GetMapping ("/listado") // ruta
    public String listado(Model model) {
        
        var lista=productoService.getProductos(false);
        
        model.addAttribute("productos", lista); // los objetos dentro del () tienen que ser igual a el html "fragmentos"
        
        // pasar lista de categorias:
        var categorias=categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias); 
        
        
        return "/pruebas/listado"; // archivo html
    }
    
    @GetMapping("/listado/{idCategoria}")
    public String listado(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("productos", categoria.getProductos());
        
        // pasar lista de categorias:
        var categorias=categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias); 
        
        return "/pruebas/listado";
    }
    
    @GetMapping ("/listado2") // ruta
    public String listado2(Model model) {
        var lista=productoService.getProductos(false);
        model.addAttribute("productos", lista); // los objetos dentro del () tienen que ser igual a el html "fragmentos"
        return "/pruebas/listado2"; // archivo html
    }
    
    @PostMapping ("/consultasAmpliadas") // ruta
    public String consultasAmpliadas(
            @RequestParam("precioInf") double precioInf,
            @RequestParam("precioSup") double precioSup,
            Model model) {    
        var lista=productoService.consultaAmpliada(precioInf, precioSup);
        model.addAttribute("productos", lista); // los objetos dentro del () tienen que ser igual a el html "fragmentos"
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2"; // archivo html
    }
    
    @PostMapping ("/consultasJPQL") // ruta
    public String consultasJPQL(
            @RequestParam("precioInf") double precioInf,
            @RequestParam("precioSup") double precioSup,
            Model model) {    
        var lista=productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", lista); // los objetos dentro del () tienen que ser igual a el html "fragmentos"
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2"; // archivo html
    }
    
    @PostMapping ("/consultasSQL") // ruta
    public String consultasSQL(
            @RequestParam("precioInf") double precioInf,
            @RequestParam("precioSup") double precioSup,
            Model model) {    
        var lista=productoService.consultaSQL(precioInf, precioSup);
        model.addAttribute("productos", lista); // los objetos dentro del () tienen que ser igual a el html "fragmentos"
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2"; // archivo html
    }
    
    @PostMapping ("/consultasAmpliadas2") // ruta
    public String consultasAmpliadas2(
            @RequestParam("existencias") int existencias,
            Model model) {    
        var lista=productoService.consultaAmpliada2(existencias);
        model.addAttribute("productos", lista); // los objetos dentro del () tienen que ser igual a el html "fragmentos"
        model.addAttribute("existencias", existencias);
        return "/pruebas/listado2"; // archivo html
    }
    
}
