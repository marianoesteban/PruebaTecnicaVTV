package marianoesteban.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_persona")
	private Long id;
	
	@NotBlank(message="Debe especificar el DNI")
	@Size(min=7, max=8, message="El DNI debe tener 7 u 8 caracteres")
	@Pattern(regexp="[0-9]*", message="El DNI sólo debe contener números")
	private String dni;
	
	@NotBlank(message="Debe especificar el nombre")
	private String nombres;
	
	@NotBlank(message="Debe especificar el apellido")
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
