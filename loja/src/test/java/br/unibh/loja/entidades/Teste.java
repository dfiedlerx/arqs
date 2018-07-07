package br.unibh.loja.entidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

public class Teste {
	Categoria c5 = new Categoria(1L,"Celular");
	
	//Testes CLIENTES
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
	public void testGenerateHashCliente() {
		Date d = new Date(96, 8 , 9);
		Date d1 = new Date (2018, 1 , 12);
		Cliente c1 = new Cliente(1L,"Lowran","lvelias","123456","teste","12494031621","993058850","victorlowran@gmail.com",d , d1);
		Cliente c2 = new Cliente(1L,"Lowran","lvelias","123456","teste","12494031621","993058850","victorlowran@gmail.com",d , d1);
		assertEquals(c1.hashCode(), c2.hashCode());
		Cliente c3 = new Cliente(1L,"lowran","lvelias","123456","teste","12494031621","993058850","victorlowran@gmail.com",d , d1);
		assertNotEquals(c1.hashCode(), c3.hashCode());
		assertNotEquals(c2.hashCode(), c3.hashCode());
	}
	
	@Test
	public void testPrintObjectCliente() {
		Date d = new Date(96, 8 , 9);
		Date d1 = new Date (2018, 1 , 12);
		Cliente c1 = new Cliente(1L,"Lowran","lvelias","123456","teste","12494031621","993058850","victorlowran@gmail.com",d , d1);
		assertNotEquals(c1.toString(), "Cliente [id=1, nome=Lowran,login=lvelias, senha=123456, perfil=teste, cpf=12494031621, telefone=993058850, email=victorlowran@gmail.com, dataNascimento=9698, dataCadastro=2018112]");
	}
	
	//Testes CATEGORIA
	@Test
	public void testCriarCategoria() {
		Categoria c1 = new Categoria(1L,"Celular");
		assertEquals(c1.getId(), new Long(1));
		assertNotEquals(c1.getDescricao(),"Mobile");
	}
	
	@Test
	public void testGenerateHashCategoria() {
		Categoria c1 = new Categoria(1L,"Celular");
		Categoria c2 = new Categoria(1L,"Celular");
		assertEquals(c1.hashCode(), c2.hashCode());
		Categoria c3 = new Categoria(1L,"Mobile");
		assertNotEquals(c1.hashCode(), c3.hashCode());
		assertNotEquals(c2.hashCode(), c3.hashCode());
	}
	
	@Test
	public void testPrintObjectCategoria() {
		Categoria c1 = new Categoria(1L,"Celular");
		assertEquals(c1.toString(), "Categoria [id=1, descricao=Celular]");
		assertNotEquals(c1.toString(), "Categoria [id=1, descricao=Mobile]");
	}
	
	//Testes PRODUTO
	@Test
	public void testCriarProduto() {
		BigDecimal a = new BigDecimal("3589.00");
		Produto pr = new Produto(1L,"iPhone 7","Red",c5, a ,"Apple");
		assertEquals(pr.getId(),new Long (1));
		assertNotEquals(pr.getCategoria(), "celular");
	}
	
	@Test
	public void testGenerateHashProduto() {
		BigDecimal a = new BigDecimal("3589.00");
		Produto p1 = new Produto(1L,"iPhone 7","Red",c5, a ,"Apple");
		Produto p2 = new Produto(1L,"iPhone 7","Red",c5, a ,"Apple");
		assertEquals(p1.hashCode(), p2.hashCode());
		Produto p3 = new Produto(1L,"iPhone 7","Red",c5, a ,"apple");
		assertNotEquals(p1.hashCode(), p3.hashCode());
	}
	@Test
	public void testPrintObjectProduto() {
		BigDecimal a = new BigDecimal("3589.00");
		Produto p1 = new Produto(1L,"iPhone 7","Red",c5, a ,"Apple");
		assertNotEquals(p1.toString(), "Produto [id=1, nome=iPhone 7,descrição = Black, Categoria = celular, preco = 3589.00 , fabricante = Apple]");
	}
}
