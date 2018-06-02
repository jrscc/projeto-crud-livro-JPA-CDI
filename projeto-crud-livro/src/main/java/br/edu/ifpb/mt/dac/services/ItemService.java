package br.edu.ifpb.mt.dac.services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import br.edu.ifpb.mt.dac.dao.ItemDAO;
import br.edu.ifpb.mt.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.entities.Item;
import br.edu.ifpb.mt.dac.filters.ItemFilter;
import br.edu.ifpb.mt.dac.util.TransacionalCdi;

@ApplicationScoped
public class ItemService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	@Inject
	private ItemDAO itemDAO;
	
	@TransacionalCdi
	public void save(Item item) throws ServiceDacException {
		try {
			// Verificar se login já existe
			itemDAO.save(item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
	@TransacionalCdi
	public void update(Item item) throws ServiceDacException {
		
		try {
			// Verificar se login já existe
			itemDAO.update(item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
	@TransacionalCdi
	public void delete(Item item) throws ServiceDacException {
		try {
			itemDAO.delete(item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public Item getByID(int userId) throws ServiceDacException {
		try {
			return itemDAO.getByID(userId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Item> getAll() throws ServiceDacException {
		try {
			return itemDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public List<Item> findBy(ItemFilter filter) throws ServiceDacException {
		try {
			return itemDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public String calcularHash(String password) throws ServiceDacException {
		return hash(password);
	}

	/**
	 * Método que calcula o hash de uma dada senha usando o algoritmo SHA-256.
	 * 
	 * @param password
	 *            senha a ser calculada o hash
	 * @return hash da senha
	 * @throws ServiceDacException
	 *             lançada caso ocorra algum erro durante o processo
	 */
	private static String hash(String password) throws ServiceDacException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new ServiceDacException("Could not calculate hash!", e);
		}
	}

}
