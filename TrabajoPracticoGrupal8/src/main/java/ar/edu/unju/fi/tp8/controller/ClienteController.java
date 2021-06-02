package ar.edu.unju.fi.tp8.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp8.controller.ClienteController;
import ar.edu.unju.fi.tp8.model.Cliente;
import ar.edu.unju.fi.tp8.service.IClienteService;

@Controller
public class ClienteController {
	
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
		
	@Autowired
	@Qualifier("clienteServiceMysql")
	private IClienteService clienteService;
	
	
	@GetMapping("/cliente/nuevo")
	public ModelAndView getNuevoClientePage() {
		LOGGER.info("CONTROLLER: ClienteController");
		LOGGER.info("METHOD: getNuevoClientePage()");
		LOGGER.info("RESULT: visualiza la pagina nuevocliente.html");
		ModelAndView mav = new ModelAndView("nuevocliente");
		mav.addObject("cliente",clienteService.generarCliente());
		return mav;
	}
	
	@PostMapping("/cliente/guardar")
	public ModelAndView getNuevoClientePage(@ModelAttribute("cliente") Cliente unCliente) {
		LOGGER.info("CONTROLLER: ClienteController");
		LOGGER.info("METHOD: getNuevoClientePage() con parametros");
		LOGGER.info("RESULT: guarda los datos registrados en el formulario de la nuevocliente.html, y muestra la pagina clientes.html");
		ModelAndView mav = new ModelAndView("clientes");
		clienteService.agregarCliente(unCliente);
		mav.addObject("clientes", clienteService.obtenerListaClientes());
		return mav;
	}
	
	@GetMapping("/cliente/listado")
	public ModelAndView getListadoClientesPage() {
		LOGGER.info("CONTROLLER: ClienteController");
		LOGGER.info("METHOD: getListadoClientePage()");
		LOGGER.info("RESULT: muestra el listado de clientes en la pagina clientes.html");
		ModelAndView mav = new ModelAndView("clientes");			
		mav.addObject("clientes", clienteService.obtenerListaClientes());
		return mav;
	}
	
	@GetMapping("/cliente/editar/{id}")
	public ModelAndView getEditarClientePage(@PathVariable(value = "id") Long id) {
		ModelAndView modelView = new ModelAndView("nuevoCliente");
		Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
		modelView.addObject("cliente",cliente);
		return modelView;
	}
	
	@GetMapping("/cliente/eliminar/{id}")
	public ModelAndView getEliminarClientePage(@PathVariable(value = "id") Long id) {
		clienteService.eliminarCliente(id);
		ModelAndView modelView = new ModelAndView("clientes");
		modelView.addObject("clientes", clienteService.obtenerListaClientes());
		return modelView ;
	}
	
}
