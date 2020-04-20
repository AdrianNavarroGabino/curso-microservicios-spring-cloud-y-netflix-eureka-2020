package com.adriannavarrogabino.oauth.services;

import com.adriannavarrogabino.usuarios.commons.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
