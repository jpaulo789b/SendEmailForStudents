/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.servlets;

import com.fatecib.projetoemail.classes.Email;
import com.fatecib.projetoemail.servlets.DAO.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author harlock
 */
@WebServlet(name = "EnviarEmail", urlPatterns = {"/email.ib"})
public class EnviarEmailServlet extends HttpServlet {
private DAO dados;
private String acao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dados = new DAO();
            acao = req.getParameter("k");
            if(acao.equalsIgnoreCase("email")){
                req.setAttribute("turmas", dados.listarturmas());
                req.getRequestDispatcher("/adminstracao/index.jsp?k=email").forward(req, resp);
            }
            if(acao.equalsIgnoreCase("listarEmails")){
                req.setAttribute("turmas", dados.listarEmail());
                req.getRequestDispatcher("/adminstracao/index.jsp?k=listarem").forward(req, resp);
            }
            if(acao.equalsIgnoreCase("enviarEmail")){
                
            }
        } catch (Exception e) {
            resp.getWriter().print(e.getCause());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dados = new DAO();
            acao = req.getParameter("k");
            if(acao.equalsIgnoreCase("enviar")){
                Email em = new Email();
                em.setTitulo(req.getParameter("titulo"));
                em.setConteudo(req.getParameter("corpoEmail"));
                em.setUrlAnexo(req.getParameter("file"));
                String[] idts = req.getParameterValues("idt");
                int[] destino = new int[idts.length];
                for (int i = 0; i < idts.length; i++) {
                    destino[i] = Integer.parseInt(idts[i]);
                }
                em.setDestino(destino);
                dados.emailEnviar(em);
                req.getRequestDispatcher("/adminstracao/index.jsp?k=email").forward(req, resp);
            }

        } catch (Exception e) {
            resp.getWriter().print(e.getMessage()+"Erro de post");
        }
    }

}
