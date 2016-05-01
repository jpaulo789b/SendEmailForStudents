
<%@page import="com.fatecib.projetoemail.classes.Turma"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Turma> tur = (ArrayList<Turma>) request.getAttribute("turmas");
%>


<script src='tpl/tinymce/js/tinymce/tinymce.min.js'></script>.
<script>
    tinymce.init({
        selector: '#corpoEmail'
    });
    function myFunction() {
        delet = "<a  onclick='deleteRow(this)'>Deletar</a>";

        var x = document.getElementById("turma").selectedIndex;
        var y = document.getElementById("turma").options;
        var z = document.getElementById("turma").value;
//y[x].index
        var inputhidden = "<input type='hidden' id='idt' name='idt'  value=" + z + " >"
        var table = document.getElementById("myTable");
        var row = table.insertRow(1);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        cell1.innerHTML = inputhidden + "Email para:" + y[x].text;
        cell2.innerHTML = delet;
    }
    function deleteRow(r) {
        var i = r.parentNode.parentNode.rowIndex;
        document.getElementById("myTable").deleteRow(i);
    }
</script>
<style type="text/css">
    textarea {
        height: 350px;
    }

</style>
<form action="email.ib?k=enviar" method="post" >
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-12">
                        <input id="titulo" name="titulo" placeholder="Titulo do email" class="form-control input-md" type="text">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <textarea id="corpoEmail" name="corpoEmail" >
                        </textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <span class="btn btn-default btn-block">
                            Anexo <input type="file" name="file" id="file">
                        </span>

                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-12">
                        <!--Seleção de turma e curso-->
                        <table class="table table-striped" id="myTable">
                            <thead>
                                <tr>
                                    <th>
                                        Selecione a turma
                                    </th>
                                    <th>
                                        <select id="turma" name="turma" class="form-control">
                                            <% for (Turma t : tur) {%>
                                            <option value="<%=t.getIdTur()%>"><%=t.getNomeCurso()%> Nº <%=t.getNumero()%> | <%=t.getPerido()%></option>
                                            <% }%>
                                        </select>
                                    </th>
                                    <th>
                                        <a onclick="myFunction()"><span class="glyphicon glyphicon-plus-sign">Adicionar</span></a>
                                    </th>

                                </tr>
                            </thead>
                            ---------------
                            <tr>
                                <td>
                                </td>
                            </tr>
                            <tbody>
                            </tbody>
                        </table>
                        <!--selecao de turma e curso-->
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <button  type="submit" class="btn btn-block btn-primary" id="salvar" >
                            Enviar
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>  
</form>
