package ar.edu.unju.fi.tp8.controller;


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

import ar.edu.unju.fi.tp8.controller.ProductoController;
import ar.edu.unju.fi.tp8.model.Producto;
import ar.edu.unju.fi.tp8.service.IProductoService;

@Controller
public class ProductoController {
	private static final Log LOGGER = LogFactory.getLog(ProductoController.class);

	@Autowired
	@Qualifier("productoServiceMysql")
	IProductoService productoService;
	
	
	@GetMapping("/producto")
	public ModelAndView getProductoFormPage() {
		LOGGER.info("CONTROLLER: ProductoController");
		LOGGER.info("METHOD: getProductoFormPage()");
		LOGGER.info("RESULT: visualiza la pagina nuevoproducto.html");
		ModelAndView model = new ModelAndView("nuevoproducto");
		model.addObject("producto", productoService.generarProducto());
		return model;
	}
	
	@PostMapping("/producto/guardar")
	public ModelAndView getProductoResultPage(@Valid @ModelAttribute("producto") Producto unProducto , BindingResult resultadoValidacion ) {
		LOGGER.info("CONTROLLER: ProductoController");
		LOGGER.info("METHOD: getProductoResultPage()");
		LOGGER.info("RESULT: muestra en la pagina resultado_producto.html un mensaje que el producto se guardó exitosamente");
		ModelAndView modelAndView;
		if (resultadoValidacion.hasErrors()) {
			modelAndView = new ModelAndView("nuevoproducto");
			modelAndView.addObject("producto", unProducto);
			return modelAndView;
		}else {
			modelAndView = new ModelAndView("resultado_producto");
			productoService.agregarProducto(unProducto);
			return modelAndView;
		}
	}
	
	@GetMapping("/producto/ultimo")
	public ModelAndView getUltimoProducto() {
		LOGGER.info("CONTROLLER: ProductoController");
		LOGGER.info("METHOD: getUltimoProducto()");
		LOGGER.info("RESULT: muestra la pagina producto.html con el ultimo producto agregado");
		ModelAndView mav = new ModelAndView("ultimo_producto");
		mav.addObject("producto", productoService.obtenerUltimoProducto());
		return mav;
	}
	
	@GetMapping("/producto/listado")
	public ModelAndView getListProductoPage() {
		LOGGER.info("CONTROLLER: ProductoController");
		LOGGER.info("METHOD: getListProductoPage()");
		LOGGER.info("RESULT: visualiza en la pagina productos.html la lista de todos los productos");
		ModelAndView modelAndView = new ModelAndView("productos");
		modelAndView.addObject("productos", productoService.obtenerListaProductos());
		return modelAndView;
	}
	
	@GetMapping("/producto/eliminar/{id}")
	public ModelAndView getEliminarProductoPage(@PathVariable(value = "id") Long id) {
		productoService.eliminarProducto(id);
		ModelAndView modelView = new ModelAndView("redirect:/producto/listado");
		return modelView;
	}
	
	@GetMapping("/producto/editar/{id}")
	public ModelAndView getEditarProductoPage(@PathVariable(value = "id") Long id) {
		ModelAndView mav = new ModelAndView("nuevoproducto");
		mav.addObject("producto",  productoService.buscarPorId(id));
		return mav;
	}
}
