package ar.edu.unju.fi.tp8.controller;


import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp8.controller.CompraController;
import ar.edu.unju.fi.tp8.model.Compra;

import ar.edu.unju.fi.tp8.service.ICompraService;
import ar.edu.unju.fi.tp8.service.IProductoService;
@Controller
public class CompraController {
	private static final Log LOGGER = LogFactory.getLog(CompraController.class);
	
	@Autowired
	@Qualifier("compraServiceMysql")
	private ICompraService compraService;
	
	@Autowired
	@Qualifier("productoServiceMysql")
	private IProductoService productoService;
	
	@GetMapping("/compra/nuevo")
	public ModelAndView getPageNuevaCompra() {
		LOGGER.info("CONTROLLER: CompraController");
		LOGGER.info("METHOD: getPageNuevaCompra()");
		LOGGER.info("RESULT: visualiza la pagina nuevocompra.html");
		ModelAndView model = new ModelAndView("nuevacompra");
		model.addObject("nuevaCompra", compraService.generarCompra());
		model.addObject("productos", productoService.obtenerListaProductos());
		
		return model;
	}
	
	@PostMapping("/compra/guardar")
	public ModelAndView postGuardarNuevaCompra(@Valid @ModelAttribute("nuevaCompra") Compra nuevaCompra, BindingResult resultadoValidacion ) {
		LOGGER.info("CONTROLLER: CompraController");
		LOGGER.info("METHOD: postGuardarNuevaCompra()");
		LOGGER.info("RESULT: carga los datos del formulario en la pagina nuevacompra.html y muestra la pagina compras.html");
		ModelAndView modelView;
		if(resultadoValidacion.hasErrors()) {
			modelView= new ModelAndView("nuevacompra");
			modelView.addObject("nuevaCompra",nuevaCompra );
			
			modelView.addObject("productos", productoService.obtenerListaProductos());
			return modelView;
		}else {
			nuevaCompra.calcularTotal(nuevaCompra.getProducto());
			modelView = new ModelAndView("compras");
			compraService.agregarCompra(nuevaCompra);
			modelView.addObject("auxCompra",compraService.generarCompra());
			modelView.addObject("compras",compraService.obtenerListaCompras());
			return modelView;
		}
	}
	
	@GetMapping("/compra/listado")
	public ModelAndView getListaCompras() {
		LOGGER.info("CONTROLLER: CompraController");
		LOGGER.info("METHOD: getListCompras()");
		LOGGER.info("RESULT: visualiza la lista de compras en la pagina compras.html");
		ModelAndView modelView = new ModelAndView("compras");
		modelView.addObject("auxCompra",compraService.generarCompra());	
		modelView.addObject("compras", compraService.obtenerListaCompras());
		return modelView;	
	}
	
	@PostMapping("/compra/buscar")
	public ModelAndView getBuscarCompraPage(@Valid @ModelAttribute("auxCompra") Compra auxCompra , BindingResult resultadoValidacion ) {
		ModelAndView mav = new ModelAndView("compras");
		if(auxCompra.getProducto().getNombre().isEmpty()) {
			List<Compra> compras1 = compraService.buscarPorTotal(auxCompra.getTotal());
			mav.addObject("auxCompra",compraService.generarCompra());		
			mav.addObject("compras", compras1);
			return mav;
		}else {
			List<Compra> compras2 = compraService.buscarPorNombreProductoYTotal(auxCompra.getTotal(), auxCompra.getProducto().getNombre());
			mav.addObject("auxCompra",compraService.generarCompra());		
			mav.addObject("compras", compras2);
			return mav;
		}
		
	}
	
	@GetMapping("/compra/eliminar/{id}")
	public ModelAndView getEliminarCompraPage(@PathVariable(value = "id") Long id) {
		compraService.eliminarCompra(id);
		ModelAndView mav = new ModelAndView("redirect:/compra/listado");
		return mav ;
	}
	
	@GetMapping("/compra/editar/{id}")
	public ModelAndView getEditarCompraPage(@PathVariable(value = "id") Long id) {
		ModelAndView mav = new ModelAndView("nuevacompra");
		mav.addObject("nuevaCompra", compraService.buscarPorId(id));
		mav.addObject("productos", productoService.obtenerListaProductos());
		return mav;
	}
	
	
}
