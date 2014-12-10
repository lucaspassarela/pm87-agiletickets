package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	
	DIARIA(1), SEMANAL(7);
	
	private Integer diasPeriodicidade;
	
	private Periodicidade(Integer diasPeriodicidade) {
		this.diasPeriodicidade = diasPeriodicidade;
	}
	
	public Integer getDiasPeriodicidade() {
		return diasPeriodicidade;
	} 
	
}
