package br.com.vicente.itfwebspringapi.dto;

import br.com.vicente.itfwebspringapi.model.Empresa;

public class EmpresaDto {

	private Long id;
	private String nome;
	
	
	public EmpresaDto(Empresa empresa) {
		this.id = empresa.getid();
		this.nome = empresa.getNome();
	}


	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
}
