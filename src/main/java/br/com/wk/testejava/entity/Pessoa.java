package br.com.wk.testejava.entity;

import java.io.Serializable;
//import java.util.Date;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;

	private String nome;

	private String cpf;

	private String rg;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_nasc")
	@JsonProperty("data_nasc")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Brazil/East")
	private Date dataNasc;

	private String sexo;

	private String mae;
	
	private String pai;
	
	private String email;
	
	private String cep;
	
	private String endereco;
	
	private String numero;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	private String telefone_fixo;
	
	private String celular;
	
	private Float altura;
	
	private int peso;
	
	@Column(name="tipo_sanguineo")
	@JsonProperty("tipo_sanguineo")
	private String tipoSanguineo;

}