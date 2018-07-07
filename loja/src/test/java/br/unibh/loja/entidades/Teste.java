package br.unibh.loja.entidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

public class Teste {
	@Test
	public void testCriarCliente() {
		Date d = new Date(96, 8 , 9);
		Date d1 = new Date (2018, 1 , 12);
		Cliente cl = new Cliente(1L,"Lowran","lvelias","123456","teste","12494031621","993058850","victorlowran@gmail.com",d , d1);
		assertEquals(cl.getId(),new Long (1));
		assertEquals(cl.getCpf(), "12494031621");
		assertEquals(cl.getLogin(), "lvelias");
	}
	
	@Test
	public void testCriarProduto() {
		BigDecimal a = new BigDecimal("3589.00");
		Produto pr = new Produto(1L,"iPhone 7","Red",Categoria.TipoCategoria.celular, a ,"Apple");
		assertEquals(pr.getId(),new Long (1));
		assertEquals(pr.getCategoria(), "celular");
	}
	
	@Test
	public void testGenerateHash() {
		BigDecimal a = new BigDecimal("3589.00");
		Produto p1 = new Produto(1L,"iPhone 7","Red",Categoria.TipoCategoria.celular, a ,"Apple");
		Produto p2 = new Produto(1L,"iPhone 7","Red",Categoria.TipoCategoria.celular, a ,"Apple");
		assertEquals(p1.hashCode(), p2.hashCode());
		Produto p3 = new Produto(1L,"iPhone 7","Red",Categoria.TipoCategoria.celular, a ,"apple");
		assertNotEquals(p1.hashCode(), p3.hashCode());
	}
	@Test
	public void testPrintObject() {
		BigDecimal a = new BigDecimal("3589.00");
		Produto p1 = new Produto(1L,"iPhone 7","Red",Categoria.TipoCategoria.celular, a ,"Apple");
		assertNotEquals(p1.toString(), "Produto [id=1, nome=iPhone 7,descrição = Black, Categoria = celular, preco = 3589.00 , fabricante = Apple]");
	}
}
