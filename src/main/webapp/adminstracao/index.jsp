<%
    String oQ = request.getParameter("k");
%>
<jsp:include page="../tpl/header.jsp" />
<%if(oQ.equalsIgnoreCase("sistema"))%><jsp:include page="informacao.jsp" /><%;%>
<%if(oQ.equalsIgnoreCase("turmas"))%><jsp:include page="turmas/listarTurmas.jsp" /><%;%>
<%if(oQ.equalsIgnoreCase("cadastro"))%><jsp:include page="turmas/novaTurma.jsp" /><%;%>
<%if(oQ.equalsIgnoreCase("editar"))%><jsp:include page="turmas/editarTurma.jsp" /><%;%>
<%if(oQ.equalsIgnoreCase("cursos"))%><jsp:include page="cursos/listaCursos.jsp" /><%;%>
<%if(oQ.equalsIgnoreCase("novoC"))%><jsp:include page="cursos/novoCurso.jsp" /><%;%>
<%if(oQ.equalsIgnoreCase("editarC"))%><jsp:include page="cursos/editarcurso.jsp" /><%;%>
<%if(oQ.equalsIgnoreCase("email"))%><jsp:include page="email/novoEmail.jsp" /><%;%>
<%if(oQ.equalsIgnoreCase("listarem"))%><jsp:include page="email/listarEmails.jsp" /><%;%>
<jsp:include page="../tpl/footer.jsp" />