package marianoesteban.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Automovil {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_automovil")
	private Long id;
	
	private String dominio;
	
	private String marca;
	
	private String modelo;
	
	@ManyToOne
	@JoinColumn(name="id_propietario")
	private Persona propietario;
	
	public Automovil() {
	}

	public Automovil(Long id, String dominio, String marca, String modelo, Persona propietario) {
		this.id = id;
		this.dominio = dominio;
		this.marca = marca;
		this.modelo = modelo;
		this.propietario = propietario;
	}

	public Automovil(String dominio, String marca, String modelo, Persona propietario) {
		this.dominio = dominio;
		this.marca = marca;
		this.modelo = modelo;
		this.propietario = propietario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	@Override
	public String toString() {
		return "Automovil [dominio=" + dominio + ", marca=" + marca + ", modelo=" + modelo + ", propietario="
				+ propietario + "]";
	}
}
