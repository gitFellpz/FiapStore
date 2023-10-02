package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.CategoriaDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Produto;

public class OracleCategoriaDAO implements CategoriaDAO {

	private Connection conexao;
	
	@Override
	public void cadastrar(Categoria categoria) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_CATEGORIAS (NOME_CATEGORIA) VALUES (?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());
			stmt.execute();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		} 
		finally {
			try {
				stmt.close();
				conexao.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Categoria categoria) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_CATEGORIAS SET NOME_CATEGORIA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());

			stmt.execute();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} 
		finally {
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
			String sql = "DELETE FROM TB_CATEGORIAS WHERE ID_CATEGORIA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.execute();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		} 
		finally {
			try {
				stmt.close();
				conexao.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Categoria buscar(int id) {
		Categoria categoria = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM TB_CATEGORIAS WHERE ID_CATEGORIA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int codigo = rs.getInt("ID_CATEGORIA");
				String nome = rs.getString("NOME_CATGORIA");


				categoria = new Categoria(codigo, nome);
			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return categoria;
	}

//	@Override
//	public List<Categoria> listar() {
//		List<Categoria> lista = new ArrayList<Categoria>();
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		try {
//			conexao = ConnectionManager.getInstance().getConnection();
//			stmt = conexao.prepareStatement(
//					"SELECT * FROM TB_CATEGORIAS");
//			rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				int codigo = rs.getInt("ID_PRODUTO");
//				String nome = rs.getString("NOME_PRODUTO");
//
//				Categoria categoria = new Categoria(codigo, nome);
//				lista.add(categoria);
//			}
//		} 
//		catch (SQLException e) {
//			e.printStackTrace();
//		} 
//		finally {
//			try {
//				stmt.close();
//				rs.close();
//				conexao.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return lista;
//	}

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
