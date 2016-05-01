<%@page import="com.fatecib.projetoemail.classes.Aluno"%>
<%@page import="com.fatecib.projetoemail.classes.Turma"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.fatecib.projetoemail.classes.Cursos"%>
<%
    ArrayList<Cursos> c = (ArrayList<Cursos>) request.getAttribute("curso");
    Turma t = (Turma) request.getAttribute("turma");
    ArrayList<Aluno> al = (ArrayList<Aluno>) request.getAttribute("alunos");
%>

<script type="text/javascript">

    function SelectElement(valueToSelect)
    {
        var element = document.getElementById('curso');
        element.value = valueToSelect;
    }

function confirmacao(id) {
     var resposta = confirm("Deseja remover esse registro?");
 
     if (resposta == true) {
          window.location.href = "emailsdeturma.ib?k=deletar&id=<%=t.getIdTur()%>&idd="+id;
     }
}
</script>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-6">
            <form class="form-horizontal" action="turma.ib?k=editar" method="post">
                <fieldset>

                    <!-- Form Name -->
                    <legend>Cadastrar Turma</legend>

                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="selectbasic">Período</label>
                        <div class="col-md-4">
                            <select id="selectbasic" name="selectbasic" class="form-control">
                                <option value="Matutino">Matutino</option>
                                <option value="Vespertino">Vespertino</option>
                                <option value="Noturno">Noturno</option>
                            </select>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label"  for="numero" >Número</label>  
                        <div class="col-md-4">
                            <input id="numero" name="numero" placeholder="Número da turma" value="<%=t.getNumero()%>" class="form-control input-md" required="" type="text">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="dataIn">Data de início</label>  
                        <div class="col-md-4">
                            <input id="dataIn" name="dataIn" placeholder="00/00/0000" value="<%=t.getDataInicio()%>" class="form-control input-md" required="" type="text">
                            <input id="idTur" name="idTur" type="hidden" value="<%=t.getIdTur()%>"/>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="datafim">Data de fim</label>  
                        <div class="col-md-4">
                            <input id="datafim" name="datafim" placeholder="00/00/0000" value="<%=t.getDataFim()%>" class="form-control input-md" required="" type="text">

                        </div>
                    </div>

                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="curso">Selecione o Curso</label>
                        <div class="col-md-4">
                            <select id="curso" name="curso" class="form-control" itemid="">
                                <% for (Cursos cs : c) {
                                        if (t.getIdCurso() != cs.getIdCurso()) {
                                %>
                                <option value="<%=cs.getIdCurso()%>"><%=cs.getNome()%></option>
                                <% } else {%>
                                <option selected="selected" value="<%=cs.getIdCurso()%>"><%=cs.getNome()%></option>
                                <script>
                                    SelectElement(<%=cs.getIdCurso()%>);
                                </script>
                                <% }
                                    }%>
                            </select>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="salvar"></label>
                        <div class="col-md-4">
                            <button id="salvar" name="salvar" class="btn btn-success">Salvar Turma</button>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
        <div class="col-md-6">
            <!--inicio tabela de adição-->


            <form action="turma.ib?k=adicionarAluno&id=<%=t.getIdTur()%>" method="post" >
                <table class="table table-bordered table-striped table-responsive">
                    <thead>
                        <tr>
                            <th>
                                Nome
                            </th>
                            <th>
                                Email
                            </th>
                            <th>

                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input id="nome" name="nome" type="text">
                            </td>
                            <td>
                                <input id="email" name="email" type="text">
                            </td>
                            <td>
                                <button id="salvar" name="salvar" class="btn btn-warning">Adicionar</button>
                            </td>
                        </tr>
                    </tbody>
                </table>

            </form>
            <!--fim tabela de adição-->


            <!--inicio listagem dos emails da turma-->
            <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>


            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="panel-title">Emails Cadastrados na turma número:<%=t.getNumero()%> </h3>
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
                                <th>Nome</th>
                                <th>Email</th>
                            </tr> 
                        </thead>
                        <tbody>
                            <% for (Aluno ll : al) {%>
                            <tr>
                                <td align="center">
                                    <!--<a data-toggle="modal" class="btn btn-info" data-show="true" href="emailsdeturma.ib?id=<%=ll.getId()%>&nome=<%=ll.getNome()%>&email=<%=ll.getEmail()%>"  data-target="#myModal"><em class="fa fa-pencil"></em></a>-->
                                    <a class="btn btn-danger" onclick="confirmacao(<%=ll.getId()%>)"><em class="fa fa-trash"></em></a>
                                </td>
                                <td><%=ll.getNome()%></td>
                                <td><%=ll.getEmail()%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>

                </div>

            </div>



            <!--fim da listagem dos emails da turma-->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.0-rc2/js/bootstrap.min.js"></script>