package marianoesteban.vtv.model;

public class Control {

	private String descripcion;
	
	private String estado;

	public Control(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Control [descripcion=" + descripcion + ", estado=" + estado + "]";
	}
}
