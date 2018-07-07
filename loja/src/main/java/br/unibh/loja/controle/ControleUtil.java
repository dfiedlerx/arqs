package br.unibh.loja.controle;

import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class ControleUtil {
	@Inject
	protected Logger log;

	protected void mostrarAviso(String mensagem, String detalhe) {
		FacesContext.getCurrentInstance().addMessage("messagePanel",
				new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, detalhe));
		log.warning("Warn: " + mensagem);
	}

	protected void mostrarMensagem(String mensagem, String detalhe) {
		FacesContext.getCurrentInstance().addMessage("messagePanel",
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, detalhe));
		log.info("Info: " + mensagem);
	}

	protected void mostrarErro(String mensagem, String detalhe) {
		FacesContext.getCurrentInstance().addMessage("messagePanel",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, detalhe));
		log.severe("Error: " + mensagem);
	}

	protected void mostrarErro(Exception e) {
		mostrarErro(e.getMessage(), e.getCause().getMessage());
		e.printStackTrace();
	}
}
