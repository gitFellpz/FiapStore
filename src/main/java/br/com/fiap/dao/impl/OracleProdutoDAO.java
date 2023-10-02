package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.ProdutoDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Produto;

public class OracleProdutoDAO implements ProdutoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Produto produto) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_PRODUTOS (NOME_PRODUTO, QUANTIDADE, VALOR, DT_FABRICACAO, ID_CATEGORIA) VALUES (?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getQuantidade());
			stmt.setDouble(3, produto.getValor());
			java.sql.Date data = new java.sql.Date(produto.getDataFabricacao().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, produto.getCategoria().getCodigo());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Produto produto) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_PRODUTOS SET NOME_PRODUTO = ?, QUANTIDADE = ?, VALOR = ?, DT_FABRICACAO = ?, ID_CATEGORIA = ? WHERE ID_PRODUTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getQuantidade());
			stmt.setDouble(3, produto.getValor());
			java.sql.Date data = new java.sql.Date(produto.getDataFabricacao().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, produto.getCategoria().getCodigo());
			stmt.setInt(6, produto.getCodigo());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM TB_PRODUTOS WHERE ID_PRODUTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Produto buscar(int id) {
		Produto produto = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM TB_PRODUTOS INNER JOIN TB_CATEGORIAS ON TB_PRODUTOS.ID_CATEGORIA = TB_CATEGORIAS.ID_CATEGORIA WHERE TB_PRODUTOS.ID_PRODUTO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int codigo = rs.getInt("ID_PRODUTO");
				String nome = rs.getString("NOME_PRODUTO");
				int qtd = rs.getInt("QUANTIDADE");
				double valor = rs.getDouble("VALOR");
				java.sql.Date data = rs.getDate("DT_FABRICACAO");
				Calendar dataFabricacao = Calendar.getInstance();
				dataFabricacao.setTimeInMillis(data.getTime());
				int codigoCategoria = rs.getInt("ID_CATEGORIA");
				String nomeCategoria = rs.getString("NOME_CATEGORIA");

				produto = new Produto(nome, valor, dataFabricacao, qtd);
				Categoria categoria = new Categoria(codigoCategoria, nomeCategoria);
				produto.setCategoria(categoria);
				produto.setCodigo(codigo);
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
		return produto;
	}

	@Override
	public List<Produto> listar() {
		List<Produto> lista = new ArrayList<Produto>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM TB_PRODUTOS INNER JOIN TB_CATEGORIAS ON TB_PRODUTOS.ID_CATEGORIA = TB_CATEGORIAS.ID_CATEGORIA");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("ID_PRODUTO");
				String nome = rs.getString("NOME_PRODUTO");
				int qtd = rs.getInt("QUANTIDADE");
				double valor = rs.getDouble("VALOR");
				java.sql.Date data = rs.getDate("DT_FABRICACAO");
				Calendar dataFabricacao = Calendar.getInstance();
				dataFabricacao.setTimeInMillis(data.getTime());
				int codigoCategoria = rs.getInt("ID_CATEGORIA");
				String nomeCategoria = rs.getString("NOME_CATEGORIA");

				Produto produto = new Produto(nome, valor, dataFabricacao, qtd);
				Categoria categoria = new Categoria(codigoCategoria, nomeCategoria);
				produto.setCategoria(categoria);
				produto.setCodigo(codigo);
				lista.add(produto);
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
