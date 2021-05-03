package br.com.wk.testejava.service;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wk.testejava.dto.DoadorTipoSanguineoDTO;
import br.com.wk.testejava.dto.DoadorTipoSanguineoTotalDTO;
import br.com.wk.testejava.dto.IdadeTipoSanguineoDTO;
import br.com.wk.testejava.dto.PessoaEstadoDTO;
import br.com.wk.testejava.dto.PessoaIMCDTO;
import br.com.wk.testejava.dto.PessoaSexoObesidadeDTO;
import br.com.wk.testejava.entity.Pessoa;
import br.com.wk.testejava.exception.SalvarPessoaException;
import br.com.wk.testejava.repository.PessoaRepository;
import br.com.wk.testejava.tools.Util;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public List<Pessoa> getAllPessoas() {
		return pessoaRepository.findAll();
	}
	
	public List<Pessoa> getAllPessoasByNome() {
		return pessoaRepository.findByOrderByNome();
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public void salvarJSON(List<Pessoa> pessoas) {
		pessoas.forEach(pessoa -> {
			try {
				if (pessoaRepository.findByCpf(pessoa.getCpf()) == null ) {
					pessoaRepository.save(pessoa);
					System.out.println("salvou -> " + pessoa.getNome());
				} else {
					System.out.println("existe -> " + pessoa.getNome());
				}
				
			} catch (Exception e) {
				System.out.println("Erro ao salvar pessoa (CPF: " + pessoa.getCpf() + ")\nErro => " + e.getMessage());
//				throw new SalvarPessoaException("Erro ao salvar pessoa (CPF: " + pessoa.getCpf() + ")");
			}
		});
	}
	
	public List<PessoaEstadoDTO> countByEstado() {
		return pessoaRepository.countByEstado();
	}
	
	public List<PessoaIMCDTO> getIMC() {
		List<Pessoa> pessoas = pessoaRepository.findByOrderByDataNascDesc();
		
		List<PessoaIMCDTO> ret = new ArrayList<PessoaIMCDTO>();
		
		int idade = 0;
		int idadeDecimal = 10;
		int countImc = 0;
		float imc = 0;
		
		for(Pessoa pessoa: pessoas) {
			idade = Util.calculaIdade(pessoa.getDataNasc());
			int idadeDecimalTemp = (idade / 10) * 10;
			idadeDecimal = idadeDecimal == 0 ? idadeDecimalTemp : idadeDecimal;
			if (idadeDecimalTemp != idadeDecimal ) {
				ret.add(new PessoaIMCDTO(idadeDecimal + 10 - 1, imc/countImc));
				idadeDecimal = 0;
				countImc = 0;
				imc = 0;
			}
				
			imc += Util.calculaIMC(pessoa.getPeso(), pessoa.getAltura());
			countImc++;
						
		}
		
		return ret;
	}
	
	public List<PessoaSexoObesidadeDTO> getMediaObesidade() {

		List<Pessoa> pessoas = pessoaRepository.findByOrderBySexo();
		List<PessoaSexoObesidadeDTO> ret = new ArrayList<PessoaSexoObesidadeDTO>();
		
		int m = 0;
		int f = 0;
		
		for(Pessoa pessoa: pessoas) {
			if (Util.calculaIMC(pessoa.getPeso(),  pessoa.getAltura()) > 30) {
				m += pessoa.getSexo().equals("Masculino") ? 1 : 0;
				f += pessoa.getSexo().equals("Feminino") ? 1 : 0;
			}
		}
		ret.add(new PessoaSexoObesidadeDTO("Masculino", (float)m * 100 / pessoas.size()));
		ret.add(new PessoaSexoObesidadeDTO("Feminino", (float)f * 100 / pessoas.size()));
		
		return ret;
		
	}
	
	public List<IdadeTipoSanguineoDTO> getMediaIdadeTipoSanguineo() {
		
		List<Pessoa> pessoas = pessoaRepository.findByOrderByTipoSanguineo();
		
		List<IdadeTipoSanguineoDTO> medias = new ArrayList<IdadeTipoSanguineoDTO>();
		
		String tipo = "";
		int idade = 0;
		int qtd = 0;
		
//		String s = "tipo,idade\n";
		
		for(Pessoa pessoa: pessoas) {
			tipo = tipo.equals("") ? pessoa.getTipoSanguineo() : tipo;
			if (!tipo.equals(pessoa.getTipoSanguineo())) {
				medias.add(new IdadeTipoSanguineoDTO(tipo, idade/qtd));
				tipo = pessoa.getTipoSanguineo();
				idade = 0;
				qtd = 0;
			}
			
			idade+= Util.calculaIdade(pessoa.getDataNasc());
			qtd++;
//			s += tipo + "," + String.valueOf(Util.calculaIdade(pessoa.getDataNasc())) + "\n";
		}
		
		return medias;
		
	}
	
	public List<DoadorTipoSanguineoTotalDTO> getDoadoresTipoSanguineo() {
		
		List<DoadorTipoSanguineoDTO> doadoresTipos = pessoaRepository.countByTipoSanguineo();
		
		List<DoadorTipoSanguineoTotalDTO> receptores = new ArrayList<DoadorTipoSanguineoTotalDTO>() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
			add(new DoadorTipoSanguineoTotalDTO("A+", "A+,A-,O+,O-", 0));
			add(new DoadorTipoSanguineoTotalDTO("A-", "A-,O-", 0));
			add(new DoadorTipoSanguineoTotalDTO("B+", "B+,B-,O+,O-", 0));
			add(new DoadorTipoSanguineoTotalDTO("B-", "B-,O-", 0));
			add(new DoadorTipoSanguineoTotalDTO("AB+", "A+,B+,O+,AB+,A-,B-,O-,AB-", 0));
			add(new DoadorTipoSanguineoTotalDTO("AB-", "A-,B-,O-,AB-", 0));
			add(new DoadorTipoSanguineoTotalDTO("O+", "O+,O-", 0));
			add(new DoadorTipoSanguineoTotalDTO("O-", "O-", 0));

		}};
		
		receptores.forEach((receptor) -> {
			doadoresTipos.forEach((doadores) -> {
				if ( (Arrays.asList(receptor.getRecebeDe().split(","))).contains(doadores.getTipo_sanguineo())) {
					receptor.setDoadores(receptor.getDoadores() + doadores.getDoadores());
				}
			});
		});
	
		
		return receptores;
				
		
	}
	
}

	