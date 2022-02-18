package br.com.vicente.itfwebspringapi.dto;

import br.com.vicente.itfwebspringapi.model.Empresa;

public class EmpresaDetalhesDto {

	private Long id;
	private String nome;
	private Boolean aberta;
	
	
	public EmpresaDetalhesDto(Empresa empresa) {
		this.id = empresa.getid();
		this.nome = empresa.getNome();
		this.aberta = empresa.getAberta();
	}


	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public Boolean getAberta() {
		return aberta;
	}
	
}
