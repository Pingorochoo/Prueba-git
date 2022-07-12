package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Plan")
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name = "nombre", nullable = false, length = 15)
	private String nombre;
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Column(name = "descripcion", nullable = false, length = 50)
	private String descripcion;
	@NotEmpty(message = "{NotEmty.Psicologo.CampoVacio}")
	@Size(min = 1, max=99999,message = "ingrese un precio")
	@Column(name = "precio", nullable = false)
	private double precio;//cambiar nombre a mensualidad

	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plan(int id, String nombre, String descripcion, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
}

