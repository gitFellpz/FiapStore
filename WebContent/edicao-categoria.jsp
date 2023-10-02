<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualização de Categoria</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<h1>Edição de Categoria</h1>
		
		<form action="categoria" method="post">
		
			<input type="hidden" value="editar" name="acao"> <input
				type="hidden" value="${categoria.codigo }" name="codigo">
				
			<div class="form-group">
				<label for="id-nome">Nome</label> <input type="text" name="nome"
					id="id-nome" class="form-control" value="${categoria.nome }">
			</div>
			
			<br>
			
			<input type="submit" value="Salvar" class="btn btn-primary">
			
			<a href="categoria?acao=listar" class="btn btn-danger">Cancelar</a>
			
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
