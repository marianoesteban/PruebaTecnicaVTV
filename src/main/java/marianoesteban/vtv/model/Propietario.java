package marianoesteban.vtv.model;

import javax.persistence.Entity;

@Entity
public class Propietario extends Persona {

	public Propietario() {
		super();
	}
	
	public Propietario(Long id, Long dni, String nombres, String apellido) {
		super(id, dni, nombres, apellido);
	}
	
	public Propietario(Long dni, String nombres, String apellido) {
		super(dni, nombres, apellido);
	}

	@Override
	public String toString() {
		return "Propietario [getId()=" + getId() + ", getDni()=" + getDni() + ", getNombres()=" + getNombres()
				+ ", getApellido()=" + getApellido() + "]";
	}
}
