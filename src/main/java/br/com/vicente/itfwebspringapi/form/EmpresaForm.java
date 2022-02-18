package br.com.vicente.itfwebspringapi.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmpresaForm {
	
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String cnpj;
	
	
	public String getNome() {
		return nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	
	
	
	
}
