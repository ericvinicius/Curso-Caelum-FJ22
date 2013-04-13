package br.com.caelum.argentum.modelo;

import java.util.Calendar;

public final class Negociacao {
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public double getVolume(){
		return preco * quantidade;
	}
	
	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) this.data.clone();
	}

	public Negociacao(double preco, int quantidade, Calendar data) {
		
		if(data == null){
			throw new IllegalArgumentException("Data Nao pode ser nula!");
		}
		
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}
	
}
