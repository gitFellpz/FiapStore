package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.exception.DBException;
import br.com.fiap.model.Produto;

public interface ProdutoDAO {

	void cadastrar(Produto produto) throws DBException;
	void atualizar(Produto produto) throws DBException;
	void remover(int codigo) throws DBException;
	Produto buscar(int id);
	List<Produto> listar();

}
