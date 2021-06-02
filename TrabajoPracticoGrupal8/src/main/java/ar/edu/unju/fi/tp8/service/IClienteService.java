package ar.edu.unju.fi.tp8.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp8.model.Cliente;

public interface IClienteService {
	/**
	 * Agrega un objeto del tipo Cliente
	 * @param cliente
	 */
	public void agregarCliente(Cliente cliente);
	/**
	 * Devuelve una lista con objetos de tipo  Cliente
	 * @return
	 */
	public List<Cliente> obtenerListaClientes();
	
	/**
	 * Genera un objeto de tipo cliente
	 * @return
	 */
	public Cliente generarCliente();
	/**
	 * Obtiene un objeto de tipo cliente buscado por su nro de id
	 * @param id
	 * @return
	 */
	public Optional<Cliente> obtenerClientePorId(Long id);
	/**
	 * Elimina un objeto de tipo cliente por su numero de id
	 * @param id
	 */
	public void eliminarCliente(Long id);

}
