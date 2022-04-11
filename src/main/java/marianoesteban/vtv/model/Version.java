package marianoesteban.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Version {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_version")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_modelo")
	private Modelo modelo;
	
	private String nombre;
	
	public Version() {
	}

	public Version(Long id, Modelo modelo, String nombre) {
		this.id = id;
		this.modelo = modelo;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Version [id=" + id + ", modelo=" + modelo + ", nombre=" + nombre + "]";
	}
	
}
