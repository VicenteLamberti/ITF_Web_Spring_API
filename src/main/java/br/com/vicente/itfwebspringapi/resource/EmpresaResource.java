package br.com.vicente.itfwebspringapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vicente.itfwebspringapi.dto.EmpresaDetalhesDto;
import br.com.vicente.itfwebspringapi.dto.EmpresaDto;
import br.com.vicente.itfwebspringapi.model.Empresa;
import br.com.vicente.itfwebspringapi.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaService empresaService;
	

	@GetMapping
	public ResponseEntity<List<EmpresaDto>> findAll(){
		List<Empresa> empresas = empresaService.buscar();
		List<EmpresaDto> empresasDto = empresaService.converterParaDto(empresas);
		
		return ResponseEntity.ok().body(empresasDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpresaDetalhesDto> findById(@PathVariable Long id){
		Empresa empresa = empresaService.buscarPorId(id);
		
		return ResponseEntity.ok().body(new EmpresaDetalhesDto(empresa));
	}
}
