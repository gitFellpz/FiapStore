package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.exception.DBException;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Produto;

public interface CategoriaDAO {

	List<Categoria> listar();
	void cadastrar(Categoria categoria) throws DBException;
	void atualizar(Categoria categoria) throws DBException;
	void remover(int codigo) throws DBException;
	Categoria buscar(int id);

}
