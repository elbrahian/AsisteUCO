package co.edu.uco.asistenciauco.infreaestructure.secondaryadapter;

import co.edu.uco.asistenciauco.aplication.outport.mail.SendMailPort;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridAdapter implements SendMailPort {
    @Value("${sendgrid.api-key}")
    private String apiKey;
    @Override
    public boolean enviarCorreo(String remitente, String destinatario, String asunto, String mensaje) {
        Email from = new Email(remitente);
        Email to = new Email(destinatario);
        Content content = new Content("text/plain", mensaje);
        Mail mail = new Mail(from, asunto, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                return true;
            } else {
                System.err.println("Fallo en el envío. Código: " + response.getStatusCode());
                System.err.println("Respuesta: " + response.getBody());
                return false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}