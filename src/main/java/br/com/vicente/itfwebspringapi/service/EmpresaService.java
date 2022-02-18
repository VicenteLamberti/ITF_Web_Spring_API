package br.com.vicente.itfwebspringapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vicente.itfwebspringapi.dto.EmpresaDto;
import br.com.vicente.itfwebspringapi.model.Empresa;
import br.com.vicente.itfwebspringapi.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	public List<EmpresaDto> converterParaDto(List<Empresa> empresas) {
		return empresas.stream().map(EmpresaDto::new).collect(Collectors.toList());
	}
	
	
	public List<Empresa> buscar(){
		return empresaRepository.findAll();
	}

	public Empresa buscarPorId(Long id) {
		Optional<Empresa> obj = empresaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName(),null));
	}


	
}
