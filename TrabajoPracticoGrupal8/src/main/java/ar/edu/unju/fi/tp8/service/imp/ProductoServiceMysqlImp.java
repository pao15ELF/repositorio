package ar.edu.unju.fi.tp8.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Producto;
import ar.edu.unju.fi.tp8.repository.IProductoRepository;
import ar.edu.unju.fi.tp8.service.IProductoService;

@Service("productoServiceMysql")
public class ProductoServiceMysqlImp implements IProductoService {

	@Autowired
	private IProductoRepository productoRepository;
	
	@Autowired
	private Producto producto;
	
	@Override
	public void agregarProducto(Producto producto) {
		productoRepository.save(producto);
		
	}

	@Override
	public Producto obtenerUltimoProducto() {
		List<Producto> productos = (List<Producto>) productoRepository.findAll();
		int tam = productos.size();
		producto = productos.get(tam-1);
		return producto;
	}

	@Override
	public Producto generarProducto() {
		Producto producto = new Producto();
		return producto;
	}

	@Override
	public Optional<Producto> buscarPorId(Long id) {
		Optional<Producto> producto  = productoRepository.findById(id);
		return producto;
	}

	@Override
	public List<Producto> obtenerListaProductos() {
		List<Producto> productos = (List<Producto>) productoRepository.findAll();
		return productos;
	}

	@Override
	public void eliminarProducto(Long id) {
		productoRepository.deleteById(id);
	}

}
