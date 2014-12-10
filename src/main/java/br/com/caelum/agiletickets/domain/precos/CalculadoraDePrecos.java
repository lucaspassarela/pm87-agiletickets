package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {
	

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;

		if (sessao.isDoTipo(TipoDeEspetaculo.CINEMA)
				|| sessao.getEspetaculo().getTipo()
						.equals(TipoDeEspetaculo.SHOW)) {
			// quando estiver acabando os ingressos...
			preco = calculaPrecoCinemaeShow(sessao);

		} else if (sessao.isDoTipo(TipoDeEspetaculo.BALLET)
				|| sessao.isDoTipo(TipoDeEspetaculo.ORQUESTRA)) {
			preco = calculaPrecoBalleteOrquestra(sessao);
		} else {
			// nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaPrecoCinemaeShow(Sessao sessao) {
		BigDecimal preco;
		if (sessao.getPercentualReservado() <= 0.05) {
			preco = sessao.getPreco().add(sessao.calculoPercentualAcrescimo(0.10));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

	private static BigDecimal calculaPrecoBalleteOrquestra(Sessao sessao) {
		BigDecimal preco;
		if (sessao.getPercentualReservado() <= 0.50) {
			preco = sessao.getPreco().add(sessao.calculoPercentualAcrescimo(0.20));
		} else {
			preco = sessao.getPreco();
		}

		if (sessao.getDuracaoEmMinutos() > 60) {
			preco = preco.add(sessao.calculoPercentualAcrescimo(0.10));
		}
		return preco;
	}

}