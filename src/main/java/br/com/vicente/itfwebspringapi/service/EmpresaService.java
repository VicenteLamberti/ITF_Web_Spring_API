package br.com.vicente.itfwebspringapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.vicente.itfwebspringapi.dto.EmpresaDto;
import br.com.vicente.itfwebspringapi.form.EmpresaAlteracaoForm;
import br.com.vicente.itfwebspringapi.form.EmpresaForm;
import br.com.vicente.itfwebspringapi.model.Empresa;
import br.com.vicente.itfwebspringapi.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	private List<EmpresaDto> converteEmpresarParaDtos(List<Empresa> empresas) {
		return empresas.stream().map(EmpresaDto::new).collect(Collectors.toList());
	}

	private Empresa converterEmpresaFormParaEmpresa(EmpresaForm empresaForm) {

		Empresa empresa = new Empresa(empresaForm.getNome(), empresaForm.getCnpj());
		return empresa;
	}

	private Empresa converterEmpresaAlteracaoFormParaEmpresa(Long id, EmpresaAlteracaoForm empresaAlteraracaoForm) {
		Optional<Empresa> empresa = Optional.ofNullable(empresaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(null,null)));
		

		empresa.get().setNome(empresaAlteraracaoForm.getNome());
		return empresa.get();
	}

	public List<EmpresaDto> buscar() {
		List<Empresa> empresas = empresaRepository.findAll();
		List<EmpresaDto> empresasDtos = converteEmpresarParaDtos(empresas);
		return empresasDtos;
	}

	public Empresa buscarPorId(Long id) {
		Optional<Empresa> empresa = empresaRepository.findById(id);
		return empresa.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName(), null));
	}

	public Empresa salvar(EmpresaForm empresaForm) {
		Empresa empresa = converterEmpresaFormParaEmpresa(empresaForm);
		empresa.setNome(empresa.getNome().toUpperCase());
		empresaRepository.save(empresa);
		return empresa;
	}

	public void deletar(Long id) {
		Optional<Empresa> empresa = empresaRepository.findById(id);
		empresaRepository.delete(empresa.orElseThrow(() -> new RuntimeException()));
	}

	public Empresa editar(Long id, EmpresaAlteracaoForm empresaAlteraracaoForm) {
		Empresa empresa = converterEmpresaAlteracaoFormParaEmpresa(id, empresaAlteraracaoForm);
		empresaRepository.save(empresa);
		return empresa;
	}
	
	

}
