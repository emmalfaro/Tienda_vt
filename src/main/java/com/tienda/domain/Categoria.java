
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data 
@Entity 
@Table(name="categoria")
// clase para almacenar datos
public class Categoria implements Serializable { // los datos pueden andar dentro de la red y por eso son serializables
    
    private static final long serialVersionUID = 11; // para conocer los valores y que son asignados en la base de datos
    
    @Id // para saber que es un ID y primary key 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // la base de datos asigna los valores
    private Long idCategoria; // sql usa _ para separar palabras pero programacion orientado a objetos usa camel case entonces lo lee como si tuviera _
    
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
    
    @OneToMany // una categoria tiene muchos productos 
    @JoinColumn(name="id_categoria", updatable = false)
    private List<Producto> productos;
}
