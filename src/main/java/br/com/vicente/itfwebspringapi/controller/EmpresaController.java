package br.com.vicente.itfwebspringapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vicente.itfwebspringapi.dto.EmpresaDetalhesDto;
import br.com.vicente.itfwebspringapi.dto.EmpresaDto;
import br.com.vicente.itfwebspringapi.form.EmpresaAlteracaoForm;
import br.com.vicente.itfwebspringapi.form.EmpresaForm;
import br.com.vicente.itfwebspringapi.model.Empresa;
import br.com.vicente.itfwebspringapi.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	

	@GetMapping
	public ResponseEntity<List<EmpresaDto>> buscar(){
		List<EmpresaDto> empresasDto = empresaService.buscar();
		return ResponseEntity.ok().body(empresasDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpresaDetalhesDto> detalhar(@PathVariable Long id){
		Empresa empresa = empresaService.buscarPorId(id);
		return ResponseEntity.ok().body(new EmpresaDetalhesDto(empresa));
	}
	
	
	@PostMapping
	public ResponseEntity<EmpresaDto> salvar(@RequestBody @Valid EmpresaForm formEmpresa, UriComponentsBuilder uriBuilder) {
		Empresa empresa = empresaService.salvar(formEmpresa);
		URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(empresa.getId()).toUri(); 
		return ResponseEntity.created(uri).body(new EmpresaDto(empresa));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable @Valid Long id){
		empresaService.deletar(id);
		return new ResponseEntity<>("Empresa deletada",HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmpresaDto> editar(@RequestBody @Valid EmpresaAlteracaoForm empresaForm, @PathVariable Long id, UriComponentsBuilder uriBuilder) {
		Empresa empresa = empresaService.editar(id,empresaForm);
		return ResponseEntity.ok().body(new EmpresaDto(empresa));
	}
	
	
}
