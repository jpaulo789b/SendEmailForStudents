/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.servlets.DAO;


import com.fatecib.projetoemail.classes.Aluno;
import com.fatecib.projetoemail.classes.ConfiguracaoSQL;
import com.fatecib.projetoemail.classes.Email;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Enviaremail {
    public boolean setEnviarAlunos(ArrayList<Aluno> al, Email em, ConfiguracaoSQL conf) {
    for(int i = 0; i < al.size(); i++){
        enviar(em, conf, al.get(i).getEmail());
    }
    return true;
}
    
   public  void enviar(Email em, ConfiguracaoSQL conf, String destinatario) {
      // Recipient's email ID needs to be mentioned.
      String to = destinatario;

      // Sender's email ID needs to be mentioned
      String from = conf.getUsuario();
      final String username = conf.getUsuario();//change accordingly
      final String password = conf.getSENHA();//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = conf.getEMAILSMTP();

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", conf.getPORTASMTP());

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
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
	   message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(to));

	   // Set Subject: header field
	   message.setSubject(em.getTitulo());

	   // Send the actual HTML message, as big as you like
	   message.setContent(
             em.getConteudo(),
             "text/html");

	   // Send message
	   Transport.send(message);

	   System.out.println("Email enviado com sucesso.");

      } catch (MessagingException e) {
	   e.printStackTrace();
	   throw new RuntimeException(e);
      }
   }
}