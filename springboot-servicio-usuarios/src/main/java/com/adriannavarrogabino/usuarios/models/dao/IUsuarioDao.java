package com.adriannavarrogabino.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.adriannavarrogabino.usuarios.commons.models.entity.Usuario;

@RepositoryRestResource(path = "usuarios")
public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

	// http://localhost:8090/api/usuarios/usuarios/search/buscar-username?nombre=adrian
	@RestResource(path = "buscar-username")
	public Usuario findByUsername(@RequestParam("username") String username);
	
	@Query("select u from Usuario u where u.username = ?1")
	public Usuario obtenerPorUsername(String username);
}
