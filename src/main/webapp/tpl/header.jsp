<%-- 
    Document   : header
    Created on : 21/04/2016, 12:01:33
    Author     : harlock
--%>
<%@page import="com.fatecib.projetoemail.classes.Adminstrador"%>
<%
// Caputurando tipo de usuario do request.
    Adminstrador usu = (Adminstrador) session.getAttribute("usuAutenticado");

%>
<%    if (session == null) {
        String address = "index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FATEC-IB. Envio de emails</title>

        <link rel="stylesheet" href="tpl/bootstrap/css/bootstrap.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body style="padding-top: 70px;">
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Menu</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"><strong>FATEC-IB</strong></a>
                </div>
                <%if (usu != null) {%>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="#">Página Inicial</a></li>
                        <li >

                                <a href="#" data-toggle="dropdown" ><span class="glyphicon glyphicon-inbox"></span> E-mail</a>
                                <ul class="dropdown-menu">
                                    <li><a href="email.ib?k=listarEmails">Listar e-mails</a></li>
                                    <li><a href="email.ib?k=email">Enviar e-mail</a></li>
                                </ul>
                            <!-- Single button -->

                        </li>
                        <li><a href="turma.ib?k=turmas">Turmas</a></li>
                        <li><a href="cadastrarcurso.ib?k=cursos">Cursos</a></li>

                    </ul>  

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Bem vindo! <%=usu.getNome()%></a></li>
                        <li><a href="#">Configurações</a></li>
                    </ul>

                </div>
                <%}%>
            </div>
        </nav>
        <div class="container">
