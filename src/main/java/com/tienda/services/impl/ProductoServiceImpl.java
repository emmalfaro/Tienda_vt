
package com.tienda.services.impl;

import com.tienda.dao.ProductoDao;
import com.tienda.domain.Producto;
import com.tienda.services.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl 
        implements ProductoService {

    @Autowired // como un @bean
    private ProductoDao productoDao;
    
    @Override
    @Transactional(readOnly=true) // transaccion de solo lectura para que no lo bloquee
    public List<Producto> getProductos(boolean activos) {
        var lista = productoDao.findAll();
        if (activos) {
            // se elimina los inactivos
            lista.removeIf(c -> !c.isActivo());
        }
        
        
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
    
     // 1. Consulta ampliada
    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaAmpliada(double precioInf, double precioSup) {
        return productoDao.findByPrecioBetweenOrderByPrecioAsc(precioInf, precioSup);
    }
    
    // 2. Consulta JPQL
    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaJPQL (double precioInf, double precioSup) {
        return productoDao.consultaJPQL(precioInf, precioSup);
    }
    
    // 3. Consulta SQL
    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaSQL (double precioInf, double precioSup) {
        return productoDao.consultaSQL(precioInf, precioSup);
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaAmpliada2(int existencias) {
        return productoDao.findByExistencias(existencias);
    }
    
}
