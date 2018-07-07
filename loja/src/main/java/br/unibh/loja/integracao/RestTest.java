package br.unibh.loja.integracao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import io.swagger.annotations.Api;

@Api
@Path("app")
public class RestTest {
	@GET
	@Path("name")
	public String helloworld() {
		return "<h1>Loja</h1>";
	}
}
