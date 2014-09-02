<%@page import="Salao.Comanda"%>
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


<%!
	int codigo_funcionario = 0;
	int codigo_servico = 0;
	String numero_celular = "";
	String data="";
	String hora="";
%>
	
	
		
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
	
	<form action="cadastrarComanda.jsp" method="get">
		
		Data: <input type="text" id ="data" name="data" value='<%= request.getParameter("data") %>'>
		<br/>
		
		Horario: <input type="text" name="horario" value='<%= request.getParameter("horario") %>'>
		<br/>
	
		<%
			Salao.Funcionario s1 = new Salao.Funcionario();
	
			out.print(s1.buscafuncionario2());
		%>
	
		<input type="submit" value="Buscar serviços" name="ok" />
		
		<%
			Salao.Funcionario s = new Salao.Funcionario();
			if(request.getParameter("ok") != null){
				codigo_funcionario = Integer.parseInt(request.getParameter("funcionario"));
				
				out.print("<br />");
				out.print(s.buscaServicofuncionario(Integer.parseInt(request.getParameter("funcionario"))));
				out.print("<input type='submit' value='Inserir' name='Inserir'>");
			}
			
			if(request.getParameter("Inserir") != null){
				codigo_servico = Integer.parseInt(request.getParameter("servicos1"));
				
				out.print("<br />");
				out.print(s.buscaServicofuncionario(Integer.parseInt(request.getParameter("funcionario"))));
				out.print("<input type='submit' value='Inserir'>");
				out.print("<br />");
				
				Salao.Clientes c = new Salao.Clientes();
				out.print(c.listaClientes()); 
				
				out.print("<input type='submit' value='Cadastrar' name='Cadastrar'>");
				String data = request.getParameter("data");
				String hora = request.getParameter("horario");
			}
			
			if(request.getParameter("Cadastrar") != null){
				numero_celular = request.getParameter("cliente");
				
				Salao.Comanda c1 = new Salao.Comanda(request.getParameter("data"),request.getParameter("horario"));
				if(c1.cadastraComanda(c1, codigo_servico, codigo_funcionario, Integer.parseInt(numero_celular)) == true){
					out.print("<script>alert('Comanda Cadastrada!')</script>");
				}
			}
		%>
		
		</form>
	</div>
  <div class="caixas1 " >
  </div>
</div>

</body>
</html>