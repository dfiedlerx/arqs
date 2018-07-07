package br.unibh.loja.controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.negocio.ServicoCliente;
import br.unibh.loja.controle.ControleUtil;

@ManagedBean(name = "clientemb")
@ViewScoped

public class ControleCliente extends ControleUtil {

	@Inject
	ServicoCliente ejb;

	private Cliente cliente;

	private String nomeArg;
	private String perfilArg;
	private List<Cliente> lista;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}
	
	public String getPerfilArg() {
		return perfilArg;
	}

	public void setPerfilArg(String perfilArg) {
		this.perfilArg = perfilArg;
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	@PostConstruct
	public void inicializaLista() {
		try {
			lista = ejb.findAll();
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	public void gravar() {
		try {
			if (cliente.getId() == null) {
				cliente = ejb.insert(cliente);
				mostrarMensagem("Inclusão realizada com sucesso!", "");
			} else {
				cliente = ejb.update(cliente);
				mostrarMensagem("Atualização realizada com sucesso!", "");
			}
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	public void pesquisar() {
		cliente = null;
		try {
			lista = ejb.find("%"+nomeArg+"%","%"+perfilArg+"%");
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	/*public void pesquisarPerfil() {
		cliente = null;
		try {
			lista = ejb.findByPerfil(nomArg);
		} catch (Exception e) {
			mostrarErro(e);
		}
	}*/
	
	public void novo() {
		cliente = new Cliente();
	}

	public void editar(Long id) {
		try {
			cliente = ejb.find(id);
			return;
		} catch (Exception e) {
			mostrarErro(e);
		}
		cliente = null;
	}

	public void excluir(Long id) {
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
			return;
		}
		cliente = null;
		mostrarMensagem("Exclusão realizada com sucesso!", "");
	}
}
