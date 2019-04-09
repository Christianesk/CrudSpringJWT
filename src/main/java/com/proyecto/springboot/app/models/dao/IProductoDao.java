package com.proyecto.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyecto.springboot.app.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	
	//metodo 1 para buscar productos por medio de objetos
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
	
	//metodo busca indiferentemente si es mayuscula o minuscula y es parte del appi de springboot buscar en spring.io - query creation
	public List<Producto> findByNombreLikeIgnoreCase(String term);
	
	
	
}
