package br.com.fiap.factory;

import br.com.fiap.dao.CategoriaDAO;
import br.com.fiap.dao.ProdutoDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.dao.impl.OracleCategoriaDAO;
import br.com.fiap.dao.impl.OracleProdutoDAO;
import br.com.fiap.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

	public static ProdutoDAO getProdutoDAO() {
		return new OracleProdutoDAO();
	}

	public static CategoriaDAO getCategoriaDAO() {
		return new OracleCategoriaDAO();
	}

	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}

}
