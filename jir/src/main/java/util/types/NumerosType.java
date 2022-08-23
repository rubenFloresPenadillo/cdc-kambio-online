package util.types;

public enum NumerosType {
	
	NUMERO_MINIMO_CERO(0),
	INDICADOR_NEGATIVO_UNO(-1),
	INDICADOR_POSITIVO_UNO(1),
	NUMERO_DOS(2),
	NUMERO_TRECE(13),
	NUMERO_9999(9999);
	private Integer valor;

	private NumerosType(Integer valor) {
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}
		
}
