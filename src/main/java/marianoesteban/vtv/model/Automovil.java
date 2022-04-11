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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_automovil")
	private Long id;

	private String dominio;

	@ManyToOne
	@JoinColumn(name = "id_version_automovil")
	private Version versionAutomovil;

	@ManyToOne
	@JoinColumn(name = "id_propietario")
	private Persona propietario;

	public Automovil() {
	}

	public Automovil(Long id, String dominio, Version versionAutomovil, Persona propietario) {
		this.id = id;
		this.dominio = dominio;
		this.versionAutomovil = versionAutomovil;
		this.propietario = propietario;
	}

	public Automovil(String dominio, Version versionAutomovil, Persona propietario) {
		this.dominio = dominio;
		this.versionAutomovil = versionAutomovil;
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

	public Version getVersionAutomovil() {
		return versionAutomovil;
	}

	public void setVersionAutomovil(Version versionAutomovil) {
		this.versionAutomovil = versionAutomovil;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	@Override
	public String toString() {
		return "Automovil [id=" + id + ", dominio=" + dominio + ", versionAutomovil=" + versionAutomovil
				+ ", propietario=" + propietario + "]";
	}
}
