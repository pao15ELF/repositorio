package ar.edu.unju.fi.tp8.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "CLIENTES")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "cli_tipoDocumento" , length = 10 , nullable = false)
	private String tipoDocumento ;
	
	@Column(name = "cli_nroDocumento" , nullable = false)
	private int nroDocumento;
	
	@Column(name = "cli_nombreApellido" , length = 50 , nullable = false)
	private String nombreApellido;
	
	@Column(name = "cli_email" , length = 50 , nullable = false)
	private String email;
	
	@Column(name = "cli_password" , length = 15 , nullable = false)
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "cli_fechaNacimiento" , nullable = false)
	private LocalDate fechaNacimiento;
	
	@Column(name = "cli_codigoATelefono" , nullable = false)
	private int codigoAreaTelefono;
	
	@Column(name = "cli_nroTelefono" , nullable = false)
	private int nroTelefono;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "cli_fechaUltimaCompra" , nullable = false)
	private LocalDate fechaUltimaCompra; 
	
	@Autowired
	@JoinColumn(name="cue_id")
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private Cuenta cuenta;
	
	public Cliente() {
	 
	}
	

	/**
	 * @param id
	 * @param tipoDocumento
	 * @param nroDocumento
	 * @param nombreApellido
	 * @param email
	 * @param password
	 * @param fechaNacimiento
	 * @param codigoAreaTelefono
	 * @param nroTelefono
	 * @param fechaUltimaCompra
	 * @param cuenta
	 */
	public Cliente(Long id, String tipoDocumento, int nroDocumento, String nombreApellido, String email,
			String password, LocalDate fechaNacimiento, int codigoAreaTelefono, int nroTelefono,
			LocalDate fechaUltimaCompra, Cuenta cuenta) {
		super();
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.nombreApellido = nombreApellido;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.codigoAreaTelefono = codigoAreaTelefono;
		this.nroTelefono = nroTelefono;
		this.fechaUltimaCompra = fechaUltimaCompra;
		this.cuenta = cuenta;
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

	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(int nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}
	
	public int getEdad() {
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, hoy);
		periodo.getYears();
		int edad = periodo.getYears();
		return edad;
	}
	
	public String getTiempoHastaCumple() {
		String texto = "";
		int anio = 0 ;
		LocalDate hoy = LocalDate.now();
		if (hoy.getMonthValue() < fechaNacimiento.getMonthValue()) {
			anio = hoy.getYear();
		}else {
			anio = hoy.getYear() +1;
		}
		LocalDate fechaProxCumple = LocalDate.of( anio, fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());
		Period periodo = Period.between(hoy, fechaProxCumple);
		
		texto = texto + periodo.getYears()+"A "+periodo.getMonths()+"M "+periodo.getDays()+"D ";
		
		LocalDateTime ahora = LocalDateTime.now();
		LocalDateTime fechaHoraProxCumple = LocalDateTime.of(anio,fechaNacimiento.getMonth(),fechaNacimiento.getDayOfMonth(),0,0,0);
		
		Duration duracion = Duration.between(ahora,fechaHoraProxCumple);
		texto = texto + duracion.toHoursPart() + "H "+duracion.toMinutesPart()+"M "+duracion.toSecondsPart()+"S ";
		
		return texto;
	}
		public String getTTransDesdeFechaN() {
			int tiempoTrans = 0;
			
			LocalDate hoy = LocalDate.now();
			//Period periodo = Period.between(fechaNacimiento, hoy);
			
			tiempoTrans = (int) ChronoUnit.DAYS.between(fechaNacimiento, hoy);
			
			return tiempoTrans+"D";
		}
		
		public String getTTransDesdeUltCompra () {
			String tiempoTrans = "";
			
			LocalDate hoy = LocalDate.now();
			Period periodo = Period.between(fechaUltimaCompra, hoy);
			int anio = periodo.getYears();
			int mes = periodo.getMonths();
			int dia = periodo.getDays();
			
			tiempoTrans = anio+"A "+mes+"M "+dia+"D ";
			
			return tiempoTrans;
		}


		/**
		 * @return the cuenta
		 */
		public Cuenta getCuenta() {
			return cuenta;
		}


		/**
		 * @param cuenta the cuenta to set
		 */
		public void setCuenta(Cuenta cuenta) {
			this.cuenta = cuenta;
		}


		@Override
		public String toString() {
			return "Cliente [id=" + id + ", tipoDocumento=" + tipoDocumento + ", nroDocumento=" + nroDocumento
					+ ", nombreApellido=" + nombreApellido + ", email=" + email + ", password=" + password
					+ ", fechaNacimiento=" + fechaNacimiento + ", codigoAreaTelefono=" + codigoAreaTelefono
					+ ", nroTelefono=" + nroTelefono + ", fechaUltimaCompra=" + fechaUltimaCompra + ", cuenta=" + cuenta
					+ "]";
		}
	
}
