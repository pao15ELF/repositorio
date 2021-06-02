package ar.edu.unju.fi.tp8.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp8.model.Producto;

public interface IProductoService {
	/**
	 * Agrega un objeto de tipo Producto
	 * @param producto
	 */
	public void agregarProducto(Producto producto);
	
	/**
	 * Devuelve un objeto de tipo Producto
	 * @return
	 */
	public Producto obtenerUltimoProducto();
	
	/**
	 * Devuelve el ultimo objeto del tipo producto de una lista
	 * @return
	 */
	public Producto generarProducto();
	
	/**
	 *  Devuelve un objeto del tipo producto de una lista buscado por la variable codigo
	 * @param codigo
	 * @return
	 */
	public Optional<Producto> buscarPorId(Long Id);
	
	/**
	 * Devuelve una lista con objetos del tipo Producto
	 * @return
	 */
	public List<Producto> obtenerListaProductos();
	
	/**
	 * Elimina un objeto producto por su ID
	 * @param id
	 */
	public void eliminarProducto(Long id);
}
