package br.unibh.loja.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Produto;
import br.unibh.loja.util.Resources;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TesteServicoProduto {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		
		// Cria o pacote que vai ser instalado no Wildfly para realizacao dos testes
		return ShrinkWrap.create(WebArchive.class, "testeseguro.war")
		.addClasses(Cliente.class, Categoria.class, Produto.class, 	Resources.class, DAO.class, ServicoCliente.class)
		.addAsResource("META-INF/persistence.xml", "METAINF/persistence.xml")
		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		
		}
	
	// Realiza as injecoes com CDI
	@Inject
	private Logger log;
	@Inject
	private ServicoProduto sp;
	


}
