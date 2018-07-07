package br.unibh.loja.entidades;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.junit.Test;

public class TesteValidation {
	
	static Categoria c5 = new Categoria(1L,"Celular");
	
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class TestValidationCidade {

		private static Validator validator;

		@BeforeClass
		public static void setUp() {
			System.out.println("Inicializando validador...");
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
		}
		//Cliente
		@Test
		public void testeValidacaoCliente1() {
			Date d = new Date(96, 8 , 9);
			Date d1 = new Date (2018, 1 , 12);
			Cliente c1 = new Cliente(1L,"Lowran","lveliasteste","123456","lowrantestes","12494031621","(31)99658-7412","lowranelias@teste.com",d,d1);
			System.out.println(c1);
			Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate( c1 );
			for (ConstraintViolation<Cliente> c: constraintViolations) {
				System.out.println(" Erro de Validacao: "+c.getMessage());
			}
			Assert.assertEquals(0, constraintViolations.size() );
		}
		@Test
		public void testeValidacaoCliente2() {
			Date d = new Date(96, 8 , 9);
			Date d1 = new Date (2018, 1 , 12);
			Cliente c1 = new Cliente(1L,"Lowran","lveliastestess","123456","lowrantestes","12494031621","(31)99658-7412","lowranelias@teste.com",d,d1);
			System.out.println(c1);
			Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate( c1 );
			for (ConstraintViolation<Cliente> c: constraintViolations) {
				System.out.println(" Erro de Validacao: "+c.getMessage());
			}
			Assert.assertNotEquals(1, constraintViolations.size() );
		}
		//Produto
				@Test
				public void testeValidacaoProduto1() {
					BigDecimal a = new BigDecimal("3589.00");
					Produto pr = new Produto(1L,"iPhone 7","Red",c5, a ,"Apple");
					System.out.println(pr);
					Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(pr);
					for (ConstraintViolation<Produto> c: constraintViolations) {
						System.out.println(" Erro de Validacao: "+c.getMessage());
					}
					Assert.assertEquals(1, constraintViolations.size() );
				}
				@Test
				public void testeValidacaoProduto2() {
					BigDecimal a = new BigDecimal("3589.00");
					Produto pr = new Produto(1L,"iPhone 72","Red",c5, a ,"Apple");
					System.out.println(pr);
					Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(pr);
					for (ConstraintViolation<Produto> c: constraintViolations) {
						System.out.println(" Erro de Validacao: "+c.getMessage());
					}
					Assert.assertNotEquals(0, constraintViolations.size() );
				}
				//Categoria
				@Test
				public void testeValidacaoCategoria() {
					Categoria ca = new Categoria(1L, "Eletrodomestico");
					System.out.println(ca);
					Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(ca);
					for (ConstraintViolation<Categoria> c: constraintViolations) {
						System.out.println(" Erro de Validacao: "+c.getMessage());
					}
					Assert.assertEquals(0, constraintViolations.size() );
				}
				@Test
				public void testeValidacaoCategoria1() {
					Categoria ca = new Categoria(1L, "Eletrodomesticos");
					System.out.println(ca);
					Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(ca);
					for (ConstraintViolation<Categoria> c: constraintViolations) {
						System.out.println(" Erro de Validacao: "+c.getMessage());
					}
					Assert.assertNotEquals(1, constraintViolations.size() );
				}
	}
}