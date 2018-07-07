package br.unibh.loja.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.loja.entidades.Cliente;

@Stateless
@LocalBean
public class ServicoCliente implements DAO<Cliente, Long> {

	@Inject
	EntityManager em;

	@Inject
	private Logger log;

	public Cliente insert(Cliente c) throws Exception {
		log.info("Persistindo " + c);
		em.persist(c);
		Date date = new Date(System.currentTimeMillis());
		c.setPerfil("Standard");
		c.setDataCadastro(date);
		return c;
	}

	public Cliente update(Cliente c) throws Exception {
		log.info("Atualizando " + c);
		
		Date date = new Date(System.currentTimeMillis());
		
		SimpleDateFormat d = new SimpleDateFormat("yyyy");
		
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy");
				
		int ano = Integer.parseInt(d.format(c.getDataCadastro()));
		
		int ano1 =  Integer.parseInt(d1.format(date));
		
		int anoTotal = ano1 - ano;
		
		if(anoTotal <= 1 )
		{
			c.setPerfil("Standard");
		}
		else if(anoTotal > 1 || ano < 5 )
		{
			c.setPerfil("Standard Premium");
		}
		else if(anoTotal >= 5 )
		{
			c.setPerfil("Standard Premium Gold");
		}
		return em.merge(c);
	}

	public void delete(Cliente c) throws Exception {
		log.info("Removendo " + c);
		Object cl = em.merge(c);
		em.remove(cl);
	}

	public Cliente find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Cliente.class, k);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Cliente").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findByName(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Cliente.findByName").setParameter("nome", "%" + name + "%").getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Cliente> find(String nome, String perfil) throws Exception {
		log.info("Encontrando o " + perfil);
		return em.createNamedQuery("Cliente.find").setParameter("perfil",perfil).setParameter("nome",nome).getResultList();
	}
}
