package co.edu.uco.asistenciauco.crosscutting.exceptions;

import co.edu.uco.asistenciauco.crosscutting.exceptions.enums.Layer;
import co.edu.uco.asistenciauco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.asistenciauco.crosscutting.helpers.TextHelper;

public class AplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String userMessage;
	
	private Layer layer;

	public AplicationException(final String userMessage, 
			final String technicalMessage, 
			final Exception rootException, 
			final Layer layer) {
		super(TextHelper.applyTrim(technicalMessage), ObjectHelper.getDefault(rootException, new Exception()));
		
		setUserMessage(userMessage);
		setLayer(layer);
	}

	private void setUserMessage(final String userMessage) {
		this.userMessage = TextHelper.applyTrim(userMessage);
	}
	
	
	private void setLayer(final Layer layer) {
		this.layer = ObjectHelper.getDefault(layer, Layer.GENERAL);
	}
	
	public String getUserMessage() {
		return userMessage;
	}
	
	public Layer getLayer() {
		return layer;
	}
}
