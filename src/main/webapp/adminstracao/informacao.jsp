<%@page import="com.fatecib.projetoemail.classes.InfoSis"%>
<%
InfoSis inf = (InfoSis) request.getAttribute("Info");
%>
<div class="panel panel-default">
    <div class="panel-heading">Informações basicas do sistema</div>  
    <table class="table"> 
        <thead> 
            <tr>
                <th>Alunos Cadastrados</th> 
                <th>Turmas Cadastradas</th>
                <th>Cursos Cadastrados</th>
                <th>Emails enviados</th>
            </tr>
        </thead>
        <tbody> 
            <tr>
                <td scope="row"><%=inf.getAlunos()%></td> 

                <td scope="row"><%=inf.getTurmas()%></td>

                <td scope="row"><%=inf.getCursos()%></td>

                <td scope="row"><%=inf.getEmails()%></td>
            </tr> 
        </tbody> 
    </table> 
</div>
            
            



