package enums;

public enum NaturezadaConta {
	DEVEDORA("Devedora"),
	CREDORA("Credora");
    

    private String n;

    private NaturezadaConta(String n) {
        this.n = n;
    }
    public String getServicos() {
        return n;
    }

}
