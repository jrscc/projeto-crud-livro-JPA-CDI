package br.edu.ifpb.mt.dac.dao;

import br.edu.ifpb.mt.dac.DacException;

public class PersistenciaDacException extends DacException {

	private static final long serialVersionUID = 1780243892137781599L;

	public PersistenciaDacException(String mensagem) {
		super(mensagem);
	}

	public PersistenciaDacException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
