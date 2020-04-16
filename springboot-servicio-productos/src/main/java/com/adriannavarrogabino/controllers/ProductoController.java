package com.adriannavarrogabino.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.adriannavarrogabino.models.entity.Producto;
import com.adriannavarrogabino.models.services.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	/*
	 * Alternativa mucho más fácil que Environment para coger cualquier valor
	 * que haya en el fichero properties.
	 */
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoService.findAll().stream().map(producto -> {
			
			// Así sería con Environment
			// producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			
			// Así con @Value
			producto.setPort(port);
			
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception {
		Producto producto = productoService.findById(id);
		
		// Así sería con Environment
		// producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		// Así con @Value
		producto.setPort(port);
		
		/*
		// Prueba para probar el HystrixCommand en el controlador del item
		boolean ok = false;
		if(!ok)
		{
			throw new Exception("Fallo de prueba");
		}
		*/
		
		
		
		/*
		// Vamos a probar el timeout con Hystrix y Ribbon
		// Al cambiar el timeout de hyxtrix en application.properties, deja de
		// dar fallo
		Thread.sleep(2000L);
		*/
		
		return producto;
	}
}
