package br.unibh.loja.integracao;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.negocio.ServicoCategoria;
import io.swagger.annotations.Api;

@Api
@Path("categoria")
public class RestCategoria {
	
	@Inject
	private ServicoCategoria sc;

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> helloworld() throws Exception {
		return sc.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria hello(@PathParam("id") final Long id) throws Exception {
		return sc.find(id);
	}

}
