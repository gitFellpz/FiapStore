package br.com.fiap.teste;

import br.com.fiap.util.CriptografiaUtils;

public class CriptografiaTeste {

	public static void main(String[] args) {
		try {
			System.out.println(CriptografiaUtils.criptografar("123456"));
			System.out.println(CriptografiaUtils.criptografar("123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
