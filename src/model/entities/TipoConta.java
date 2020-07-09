package model.entities;

public class TipoConta {

	private Integer Id;
	private String Tipo;
	public TipoConta(Integer id, String tipo) {
		super();
		Id = id;
		Tipo = tipo;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	@Override
	public String toString() {
		return getTipo();
	}
	
	
}
