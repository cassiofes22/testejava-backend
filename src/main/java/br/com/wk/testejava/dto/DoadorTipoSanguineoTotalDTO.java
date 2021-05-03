package br.com.wk.testejava.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoadorTipoSanguineoTotalDTO extends DoadorTipoSanguineoDTO {
	
	private String recebeDe;
	
	public DoadorTipoSanguineoTotalDTO(String tipo_sanguineo, String recebeDe, int total) {
		super(tipo_sanguineo, total);
		this.recebeDe = recebeDe;
	}


}
