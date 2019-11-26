package br.edu.ifpb.banco.controller;

import br.edu.ifpb.banco.DAO.DaoFactory;
import br.edu.ifpb.banco.DAO.ProdutoDAO;
import br.edu.ifpb.banco.DAO.ProdutoDAOBD;
import br.edu.ifpb.banco.model.Produto;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adicionarCarrinho")
public class AdicionarCarrinhoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        Produto produto = null;
        try {
            ProdutoDAO produtoDAO = DaoFactory.criarProdutoDAO();
            produto = produtoDAO.recuperaPorId(id).orElseThrow(() -> new ServletException());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Produto> produtosCarrinho = (List<Produto>) req.getSession().getAttribute("produtosCarrinho");
        if (produtosCarrinho == null) {
            produtosCarrinho = new ArrayList<>();
        }
        produtosCarrinho.add(produto);

        Jedis jedis = new Jedis();
        Gson gson = new Gson();
        jedis.set(req.getSession().getId() ,gson.toJson(produtosCarrinho),
                SetParams.setParams().ex(7200));

        req.getSession().setAttribute("produtosCarrinho", produtosCarrinho);
        resp.sendRedirect("carrinho.jsp");
    }
}
