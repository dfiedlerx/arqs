package br.unibh.loja.integracao;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unibh.loja.entidades.Produto;
import br.unibh.loja.negocio.ServicoCategoria;
import br.unibh.loja.negocio.ServicoProduto;
import io.swagger.annotations.Api;


@Api
@Path("produto")
public class RestProduto {
	
	@Inject
	private ServicoProduto sp;
	private ServicoCategoria sc;
	
	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public void incluiProduto(Produto produto) throws Exception {
		sp.insert(produto);
	}
	
	 @PUT
	 @Path("{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Produto update(@PathParam("id") Long id, Produto produto) throws Exception {
			
			sp.update(produto);
			return sp.find(id);
	 }
	
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) throws Exception {
		sp.delete(sp.find(id));
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> helloworld() throws Exception {
		return sp.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto hello(@PathParam("id") final Long id) throws Exception {
		return sp.find(id);
	}
	
	@GET
	@Path("categoria/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> retornaProdutoCategoria(@PathParam("id") Long id) throws Exception {
		//Categoria aux = (Categoria) sc.find(id);
		return sp.findByCategoria(id);
	}

}
