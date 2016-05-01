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
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.mail.*;

public class Enviaremail2 {
public boolean setEnviarAlunos(ArrayList<Aluno> al, Email em, ConfiguracaoSQL conf) throws EmailException {
    for(int i = 0; i < al.size(); i++){
        enviar(em, conf, al.get(i).getEmail());
    }
    return true;
}
private  void enviar(Email em, ConfiguracaoSQL conf, String destinatario) throws EmailException {
    try {
          HtmlEmail email = new HtmlEmail();
        email.setHostName(conf.getEMAILHOST());
        email.setSmtpPort(conf.getPORTASMTP());
        email.setAuthenticator(new DefaultAuthenticator(conf.getUsuario(), conf.getSENHA()));
        email.setSSL(true);
        email.setFrom(conf.getUsuario());
        email.setSubject(em.getTitulo());
        email.setHtmlMsg(em.getConteudo());
        // set the alternative message
        email.setTextMsg("Email enviado com sucesso");
        email.addTo(destinatario);
        email.send();
    } catch (Exception e) {
        throw e;
    }
      
  }
}