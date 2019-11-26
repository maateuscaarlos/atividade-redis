package br.edu.ifpb.banco.controller;

import br.edu.ifpb.banco.DAO.DaoFactory;
import br.edu.ifpb.banco.DAO.ProdutoDAO;
import br.edu.ifpb.banco.DAO.ProdutoDAOBD;
import br.edu.ifpb.banco.model.Produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/produtos")

public class ProdutoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        List<Produto> produtos = new ArrayList<>();
        try {
             ProdutoDAO produtosDAO = produtosDAO = DaoFactory.criarProdutoDAO();
             produtos = produtosDAO.listarProdutos();
        } catch (Exception e) {
            e.printStackTrace();
        }




        req.setAttribute("produtos", produtos);
        req.getRequestDispatcher("produtos.jsp").forward(req, resp);
    }
}
