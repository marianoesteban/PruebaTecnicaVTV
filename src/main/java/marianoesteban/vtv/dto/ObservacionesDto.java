package marianoesteban.vtv.dto;

import java.util.ArrayList;
import java.util.List;

import marianoesteban.vtv.model.Control;

public class ObservacionesDto {
	
	private List<Control> observaciones;

	public ObservacionesDto() {
		observaciones = new ArrayList<Control>();
		observaciones.add(new Control("Luces"));
		observaciones.add(new Control("Patente"));
		observaciones.add(new Control("Espejos"));
		observaciones.add(new Control("Chasis"));
		observaciones.add(new Control("Vidrios"));
		observaciones.add(new Control("Seguridad y emergencia"));
	}

	public List<Control> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(List<Control> observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "ObservacionesDto [observaciones=" + observaciones + "]";
	}
}
