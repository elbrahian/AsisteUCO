package co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel;

public enum CatalogoMensajes {
    NO_SESION_IDENTIFICADOR("No existe una sesión con el identificador "),
    NO_RELACION_ESTUDIANTE_GRUPO("No se encontró la relación estudiante-grupo especificada."),
    GRUPO_NO_ACTIVO("El grupo no está activo."),
    PROFESOR_NO_ASIGNADO_GRUPO("El profesor no está asignado al grupo especificado."),
    NO_PROFESOR_IDENTIFICADOR("No existe un profesor con el identificador "),
    ERROR_VALIDACION_ASISTENCIA("Error de validación al registrar asistencia"),
    ERROR_SERVIDOR_ASISTENCIA("Error interno del servidor al registrar asistencia"),
    GRUPOS_NO_ENCONTRADOS("No se encontraron grupos"),
    GRUPOS_RECUPERADOS("Grupos recuperados exitosamente"),
    ERROR_RECUPERAR_GRUPOS("Error al recuperar los grupos"),
    ERROR_RECUPERAR_ESTUDIANTES_GRUPO("Error al recuperar los estudiantes del grupo"),
    NO_SESIONES_GRUPO("No se encontraron sesiones para el grupo especificado"),
    SESIONES_GRUPO_EXITO("Sesiones del grupo recuperadas exitosamente"),
    ERROR_SESIONES_GRUPO("Error al recuperar las sesiones del grupo"),
    NO_ESTUDIANTES_GRUPO("No se encontraron estudiantes para el grupo especificado"),
    ESTUDIANTES_RECUPERADOS("Estudiantes del grupo recuperados exitosamente"),
    PROFESOR_NO_ENCONTRADO("profesor no encontrado"),
    ESTUDIANTE_NO_REGISTRADO("El estudiante no está registrado en este grupo"),
    ESTUDIANTE_NO_ENCONTRADO("Estudiante no fue encontrado"),
    ASISTENCIA_REGISTRADA("Asistencia registrada correctamente"),
    ESTUDIANTE_TIENE_MATERIA_CANCELADA("El estudiante tiene la materia cancelada en este grupo."),
    ERROR_ASISTENCIA_BASE_DE_DATOS("Error al guardar la asistencia en la base de datos"),
    ERROR_NOTIFICACION_INASISTENCIA("Error al enviar la notificación de inasistencia: "),
    ESTUDIANTE_NO_EXISTE_CON_ID("No existe un estudiante con el identificador");


    private final String mensaje;

    CatalogoMensajes(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
