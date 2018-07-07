package br.unibh.loja.controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.negocio.ServicoCategoria;
import br.unibh.loja.controle.ControleUtil;

@ManagedBean(name = "categoriamb")
@ViewScoped

public class ControleCategoria extends ControleUtil {

	@Inject
	ServicoCategoria ejb;

	private Categoria categoria;

	private String nomeArg;
	private List<Categoria> lista;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public List<Categoria> getLista() {
		return lista;
	}

	public void setLista(List<Categoria> lista) {
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
			if (categoria.getId() == null) {
				categoria = ejb.insert(categoria);
				mostrarMensagem("Inclusão realizada com sucesso!", "");
			} else {
				categoria = ejb.update(categoria);
				mostrarMensagem("Atualização realizada com sucesso!", "");
			}
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	public void pesquisar() {
		categoria = null;
		try {
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	public void novo() {
		categoria = new Categoria();
	}

	public void editar(Long id) {
		try {
			categoria = ejb.find(id);
			return;
		} catch (Exception e) {
			mostrarErro(e);
		}
		categoria = null;
	}

	public void excluir(Long id) {
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
			return;
		}
		categoria = null;
		mostrarMensagem("Exclusão realizada com sucesso!", "");
	}
}
