
package com.tienda.services;

import com.tienda.domain.Producto;
import java.util.List;


public interface ProductoService {
    
    // se obtiene un list de objetos producto que son los registros de la tabla producto
    // pueden ser todos o solo los activos
    public List<Producto> getProductos(boolean activos);
    
    // eliminar, actualizar, o agregar productos
    
    // Se obtiene un Producto, a partir del id de un producto
    public Producto getProducto(Producto producto);
    
    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Producto producto);
    
    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Producto producto);
    
}