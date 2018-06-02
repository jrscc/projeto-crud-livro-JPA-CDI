package br.edu.ifpb.mt.dac.beans;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.mt.dac.entities.Item;
import br.edu.ifpb.mt.dac.services.ServiceDacException;
import br.edu.ifpb.mt.dac.services.ItemService;

@Named
@ViewScoped
public class ItemEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7779155604704232174L;

	private Item item;
	
	private String confirmacaoPassword;
	
	@Inject
	private ItemService itemService;

	public void init() {
		try {
			if (item == null) {
				item = new Item();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String saveUser() {
		try {
			if (isEdicaoDeUsuario()) {
				itemService.update(item);
			} else {
				
				itemService.save(item);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Item '" + item.getTitulo() + "' saved");

		return "index.xhtml?faces-redirect=true";
	}

	public boolean isEdicaoDeUsuario() {
		return item.getId() != null;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getConfirmacaoPassword() {
		return confirmacaoPassword;
	}
	
	public void setConfirmacaoPassword(String confirmacaoPassword) {
		this.confirmacaoPassword = confirmacaoPassword;
	}
}
