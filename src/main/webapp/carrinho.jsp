<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            background: #333333;
            color: #fff;
        }
        input {
            color: #fff;
            background: #888;
        }
    </style>
    <meta charset="UTF-8">
    <title>Lista de Produtos</title>
</head>
<body>
<h1>Carrinho de compras</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Preço</th>
        <th>Ação</th>
    </tr>
    <c:forEach items="${sessionScope.produtosCarrinho}" var="produto">
        <tr>
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.preco}</td>
            <td>
                <form method="post" action="removerCarrinho?id=${produto.id}">
                    <button type="submit">Remover</button>
                </form>

            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>