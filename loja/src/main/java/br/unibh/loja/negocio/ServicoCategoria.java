package br.unibh.loja.negocio;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.unibh.loja.entidades.Categoria;

@Stateless
@LocalBean
public class ServicoCategoria implements DAO<Categoria, Long> {

	@Inject
	EntityManager em;

	@Inject
	private Logger log;

	public Categoria insert(Categoria p) throws Exception {
		log.info("Persistindo " + p);
		em.persist(p);
		return p;
	}

	public Categoria update(Categoria p) throws Exception {
		log.info("Atualizando " + p);
		return em.merge(p);
	}

	public void delete(Categoria p) throws Exception {
		log.info("Removendo " + p);
		Object pr = em.merge(p);
		em.remove(pr);
	}

	public Categoria find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Categoria.class, k);
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Categoria").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> findByName(String descricao) throws Exception {
		log.info("Encontrando o " + descricao);
		return em.createNamedQuery("Categoria.findByName").setParameter("descricao", "%" + descricao + "%").getResultList();
	}

}
