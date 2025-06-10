package co.edu.uco.asistenciauco.aplication.outport.entity;

import java.util.UUID;

import co.edu.uco.asistenciauco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.asistenciauco.crosscutting.helpers.TextHelper;
import co.edu.uco.asistenciauco.crosscutting.helpers.UUIDHelper;
import jakarta.persistence.*;

@Entity
@Table(name = "Profesor")
public class ProfesorEntity {

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

	public void setId(UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
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

	public TipoIdentificacionEntity getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = TextHelper.getDefault(correo);
	}
	
	public void setTipoIdentificacion(TipoIdentificacionEntity tipoIdentificacion) {
		this.tipoIdentificacion = ObjectHelper.getDefault(tipoIdentificacion, new TipoIdentificacionEntity());
	}

	public ProfesorEntity() {
		setDefaultId();
        setDefaultTipoIdentificacion();
        setDefaultNumeroIdentificacion();
        setDefaultNombresCompletos();
        setDefaultCorreo();
	}
	
	public ProfesorEntity(final UUID id) {
        setId(id);
        setDefaultTipoIdentificacion();
        setDefaultNumeroIdentificacion();
        setDefaultNombresCompletos();
        setDefaultCorreo();
    }

	public ProfesorEntity(UUID id, String numeroIdentificacion, String nombresCompletos) {
		setId(id);
        setDefaultTipoIdentificacion();
        setNumeroIdentificacion(numeroIdentificacion);
        setNombresCompletos(nombresCompletos);
        setDefaultCorreo();
	}
	
	private void setDefaultId() {
		setId(UUIDHelper.generate());
    }

    private void setDefaultTipoIdentificacion() {
        setTipoIdentificacion(new TipoIdentificacionEntity());
    }

    private void setDefaultNumeroIdentificacion() {
        setNumeroIdentificacion(TextHelper.EMPTY);
    }

    private void setDefaultNombresCompletos() {
        setNombresCompletos(TextHelper.EMPTY);
    }
    
    private void setDefaultCorreo() {
        setCorreo(TextHelper.EMPTY);
    }
}
