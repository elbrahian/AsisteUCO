package co.edu.uco.asistenciauco.aplication.outport.entity;

import java.util.UUID;

import co.edu.uco.asistenciauco.crosscutting.helpers.TextHelper;
import co.edu.uco.asistenciauco.crosscutting.helpers.UUIDHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "TipoIdentificacion")
public class TipoIdentificacionEntity {
	
	@Id
	@Column(name = "id")
	private UUID id;
	@Column(name = "clave")
	private String clave;
	@Column(name = "nombre")
	private String nombre;
	
	public UUID getId() {
		return id;
	}
	
	public void setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(final String clave) {
		this.clave = TextHelper.getDefault(clave);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(final String nombre) {
		this.nombre = TextHelper.getDefault(nombre);
	}
	
	public TipoIdentificacionEntity() {
		setDefaultId();
		setDefaultNombre();
		setDefaultClave();
	}
	
	public TipoIdentificacionEntity(final UUID id) {
	    setId(id);
	    setDefaultClave();
	    setDefaultNombre();
	}
	
	public TipoIdentificacionEntity(final UUID id, final String clave, final String nombre) {
	    setId(id);
	    setClave(clave);
	    setNombre(nombre);
	}

	private void setDefaultId() {
		setId(UUIDHelper.getDefault());
	}
	
	private void setDefaultNombre() {
	    setNombre(TextHelper.EMPTY);
	}

	private void setDefaultClave() {
		setClave(TextHelper.EMPTY);
	}
}