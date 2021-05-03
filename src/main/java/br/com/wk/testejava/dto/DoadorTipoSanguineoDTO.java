package br.com.wk.testejava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoadorTipoSanguineoDTO {
	private String tipo_sanguineo;
	private long doadores;
}
