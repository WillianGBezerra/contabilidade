package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Lancamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String Documento;
	private Date DataLancamento;
	private Conta ContaDevedora;
	private Conta ContaCredora;
	private String Historico;
	private Double Valor;
	
	public Lancamento() {
		// TODO Auto-generated constructor stub
	}

	public Lancamento(Integer id, String documento, Date dataLancamento, Conta contaDevedora, Conta contaCredora,
			String historico, Double valor) {
		super();
		Id = id;
		Documento = documento;
		DataLancamento = dataLancamento;
		ContaDevedora = contaDevedora;
		ContaCredora = contaCredora;
		Historico = historico;
		Valor = valor;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getDocumento() {
		return Documento;
	}

	public void setDocumento(String documento) {
		Documento = documento;
	}

	public Date getDataLancamento() {
		return DataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		DataLancamento = dataLancamento;
	}

	public Conta getContaDevedora() {
		return ContaDevedora;
	}

	public void setContaDevedora(Conta contaDevedora) {
		ContaDevedora = contaDevedora;
	}

	public Conta getContaCredora() {
		return ContaCredora;
	}

	public void setContaCredora(Conta contaCredora) {
		ContaCredora = contaCredora;
	}

	public String getHistorico() {
		return Historico;
	}

	public void setHistorico(String historico) {
		Historico = historico;
	}

	public Double getValor() {
		return Valor;
	}

	public void setValor(Double valor) {
		Valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Lancamento other = (Lancamento) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lancamento [Id=");
		builder.append(Id);
		builder.append(", Documento=");
		builder.append(Documento);
		builder.append(", DataLancamento=");
		builder.append(DataLancamento);
		builder.append(", ContaDevedora=");
		builder.append(ContaDevedora);
		builder.append(", ContaCredora=");
		builder.append(ContaCredora);
		builder.append(", Historico=");
		builder.append(Historico);
		builder.append(", Valor=");
		builder.append(Valor);
		builder.append("]");
		return builder.toString();
	}
}
