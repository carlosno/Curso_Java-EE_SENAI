<%@page import="Salao.Servicos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form action="getCadastrarServicos.jsp" method="get">

Tipo:<input type="text" name="tipo" >
<Br/>

Descrição:<input type="text" name="descricao"  >
<Br/>

Valor:<input type="text" name="valor" >
<Br/>


<input type="submit" value="cadastrar">
</form>
	
	<br/>
	
	<form action="servicos.jsp" method="get">

	Buscar Tipos:<input type="text" name="tipobusca" >
	<Br/>
	
	<input type="submit" value="buscar">
	</form>


	<form action="updateServicos.jsp" method="get">
	Codigo:<input type="text" name="codigo" >
<Br/>

	Valor:<input type="text" name="valor" >
<Br/>

	Descrição:<input type="text" name="descricao"  >
<Br/>
<input type="submit" value="editar">
</form>
	
	

	<%
	String tipobusca = request.getParameter("tipobusca");	
	Salao.Servicos f1 = new Salao.Servicos();
	out.print(f1.buscaServicoTipo(tipobusca));
	%>

</body>
</html>