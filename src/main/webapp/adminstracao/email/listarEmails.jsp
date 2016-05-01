<%@page import="com.fatecib.projetoemail.classes.Aluno"%>
<%@page import="com.fatecib.projetoemail.classes.Email"%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
<%@page import="java.util.ArrayList"%>
<%@page import="com.fatecib.projetoemail.classes.Turma"%>
<%
    ArrayList<Email> ems = (ArrayList<Email>)request.getAttribute("turmas");
    String sl = null;
%>
<style type="text/css">
.panel-table .panel-body{
  padding:0;
}

.panel-table .panel-body .table-bordered{
  border-style: none;
  margin:0;
}

.panel-table .panel-body .table-bordered > thead > tr > th:first-of-type {
    text-align:center;
    width: 100px;
}

.panel-table .panel-body .table-bordered > thead > tr > th:last-of-type,
.panel-table .panel-body .table-bordered > tbody > tr > td:last-of-type {
  border-right: 0px;
}

.panel-table .panel-body .table-bordered > thead > tr > th:first-of-type,
.panel-table .panel-body .table-bordered > tbody > tr > td:first-of-type {
  border-left: 0px;
}

.panel-table .panel-body .table-bordered > tbody > tr:first-of-type > td{
  border-bottom: 0px;
}

.panel-table .panel-body .table-bordered > thead > tr:first-of-type > th{
  border-top: 0px;
}

.panel-table .panel-footer .pagination{
  margin:0; 
}

/*
used to vertically center elements, may need modification if you're not using default sizes.
*/
.panel-table .panel-footer .col{
 line-height: 34px;
 height: 34px;
}

.panel-table .panel-heading .col h3{
 line-height: 30px;
 height: 30px;
}

.panel-table .panel-body .table-bordered > tbody > tr > td{
  line-height: 34px;
}
</style>


<div class="container">
    <div class="row">
    
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
              <div class="panel-heading">
                <div class="row">
                  <div class="col col-xs-6">
                    <h3 class="panel-title">Emails enviados</h3>
                  </div>
                  <div class="col col-xs-6 text-right">
                  </div>
                </div>
              </div>
              <div class="panel-body">
                <table class="table table-striped table-bordered table-list">
                  <thead>
                    <tr>
                        <th><em class="fa fa-cog"></em></th>
                        <th class="hidden-xs">TITULO</th>
                        <th>Turmas</th>
                        <th>Data</th>
                    </tr> 
                  </thead>
                  <tbody>
                  <% for(Email em:ems){ %>    
                          <tr>
                            <td align="center">
                                
                            </td>
                            <td><%=em.getTitulo()%></td>
                            <td>
                                <%for(Turma ts: em.getTurmasEnviadas()){ %>
                                <span class="badge">
                                Turma nº <%=ts.getNumero()%> |
                                <%=ts.getNomeCurso()%>
                                </span>
                                <%}%>
                            </td>
                            <td><%=em.getData()%></td>
                          </tr>
                      <%}%>
                  </tbody>
                </table>
            
              </div>
              
            </div>

</div></div></div>