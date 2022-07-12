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

@Entity
@Table(name = "DocumentoDeTrabajo")
public class DocumentoDeTrabajo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name="tipo",nullable = false, length= 30)
	private String tipo;
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name="descripcion",nullable = false, length= 50)
	private String descripcion;
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name="link",nullable = false, length= 50)
	private String link;
	
	@OneToOne(mappedBy = "documento", fetch = FetchType.LAZY)
	private SesionTerapia sesionTerapia;
	
	public Boolean tieneSesion() {
		if(sesionTerapia==null) return false;
		else return false;		
	}
	
	
	public DocumentoDeTrabajo() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public DocumentoDeTrabajo(int id, String tipo, String descripcion, String link) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.link = link;
	}

	


	public DocumentoDeTrabajo(int id, String tipo, String descripcion, String link, SesionTerapia sesionTerapia) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.link = link;
		this.sesionTerapia = sesionTerapia;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public SesionTerapia getSesionTerapia() {
		return sesionTerapia;
	}


	public void setSesionTerapia(SesionTerapia sesionTerapia) {
		this.sesionTerapia = sesionTerapia;
	}


	
	
}
