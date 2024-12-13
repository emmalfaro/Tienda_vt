
package com.tienda.dao;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;


@EnableJpaRepositories 
public interface ProductoDao 
        extends JpaRepository<Producto,Long> {
    
    // 3 Metodos que recuperan los productos que estan en un rango de precios
    // 1. Consulta Ampliada
    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(double precioInf, double precioSup);
    
    // 2. Consulta JPQL
    @Query(value="SELECT a "
            + "FROM Producto a "
            + "WHERE a.precio BETWEEN :precioInf AND :precioSup "
            + "ORDER BY a.precio ASC")
    public List<Producto> consultaJPQL (
            @Param("precioInf")double precioInf, 
            @Param("precioSup")double precioSup);
    
    // 3. Consulta SQL
    @Query(nativeQuery=true,
            value="SELECT * "
            + "FROM producto a "
            + "WHERE a.precio BETWEEN :precioInf AND :precioSup "
            + "ORDER BY a.precio ASC")
    public List<Producto> consultaSQL (
            @Param("precioInf")double precioInf, 
            @Param("precioSup")double precioSup);
   
    
    // Método para filtrar los productos según sus existencias por consulta ampliada
    public List<Producto> findByExistencias(int existencias);   
}
