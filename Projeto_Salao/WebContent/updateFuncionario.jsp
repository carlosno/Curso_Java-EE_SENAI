<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String matricula = request.getParameter("matricula");
	String nome = request.getParameter("nome");
	String telefone = request.getParameter("telefone");
	String comissao = request.getParameter("comissao");
	Salao.Funcionario f2 = new Salao.Funcionario(nome,telefone,comissao);
	f2.editarFuncionario(Integer.parseInt(matricula));
	%>
</body>
</html>