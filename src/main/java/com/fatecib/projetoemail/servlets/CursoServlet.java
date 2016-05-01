/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.servlets;

import com.fatecib.projetoemail.classes.Cursos;
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
@WebServlet(name = "CadastrarCurso", urlPatterns = {"/cadastrarcurso.ib"})
public class CursoServlet extends HttpServlet {
private DAO dados;
private String acao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dados = new DAO();
            acao = req.getParameter("k");
            
            if(acao.equalsIgnoreCase("cursos")){
                req.setAttribute("cursos", dados.listarCursos());
                 req.getRequestDispatcher("/adminstracao/index.jsp?k=cursos").forward(req, resp);
            }
            if(acao.equalsIgnoreCase("deletar")){
                 dados.deletaCurso(Integer.parseInt(req.getParameter("id")));
                 req.getRequestDispatcher("/adminstracao/index.jsp?k=cursos").forward(req, resp);
            }
            if(acao.equalsIgnoreCase("inserirC")){
                Cursos c = new Cursos();
                c.setNome(req.getParameter("nome"));
                dados.adicionarCursos(c);
                req.getRequestDispatcher("/adminstracao/index.jsp?k=novoC").forward(req, resp);
            }
            if(acao.equalsIgnoreCase("editar")){
                req.setAttribute("curso", dados.retornaUmCurso(Integer.parseInt(req.getParameter("id"))));
                req.getRequestDispatcher("/adminstracao/index.jsp?k=editarC").forward(req, resp);
            }
        } catch (Exception e) {
            resp.getWriter().print(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
        dados = new DAO();
        acao = req.getParameter("k");
        if(acao.equalsIgnoreCase("editar")){
            Cursos c = new Cursos();
            c.setIdCurso(Integer.parseInt(req.getParameter("id")));
            c.setNome(req.getParameter("nome"));
            dados.updateCurso(c);
        }
            
        } catch (Exception e) {
            resp.getWriter().print(e);
        }
    }
    
    

}
