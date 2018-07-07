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

public class TesteServicoCliente {
	
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
	private ServicoCliente sc;
	
	@Test
	public void inserirSemErroCliente() throws Exception {
		
		log.info("============> Iniciando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
		Date d = new Date(96, 8 , 9);
		Date d1 = new Date (2018, 1 , 12);
		Cliente o = new Cliente(1L,"Lowran","lvelias","123456","Standard","12494031621","993058850","victorlowran@gmail.com",d , d1);
		sc.insert(o);
		Cliente aux = (Cliente) sc.findByName("Lowran").get(0);
		assertNotNull(aux);
		log.info("============> Finalizando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@Test
	public void inserirComErroCliente() throws Exception {
		
		log.info("============> Iniciando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
				Date d = new Date(96, 8 , 9);
				Date d1 = new Date (2018, 1 , 12);
				Cliente o = new Cliente(1L,"Lowran@","lvelias","123456","Standard","12494031621","993058850","victorlowran@gmail.com",d , d1);
				sc.insert(o);
		} 
		catch (Exception e){
				assertTrue(checkString(e, "Caracteres permitidos: letras, espaços, ponto e aspas simples"));
		}
		log.info("============> Finalizando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	public void atualizarCliente() throws Exception {
		
		log.info("============> Iniciando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
		Cliente o = (Cliente) sc.findByName("Lowran").get(0);
		o.setNome("Belo Horizonte modificado");
		sc.update(o);
		Cliente aux = (Cliente) sc.findByName("Lowran modificado").get(0);
		assertNotNull(aux);
		log.info("============> Finalizando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@Test
	public void excluirCliente() throws Exception {
		
		log.info("============> Iniciando o teste " +
		Thread.currentThread().getStackTrace()[1].getMethodName());
		Cliente o = (Cliente) sc.findByName("Lowran").get(0);
		sc.delete(o);
		assertEquals(0, sc.findByName("Lowran modificado").size());
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
