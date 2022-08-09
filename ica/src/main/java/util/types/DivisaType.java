package util.types;

public enum DivisaType {

	SOL(1, "PEN", "Sol", "Soles", "S/." ),
	DOLAR(2, "USD", "Dolar", "Dolares", "$");

	private Integer codDivi;
	private String codIsoDivi;
	private String nomDiviSing;
	private String nomDiviPlur;
	private String simDivi;
	
	private DivisaType(Integer codDivi, String codIsoDivi, String nomDiviSing, String nomDiviPlur, String simDivi) {
		this.codDivi = codDivi;
		this.codIsoDivi = codIsoDivi;
		this.nomDiviSing = nomDiviSing;
		this.nomDiviPlur = nomDiviPlur;
		this.simDivi = simDivi;
	}

	public Integer getCodDivi() {
		return codDivi;
	}

	public String getCodIsoDivi() {
		return codIsoDivi;
	}

	public String getNomDiviSing() {
		return nomDiviSing;
	}

	public String getNomDiviPlur() {
		return nomDiviPlur;
	}

	public String getSimDivi() {
		return simDivi;
	}

	
	
}
