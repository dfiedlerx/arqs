package br.unibh.loja.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.entidades.Produto;
import br.unibh.loja.util.Resources;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TesteServicoCategoria {

	@Deployment
	public static Archive<?> createTestArchive() {
		
		// Cria o pacote que vai ser instalado no Wildfly para realizacao dos testes
		return ShrinkWrap.create(WebArchive.class, "testeloja.war")
		.addClasses(Cliente.class, Categoria.class, Produto.class, 	Resources.class, DAO.class, ServicoCategoria.class)
		.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		
		}
	
	// Realiza as injecoes com CDI
	@Inject
	private Logger log;
	@Inject
	private ServicoCategoria sc;
	
	@Test
	public void teste01_inserirSemErroCategoria() throws Exception {
		
		log.info("============> Iniciando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
		
		Categoria p1 = new Categoria(1L,"Celular");
		sc.insert(p1);
		Categoria aux = (Categoria) sc.findByName("Celular").get(0);
		assertNotNull(aux);
		log.info("============> Finalizando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@Test
	public void teste02_inserirComErroCategoria() throws Exception {
		
		log.info("============> Iniciando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			
			Categoria p1 = new Categoria(1L,"Celular@");
				sc.insert(p1);
		} 
		catch (Exception e){
				assertTrue(checkString(e, "Caracteres permitidos: letras, espaços, barra, traços ,ponto e aspas simples"));
		}
		log.info("============> Finalizando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	public void teste03_atualizarCategoria() throws Exception {
		
		log.info("============> Iniciando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
		Categoria o = (Categoria) sc.findByName("Celular").get(0);
		o.setDescricao("Celular modificado");
		sc.update(o);
		Categoria aux = (Categoria) sc.findByName("Celular modificado").get(0);
		assertEquals("Celular modificado", aux.getDescricao());
		log.info("============> Finalizando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@Test
	public void teste04_excluirCategoria() throws Exception {
		
		log.info("============> Iniciando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
		Categoria o = (Categoria) sc.findByName("Celular").get(0);
		sc.delete(o);
		assertEquals(0, sc.findByName("Celular modificado").size());
		log.info("============> Finalizando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	private boolean checkString(Throwable e, String str){
		
		if (e.getMessage().contains(str)){
			return true;
		} 
		else if (e.getCause() != null){
			return checkString(e.getCause(), str);
		}
			return false;
		}

	
}
