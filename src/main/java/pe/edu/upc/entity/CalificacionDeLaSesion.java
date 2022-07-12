package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CalificacionDeLaSesion")
public class CalificacionDeLaSesion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Size(min = 0, max=10,message = "Ingrese un numero entre 0 y 10")
	@Column(name="puntuacion", nullable = false)
	private byte puntuacion;
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name="comentarios", nullable = false, length=200)
	private String comentarios;
	
	@OneToOne(mappedBy = "calificacion", fetch = FetchType.LAZY)
	private SesionTerapia sesionTerapia;
	
	public CalificacionDeLaSesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public CalificacionDeLaSesion(int id, byte puntuacion, String comentarios) {
		super();
		this.id = id;
		this.puntuacion = puntuacion;
		this.comentarios = comentarios;
	}


	public CalificacionDeLaSesion(int id, byte puntuacion, String comentarios, SesionTerapia sesionTerapia) {
		super();
		this.id = id;
		this.puntuacion = puntuacion;
		this.comentarios = comentarios;
		this.sesionTerapia = sesionTerapia;
	}

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public byte getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(byte puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public SesionTerapia getSesionTerapia() {
		return sesionTerapia;
	}

	public void setSesionTerapia(SesionTerapia sesionTerapia) {
		this.sesionTerapia = sesionTerapia;
	}


	
}
