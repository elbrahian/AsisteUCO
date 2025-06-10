package co.edu.uco.asistenciauco.infreaestructure.primaryadapters.api.rest.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RutaMensajes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:mensajeError")
                .process(exchange -> {
                    CatalogoMensajes tipoMensaje = exchange.getIn().getHeader("tipoMensaje", CatalogoMensajes.class);
                    String mensaje = tipoMensaje.getMensaje();

                    exchange.getMessage().setBody(Map.of(
                            "mensaje", mensaje,
                            "codigo", tipoMensaje.name()
                    ));
                });
    }
}
