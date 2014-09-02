<%@page import="Salao.Funcionario"%>
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
      <div class="caixas" align="center">
	<%
	String nome = request.getParameter("nomebusca");	
	Salao.Funcionario f = new Salao.Funcionario();
	out.print(f.buscaFuncionario(nome));
	%>
	<% if(request.getParameter("cadastrarservico") != null){
			String codigofuncionario = request.getParameter("codigofuncionario");
			String codigoservico = request.getParameter("codigoservico");
									
			Salao.Funcionario f1 = new Salao.Funcionario();
			if(f1.cadastraServicoFuncionario(Integer.parseInt(codigofuncionario), Integer.parseInt(codigoservico)) == true)
					{
				out.print("<script>alert('Serviço Cadastrado!')</script>");
			}
		}
	if(request.getParameter("removerservico") != null){
		String codigofuncionario = request.getParameter("codigofuncionario");
		String codigoservico = request.getParameter("codigoservico");
								
		Salao.Funcionario f2 = new Salao.Funcionario();
		if(f2.removeServicoFuncionario(Integer.parseInt(codigofuncionario), Integer.parseInt(codigofuncionario)) == true)
				{
			out.print("<script>alert('Serviço Removido!')</script>");
		}
	}
	%>
	
	<h2>Cadastrar Funcionarios</h2>
	<form action="getCadastraFuncionario.jsp" method="get">

Nome:<input type="text" name="nome" >
<Br/>

Telefone:<input type="text" name="telefone"  >
<Br/>

Comissâo:<input type="text" name="comissao" >
<Br/>


<input type="submit" value="cadastrar">
</form>
	
	<br/>
	

	<form action="cadastroFuncionario.jsp" method="get">

	Nome:<input type="text" name="nomebusca" >
	<Br/>



	<input type="submit" value="buscar">
	</form>
	
	
	
		<form action="updateFuncionario.jsp" method="get">
	Matricula:<input type="text" name="matricula" >
<Br/>

	Nome:<input type="text" name="nome" >
<Br/>

	Telefone:<input type="text" name="telefone"  >
<Br/>

	Comissâo:<input type="text" name="comissao" >
<Br/>


<input type="submit" value="editar">
</form>
	
	</div>
	
	<div class="caixas1" align="center">
	<h2>Cadastrar Servicos</h2>
<form action="cadastroFuncionario.jsp" method="get">
		Codigo Funcionario: <input type="text" name="codigofuncionario" value="">
		<br/>
		Codigo Serviço: <input type="text" name="codigoservico" value="">
		<br/>
				
		
		<input type="submit" name="cadastrarservico" value="Cadastrar">
		<input type="submit" name="removerservico" value="Remover">
	</form>
	
</div>
		</div>

<br/>

</body>
</html>