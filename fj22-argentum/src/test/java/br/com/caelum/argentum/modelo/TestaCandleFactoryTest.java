package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class TestaCandleFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4);

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(42.3, candle.getFechamento(), 0.00001);
		Assert.assertEquals(39.8, candle.getMinimo(), 0.00001);
		Assert.assertEquals(45.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(16760.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void semNenhumaNegociacao() {
		Calendar hoje = Calendar.getInstance();


		List<Negociacao> negociacoes = Arrays.asList();

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		
		Assert.assertEquals(0.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void UmaUnicaNegociacao() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1);

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(40.5, candle.getFechamento(), 0.00001);
		Assert.assertEquals(40.5, candle.getMinimo(), 0.00001);
		Assert.assertEquals(40.5, candle.getMaximo(), 0.00001);
		Assert.assertEquals(4050.0, candle.getVolume(), 0.00001);
	}
	
	

}
