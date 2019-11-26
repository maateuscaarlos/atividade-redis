package br.edu.ifpb.banco.DAO;


import br.edu.ifpb.banco.model.Produto;

import java.util.List;
import java.util.Optional;


public interface ProdutoDAO {
    public void CadastrarProduto(Produto produto) throws Exception;

    public void removerProduto(Long id) throws Exception;

    public List<Produto> listarProdutos() throws Exception;


    public Optional<Produto> recuperaPorId(Long id) throws Exception ;

}
