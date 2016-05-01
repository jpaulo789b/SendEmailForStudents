<%@page import="com.fatecib.projetoemail.classes.Cursos"%>
<%
    Cursos cs = (Cursos) request.getAttribute("curso");
%>
<form class="form-horizontal" action="cadastrarcurso.ib?k=editar" method="POST">
<fieldset>

<!-- Form Name -->
<legend>Adicionar um novo curso</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nome">Nome do curso::</label>  
  <div class="col-md-4">
      <input id="nome" name="nome" type="text" placeholder="Nome do curso" value="<%=cs.getNome()%>"class="form-control input-md">
  <input type="hidden" name="id" id="id" value="<%=cs.getIdCurso()%>">  
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="salvar"></label>
  <div class="col-md-4">
    <button id="salvar" name="salvar" class="btn btn-primary">Salvar</button>
  </div>
</div>

</fieldset>
</form>
