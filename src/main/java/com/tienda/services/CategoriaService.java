
package com.tienda.services;

import com.tienda.domain.Categoria;
import java.util.List;


public interface CategoriaService {
    
    // se obtiene un list de objetos categoria que son los registros de la tabla categoria
    // pueden ser todos o solo los activos
    public List<Categoria> getCategorias(boolean activos);
}
