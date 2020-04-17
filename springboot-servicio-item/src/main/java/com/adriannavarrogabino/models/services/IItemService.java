package com.adriannavarrogabino.models.services;

import java.util.List;

import com.adriannavarrogabino.models.Item;
import com.adriannavarrogabino.commons.models.entity.Producto;

public interface IItemService {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
	
	public Producto save(Producto producto);
	
	public Producto update(Producto producto, Long id);
	
	public void delete(Long id);
}
