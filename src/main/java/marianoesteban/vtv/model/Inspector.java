package marianoesteban.vtv.model;

import javax.persistence.Entity;

@Entity
public class Inspector extends Persona {

	private String nroLegajo;

	public Inspector() {
		super();
	}
	
	public Inspector(String dni, String nroLegajo, String nombres, String apellido) {
		super(dni, nombres, apellido);
		this.nroLegajo = nroLegajo;
	}

	public String getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(String nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

	@Override
	public String toString() {
		return "Inspector [nroLegajo=" + nroLegajo + ", getId()=" + getId() + ", getDni()=" + getDni()
				+ ", getNombres()=" + getNombres() + ", getApellido()=" + getApellido() + "]";
	}
}
