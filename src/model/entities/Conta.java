package model.entities;

import java.io.Serializable;

public class Conta implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer Id;
	private Integer Conta;
	private String Descricao;
	private String CentroCusto;
	private Integer ContaSupeior;
	private String TipoConta;
	private String NaturezadaConta;
	private String ObservacaoConta;
	
	public Conta() {
	}

	public Conta(Integer id, Integer conta, String descricao, String centroCusto, Integer contaSupeior,
			String tipoConta, String naturezadaConta, String observacaoConta) {
		super();
		Id = id;
		Conta = conta;
		Descricao = descricao;
		CentroCusto = centroCusto;
		ContaSupeior = contaSupeior;
		this.TipoConta = tipoConta;
		NaturezadaConta = naturezadaConta;
		ObservacaoConta = observacaoConta;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getConta() {
		return Conta;
	}

	public void setConta(Integer conta) {
		Conta = conta;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getCentroCusto() {
		return CentroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		CentroCusto = centroCusto;
	}

	public Integer getContaSupeior() {
		return ContaSupeior;
	}

	public void setContaSupeior(Integer contaSupeior) {
		ContaSupeior = contaSupeior;
	}

	public String getTipoConta() {
		return TipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.TipoConta = tipoConta;
	}

	public String getNaturezadaConta() {
		return NaturezadaConta;
	}

	public void setNaturezadaConta(String naturezadaConta) {
		NaturezadaConta = naturezadaConta;
	}

	public String getObservacaoConta() {
		return ObservacaoConta;
	}

	public void setObservacaoConta( String observacaoConta) {
		ObservacaoConta = observacaoConta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Conta == null) ? 0 : Conta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (Conta == null) {
			if (other.Conta != null)
				return false;
		} else if (!Conta.equals(other.Conta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conta [Id=");
		builder.append(Id);
		builder.append(", Conta=");
		builder.append(Conta);
		builder.append(", Descricao=");
		builder.append(Descricao);
		builder.append(", CentroCusto=");
		builder.append(CentroCusto);
		builder.append(", ContaSupeior=");
		builder.append(ContaSupeior);
		builder.append(", tipoConta=");
		builder.append(TipoConta);
		builder.append(", NaturezadaConta=");
		builder.append(NaturezadaConta);
		builder.append(", ObservacaoConta=");
		builder.append(ObservacaoConta);
		builder.append("]");
		return builder.toString();
	}
}
