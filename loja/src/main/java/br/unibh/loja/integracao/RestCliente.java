package br.unibh.loja.integracao;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.negocio.ServicoCliente;
import io.swagger.annotations.Api;


@Api
@Path("cliente")
public class RestCliente {

	@Inject
	private ServicoCliente sc;
	
	
	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public void incluiCliente(Cliente cliente) throws Exception {
		sc.insert(cliente);
	}
	
	 @PUT
	 @Path("{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Cliente update(@PathParam("id") Long id, Cliente cliente) throws Exception {
			
			sc.update(cliente);
			return sc.find(id);
	 }
	
	 @DELETE
	 @Path("{id}")
	 public void delete(@PathParam("id") Long id) throws Exception {
		sc.delete(sc.find(id));
	 }
	 
	@GET
	@Path("nome/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> retornaNome(@PathParam("nome") String nome) throws Exception {
		return sc.findByName(nome);
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> buscaTodosClientes() throws Exception {
		return sc.findAll();
	}
	
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente hello(@PathParam("id") final Long id) throws Exception {
		return sc.find(id);
	}
	
}
