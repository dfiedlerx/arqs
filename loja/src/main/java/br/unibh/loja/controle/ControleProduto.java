package br.unibh.loja.controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.unibh.loja.entidades.Produto;
import br.unibh.loja.negocio.ServicoProduto;

@ManagedBean(name = "produtomb")
@ViewScoped

public class ControleProduto extends ControleUtil {

	@Inject
	ServicoProduto ejb;

	private Produto produto;

	private String nomeArg;
	private Long idCategoria;
	private List<Produto> lista;
	private String descArg;
	

	public String getDescArg() {
		return descArg;
	}

	public void setDescArg(String descArg) {
		this.descArg = descArg;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
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
			if (produto.getId() == null) {
				produto = ejb.insert(produto);
				mostrarMensagem("Inclusão realizada com sucesso!", "");
			} else {
				produto = ejb.update(produto);
				mostrarMensagem("Atualização realizada com sucesso!", "");
			}
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	public void pesquisar() {
		produto = null;
		try {
			lista = ejb.findByCategoriaAndNome("%"+descArg+"%","%"+nomeArg+"%");
		} catch (Exception e) {
			mostrarErro(e);
		}
	}

	
	public void novo() {
		produto = new Produto();
	}

	public void editar(Long id) {
		try {
			produto = ejb.find(id);
			return;
		} catch (Exception e) {
			mostrarErro(e);
		}
		produto = null;
	}

	public void excluir(Long id) {
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			mostrarErro(e);
			return;
		}
		produto = null;
		mostrarMensagem("Exclusão realizada com sucesso!", "");
	}
}
