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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Psicologo")
public class Psicologo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name="idUser",referencedColumnName = "id")
	private Users user;
	
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name = "nombres", nullable = false, length = 50)
	private String nombres;
	
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name = "apellidos", nullable = false, length = 50)
	private String apellidos;
	
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Size(min = 15, max=15,message = "{NotEmty.Psicologo.CelularPsicologo}")
	@Column(name = "celular", nullable = false, length = 9)
	private String celular;
	
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name = "direccion", nullable = false, length = 100)
	private String direccion;
	
	@NotEmpty(message = "NotEmty.Psicologo.CampoVacio")
	@Size(max=1,message ="{NotEmty.Psicologo.SexoPsicologo}")
	@Column(name = "sexo", nullable = false, length = 1)
	private String sexo;
	
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Size(min=8,max=8,message = "{NotEmty.Psicologo.DNIPsicologo}")
	@Column(name = "dni", nullable = false, length = 8)
	private String dni;
	
	@NotNull(message = "{NotEmty.Psicologo.Fecha}")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechaDeNacimiento", nullable = false)
	private Date fechaDeNacimiento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechadeRegistro", nullable = false)
	private Date fechadeRegistro;
	
	@NotNull(message = "{NotEmty.Psicologo.Fecha}")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechaInicioContrato", nullable = false)
	private Date fechaInicioContrato;
	
	@NotNull(message = "{NotEmty.Psicologo.Fecha}")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechaFinalContrato", nullable = false)
	private Date fechaFinalContrato;
	
	@NotEmpty(message = "{}NotEmty.Psicologo.CampoVacio}")
	@Size(min=3,max=9999999,message = "{NotEmty.Psicologo.Sueldo}")
	@Column(name = "sueldo", nullable = false, length = 7)
	private String sueldo;

	public Psicologo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	
	
	public Psicologo(int id, Users user, String nombres, String apellidos, String celular, String direccion,
			String sexo, String dni, Date fechaDeNacimiento, Date fechadeRegistro, Date fechaInicioContrato,
			Date fechaFinalContrato, String sueldo) {
		super();
		this.id = id;
		this.user = user;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.celular = celular;
		this.direccion = direccion;
		this.sexo = sexo;
		this.dni = dni;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.fechadeRegistro = fechadeRegistro;
		this.fechaInicioContrato = fechaInicioContrato;
		this.fechaFinalContrato = fechaFinalContrato;
		this.sueldo = sueldo;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Users getUser() {
		return user;
	}



	public void setUser(Users user) {
		this.user = user;
	}



	public String getNombres() {
		return nombres;
	}



	public void setNombres(String nombres) {
		this.nombres = nombres;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public String getCelular() {
		return celular;
	}



	public void setCelular(String celular) {
		this.celular = celular;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getSexo() {
		return sexo;
	}



	public void setSexo(String sexo) {
		this.sexo = sexo;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}



	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}



	public Date getFechadeRegistro() {
		return fechadeRegistro;
	}



	public void setFechadeRegistro(Date fechadeRegistro) {
		this.fechadeRegistro = fechadeRegistro;
	}



	public Date getFechaInicioContrato() {
		return fechaInicioContrato;
	}



	public void setFechaInicioContrato(Date fechaInicioContrato) {
		this.fechaInicioContrato = fechaInicioContrato;
	}



	public Date getFechaFinalContrato() {
		return fechaFinalContrato;
	}



	public void setFechaFinalContrato(Date fechaFinalContrato) {
		this.fechaFinalContrato = fechaFinalContrato;
	}



	public String getSueldo() {
		return sueldo;
	}



	public void setSueldo(String sueldo) {
		this.sueldo = sueldo;
	}

	
	
}
