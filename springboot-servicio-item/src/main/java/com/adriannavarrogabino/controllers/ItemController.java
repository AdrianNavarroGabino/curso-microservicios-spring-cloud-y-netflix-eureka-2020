package com.adriannavarrogabino.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.adriannavarrogabino.models.Item;
import com.adriannavarrogabino.models.services.IItemService;

@RestController
public class ItemController {
	
	@Autowired
	@Qualifier("serviceRestTemplate")
	private IItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listar() {
		
		return itemService.findAll();
	}
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		
		return itemService.findById(id, cantidad);
	}
}
