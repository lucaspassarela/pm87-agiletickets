package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {
	
	@Test
	public void deveCriarUmaSessaoParaEspetaculoSeInicioIgualFimPeriodicidadeDiaria() {
		Espetaculo ivete = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime();
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		assertTrue(sessoes.size()==1);
		assertTrue(sessoes.get(0).getEspetaculo().equals(ivete));
		assertTrue(sessoes.get(0).getInicio().equals(inicio.toDateTime(horario)));
	}
	
	@Test
	public void naoDeveCriarSessoesParaEspetaculoSeInicioMaiorFimPeriodicidadeDiaria() {
		Espetaculo ivete = new Espetaculo();
		LocalDate fim = new LocalDate();
		LocalDate inicio = fim.plusDays(1);
		LocalTime horario = new LocalTime();
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		assertTrue(sessoes.isEmpty());
	}
	
	@Test
	public void deveCriarSessoesParaEspetaculoSeInicioMenorFimPeriodicidadeDiaria() {
		Espetaculo ivete = new Espetaculo();
		Integer dias = 10;
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(dias);
		LocalTime horario = new LocalTime();
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		assertTrue(sessoes.size()==(dias+1));
		
		LocalDate dia = inicio;
		int i = 0;
		while (!dia.isAfter(fim)) {
			assertTrue(sessoes.get(i).getEspetaculo().equals(ivete));
			assertTrue(sessoes.get(i).getInicio().equals(dia.toDateTime(horario)));
			dia = dia.plusDays(1);
			i++;
		}
	}
	
	@Test
	public void naoDeveCriarSessoesParaEspetaculoSeInicioMaiorFimPeriodicidadeSemanal() {
		Espetaculo ivete = new Espetaculo();
		LocalDate fim = new LocalDate();
		LocalDate inicio = fim.plusDays(1);
		LocalTime horario = new LocalTime();
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		assertTrue(sessoes.isEmpty());
	}
	
	@Test
	public void deveCriarUmaSessaoParaEspetaculoSeInicioIgualFimPeriodicidadeSemanal() {
		Espetaculo ivete = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime();
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		assertTrue(sessoes.size()==1);
		assertTrue(sessoes.get(0).getEspetaculo().equals(ivete));
		assertTrue(sessoes.get(0).getInicio().equals(inicio.toDateTime(horario)));
	}
	
	@Test
	public void deveCriarSessoesParaEspetaculoSeInicioMenorFimPeriodicidadeSemanal() {
		Espetaculo ivete = new Espetaculo();
		Integer dias = 10;
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(dias);
		LocalTime horario = new LocalTime();
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		assertTrue(sessoes.size()==((dias-1)/7+1));
		
		LocalDate dia = inicio;
		int i = 0;
		while (!dia.isAfter(fim)) {
			assertTrue(sessoes.get(i).getEspetaculo().equals(ivete));
			assertTrue(sessoes.get(i).getInicio().equals(dia.toDateTime(horario)));
			dia = dia.plusDays(7);
			i++;
		}
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
