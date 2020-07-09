package model.entities;

public class Natureza {

	private Integer Id;
	private String Tipo;
	public Natureza(Integer id, String tipo) {
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
