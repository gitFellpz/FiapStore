package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.exception.DBException;
import br.com.fiap.model.Categoria;

public interface CategoriaDAO {

	List<Categoria> listar();
	void cadastrar(Categoria categoria) throws DBException;

}
