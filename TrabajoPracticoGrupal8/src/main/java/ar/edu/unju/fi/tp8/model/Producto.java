package ar.edu.unju.fi.tp8.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "PRODUCTOS")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "id")
	private Long id;
	
	@NotEmpty(message="El ingreso de nombre es obligatorio.")
	@Column(name = "pro_nombre" , length = 30 , nullable = false)
	private String nombre;
	
	@Min(value=1 , message="El ingreso de precio es incorrecto.")
	@Column(name = "pro_precio" , nullable = false)
	private double precio;
	
	@NotEmpty(message= "La marca del producto es obligatorio.")
	@Size(min = 1 , max = 15 ,message ="El rango de caracteres es 1-15 caracteres.")
	@Column(name = "pro_marca" , length = 20 , nullable = false)
	private String marca;
	
	@Min(value=0,message="Ingreso de stock invalido.")
	@Column(name = "pro_stock" , nullable = false)
	private int stock;
	
	public Producto() {
	
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
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
 
	/**
	 * 
	 * @return
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * 
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * 
	 * @return
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * 
	 * @param marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * 
	 * @return
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * 
	 * @param stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + ", stock="
				+ stock + "]";
	}
	
	
}
