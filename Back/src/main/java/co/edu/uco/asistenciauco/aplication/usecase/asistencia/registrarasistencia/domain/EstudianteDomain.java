package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain;

import lombok.ToString;

import java.util.UUID;

@ToString
public final class EstudianteDomain {
	
	private UUID id;
	private boolean asistio;
	
	public EstudianteDomain(final UUID id, final boolean asistio) {
		setId(id);
		setAsistio(asistio);
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public boolean isAsistio() {
		return asistio;
	}

	public void setAsistio(final boolean asistio) {
		this.asistio = asistio;
	}
	
	
}
