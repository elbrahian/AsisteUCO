package co.edu.uco.asistenciauco.aplication.outport.mail;

public interface SendMailPort {
    boolean enviarCorreo(String remitente, String destinatario, String asunto, String mensaje);
}