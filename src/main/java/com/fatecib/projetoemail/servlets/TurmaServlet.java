/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.servlets;

import com.fatecib.projetoemail.classes.Aluno;
import com.fatecib.projetoemail.classes.Cursos;
import com.fatecib.projetoemail.classes.Turma;
import com.fatecib.projetoemail.servlets.DAO.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author harlock
 */
@WebServlet(name = "TurmaServlet", urlPatterns = {"/turma.ib"})
public class TurmaServlet extends HttpServlet {

    private DAO dados;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dados = new DAO();
            String acao = null;
            acao = req.getParameter("k");
            if (acao.equalsIgnoreCase("turmas")) {
                ArrayList<Turma> lis = dados.listarturmas();
                req.setAttribute("lista", lis);
                req.getRequestDispatcher("/adminstracao/index.jsp?k=turmas").forward(req, resp);
            }
            if (acao.equalsIgnoreCase("novoCadastro")) {
                req.setAttribute("curso", dados.listarCursos());
                req.getRequestDispatcher("/adminstracao/index.jsp?k=cadastro").forward(req, resp);
            }
            if (acao.equalsIgnoreCase("editar")) {
                req.setAttribute("curso", dados.listarCursos());
                req.setAttribute("turma", dados.retornaUmaTurma(
                        Integer.parseInt(req.getParameter("id"))
                )
                );
                req.setAttribute("alunos", dados.retornaAlunos(Integer.parseInt(req.getParameter("id"))));
                req.getRequestDispatcher("/adminstracao/index.jsp?k=editar").forward(req, resp);
            }
            if (acao.equalsIgnoreCase("email")) {
                int id = Integer.parseInt(req.getParameter("turm"));
                req.setAttribute("curso", dados.listarCursos());
                req.setAttribute("turma", dados.retornaUmaTurma(
                Integer.parseInt(req.getParameter("id"))
                )
                );
                req.getRequestDispatcher("/adminstracao/index.jsp?k=turmas").forward(req, resp);
            }
        } catch (Exception e) {
            resp.getWriter().print(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            dados = new DAO();
            String acao = req.getParameter("k");
            if (acao.equalsIgnoreCase("Cadastrar")) {
                Turma t = new Turma();
                t.setPerido(req.getParameter("selectbasic"));
                t.setNumero(req.getParameter("numero"));
                t.setNomeCurso(req.getParameter("curso"));
                t.setDataInicio(req.getParameter("dataIn"));
                t.setDataFim(req.getParameter("datafim"));
                t.setIdCurso(Integer.parseInt(req.getParameter("curso")));
                dados.adicionarTurma(t);
                req.getRequestDispatcher("turma.ib?k=turmas").forward(req, resp);
            }
            if (acao.equalsIgnoreCase("editar")) {
                Turma t = new Turma();
                t.setPerido(req.getParameter("selectbasic"));
                t.setNumero(req.getParameter("numero"));
                t.setDataInicio(req.getParameter("dataIn"));
                t.setDataFim(req.getParameter("datafim"));
                t.setIdCurso(Integer.parseInt(req.getParameter("curso")));
                t.setIdTur(Integer.parseInt(req.getParameter("idTur")));
                dados.updateUmaTurma(t);
                req.getRequestDispatcher("/adminstracao/index.jsp?k=turmas").forward(req, resp);
            }
            if(acao.equalsIgnoreCase("adicionarAluno")){
                Aluno al = new Aluno();
                al.setNome(req.getParameter("nome"));
                al.setEmail(req.getParameter("email"));
                int id = Integer.parseInt(req.getParameter("id"));
                dados.cadastrarAluno(al, id);
                resp.sendRedirect("http://localhost:8080/ProjetoEmail/turma.ib?k=editar&id="+Integer.parseInt(req.getParameter("id")));
                        
            }
        } catch (Exception e) {
            resp.getWriter().print(e);
        }
    }

}
