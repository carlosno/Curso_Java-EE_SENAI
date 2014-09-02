<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
      <div class="caixas " >
      <h2>Gerenciar Clientes</h2>



<%!
		//Declaração de variável global
		Salao.Clientes c = new Salao.Clientes();
		DateFormat df;
	%>


<%
		//Testando qual botão foi clicado
		if(request.getParameter("cadastrar") != null){
			String numerocel = request.getParameter("numerocel");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String nascimento = request.getParameter("nascimento");
			
			
			c = new Salao.Clientes(numerocel, nome, email, nascimento);
			if(c.cadastraCliente(c) == true){
				out.print("<script>alert('Cliente cadastrado!')</script>");
			}
		}
	
		if(request.getParameter("buscar") != null){
			String numerocel = request.getParameter("numerocel");
			
			c = c.buscaCliente(numerocel);
		}
		
		if(request.getParameter("remover") != null){
			String numerocel = request.getParameter("numerocel");
			
			if(c.removeCliente(numerocel) == true){
				out.print("<script>alert('Cliente removido!')</script>");
			}
		}
		
		if(request.getParameter("atualizar") != null){
			String numerocel = request.getParameter("numerocel");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String nascimento = request.getParameter("nascimento");
		
			
			c = new Salao.Clientes(numerocel, nome, email,nascimento);
			if(c.atualizaCliente(numerocel, c) == true){
				out.print("<script>alert('Cliente atualizado!')</script>");
			}
		}
	
	
	
	%>



	<form action="manterClientes.jsp" method="get">
		Numero Celular: <input type="text" name="numerocel" value="<%=c.getnumerocel() %>">
		<br/>
		Nome: <input type="text" name="nome" value="<%=c.getnome() %>">
		<br/>
		Email: <input type="text" name="email" value="<%=c.getemail() %>">
		<br/>
		Data Nascimento: <input type="text" id="data" name="nascimento" value="<%= c.getdata()%>">
		<br/>
		
		
		<input type="submit" name="cadastrar" value="Cadastrar">
		<input type="submit" name="buscar" value="Buscar">
		<input type="submit" name="atualizar" value="Atualizar">
		<input type="submit" name="remover" value="Remover">
	</form>

</div>
  <div class="caixas1 " >
  </div>
</div>



</body>
</html>