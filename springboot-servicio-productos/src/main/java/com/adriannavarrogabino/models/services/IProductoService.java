package com.adriannavarrogabino.models.services;

import java.util.List;

import com.adriannavarrogabino.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public Producto findById(Long id);
}
