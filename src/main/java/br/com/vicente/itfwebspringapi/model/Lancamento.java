package br.com.vicente.itfwebspringapi.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "lancamento")
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;
	
	@ManyToOne
	private Empresa empresa;
	private String descricao;
	private Double valor;
	private Date dataVencimento;
	private Boolean pago;
	private Date dataPagamento;
	
	
	
	public Lancamento() {
	}
	
	

	public Lancamento(Integer codigo, TipoLancamento tipo, Empresa empresa, String descricao, Double valor,
			Date dataVencimento, Boolean pago,  Date dataPagamento) {
	
		this.tipo = tipo;
		this.empresa = empresa;
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.pago = pago;
		this.dataPagamento = dataPagamento;
	}



	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public Boolean isPago() {
		return pago;
	}
	public void setPago(Boolean pago) {
		this.pago = pago;
	}
	
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		return codigo == other.codigo;
	}



	@Override
	public String toString() {
		return "Lancamento [codigo=" + codigo + ", tipo=" + tipo + ", empresa=" + empresa.getNome() + ", descricao=" + descricao
				+ ", valor=" + valor + ", dataVencimento=" + dataVencimento + ", pago=" + pago + ", dataPagamento="
				+ dataPagamento + "]";
	}

}
