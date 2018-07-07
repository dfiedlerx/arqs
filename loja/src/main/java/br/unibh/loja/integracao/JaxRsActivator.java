package br.unibh.loja.integracao;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("/rest")
public class JaxRsActivator extends Application {
	public JaxRsActivator() {
		BeanConfig conf = new BeanConfig();
		conf.setTitle("Loja REST API");
		conf.setDescription("API de consulta para a aplicacao Loja");
		conf.setVersion("1.0");
		conf.setHost("localhost:8180");
		conf.setBasePath("/loja/rest");
		conf.setSchemes(new String[] { "http" });
		conf.setResourcePackage("br.unibh.loja.integracao");
		conf.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		// classes do swagger
		resources.add(ApiListingResource.class);
		resources.add(SwaggerSerializers.class);
		// classes da aplicacao
		resources.add(JaxRsActivator.class);
		resources.add(RestTest.class);
		resources.add(RestCategoria.class);
		return resources;
	}
}
