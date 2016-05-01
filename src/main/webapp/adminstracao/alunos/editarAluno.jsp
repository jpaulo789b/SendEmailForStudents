<%@page import="com.fatecib.projetoemail.classes.Aluno"%>
<% 
    Aluno al = new Aluno();
    al.setId(Integer.parseInt(request.getParameter("id")));
    al.setNome(request.getParameter("nome"));
    al.setEmail(request.getParameter("email"));
    String url = request.getParameter("url");
%>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
            $(function() {
                $("#myform").on("submit", function(e) {
                    e.preventDefault();
                    $.ajax({
                        url: $(this).attr("action"),
                        type: 'POST',
                        data: $(this).serialize(),
                        beforeSend: function() {
                            $("#message").html("Editando...");
                        },
                        success: function(data) {
                            $("#message").hide();
                            $("#response").html(data);
                        }
                    });
                });
            });
</script>
  <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <h4 class="modal-title">Modal title</h4>
            </div>			<!-- /modal-header -->
            <div class="modal-body">

                <form id="myForm" name="myForm"  action="emailsdeturma.ib?k=editar" >
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
                                <input id="nome" name="nome" value="<%=al.getNome()%>"type="text">
                                <input id="id" name="id" type="hidden" value="<%=al.getId()%>">
                            </td>
                            <td>
                                <input id="email" name="email" value="<%=al.getEmail()%>" type="text">
                            </td>
                            <td>
                                <button id="salvar" name="editarAlu" class="btn btn-warning">Salvar</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
     </div>			<!-- /modal-body -->
            <div class="modal-footer">

            </div>			<!-- /modal-footer -->
        </div>         <!-- /modal-content -->
    </div>     <!-- /modal-dialog -->
