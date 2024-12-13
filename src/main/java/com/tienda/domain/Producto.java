
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data 
@Entity 
@Table(name="producto")
// clase para almacenar datos
public class Producto implements Serializable { // los datos pueden andar dentro de la red y por eso son serializables
    
    private static final long serialVersionUID = 11; // para conocer los valores y que son asignados en la base de datos
    
    @Id // para saber que es un ID y primary key 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // la base de datos asigna los valores
    private Long idProducto; // sql usa _ para separar palabras pero programacion orientado a objetos usa camel case entonces lo lee como si tuviera _
   // private Long idCategoria;  ahora el idCategoria esta dentro del objeto Categoria (abajo)
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;
    
    @ManyToOne // relacion muchos a uno (muchos productos tienen una categoria)
    @JoinColumn(name="id_categoria") // unir las tablas
    private Categoria categoria; // para traer el dato
    
}

/*
create table producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  id_categoria INT NOT NULL,
  descripcion VARCHAR(30) NOT NULL,  
  detalle VARCHAR(1600) NOT NULL, 
  precio double,
  existencias int,  
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_producto),
  foreign key fk_producto_caregoria (id_categoria) references categoria(id_categoria)  
)*/