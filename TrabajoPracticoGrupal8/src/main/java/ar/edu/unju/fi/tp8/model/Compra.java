package ar.edu.unju.fi.tp8.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "COMPRAS")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prod_id")
	private Producto producto;
	
	@Column(name = "com_cantidad" , nullable = false)
	private int cantidad;
	
	@Column(name = "com_total" , nullable = false)
	private double total;
	
	public Compra() {
	
	}

	/**
	 * @param id
	 * @param producto
	 * @param cantidad
	 * @param total
	 */
	public Compra(Long id, Producto producto, int cantidad, double total) {
		super();
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
		this.total = total;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	
	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}



	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	/**
	 * 
	 * @return
	 */
	public int getCantidad() {
		return cantidad;
	}
	
	/**
	 * 
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * 
	 * @param total
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	
	/**
	 * Calcula el total de cada compra
	 * @param producto
	 */
	public void calcularTotal(Producto producto) {
				total=producto.getPrecio()*this.cantidad;
	 }

	@Override
	public String toString() {
		return "Compra [id=" + id + ", producto=" + producto + ", cantidad=" + cantidad + ", total=" + total + "]";
	}
		
}
