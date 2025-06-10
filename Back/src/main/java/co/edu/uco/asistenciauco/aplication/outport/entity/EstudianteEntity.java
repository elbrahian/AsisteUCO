package co.edu.uco.asistenciauco.aplication.outport.entity;

import java.util.UUID;

import co.edu.uco.asistenciauco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.asistenciauco.crosscutting.helpers.TextHelper;
import co.edu.uco.asistenciauco.crosscutting.helpers.UUIDHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Estudiante")
public class EstudianteEntity {
	@Id
	@Column(name = "id")
	private UUID id;
	@ManyToOne
	@JoinColumn(name = "tipoIdentificacion")
	private TipoIdentificacionEntity tipoIdentificacion;
	@Column(name = "numeroIdentificacion")
	private String numeroIdentificacion;
	@Column(name = "nombresCompletos")
	private String nombresCompletos;
	@Column(name = "correo")
	private String correo;

	public UUID getId() {
		return id;
	}
	
	public void setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}
	
	public TipoIdentificacionEntity getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	
	public void setTipoIdentificacion(TipoIdentificacionEntity tipoIdentificacion) {
		this.tipoIdentificacion = ObjectHelper.getDefault(tipoIdentificacion, new TipoIdentificacionEntity());
	}
	
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = TextHelper.getDefault(numeroIdentificacion);
	}
	
	public String getNombresCompletos() {
		return nombresCompletos;
	}
	
	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = TextHelper.getDefault(nombresCompletos);
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = TextHelper.getDefault(correo);
	}
	
	public EstudianteEntity(final UUID id, final TipoIdentificacionEntity tipoIdentificacion, final String numeroIdentificacion,
							final String nombresCompletos) {
		setId(id);
		setTipoIdentificacion(tipoIdentificacion);
		setNumeroIdentificacion(numeroIdentificacion);
		setNombresCompletos(nombresCompletos);
	}
	
	public EstudianteEntity() {
        setDefaultId();
        setDefaultNumeroIdentificacion();
        setDefaultNombresCompletos();
        setDefaultTipoIdentificacion();
        setDefaultCorreo();
    }
    
	public EstudianteEntity(final UUID id) {
	        setId(id);
	        setDefaultNumeroIdentificacion();
	        setDefaultNombresCompletos();
	        setDefaultTipoIdentificacion();
	        setDefaultCorreo();
	}
	
	private void setDefaultId() {
		 setId(UUIDHelper.generate());
	}

	private void setDefaultNumeroIdentificacion() {
	    setNumeroIdentificacion(TextHelper.EMPTY);
	}
	
	private void setDefaultNombresCompletos() {
	    setNombresCompletos(TextHelper.EMPTY);
	}
	
	private void setDefaultTipoIdentificacion() {
	    setTipoIdentificacion(new TipoIdentificacionEntity());
	}
	
	private void setDefaultCorreo() {
	    setCorreo(TextHelper.EMPTY);
	}
}