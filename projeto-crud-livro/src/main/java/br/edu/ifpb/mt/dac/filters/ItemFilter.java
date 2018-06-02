package br.edu.ifpb.mt.dac.filters;

import java.util.Date;

import br.edu.ifpb.mt.dac.entities.Group;

public class ItemFilter {

	private String isbn;
	
	private String titulo;

	private String editora;

	private String paginaRangeBegin;
	
	private String paginaRangeEnd;
	
	private Date dataDePublicacaoRangeBegin;

	private Date dataDePublicacaoRangeEnd;
	
	private Group group;
	
	public String getPaginaRangeBegin() {
		return paginaRangeBegin;
	}

	public void setPaginaRangeBegin(String paginaRangeBegin) {
		this.paginaRangeBegin = paginaRangeBegin;
	}

	public String getPaginaRangeEnd() {
		return paginaRangeEnd;
	}

	public void setPaginaRangeEnd(String paginaRangeEnd) {
		this.paginaRangeEnd = paginaRangeEnd;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public ItemFilter() {

	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public Date getDataDePublicacaoRangeBegin() {
		return dataDePublicacaoRangeBegin;
	}

	public void setDataDePublicacaoRangeBegin(Date dataDePublicacaoRangeBegin) {
		this.dataDePublicacaoRangeBegin = dataDePublicacaoRangeBegin;
	}

	public Date getDataDePublicacaoRangeEnd() {
		return dataDePublicacaoRangeEnd;
	}

	public void setDataDePublicacaoRangeEnd(Date dataDePublicacaoRangeEnd) {
		this.dataDePublicacaoRangeEnd = dataDePublicacaoRangeEnd;
	}

	public Group getGroup() {
		return group;
	}

	
	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isEmpty() {
		if (this.isbn != null && !this.isbn.trim().isEmpty()) {
			return false;
		}
		if (this.paginaRangeBegin != null && !this.paginaRangeBegin.trim().isEmpty()) {
			return false;
		}
		if (this.paginaRangeEnd != null && !this.paginaRangeEnd.trim().isEmpty()) {
			return false;
		}
		if (this.titulo != null && !this.titulo.trim().isEmpty()) {
			return false;
		}
		if (this.editora != null && !this.editora.trim().isEmpty()) {
			return false;
		}
		if (this.dataDePublicacaoRangeBegin != null) {
			return false;
		}
		if (this.dataDePublicacaoRangeEnd != null) {
			return false;
		}
		if (this.group != null) {
			return false;
		}
		
		return true;
	}


}
