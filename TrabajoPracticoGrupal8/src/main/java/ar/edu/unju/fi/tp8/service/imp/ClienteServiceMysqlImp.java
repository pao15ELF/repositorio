package ar.edu.unju.fi.tp8.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Cliente;
import ar.edu.unju.fi.tp8.repository.IClienteRepository;
import ar.edu.unju.fi.tp8.service.IClienteService;
@Service("clienteServiceMysql")
public class ClienteServiceMysqlImp implements IClienteService {
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private Cliente cliente;
	
	@Override
	public void agregarCliente(Cliente cliente) {
		clienteRepository.save(cliente);		
	}

	@Override
	public List<Cliente> obtenerListaClientes() {
		List<Cliente>clientes = (List<Cliente>) clienteRepository.findAll();
		return clientes;
	}

	@Override
	public Cliente generarCliente() {
		return cliente;
	}

	@Override
	public Optional<Cliente> obtenerClientePorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente;
	}

	@Override
	public void eliminarCliente(Long id) {
		clienteRepository.deleteById(id);
	}
}
