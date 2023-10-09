<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<%@ include file="header.jsp"%>

<link rel="stylesheet" href="./resources/css/estilo.css">

</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<br><br>
		<h1>Bem Vindo ao 5MTStore</h1>
		
		<article class="box-itens">
		
			<c:forEach items="${produtos}" var="p">
				<div class="box-item">
					<img class="imagem-produto">
					<p>${p.nome}</p>
					<p>${p.valor}</p>
				</div>
			</c:forEach>
		
		</article>
		
		
		
	</div>
</body>
</html>