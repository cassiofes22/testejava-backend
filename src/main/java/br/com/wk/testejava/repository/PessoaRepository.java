package br.com.wk.testejava.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.wk.testejava.dto.DoadorTipoSanguineoDTO;
import br.com.wk.testejava.dto.PessoaEstadoDTO;
import br.com.wk.testejava.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, BigInteger> {
	
	public List<Pessoa> findByOrderByNome();
	
	@Query(value = "select new br.com.wk.testejava.dto.PessoaEstadoDTO(p.estado, count(p.estado)) from Pessoa p group by p.estado order by p.estado")
	public List<PessoaEstadoDTO> countByEstado();
	
	public List<Pessoa> findByOrderByDataNascDesc();
	
	public List<Pessoa> findByOrderBySexo();
	
	public List<Pessoa> findByOrderByTipoSanguineo();
	
	@Query(value = "select new br.com.wk.testejava.dto.DoadorTipoSanguineoDTO(p.tipoSanguineo, count(p.tipoSanguineo)) from Pessoa p"
			+ " where TIMESTAMPDIFF(YEAR, p.dataNasc, CURDATE()) between 16 and 69 and p.peso > 50"
			+ " group by p.tipoSanguineo"
			+ " order by p.tipoSanguineo")
	public List<DoadorTipoSanguineoDTO> countByTipoSanguineo();
	
	public Pessoa findByCpf(String cpf);
	
}
