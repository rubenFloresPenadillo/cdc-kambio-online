package util.notificaciones;

import java.io.IOException;
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

import freemarker.template.TemplateException;

public class ClaseUtil {

	public static void operacion() {
		
		// Recipient's email ID needs to be mentioned.
		String to = "fpruben.89@gmail.com";
		// Sender's email ID needs to be mentioned
		String from = "casadecambioelalfarero.portal@gmail.com"; // Kambio Online
		final String username = "casadecambioelalfarero.portal@gmail.com";// change accordingly
		final String password = "D4nielD4niel123456";// change accordingly
		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// Set Subject: header field
			message.setSubject("SPlessons image");
			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");
			// first part (the html)
			
			String htmlText = "<H1>Hola este es un mensaje de prueba</H1><img src=\"cid:image\">";
			
			BodyPart messageBodyPart = new MimeBodyPart();
			
			messageBodyPart.setContent(htmlText, "text/html");
			// add it
			multipart.addBodyPart(messageBodyPart);
			// second part (the image)
			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource("D:/wsElAlfarero/app/src/main/webapp/resources/imagenes/logoHorizontal390x120.png");
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");
			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);
			// put everything together
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static void operacion2(Map<String, String> datamodel, String nombrePlantilla, String asunto) {
		
		String rutaPlantilla = "D:/wsElAlfarero/app/src/main/webapp/resources/plantillas/";
		
		
		
		// Recipient's email ID needs to be mentioned.
		String to = "fpruben.89@gmail.com";
		// Sender's email ID needs to be mentioned
		String from = "casadecambioelalfarero.portal@gmail.com"; // Kambio Online
		final String username = "casadecambioelalfarero.portal@gmail.com";// change accordingly
		final String password = "D4nielD4niel123456";// change accordingly
		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {

			String cuerpoMail = NotificacionUtil.freemarkerDo(datamodel, rutaPlantilla, nombrePlantilla);

			
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// Set Subject: header field
			message.setSubject("SPlessons image");
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
			DataSource fds = new FileDataSource("D:/wsElAlfarero/app/src/main/webapp/resources/imagenes/logoHorizontal390x120.png");
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");
			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);
			// put everything together
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException | IOException | TemplateException e ) {
			throw new RuntimeException(e);
		}
	}
}
