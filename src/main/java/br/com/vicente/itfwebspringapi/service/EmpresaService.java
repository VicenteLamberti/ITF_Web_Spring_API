package br.com.vicente.itfwebspringapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vicente.itfwebspringapi.dto.EmpresaDto;
import br.com.vicente.itfwebspringapi.form.EmpresaForm;
import br.com.vicente.itfwebspringapi.model.Empresa;
import br.com.vicente.itfwebspringapi.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	public List<EmpresaDto> converterParaDto(List<Empresa> empresas) {
		return empresas.stream().map(EmpresaDto::new).collect(Collectors.toList());
	}

	public Empresa converterParaEmpresa(EmpresaForm empresaForm) {

		Empresa empresa = new Empresa(empresaForm.getNome(), empresaForm.getCnpj());
		return empresa;
	}

	public List<Empresa> buscar() {
		return empresaRepository.findAll();
	}

	public Empresa buscarPorId(Long id) {
		Optional<Empresa> empresa = empresaRepository.findById(id);
		return empresa.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName(), null));
	}

	public void salvar(Empresa empresa) {
		empresa.setNome(empresa.getNome().toUpperCase());
		empresaRepository.save(empresa);
	}

	public void deletar (Long id) {
		Optional<Empresa> empresa = empresaRepository.findById(id);
		if(empresa.isPresent()) {
			empresaRepository.delete(empresa.get());
		}
		else {
			throw new ObjectNotFoundException(id, null);
		}
		
		
	}


	
}
