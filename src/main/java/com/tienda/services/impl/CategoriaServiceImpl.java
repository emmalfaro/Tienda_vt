
package com.tienda.services.impl;

import com.tienda.dao.CategoriaDao;
import com.tienda.domain.Categoria;
import com.tienda.services.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl 
        implements CategoriaService {

    @Autowired // como un @bean
    private CategoriaDao categoriaDao;
    
    @Override
    @Transactional(readOnly=true) // transaccion de solo lectura para que no lo bloquee
    public List<Categoria> getCategorias(boolean activos) {
        var lista = categoriaDao.findAll();
        if (activos) {
            // se elimina los inactivos
            lista.removeIf(c -> !c.isActivo());
        }
        
        
        return lista;
    }
    
}
