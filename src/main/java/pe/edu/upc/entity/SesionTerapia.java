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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SesionTerapia")
public class SesionTerapia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "idPaciente", nullable = true)
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "idPsicologo", nullable = true)
	private Psicologo psicologo;

	@OneToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.MERGE) // ,orphanRemoval = true
	@JoinColumn(name = "idCalificacion", referencedColumnName = "id", nullable = true)
	private CalificacionDeLaSesion calificacion;

	@OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE) // ,orphanRemoval = true
	@JoinColumn(name = "idDocumento", referencedColumnName = "id", nullable = false)
	private DocumentoDeTrabajo documento;

	@OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE) // ,orphanRemoval = true
	@JoinColumn(name = "idTest", referencedColumnName = "id", nullable = false)
	private Test test;

	@Column(name = "titulo", nullable = false, length = 50)
	private String titulo;
	@Column(name = "descripcion", nullable = false, length = 200)
	private String descripcion;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechaDeLaSesion", nullable = false)
	private Date fechaDeLaSesion;

	@Column(name = "horaDeInicio", nullable = false)
	private byte horaDeInicio;

	@Column(name = "horaDelFin", nullable = false)
	private byte horaDelFin;

	public SesionTerapia() {
		super();
		// TODO Auto-generated constructor stub
	}
	// con id

	public SesionTerapia(int id, Paciente paciente, Psicologo psicologo, DocumentoDeTrabajo documento, Test test,
			String titulo, String descripcion, Date fechaDeLaSesion, byte horaDeInicio, byte horaDelFin) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.psicologo = psicologo;
		this.documento = documento;
		this.test = test;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaDeLaSesion = fechaDeLaSesion;
		this.horaDeInicio = horaDeInicio;
		this.horaDelFin = horaDelFin;
	}

	// sin id
	public SesionTerapia(int id, Paciente paciente, Psicologo psicologo, CalificacionDeLaSesion calificacion,
			DocumentoDeTrabajo documento, Test test, String titulo, String descripcion, Date fechaDeLaSesion,
			byte horaDeInicio, byte horaDelFin) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.psicologo = psicologo;
		this.calificacion = calificacion;
		this.documento = documento;
		this.test = test;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaDeLaSesion = fechaDeLaSesion;
		this.horaDeInicio = horaDeInicio;
		this.horaDelFin = horaDelFin;
	}

//DocumentoDeTrabajo documento, CalificacionDeLaSesion calificacion, Test test
	public SesionTerapia(CalificacionDeLaSesion calificacion, DocumentoDeTrabajo documento, Test test) {
		super();
		this.calificacion = calificacion;
		this.documento = documento;
		this.test = test;
	}

	public SesionTerapia(CalificacionDeLaSesion calificacion) {
		super();
		this.calificacion = calificacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Psicologo getPsicologo() {
		return psicologo;
	}

	public void setPsicologo(Psicologo psicologo) {
		this.psicologo = psicologo;
	}

	public CalificacionDeLaSesion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(CalificacionDeLaSesion calificacion) {
		this.calificacion = calificacion;
	}

	public DocumentoDeTrabajo getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoDeTrabajo documento) {
		this.documento = documento;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaDeLaSesion() {
		return fechaDeLaSesion;
	}

	public void setFechaDeLaSesion(Date fechaDeLaSesion) {
		this.fechaDeLaSesion = fechaDeLaSesion;
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

}
