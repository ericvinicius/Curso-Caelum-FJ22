package br.com.caelum.argentum.indicadores;

import java.lang.reflect.Constructor;

import javax.management.RuntimeErrorException;


public class IndicadorFactory {
	private static final String PACKAGE = "br.com.caelum.argentum.modelo.";
	private String nomeMedia;
	private String nomeIndicador;

	public IndicadorFactory(String nomeIndicador, String nomeMedia) {
		this.nomeIndicador = nomeIndicador;
		this.nomeMedia = nomeMedia;
	}

	public Indicador getIndicador(){
		try {
			Indicador indicador = (Indicador) Class.forName(PACKAGE + this.nomeIndicador).newInstance(); 
		
			if(this.nomeMedia != null && !this.nomeMedia.isEmpty()){
				Constructor<?> constructor = Class.forName(PACKAGE + this.nomeMedia).getConstructor(Indicador.class);
				
				indicador = (Indicador) constructor.newInstance(indicador);
			}
		
			return indicador;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
