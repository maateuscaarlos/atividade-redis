package br.edu.ifpb.banco.DAO;

import br.edu.ifpb.banco.model.Produto;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    private static String TIPO_DAO = "BANCO";



    public static ProdutoDAO criarProdutoDAO() throws SQLException {
        ProdutoDAO userDAO = null;
        switch (TIPO_DAO) {
            case "BANCO":
                userDAO = new ProdutoDAOBD();
                break;
        }
        return userDAO;
    }

}
