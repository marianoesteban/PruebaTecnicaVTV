package marianoesteban.vtv.model;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Inspeccion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_inspeccion")
	private Long id;
	
	@NotNull(message="Debe especificar el número de inspección")
	private Long nroInspeccion;
	
	private Date fechaInspeccion;
	
	private String estadoInspeccion;
	
	@Column(columnDefinition="TINYINT")
	private boolean estaExento;
	
	@ManyToOne
	@JoinColumn(name="id_automovil")
	private Automovil automovil;
	
	@ManyToOne
	@JoinColumn(name="id_inspector")
	private Inspector inspector;
	
	public Inspeccion() {
		this.fechaInspeccion = new Date(Calendar.getInstance().getTime().getTime());
	}

	public Inspeccion(Long nroInspeccion, Date fechaInspeccion, String estadoInspeccion, boolean estaExento,
			Automovil automovil, Inspector inspector) {
		this.nroInspeccion = nroInspeccion;
		this.fechaInspeccion = fechaInspeccion;
		this.estadoInspeccion = estadoInspeccion;
		this.estaExento = estaExento;
		this.automovil = automovil;
		this.inspector = inspector;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNroInspeccion() {
		return nroInspeccion;
	}

	public void setNroInspeccion(Long nroInspeccion) {
		this.nroInspeccion = nroInspeccion;
	}

	public Date getFechaInspeccion() {
		return fechaInspeccion;
	}

	public void setFechaInspeccion(Date fechaInspeccion) {
		this.fechaInspeccion = fechaInspeccion;
	}

	public String getEstadoInspeccion() {
		return estadoInspeccion;
	}

	public void setEstadoInspeccion(String estadoInspeccion) {
		this.estadoInspeccion = estadoInspeccion;
	}

	public boolean isEstaExento() {
		return estaExento;
	}

	public void setEstaExento(boolean estaExento) {
		this.estaExento = estaExento;
	}

	public Automovil getAutomovil() {
		return automovil;
	}

	public void setAutomovil(Automovil automovil) {
		this.automovil = automovil;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	@Override
	public String toString() {
		return "Inspeccion [id=" + id + ", nroInspeccion=" + nroInspeccion + ", fechaInspeccion=" + fechaInspeccion
				+ ", estadoInspeccion=" + estadoInspeccion + ", estaExento=" + estaExento + ", automovil=" + automovil
				+ ", inspector=" + inspector + "]";
	}
}
