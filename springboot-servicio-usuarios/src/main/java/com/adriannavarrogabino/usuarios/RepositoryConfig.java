package com.adriannavarrogabino.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.adriannavarrogabino.usuarios.models.entity.Role;
import com.adriannavarrogabino.usuarios.models.entity.Usuario;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	/*
	 * Configuración opcional
	 * Solamente por si necesitamos los ids en el json
	 */
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		
		config.exposeIdsFor(Usuario.class, Role.class);
	}

	
}
