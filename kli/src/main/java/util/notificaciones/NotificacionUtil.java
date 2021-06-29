package util.notificaciones;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import loggerUtil.LoggerUtil;
import util.types.RutasBaseType;


public class NotificacionUtil {
	
    public static final String EMAIL_SERVICIO = "casadecambioelalfarero.portal@gmail.com"; // propietario de cuenta
    public static final String NOMBRE_SERVICIO = "Kambio Online"; // nombre de propietario
    public static final String PASSWORD= "D4nielD4niel123456"; // password de propietario
    public static final String EMAIL_CONTACTO_COMERCIO = "pagos@kambio.online"; // propietario de cuenta
    

	public static String freemarkerDo(Map<String, String> datamodel, String basePath, String template) throws IOException, TemplateException {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
			cfg.setDirectoryForTemplateLoading(new File(basePath));
			Template tpl = cfg.getTemplate(template);
			StringWriter sw = new StringWriter();
			tpl.process(datamodel, sw);
			return sw.toString();	
	}
	
	
	
	public static void enviarCorreo (Map<String, String> datamodel, String nombrePlantilla, String asunto, String correoDestino) {

		LoggerUtil.getInstance().getLogger().info("Inicio enviarCorreo: "+nombrePlantilla);
		
		final String  IMAGEN_LOGO = "logoHorizontal390x120.png";

//		String from = "casadecambioelalfarero.portal@gmail.com"; // Kambio Online
		String valorDelFrom = "Kambio Online";
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_SERVICIO, PASSWORD);
			}
		});
		try {

			String cuerpoMail = freemarkerDo(datamodel, RutasBaseType.RUTA_BASE_PLANTILLAS.getValor() , nombrePlantilla);

			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(EMAIL_SERVICIO,valorDelFrom));
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
			// Set Subject: header field
			message.setSubject(asunto);
			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");
			// first part (the html)
			
//			String htmlText = "<H1>Hola este es un mensaje de prueba</H1><img src=\"cid:image\">";
			
			BodyPart messageBodyPart = new MimeBodyPart();
			
//			messageBodyPart.setContent(htmlText, "text/html");
			messageBodyPart.setContent(cuerpoMail, "text/html");
			// add it
			multipart.addBodyPart(messageBodyPart);
			// second part (the image)
			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource(RutasBaseType.RUTA_BASE_IMAGENES.getValor()+IMAGEN_LOGO);
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");
			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);
			// put everything together
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			LoggerUtil.getInstance().getLogger().info("Mensaje enviado con exito a: "+correoDestino+", plantilla: "+nombrePlantilla);
		} catch (MessagingException | IOException | TemplateException e ) {
			e.printStackTrace();
	        LoggerUtil.getInstance().getLogger().error("nombrePlantilla: "+nombrePlantilla+" correoDestino: "+correoDestino+" error: "+e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
//			throw new RuntimeException(e);
		}
	}
	
	public static void enviarCorreoAComercio (String nombrePlantilla, String asunto, String cuerpoMensaje) {

		LoggerUtil.getInstance().getLogger().info("Inicio enviarCorreo: "+nombrePlantilla);

		String valorDelFrom = "Contacto - Kambio Online";
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_SERVICIO, PASSWORD);
			}
		});
		try {
			
			Email email = new SimpleEmail();
			email.setMailSession(session);
			email.setFrom(EMAIL_SERVICIO, valorDelFrom);
            email.setSubject(asunto);
            email.setMsg(cuerpoMensaje);
            email.addTo(EMAIL_CONTACTO_COMERCIO);
            email.send(); // enviar email
            
			LoggerUtil.getInstance().getLogger().info("Mensaje enviado con exito a: "+EMAIL_CONTACTO_COMERCIO+", plantilla: "+nombrePlantilla);
		} catch (EmailException e ) {
			e.printStackTrace();
	        LoggerUtil.getInstance().getLogger().error("nombrePlantilla: "+nombrePlantilla+" correoDestino: "+EMAIL_CONTACTO_COMERCIO+" error: "+e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
	}
}