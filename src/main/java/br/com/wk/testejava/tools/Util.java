package br.com.wk.testejava.tools;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Util {

	static public int calculaIdade(Date data_nasc) {
		
		LocalDate nasc = data_nasc.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
		
		Period diff=Period.between(nasc, LocalDate.now());
		return diff.getYears();
		
	} 
	
	static public float calculaIMC(int peso, float altura) {
		return (float)(peso / Math.sqrt(altura));
	}
}
