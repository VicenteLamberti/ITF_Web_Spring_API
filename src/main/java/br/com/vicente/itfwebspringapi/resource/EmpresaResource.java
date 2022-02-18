package br.com.vicente.itfwebspringapi.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vicente.itfwebspringapi.dto.EmpresaDetalhesDto;
import br.com.vicente.itfwebspringapi.dto.EmpresaDto;
import br.com.vicente.itfwebspringapi.form.EmpresaForm;
import br.com.vicente.itfwebspringapi.model.Empresa;
import br.com.vicente.itfwebspringapi.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaService empresaService;
	

	@GetMapping
	public ResponseEntity<List<EmpresaDto>> buscar(){
		List<Empresa> empresas = empresaService.buscar();
		List<EmpresaDto> empresasDto = empresaService.converterParaDto(empresas);
		
		return ResponseEntity.ok().body(empresasDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpresaDetalhesDto> buscarPorId(@PathVariable Long id){
		Empresa empresa = empresaService.buscarPorId(id);
		
		return ResponseEntity.ok().body(new EmpresaDetalhesDto(empresa));
	}
	
	@PostMapping
	public ResponseEntity<EmpresaDto> salvar(@RequestBody @Valid EmpresaForm formEmpresa, UriComponentsBuilder uriBuilder) {
		Empresa empresa = empresaService.converterParaEmpresa(formEmpresa);
		
		empresaService.salvar(empresa);
		URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(empresa.getid()).toUri(); 
		return ResponseEntity.created(uri).body(new EmpresaDto(empresa));
	}
}
