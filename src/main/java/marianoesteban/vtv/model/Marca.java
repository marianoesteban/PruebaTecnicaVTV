package marianoesteban.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Marca {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_marca")
	private Long id;
	
	@NotBlank(message="Debe especificar la marca")
	private String nombre;
	
	public Marca() {
	}

	public Marca(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Marca [id=" + id + ", nombre=" + nombre + "]";
	}
}
