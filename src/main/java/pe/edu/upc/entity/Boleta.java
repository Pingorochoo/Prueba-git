package pe.edu.upc.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Boleta")
public class Boleta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="detalles", length=200, nullable=true)
	private String detalles;
	@Column(name="fechaDePago", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaDePago;
	@Column(name="monto", nullable=false)
	private double monto;
	@Column(name="metodoDePago", length=15, nullable=false)
	private String metodoDePago;
	
	@OneToOne(mappedBy = "boleta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Suscripcion suscripcion;

	public Boleta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boleta(int id, String detalles, Date fechaDePago, double monto, String metodoDePago) {
		super();
		this.id = id;
		this.detalles = detalles;
		this.fechaDePago = fechaDePago;
		this.monto = monto;
		this.metodoDePago = metodoDePago;
	}

	public Boleta(int id, String detalles, Date fechaDePago, double monto, String metodoDePago,
			Suscripcion suscripcion) {
		super();
		this.id = id;
		this.detalles = detalles;
		this.fechaDePago = fechaDePago;
		this.monto = monto;
		this.metodoDePago = metodoDePago;
		this.suscripcion = suscripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Date getFechaDePago() {
		return fechaDePago;
	}

	public void setFechaDePago(Date fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getMetodoDePago() {
		return metodoDePago;
	}

	public void setMetodoDePago(String metodoDePago) {
		this.metodoDePago = metodoDePago;
	}

	public Suscripcion getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}
	
	
}
