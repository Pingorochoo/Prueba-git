package pe.edu.upc.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Suscripcion")

public class Suscripcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idPlan", nullable = false)
	private Plan plan;
	
	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, optional = true, cascade = CascadeType.MERGE)
	@JoinColumn(name = "idBoleta", referencedColumnName = "id", nullable = false)
	private Boleta boleta;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechaDeRegistro", nullable = false)
	private Date fechaDeRegistro;
	

	@Column(name = "meses", nullable = false)
	private int meses;

	@OneToOne(mappedBy = "suscripcion", fetch = FetchType.LAZY)
	private Paciente paciente;

	public Suscripcion() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Suscripcion(Boleta boleta) {
		super();
		this.boleta = boleta;
	}
	
	public Suscripcion(int id, Plan plan, Boleta boleta, Date fechaDeRegistro, int meses) {
		super();
		this.id = id;
		this.plan = plan;
		this.boleta = boleta;
		this.fechaDeRegistro = fechaDeRegistro;
		this.meses = meses;
	}



	public Suscripcion(int id, Plan plan, Boleta boleta, Date fechaDeRegistro, int meses, Paciente paciente) {
		super();
		this.id = id;
		this.plan = plan;
		this.boleta = boleta;
		this.fechaDeRegistro = fechaDeRegistro;
		this.meses = meses;
		this.paciente = paciente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Boleta getBoleta() {
		return boleta;
	}

	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}

	public Date getFechaDeRegistro() {
		return fechaDeRegistro;
	}

	public void setFechaDeRegistro(Date fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}

	public int getMeses() {
		return meses;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
