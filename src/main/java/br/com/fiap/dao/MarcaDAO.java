package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.exception.DBException;
import br.com.fiap.model.Marca;

public interface MarcaDAO {

	void cadastrar(Marca marca) throws DBException;

	void atualizar(Marca marca) throws DBException;

	void remover(int codigo) throws DBException;

	Marca buscar(int id);
	
	List<Marca> listar();

}
