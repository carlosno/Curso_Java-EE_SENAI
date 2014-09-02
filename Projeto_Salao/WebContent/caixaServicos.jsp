<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css.css" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
<SCRIPT type="text/javascript" src="js/jquery-1.10.2.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/jquery-ui.js"></SCRIPT>
 <script>
  $(function() {
    $( "#data" ).datepicker();
  });
  </script>
    <script>
  $(function() {
    $( "#menus" ).menu();
  });
  </script>
<title>Insert title here</title>
</head>
<body>
<div id="centro">
      <div id="cabeçalho"></div>

        <div id="menu">
        <ul id="menus">
  			<li class="ui-state-disabled">Menu</li>
  			<li><a href="http://localhost:8082/Projeto_Salao/cadastrarComanda.jsp">Cadastrar Comanda</a></li>
  			<li><a href="http://localhost:8082/Projeto_Salao/cadastroFuncionario.jsp">Cadastrar Funcionario</a></li>
  			<li><a href="http://localhost:8082/Projeto_Salao/manterClientes.jsp">Gerenciar Clientes</a></li>
  			<li><a href="http://localhost:8082/Projeto_Salao/caixaServicos.jsp">Caixa</a></li>
  		</ul>			
        </div>
        <div id="menu2"></div>
      <div class="caixas ">
<%! 
Salao.Comanda c = new Salao.Comanda();
%>

	<form action="caixaServicos.jsp" method="get">
		Buscar Comanda Data: <input type="text" id="data" name="buscadata" >
		<br/>
		<% 
		if(request.getParameter("listarComanda")!=null){
			
			
			Salao.Comanda c = new Salao.Comanda(request.getParameter("buscadata"));
			
			out.print(c.listaComanda(c));
		
			out.print("<input type='submit' value='comanda' name='comanda' >");
			out.print("<br/>");
		}
		
		
		%>
			
		
		<input type="submit" name="listarComanda" value="Buscar">
		
	</form>


</div>
<div class="caixas1 " >
  </div>
		</div>



</body>
</html>