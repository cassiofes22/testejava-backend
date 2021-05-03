package br.com.wk.testejava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PessoaEstadoDTO {
	
	private String estado;
	private long qtd;
}
