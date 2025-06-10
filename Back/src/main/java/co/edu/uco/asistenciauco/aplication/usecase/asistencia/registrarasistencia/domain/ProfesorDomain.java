package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@AllArgsConstructor
public final class ProfesorDomain {
	private UUID id;
	private String nombreCompleto;
	private String numeroIdentificacion;

	public UUID getId() {
		return id;
	}

	private void setId(final UUID id) {
		this.id = id;
	}

	public ProfesorDomain(UUID id) {
		setId(id);
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
}
