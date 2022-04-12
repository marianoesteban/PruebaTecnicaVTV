package marianoesteban.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Modelo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_modelo")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_marca")
	private Marca marca;
	
	@NotBlank(message="Debe especificar el modelo")
	private String nombre;
	
	public Modelo() {
	}

	public Modelo(Long id, Marca marca, String nombre) {
		this.id = id;
		this.marca = marca;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", marca=" + marca + ", nombre=" + nombre + "]";
	}

}
