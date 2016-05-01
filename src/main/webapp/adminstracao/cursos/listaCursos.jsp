<%@page import="com.fatecib.projetoemail.classes.Cursos"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<Cursos> lista = (ArrayList<Cursos>) request.getAttribute("cursos");
%>
<script type="text/javascript">

function confirmacao(id) {
     var resposta = confirm("Deseja remover esse registro?");
 
     if (resposta == true) {
          window.location.href = "adastrarcurso.ib?k=deletar&id="+id;
     }
}
</script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>

<div class="row">


    <div class="col-md-10 col-md-offset-1">

        <div class="panel panel-default panel-table">
            <div class="panel-heading">
                <div class="row">
                    <div class="col col-xs-6">
                        <h3 class="panel-title">Panel Heading</h3>
                    </div>
                    <div class="col col-xs-6 text-right">
                        <button type="button" class="btn btn-sm btn-primary btn-create">Create New</button>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered table-list">
                    <thead>
                        <tr>
                            <th><em class="fa fa-cog"></em></th>
                            <th>Curso</th>
                        </tr> 
                    </thead>
                    <tbody>
                        <% for(Cursos cs : lista){%>
                        <tr>
                            <td align="center">
                                <a href="cadastrarcurso.ib?k=editar&id=<%=cs.getIdCurso()%>" class="btn btn-default"><em class="fa fa-pencil"></em></a>
                                <a onclick="confirmacao(<%=cs.getIdCurso()%>)" class="btn btn-danger"><em class="fa fa-trash"></em></a>
                            </td>
                            <td><%=cs.getNome()%></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>

            </div>

        </div>

    </div></div>