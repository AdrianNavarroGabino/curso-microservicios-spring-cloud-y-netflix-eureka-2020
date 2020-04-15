package com.adriannavarrogabino.models.services;

import java.util.List;

import com.adriannavarrogabino.models.Item;

public interface IItemService {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
}
