package ar.edu.unju.fi.tp8.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tp8.model.Compra;

public interface ICompraRepository extends CrudRepository<Compra, Long>{
	
	public List<Compra> findByTotalGreaterThan(double total);
	
	public List<Compra> findByTotalGreaterThanAndProductoNombreContaining(double total, String nombre);

}
