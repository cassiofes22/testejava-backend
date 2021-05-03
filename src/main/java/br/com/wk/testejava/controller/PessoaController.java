package br.com.wk.testejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wk.testejava.dto.DoadorTipoSanguineoTotalDTO;
import br.com.wk.testejava.dto.IdadeTipoSanguineoDTO;
import br.com.wk.testejava.dto.PessoaEstadoDTO;
import br.com.wk.testejava.dto.PessoaIMCDTO;
import br.com.wk.testejava.dto.PessoaSexoObesidadeDTO;
import br.com.wk.testejava.entity.Pessoa;
import br.com.wk.testejava.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoa")
@Api(value="pessoa", description="Pessoa API")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	
	@GetMapping
	@ApiOperation(value = "GetAll pessoa", response = Pessoa[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição")
    })
	public ResponseEntity<List<Pessoa>> getAllPessoas() {
		List<Pessoa> pessoa = pessoaService.getAllPessoasByNome();
		
		return ResponseEntity.ok(pessoa);
	}
	
	@PostMapping
	@ApiOperation(value = "Save pessoa", response = Pessoa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persistencia do objeto realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição")
    })
	public ResponseEntity<Pessoa> salvar(@RequestHeader(value = "Authorization" ) String authorization, @RequestBody Pessoa pessoa) {
		return ResponseEntity.ok(pessoaService.salvar(pessoa));
	}
	
	@PostMapping("/json")
	@ApiOperation(value = "Save pessoas", response = Pessoa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persistencia do objeto realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição")
    })
	public ResponseEntity<String> salvarAll(@RequestBody List<Pessoa> pessoas) {

		pessoaService.salvarJSON(pessoas);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/estado")
	@ApiOperation(value = "get pessoas by estado", response = Pessoa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persistencia do objeto realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição")
    })
	public List<PessoaEstadoDTO> countByEstado() {
		return pessoaService.countByEstado();
	}
	
	@GetMapping("/imc")
	@ApiOperation(value = "get imc", response = Pessoa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persistencia do objeto realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição")
    })
	public ResponseEntity<List<PessoaIMCDTO>> getIMC() {
		return ResponseEntity.ok(pessoaService.getIMC());
	}
	
	@GetMapping("/obesidade")
	@ApiOperation(value = "get obesidade", response = Pessoa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persistencia do objeto realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição")
    })
	public ResponseEntity<List<PessoaSexoObesidadeDTO>> getMediaObesidade() {
		return ResponseEntity.ok(pessoaService.getMediaObesidade());
	}
	
	@GetMapping("/mediaidade")
	@ApiOperation(value = "get media tipo", response = Pessoa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persistencia do objeto realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição")
    })
	public ResponseEntity<List<IdadeTipoSanguineoDTO>> getMediaIdadeTipoSanguineo() {
		return ResponseEntity.ok(pessoaService.getMediaIdadeTipoSanguineo());
	}
	
	@GetMapping("/doadores")
	@ApiOperation(value = "get doadores", response = Pessoa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persistencia do objeto realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição")
    })
	public ResponseEntity<List<DoadorTipoSanguineoTotalDTO>> getDoadoresTipoSanguineo() {
		return ResponseEntity.ok(pessoaService.getDoadoresTipoSanguineo());
	}


}

