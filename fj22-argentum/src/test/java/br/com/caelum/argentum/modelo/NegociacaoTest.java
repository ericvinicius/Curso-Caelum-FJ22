package br.com.caelum.argentum.modelo;

import java.util.List;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import junit.framework.Assert;
import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 13);
		Negociacao n = new Negociacao(10, 5, c);

		n.getData().set(Calendar.DAY_OF_MONTH, 20);

		Assert.assertEquals(13, n.getData().get(Calendar.DAY_OF_MONTH));
	}

	@Test(expected = IllegalArgumentException.class)
	public void dataNaoPodeSerNula() {
		new Negociacao(40.5, 100, null);
	}

	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();

		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}

	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
	}

	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negocoacoes1 = new Negociacao(40.5, 100, hoje);
		Negociacao negocoacoes2 = new Negociacao(45.0, 100, hoje);
		Negociacao negocoacoes3 = new Negociacao(39.8, 100, hoje);
		Negociacao negocoacoes4 = new Negociacao(42.3, 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao negocoacoes5 = new Negociacao(48.8, 100, amanha);
		Negociacao negocoacoes6 = new Negociacao(49.3, 100, amanha);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao negocoacoes7 = new Negociacao(51.8, 100, depois);
		Negociacao negocoacoes8 = new Negociacao(52.3, 100, depois);

		List<Negociacao> negociacoes = Arrays.asList(negocoacoes1,
				negocoacoes2, negocoacoes3, negocoacoes4, negocoacoes5,
				negocoacoes6, negocoacoes7, negocoacoes8);
	
		CandleFactory fabrica = new CandleFactory();
		
		List<Candle> candles = fabrica.constroiCandles(negociacoes);
		
		Assert.assertEquals(3, candles.size());
		Assert.assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		Assert.assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
		Assert.assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		Assert.assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		Assert.assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		Assert.assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);
		
	}
}
