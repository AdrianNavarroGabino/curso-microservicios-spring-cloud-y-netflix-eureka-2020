package com.adriannavarrogabino.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.adriannavarrogabino.commons.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}
