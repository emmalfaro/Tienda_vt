
package com.tienda.services;

import com.tienda.domain.Categoria;
import java.util.List;


public interface CategoriaService {
    
    // se obtiene un list de objetos categoria que son los registros de la tabla categoria
    // pueden ser todos o solo los activos
    public List<Categoria> getCategorias(boolean activos);
    
    // eliminar, actualizar, o agregar categorias
    
    // Se obtiene un Categoria, a partir del id de un categoria
    public Categoria getCategoria(Categoria categoria);
    
    // Se inserta un nuevo categoria si el id del categoria esta vacío
    // Se actualiza un categoria si el id del categoria NO esta vacío
    public void save(Categoria categoria);
    
    // Se elimina el categoria que tiene el id pasado por parámetro
    public void delete(Categoria categoria);
    
}
