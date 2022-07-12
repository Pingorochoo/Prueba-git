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
@Table(name = "Test")
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="nota", nullable = false)
	private int nota;
	
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name="link", nullable = false, length=200)
	private String link;
	
	@Column(name="horaDeInicio",nullable = false)
	private byte horaDeInicio; 
	
	@Column(name="horaDelFin",nullable = false)
	private byte horaDelFin;
	
	@OneToOne(mappedBy = "test", fetch = FetchType.LAZY)
	private SesionTerapia sesionTerapia;
	
	
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Test(int id, int nota, String link, byte horaDeInicio, byte horaDelFin) {
		super();
		this.id = id;
		this.nota = nota;
		this.link = link;
		this.horaDeInicio = horaDeInicio;
		this.horaDelFin = horaDelFin;
	}


	public Test(int id, int nota, String link, byte horaDeInicio, byte horaDelFin, SesionTerapia sesionTerapia) {
		super();
		this.id = id;
		this.nota = nota;
		this.link = link;
		this.horaDeInicio = horaDeInicio;
		this.horaDelFin = horaDelFin;
		this.sesionTerapia = sesionTerapia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public byte getHoraDeInicio() {
		return horaDeInicio;
	}

	public void setHoraDeInicio(byte horaDeInicio) {
		this.horaDeInicio = horaDeInicio;
	}

	public byte getHoraDelFin() {
		return horaDelFin;
	}

	public void setHoraDelFin(byte horaDelFin) {
		this.horaDelFin = horaDelFin;
	}

	public SesionTerapia getSesionTerapia() {
		return sesionTerapia;
	}

	public void setSesionTerapia(SesionTerapia sesionTerapia) {
		this.sesionTerapia = sesionTerapia;
	}

	
	
}
