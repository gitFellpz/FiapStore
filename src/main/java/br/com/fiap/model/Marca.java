package br.com.fiap.model;

public class Marca {
	
	private int codigo;
	private String nome;
	
	
	public Marca() {
		
	}


	public Marca(int codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
