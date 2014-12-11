package br.com.caelum.agiletickets.acceptance;

import static org.junit.Assert.assertTrue;

import javax.validation.constraints.AssertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.agiletickets.domain.precos.SessaoTestDataBuilder;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class ReservaTest {
	
	public static String BASE_URL = "http://localhost:8080";
	private WebDriver browser;
	
	@Before
	public void setUp() throws Exception {
		browser = new FirefoxDriver();
	}
	
	@After
	public void tearDown() {
		browser.close();
	}
	
	@Test
	public void aoComprarUltimosIngressosPrecoDeveSerAcrescido() {
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.CINEMA)
				.comOPreco(50.0)
				.comTotalIngressos(100)
				.comIngressoReservados(95)
				.build();
		
		
		browser.get(BASE_URL + "/sessao/7");
		WebElement form = browser.findElement(By.id("reservaForm"));
		form.findElement(By.id("qtde")).sendKeys("1");
		form.submit();
		WebElement mensagem = browser.findElement(By.id("message"));
		assertTrue(mensagem.getText().equals("Sessão reservada com sucesso por R$ 50,00"));
		
	}

}
