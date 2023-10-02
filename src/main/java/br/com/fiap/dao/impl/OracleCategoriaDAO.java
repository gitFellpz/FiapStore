package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.CategoriaDAO;
import br.com.fiap.model.Categoria;

public class OracleCategoriaDAO implements CategoriaDAO {

	private Connection conexao;

	@Override
	public List<Categoria> listar() {
		List<Categoria> lista = new ArrayList<Categoria>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_CATEGORIAS");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo = rs.getInt("ID_CATEGORIA");
				String nome = rs.getString("NOME_CATEGORIA");
				Categoria categoria = new Categoria(codigo, nome);
				lista.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
}
