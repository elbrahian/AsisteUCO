package co.edu.uco.asistenciauco.aplication.usecase.validator;

import co.edu.uco.asistenciauco.crosscutting.helpers.ObjectHelper;

import java.util.ArrayList;
import java.util.List;

public class ValidationResultVO {
	private List<String> mensajes;
	public ValidationResultVO() {
		setMensajes(new ArrayList<>());
	}
	public List<String> getMensajes() {
		return mensajes;
	}
	private void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}
	public void agregarMensajes(List<String> mensajes) {
		if (!ObjectHelper.isNull(mensajes) && !mensajes.isEmpty()) {
			this.mensajes.addAll(mensajes);
		}
	}
	public void agregarMensaje(String mensaje) {
		if (!ObjectHelper.isNull(mensajes)) {
			getMensajes().add(mensaje);
		}
	}
	public boolean isValidacionCorrecta() {
		return getMensajes().isEmpty();
	}
}