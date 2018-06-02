package br.edu.ifpb.mt.dac.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.mt.dac.entities.Item;
import br.edu.ifpb.mt.dac.filters.ItemFilter;
import br.edu.ifpb.mt.dac.services.ItemService;
import br.edu.ifpb.mt.dac.services.ServiceDacException;

@Named
@RequestScoped
public class SelectItem extends AbstractBean{
	
	private static final long serialVersionUID = -5976838800313515033L;
	
	private Item selecionado;
	
	private List<Item> itens;
	
	@Inject
	private ItemService itemService;
	
	private ItemFilter itemFilter;

	public Item getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Item selecionado) {
		this.selecionado = selecionado;
	}

	public ItemFilter getItemFilter() {
		return itemFilter;
	}

	public void setItemFilter(ItemFilter itemFilter) {
		this.itemFilter = itemFilter;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	@PostConstruct
	public void init() {
		limpar();
		filtrar();
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