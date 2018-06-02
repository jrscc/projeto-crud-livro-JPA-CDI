package br.edu.ifpb.mt.dac.entities;


public enum Group {
	
	Livro ("Livro"), 
	Livro_de_bolso ("Livro de bolso"), 
	Capa_dura ("Capa dura"), 
	Ebook ("Ebook"), 
	Revista ("Revista"), 
	Periodico("Periodico");
	
	private String nome;
	
	private Group(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
