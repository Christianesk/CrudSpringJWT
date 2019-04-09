package com.proyecto.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.proyecto.springboot.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{
	
	@Query("select c from Cliente c left join fetch c.listaFacturas f where c.id=?1 order by c.id")
	public Cliente fetchByIdWithFacturas(Long id);
}
