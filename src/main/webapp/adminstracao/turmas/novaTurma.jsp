<%@page import="java.util.ArrayList"%>
<%@page import="com.fatecib.projetoemail.classes.Cursos"%>
<%
    ArrayList<Cursos> c = (ArrayList<Cursos>) request.getAttribute("curso");
%>
<form class="form-horizontal" action="turma.ib?k=cadastrar" method="post">
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
            <label class="col-md-4 control-label" for="numero">Número</label>  
            <div class="col-md-4">
                <input id="numero" name="numero" placeholder="Número da turma" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="dataIn">Data de início</label>  
            <div class="col-md-4">
                <input id="dataIn" name="dataIn" placeholder="00/00/0000" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="datafim">Data de fim</label>  
            <div class="col-md-4">
                <input id="datafim" name="datafim" placeholder="00/00/0000" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="curso">Selecione o Curso</label>
            <div class="col-md-4">
                <select id="curso" name="curso" class="form-control">
                    <% for(Cursos cs: c) {%>
                    <option value="<%=cs.getIdCurso()%>"><%=cs.getNome()%></option>
                    <%}%>
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
