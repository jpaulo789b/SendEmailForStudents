/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.servlets;

import com.fatecib.projetoemail.classes.Adminstrador;
import com.fatecib.projetoemail.classes.InfoSis;
import com.fatecib.projetoemail.servlets.DAO.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author harlock
 */
@WebServlet(name = "login", urlPatterns = {"/login.ib"})
public class Login extends HttpServlet {

    private DAO dados;
    private InfoSis inf;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1) Capturar os dados
            String login = req.getParameter("Login");
            String senha = req.getParameter("senha");
            // 2)Criar objeto usuário e adicionar os dados
            Adminstrador usu = new Adminstrador();
            usu.setEmail(login);
            usu.setSenha(senha);
            // 3) Criar objeto UsuarioDAO e Autenticar
            dados = new DAO();
            Adminstrador usuAutenticado = dados.retornaAdminstrador(usu);
            // 4) Verificar se usuário foi encontrado
            if (usuAutenticado != null) {
                // Usuário encontrado
                // 5) Criar Sessão
                HttpSession sessao = req.getSession();
                // 6) Adicionar objeto como atributo da sessão
                sessao.setAttribute("usuAutenticado", usuAutenticado);
                // Definindo um tempo para a Sessão expirar
                sessao.setMaxInactiveInterval(60*30);
                // 7) Pega dados do sistema
                inf = dados.infoSistema();
                req.setAttribute("Info", inf);
                // 8)Encaminhar para a tela de bem vindo
                req.getRequestDispatcher("/adminstracao/index.jsp?k=sistema").forward(req, resp);
            } else {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }catch(Exception e){
            resp.getWriter().print(e);
        }
    }

}
