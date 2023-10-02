package br.com.fiap.teste;

import java.util.List;

import br.com.fiap.dao.CategoriaDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Categoria;

public class CategoriaDAOTeste {

	public static void main(String[] args) {
		CategoriaDAO dao = DAOFactory.getCategoriaDAO();

		List<Categoria> lista = dao.listar();
		for (Categoria categoria : lista) {
			System.out.println(categoria.getCodigo() + " " + categoria.getNome());
		}
	}

}
