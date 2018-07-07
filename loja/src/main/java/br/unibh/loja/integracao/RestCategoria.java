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

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.negocio.ServicoCategoria;
import io.swagger.annotations.Api;

@Api
@Path("categoria")
public class RestCategoria {

	@Inject
	private ServicoCategoria sc;

	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria incluiCategoria(Categoria categoria) throws Exception {
		System.out.println(categoria.getDescricao());
		List<Categoria> aux = sc.findByName(categoria.getDescricao());
		if (aux == null || aux.isEmpty()) {
			return sc.insert(categoria);
		} else {
			throw new Exception("Categoria já existe.");
		}
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria update(@PathParam("id") Long id, Categoria categoria) throws Exception {

		sc.update(categoria);
		return sc.find(id);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) throws Exception {
		Categoria o = sc.find(id);
		// System.out.println(o);
		sc.delete(o);
	}

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
