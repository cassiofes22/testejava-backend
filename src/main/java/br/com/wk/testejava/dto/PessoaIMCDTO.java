package br.com.wk.testejava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PessoaIMCDTO {
	private int idade;
	private float imc;
}
