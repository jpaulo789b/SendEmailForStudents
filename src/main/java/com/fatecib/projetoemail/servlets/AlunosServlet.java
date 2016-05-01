/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.servlets;

import com.fatecib.projetoemail.classes.Aluno;
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
@WebServlet(name = "CadastrarAlunos", urlPatterns = {"/emailsdeturma.ib"})
public class AlunosServlet extends HttpServlet {
private DAO dados;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dados = new DAO();
//            String id = req.getParameter("id");
//            String nome = req.getParameter("nome");
//            String email = req.getParameter("email");
//            String ulrAnterior = req.getParameter("url");
//            req.getRequestDispatcher("/adminstracao/alunos/editarAluno.jsp?id="+id+"&nome="+nome+"&email="+email+"&url="+ulrAnterior+"").forward(req, resp);
                String acao = req.getParameter("k");
                if(acao.equalsIgnoreCase("deletar")){
                    Aluno al = new Aluno();
                    al.setId(Integer.parseInt(req.getParameter("idd")));
                    dados.excluirAluno(al);
                    String k= req.getParameter("k");
                    String id = req.getParameter("id");
//                    req.getRequestDispatcher("turma.ib?k=editar"+id).forward(req, resp);
                    resp.sendRedirect("turma.ib?k=editar&id="+id);
                }

        } catch (Exception e) {
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dados = new DAO();
            String acao = req.getParameter("k");
            if(acao.equalsIgnoreCase("editar")){
                Aluno al = new Aluno();
                al.setId(Integer.parseInt(req.getParameter("id")));
                al.setNome(req.getParameter("nome"));
                al.setEmail(req.getParameter("email"));
                dados.updateAluno(al);
                String ulrAnterior = req.getParameter("url");
                req.getRequestDispatcher(ulrAnterior).forward(req, resp);
            }
        } catch (Exception e) {
            resp.getWriter().print(e);
        }
    }




}
