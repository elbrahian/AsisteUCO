package co.edu.uco.asistenciauco.aplication.usecase.asistencia.registrarasistencia.domain;

import java.util.ArrayList;
import java.util.List;

public class RegistrarAsistenciaResponseVO {

    private List<String> mensajes;

    public RegistrarAsistenciaResponseVO() {
        setMensajes(new ArrayList<>());
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    private void setMensajes(List<String> mensajes) {
        // TODO: Asegurar que los datos no sean nulos y en caso de que lo sean crear el
        // valor defecto
        this.mensajes = mensajes;
    }

    public void agregarMensajes(List<String> mensajes) {
        //TODO: Validar que la lista de mensajes no llegue nula
    	getMensajes().addAll(mensajes);
    }

    public void agregarMensaje(String mensaje) {
        // TODO: Asegurar que no esté nulo o vacío
        getMensajes().add(mensaje);
    }

    public boolean isValidacionCorrecta() {
        return getMensajes().isEmpty();
    }
}