package com.adriannavarrogabino.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.adriannavarrogabino.clientes.IProductoClienteRest;
import com.adriannavarrogabino.models.Item;

/*
 * Dos alternativas cuando hay dos clases que implementan la misma interfaz:
 * 
 * 1. Anotar la clase principal con @Primary para que sea la que se inyecte por
 *    defecto.
 * 
 * 2. Pasarle a @Service el identificador de cada clase y, en la implementación,
 *    anotar con @Qualifier({identificador})
 */
@Service("serviceFeign")
//@Primary
public class ItemServiceFeign implements IItemService {
	
	@Autowired
	private IProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {
		
		return clienteFeign.listar().stream()
				.map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		return new Item(clienteFeign.detalle(id), cantidad);
	}

}
