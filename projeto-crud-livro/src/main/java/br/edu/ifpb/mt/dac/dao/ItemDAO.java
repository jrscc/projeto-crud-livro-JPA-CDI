package br.edu.ifpb.mt.dac.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.entities.Item;

import br.edu.ifpb.mt.dac.filters.ItemFilter;


public class ItemDAO extends DAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8654172279118395475L;

	public void save(Item item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o usuário.", pe);
		}
	}

	public Item update(Item item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Item resultado = item;
		try {
			resultado = em.merge(item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o usuário.", pe);
		}
		return resultado;
	}

	public void delete(Item item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			item = em.find(Item.class, item.getId());
			em.remove(item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o usuário.", pe);
		}
	}

	public Item getByID(int item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Item resultado = null;
		try {
			resultado = em.find(Item.class, item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o usuário com base no ID.", pe);
		}
		return resultado;
	}

	public List<Item> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

	public List<Item> findBy(ItemFilter filter) throws PersistenciaDacException {

		EntityManager em = getEntityManager();
		List<Item> resultado = new ArrayList<>();

		try {
			
			String jpql = "SELECT i FROM Item i WHERE 1 = 1 ";
			
			// isbn
			if (notEmpty(filter.getIsbn())) {
				jpql += "AND i.isbn LIKE :isbn ";
			}
						
			// titulo
			if (notEmpty(filter.getTitulo())) {
				jpql += "AND i.titulo LIKE :titulo ";
			}

			// editora
			if (notEmpty(filter.getEditora())) {
				jpql += "AND i.editora LIKE :editora ";
			}

			// data begin
			if (notEmpty(filter.getDataDePublicacaoRangeBegin())) {
				jpql += "AND i.dataDePublicacao >= :dataDePublicacaoRangeBegin ";
			}

			// data end
			if (notEmpty(filter.getDataDePublicacaoRangeEnd())) {
				jpql += "AND i.dataDePublicacao <= :dataDePublicacaoRangeEnd ";
			}

			// Group
			if (notEmpty(filter.getGroup())) {
				jpql += "AND i.group = :group ";
			}
			// paginas begin
			if (notEmpty(filter.getPaginaRangeBegin())) {
				jpql += "AND i.paginas >= cast(:paginaRangeBegin as integer) ";
			}
			// paginas end
			if (notEmpty(filter.getPaginaRangeEnd())) {
				jpql += "AND i.paginas <= cast(:paginaRangeEnd as integer) ";
			}

			TypedQuery<Item> query = em.createQuery(jpql, Item.class);
			
			// isbn
			if (notEmpty(filter.getIsbn())) {
				query.setParameter("isbn", "%" + filter.getIsbn() + "%");
			}
			// titulo
			if (notEmpty(filter.getTitulo())) {
				query.setParameter("titulo", "%" + filter.getTitulo() + "%");
			}

			// editora
			if (notEmpty(filter.getEditora())) {
				query.setParameter("editora", "%" + filter.getEditora() + "%");
			}

			// data begin
			if (notEmpty(filter.getDataDePublicacaoRangeBegin())) {
				query.setParameter("dataDePublicacaoRangeBegin", filter.getDataDePublicacaoRangeBegin());
			}

			// data end
			if (notEmpty(filter.getDataDePublicacaoRangeEnd())) {
				query.setParameter("dataDePublicacaoRangeEnd", filter.getDataDePublicacaoRangeEnd());
			}
			// paginas begin
			if (notEmpty(filter.getPaginaRangeBegin())) {
				query.setParameter("paginaRangeBegin", filter.getPaginaRangeBegin());
			}

			// paginas end
			if (notEmpty(filter.getPaginaRangeEnd())) {
					query.setParameter("paginaRangeEnd", filter.getPaginaRangeEnd());
			}

			// Group
			if (notEmpty(filter.getGroup())) {
				query.setParameter("group", filter.getGroup());
			}
			
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os usuários.", pe);
		}
		return resultado;
	}
	
	private boolean notEmpty(Object obj) {
		return obj != null;
	}
	
	private boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}
	
}
