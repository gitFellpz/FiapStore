package br.com.fiap.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.dao.ProdutoDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Produto;

public class ProdutoDAOTeste {

	public static void main(String[] args) {

		ProdutoDAO dao = DAOFactory.getProdutoDAO();

		// Cadastrar um produto
		Produto produto = new Produto("TV Samsung", 5000, Calendar.getInstance(), 50);
		try {
			dao.cadastrar(produto);
			System.out.println("Produto cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		// Buscar um produto pelo código e atualizar
		produto = dao.buscar(1);
		produto.setNome("TV Samsung 65'");
		produto.setValor(5250);
		try {
			dao.atualizar(produto);
			System.out.println("Produto atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		// Listar os produtos
		List<Produto> lista = dao.listar();
		for (Produto item : lista) {
			System.out.println(item.getNome() + " " + item.getQuantidade() + " " + item.getValor());
		}

		// Remover um produto
		try {
			dao.remover(1);
			System.out.println("Produto removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

}
