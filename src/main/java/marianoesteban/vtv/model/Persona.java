package marianoesteban.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_persona")
	private Long id;
	
	private String dni;
	
	private String nombres;
	
	private String apellido;
	
	public Persona() {
	}

	public Persona(Long id, String dni, String nombres, String apellido) {
		this.id = id;
		this.dni = dni;
		this.nombres = nombres;
		this.apellido = apellido;
	}

	public Persona(String dni, String nombres, String apellido) {
		this.dni = dni;
		this.nombres = nombres;
		this.apellido = apellido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", dni=" + dni + ", nombres=" + nombres + ", apellido=" + apellido + "]";
	}
}
