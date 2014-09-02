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
		String descricao = request.getParameter("descricao");
		String valor = request.getParameter("valor");
		String tipo = request.getParameter("tipo");
		Salao.Servicos f = new Salao.Servicos(descricao, valor, tipo);
		f.cadastrarServico();
	%>
</body>
</html>