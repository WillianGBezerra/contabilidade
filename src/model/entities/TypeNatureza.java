package model.entities;

public enum TypeNatureza {
	
	DEVEDORA("Devedora"),
	CREDORA("Credora");
	
	 private String typeNatureza;

	    private TypeNatureza(String typeNatureza) {
	        this.typeNatureza = typeNatureza;
	    }
	    public String getServicos() {
	        return typeNatureza;
	    }

}
