package marianoesteban.vtv.model;

import javax.persistence.Entity;

@Entity
public class Inspector extends Persona {

	public Inspector() {
		super();
	}
	
	public Inspector(String dni, String nombres, String apellido) {
		super(dni, nombres, apellido);
	}

	@Override
	public String toString() {
		return "Inspector [getId()=" + getId() + ", getDni()=" + getDni() + ", getNombres()=" + getNombres()
				+ ", getApellido()=" + getApellido() + "]";
	}
}
