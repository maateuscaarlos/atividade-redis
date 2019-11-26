package br.edu.ifpb.banco.DAO;

import br.edu.ifpb.banco.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDAOBD implements ProdutoDAO {


    public Connection conectar() throws ClassNotFoundException  {
        Class.forName("org.postgresql.Driver");
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Carrinho", "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProdutoDAOBD() {

    }

    @Override
    public void CadastrarProduto(Produto produto) throws Exception {
        Connection conn = new ProdutoDAOBD().conectar();
        String sql = "INSERT INTO Produto (id, nome, preco)" + "VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, produto.getId());
        statement.setString(2, produto.getNome());
        statement.setFloat(3, produto.getPreco());


        statement.executeUpdate();

        conn.close();


    }

    @Override
    public void removerProduto(Long id) throws Exception {
        Connection conn = new ProdutoDAOBD().conectar();
        String sql = "DELETE FROM Produto WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.executeUpdate();
        conn.close();


    }

    @Override
    public List<Produto> listarProdutos() throws Exception {
        Connection conn = new ProdutoDAOBD().conectar();
        String sql = "SELECT * FROM Produto";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        List<Produto> produtos = new ArrayList<>();
        while (result.next()) {
            Produto produto = new Produto();
            produto.setId(result.getLong("id"));
            produto.setNome(result.getString("nome"));
            produto.setPreco(result.getFloat("preco"));

            produtos.add(produto);
        }
        conn.close();

        return produtos;
    }

    @Override
    public Optional<Produto> recuperaPorId(Long id) throws Exception {
        Connection conn = new ProdutoDAOBD().conectar();
        String sql = "SELECT * FROM PRODUTO WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet result = statement.executeQuery();
        Produto produto = null;
        while (result.next()) {
            produto = new Produto();
            produto.setId(result.getLong("id"));
            produto.setNome(result.getString("nome"));
            produto.setPreco(result.getFloat("preco"));


        }
        conn.close();

        return Optional.ofNullable(produto);
    }
}
