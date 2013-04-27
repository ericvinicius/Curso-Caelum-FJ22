package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandleFactory {
	public Candle constroiCandleParaData(Calendar data,
			List<Negociacao> negociacoes) {

		double minimo = Double.MAX_VALUE;
		double maximo = Double.MIN_VALUE;
		double volume = 0;

		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();

			if (negociacao.getPreco() > maximo) {
				maximo = negociacao.getPreco();

			}

			if (negociacao.getPreco() < minimo) {
				minimo = negociacao.getPreco();
			}
		}

		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0)
				.getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(
				negociacoes.size() - 1).getPreco();

		return new Candle(abertura, fechamento, minimo, maximo, volume,
				data);

	}


	public List<Candle> constroiCandles(List<Negociacao> todasNegociacoes) {
		List<Candle> candles = new ArrayList<Candle>();

		List<Negociacao> negociacaoDoDia = new ArrayList<Negociacao>();

		Calendar dataAtual = todasNegociacoes.get(0).getData();

		for (Negociacao negociacao : todasNegociacoes) {

			if (!negociacao.isMesmoDia(dataAtual)) {
				Candle candleDoDia = constroiCandleParaData(dataAtual,
						negociacaoDoDia);

				candles.add(candleDoDia);
				negociacaoDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}

			negociacaoDoDia.add(negociacao);

		}

		Candle candleDoDia = constroiCandleParaData(dataAtual,
				negociacaoDoDia);
		candles.add(candleDoDia);

		return candles;
	}

}
