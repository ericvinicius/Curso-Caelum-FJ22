package br.com.caelum.argentum.modelo;

import java.util.List;

public class SerieTemporal {
	private final List<Candlestick> candles;

	public SerieTemporal(List<Candlestick> candles){
		this.candles = candles;
	}
	
	public Candlestick getCandles(int i) {
		return this.candles.get(i);
	}
	
	public int getTotal(){
		return this.candles.size();
	}
	
	
	
}
