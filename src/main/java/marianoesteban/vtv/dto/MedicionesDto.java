package marianoesteban.vtv.dto;

import java.util.ArrayList;
import java.util.List;

import marianoesteban.vtv.model.Control;

public class MedicionesDto {

	private List<Control> mediciones;
	
	public MedicionesDto() {
		mediciones = new ArrayList<Control>();
		mediciones.add(new Control("Suspensión"));
		mediciones.add(new Control("Dirección"));
		mediciones.add(new Control("Tren delantero"));
		mediciones.add(new Control("Sistema de frenos"));
		mediciones.add(new Control("Contaminación ambiental"));
	}

	public List<Control> getMediciones() {
		return mediciones;
	}

	public void setMediciones(List<Control> mediciones) {
		this.mediciones = mediciones;
	}

	@Override
	public String toString() {
		return "MedicionesDto [mediciones=" + mediciones + "]";
	}
}
