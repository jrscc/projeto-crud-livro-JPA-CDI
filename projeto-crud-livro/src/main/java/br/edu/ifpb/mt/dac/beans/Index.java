package br.edu.ifpb.mt.dac.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.mt.dac.entities.Item;
import br.edu.ifpb.mt.dac.filters.ItemFilter;
import br.edu.ifpb.mt.dac.services.ServiceDacException;
import br.edu.ifpb.mt.dac.services.ItemService;

@Named
@ViewScoped
public class Index extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5976838804313515033L;

	private List<Item> itens;
	
	@Inject
	private ItemService itemService;

	private ItemFilter itemFilter;
	
	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}
	
	public String delete(Item item) {
		try {
			itemService.delete(item);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Item '" + item.getTitulo() + "' deletado");
		
		return "index?faces-redirect=true";
	}
	
	public List<Item> getItens() {
		return itens;
	}


	public void setItens(List<Item> itens) {
		this.itens = itens;
	}


	public ItemFilter getItemFilter() {
		return itemFilter;
	}


	public void setItemFilter(ItemFilter itemFilter) {
		this.itemFilter = itemFilter;
	}


	

	public String filtrar() {
		try {
			itens = itemService.findBy(getItemFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.itemFilter = new ItemFilter();
		return null;
	}

}
