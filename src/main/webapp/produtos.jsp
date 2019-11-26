<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Produtos</title>
</head>
<body>
<h1>Lista de produtos</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Preço</th>
        <th>Ação</th>
    </tr>
    <c:forEach items="${produtos}" var="produto">
        <tr>
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.preco}</td>
            <td>
                <form method="post" action="adicionarCarrinho?id=${produto.id}">
                    <button>Adicionar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
